

-- Database upgrade script for ORACLE
-- v2.10 - v2.20

CREATE TABLE TAXCUSTCATEGORIES (
    ID VARCHAR2(255) NOT NULL,
    NAME VARCHAR2(255) NOT NULL,
    PRIMARY KEY (ID)
);
CREATE UNIQUE INDEX TAXCUSTCAT_NAME_INX ON TAXCUSTCATEGORIES(NAME);

CREATE TABLE TAXCATEGORIES (
    ID VARCHAR2(255) NOT NULL,
    NAME VARCHAR2(255) NOT NULL,
    PRIMARY KEY (ID)
);
CREATE UNIQUE INDEX TAXCAT_NAME_INX ON TAXCATEGORIES(NAME);
INSERT INTO TAXCATEGORIES(ID, NAME) VALUES ('000', 'Tax Exempt');
INSERT INTO TAXCATEGORIES(ID, NAME) VALUES ('001', 'Tax Standard');
INSERT INTO TAXCATEGORIES (ID, NAME) SELECT ID, NAME FROM TAXES;

ALTER TABLE TAXES ADD CATEGORY VARCHAR2(255);
ALTER TABLE TAXES ADD CUSTCATEGORY VARCHAR2(255);
ALTER TABLE TAXES ADD PARENTID VARCHAR2(255);
ALTER TABLE TAXES ADD RATECASCADE NUMERIC(1);
ALTER TABLE TAXES ADD RATEORDER INTEGER;
ALTER TABLE TAXES ADD CONSTRAINT TAXES_CAT_FK FOREIGN KEY (CATEGORY) REFERENCES TAXCATEGORIES(ID);
ALTER TABLE TAXES ADD CONSTRAINT TAXES_CUSTCAT_FK FOREIGN KEY (CUSTCATEGORY) REFERENCES TAXCUSTCATEGORIES(ID);
ALTER TABLE TAXES ADD CONSTRAINT TAXES_TAXES_FK FOREIGN KEY (PARENTID) REFERENCES TAXES(ID);
UPDATE TAXES SET CATEGORY = ID, RATECASCADE = 0;
ALTER TABLE TAXES MODIFY CATEGORY VARCHAR2(255) NOT NULL;
ALTER TABLE TAXES MODIFY RATECASCADE NUMERIC(1) NOT NULL;

ALTER TABLE PRODUCTS ADD TAXCAT VARCHAR2(255);
ALTER TABLE PRODUCTS ADD CONSTRAINT PRODUCTS_TAXCAT_FK FOREIGN KEY (TAXCAT) REFERENCES TAXCATEGORIES(ID);
UPDATE PRODUCTS SET TAXCAT = TAX;
ALTER TABLE PRODUCTS MODIFY TAXCAT VARCHAR2(255) NOT NULL;
ALTER TABLE PRODUCTS DROP CONSTRAINT PRODUCTS_FK_2;
ALTER TABLE PRODUCTS DROP COLUMN TAX;

ALTER TABLE CUSTOMERS ADD SEARCHKEY VARCHAR2(255);
UPDATE CUSTOMERS SET SEARCHKEY = ID;
ALTER TABLE CUSTOMERS MODIFY SEARCHKEY VARCHAR2(255) NOT NULL;
CREATE UNIQUE INDEX CUSTOMERS_SKEY_INX ON CUSTOMERS(SEARCHKEY);

ALTER TABLE CUSTOMERS ADD ADDRESS2 VARCHAR2(255);
ALTER TABLE CUSTOMERS ADD POSTAL VARCHAR2(255);
ALTER TABLE CUSTOMERS ADD CITY VARCHAR2(255);
ALTER TABLE CUSTOMERS ADD REGION VARCHAR2(255);
ALTER TABLE CUSTOMERS ADD COUNTRY VARCHAR2(255);
ALTER TABLE CUSTOMERS ADD FIRSTNAME VARCHAR2(255);
ALTER TABLE CUSTOMERS ADD LASTNAME VARCHAR2(255);
ALTER TABLE CUSTOMERS ADD EMAIL VARCHAR2(255);
ALTER TABLE CUSTOMERS ADD PHONE VARCHAR2(255);
ALTER TABLE CUSTOMERS ADD PHONE2 VARCHAR2(255);
ALTER TABLE CUSTOMERS ADD FAX VARCHAR2(255);
ALTER TABLE CUSTOMERS ADD TAXCATEGORY VARCHAR2(255);
ALTER TABLE CUSTOMERS ADD CONSTRAINT CUSTOMERS_TAXCAT FOREIGN KEY (TAXCATEGORY) REFERENCES TAXCUSTCATEGORIES(ID);

ALTER TABLE CLOSEDCASH ADD HOSTSEQUENCE INTEGER;  
UPDATE CLOSEDCASH SET HOSTSEQUENCE = 0;
ALTER TABLE CLOSEDCASH MODIFY HOSTSEQUENCE INTEGER NOT NULL;
CREATE INDEX CLOSEDCASH_SEQINX ON CLOSEDCASH(HOST, HOSTSEQUENCE);

ALTER TABLE RECEIPTS ADD ATTRIBUTES BLOB;

ALTER TABLE TICKETLINES DROP COLUMN NAME;
ALTER TABLE TICKETLINES DROP COLUMN ISCOM;

CREATE TABLE TAXLINES (
    ID VARCHAR2(255) NOT NULL,
    RECEIPT VARCHAR2(255) NOT NULL,
    TAXID VARCHAR2(255) NOT NULL, 
    BASE DOUBLE PRECISION NOT NULL, 
    AMOUNT DOUBLE PRECISION NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT TAXLINES_TAX FOREIGN KEY (TAXID) REFERENCES TAXES(ID)
);

UPDATE PEOPLE SET CARD = NULL WHERE CARD = '';

-- final script

DELETE FROM SHAREDTICKETS;

UPDATE APPLICATIONS SET NAME = $APP_NAME{}, VERSION = $APP_VERSION{} WHERE ID = $APP_ID{};
