

package com.openbravo.pos.admin;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.TableDefinition;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.BeanFactoryDataSingle;

/**
 *
 * @author adrianromero
 */
public class DataLogicAdmin extends BeanFactoryDataSingle {
    
    private Session s;
    private TableDefinition m_tpeople;
    private TableDefinition m_troles;
    private TableDefinition m_tresources;    
    
    /** Creates a new instance of DataLogicAdmin */
    public DataLogicAdmin() {
    }
    
    public void init(Session s){
        this.s = s;
        
        m_tpeople = new TableDefinition(s,
            "PEOPLE"
            , new String[] {"ID", "NAME", "APPPASSWORD", "ROLE", "VISIBLE", "CARD", "IMAGE","PRCATEGORIES","CASHACCOUNT","CHEQUEACCOUNT"}
            , new String[] {"ID", AppLocal.getIntString("label.peoplename"), AppLocal.getIntString("Label.Password"), AppLocal.getIntString("label.role"), AppLocal.getIntString("label.peoplevisible"), AppLocal.getIntString("label.card"), AppLocal.getIntString("label.peopleimage"),"PRCATEGORIES","CASHACCOUNT","CHEQUEACCOUNT"}
            , new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.IMAGE,Datas.STRING,Datas.STRING,Datas.STRING}
            , new Formats[] {Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.BOOLEAN, Formats.STRING, Formats.NULL,Formats.STRING,Formats.STRING,Formats.STRING}
            , new int[] {0}
        );   
        
        m_troles = new TableDefinition(s,
            "ROLES"
            , new String[] {"ID", "NAME", "PERMISSIONS"}
            , new String[] {"ID", AppLocal.getIntString("Label.Name"), "PERMISSIONS"}
            , new Datas[] {Datas.STRING, Datas.STRING, Datas.BYTES}
            , new Formats[] {Formats.STRING, Formats.STRING, Formats.NULL}
            , new int[] {0}
        );  
        
        m_tresources = new TableDefinition(s,
            "RESOURCES"
            , new String[] {"ID", "NAME", "RESTYPE", "CONTENT"}
            , new String[] {"ID", AppLocal.getIntString("Label.Name"), AppLocal.getIntString("label.type"), "CONTENT"}
            , new Datas[] {Datas.STRING, Datas.STRING, Datas.INT, Datas.BYTES}
            , new Formats[] {Formats.STRING, Formats.STRING, Formats.INT, Formats.NULL}
            , new int[] {0}
        );           
    }
       
    public final SentenceList getRolesList() {
        return new StaticSentence(s
            , "SELECT ID, NAME FROM ROLES ORDER BY NAME"
            , null
            , new SerializerReadClass(RoleInfo.class));
    }
    public final Object[] getaccountparentfromuserid(String id) throws BasicException{
      Object[] obj=(Object[]) new StaticSentence(s
                                   ,"SELECT A.PARENT,A.ID,A1.PARENT,A1.ID FROM PEOPLE P,ACCOUNTMASTER A,ACCOUNTMASTER A1 WHERE P.ID=? AND P.CASHACCOUNT=A.ID AND P.CHEQUEACCOUNT=A1.ID "
                                  ,SerializerWriteString.INSTANCE
                                  ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(id);
      return obj;
    }
    public final TableDefinition getTablePeople() {
        return m_tpeople;
    }    
    public final TableDefinition getTableRoles() {
        return m_troles;
    }
    public final TableDefinition getTableResources() {
        return m_tresources;
    }

}
