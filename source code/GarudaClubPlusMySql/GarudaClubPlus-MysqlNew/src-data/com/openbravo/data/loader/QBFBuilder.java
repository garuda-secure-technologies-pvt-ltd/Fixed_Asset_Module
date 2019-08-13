

package com.openbravo.data.loader;

import com.openbravo.basic.BasicException;

/**
 *
 * @author  adrian
 */
public class QBFBuilder implements ISQLBuilderStatic {
   
    private String m_sSentNullFilter;   // la sentencia que se devuelve cuando el filtro es vacio
    private String m_sSentBeginPart;  // La sentencia que se devuelve es m_sSentBeginPart + ( filtro ) + m_sSentEndPart
    private String m_sSentEndPart;
    private String m_sSentCenterPart;
    private int nos=1;
    
    private String[] m_asFindFields;    
    
    /** Creates a new instance of QBFBuilder */
    public QBFBuilder(TableDefinition tb, String[] asFindFields) {
        
        StringBuffer sent = new StringBuffer();
        sent.append("select ");

        for (int i = 0; i < tb.getFields().length; i ++) {
            if (i > 0) {
                sent.append(", ");
            }
            sent.append(tb.getFields()[i]);
        }        
        
        sent.append(" from ");        
        sent.append(tb.getTableName());
        
        m_sSentNullFilter = sent.toString();
        sent.append(" where ");
        m_sSentBeginPart = sent.toString();
        m_sSentEndPart = "";

        m_asFindFields = asFindFields;
    }
    public QBFBuilder(String sSentence, String[] asFindFields,int nos) {
         if(nos>1)
            this.nos=2;
        else
            this.nos=1;
       construct(sSentence,asFindFields);
    }
    public QBFBuilder(String sSentence, String[] asFindFields) {
        construct(sSentence,asFindFields);
    }
    private void construct(String sSentence, String[] asFindFields){

        int iPos = sSentence.indexOf("?(QBF_FILTER)");
       if(nos<=1){
        if (iPos < 0) {
            m_sSentBeginPart = sSentence;
            m_sSentEndPart = "";
            m_sSentNullFilter = sSentence;
        } else {
            m_sSentBeginPart = sSentence.substring(0, iPos);
            m_sSentEndPart = sSentence.substring(iPos + 13);
            m_sSentNullFilter = m_sSentBeginPart + "(1=1)" + m_sSentEndPart;
        }
       /* m_sSentBeginPart =m_sSentBeginPart1;
        m_sSentEndPart = m_sSentEndPart1;
        m_sSentNullFilter=m_sSentNullFilter1;*/
    }else if(nos>1){
            int ilPos= sSentence.lastIndexOf("?(QBF_FILTER)");

            if(ilPos<0 && ilPos==iPos){
                m_sSentBeginPart = sSentence;
                m_sSentEndPart = "";
                m_sSentNullFilter = sSentence;
            }else if(iPos>0 && iPos==ilPos){
                m_sSentBeginPart = sSentence.substring(0, iPos);
                m_sSentEndPart = sSentence.substring(iPos + 13);
                m_sSentCenterPart="";
                m_sSentNullFilter = m_sSentBeginPart + "(1=1)" + m_sSentEndPart;
            }else{
                m_sSentBeginPart = sSentence.substring(0, iPos);
                m_sSentCenterPart = sSentence.substring(iPos + 13,ilPos);
                m_sSentEndPart = sSentence.substring(ilPos + 13);
                m_sSentNullFilter = m_sSentBeginPart +"(1=1)"+m_sSentCenterPart+ "(1=1)" + m_sSentEndPart;
            }
        }
        m_asFindFields = asFindFields;
    }
    public String getSQL(SerializerWrite sw, Object params) throws BasicException {
        
         QBFParameter mydw = new QBFParameter(m_asFindFields);
        if (params == null) {
            return m_sSentNullFilter;
        } else {
            sw.writeValues(mydw, params);
            if(nos<=1){
            String sFilter = mydw.getFilter();
            if (sFilter.length() == 0) {
                return m_sSentNullFilter; // no hay filtro
            } else {
                return m_sSentBeginPart + "(" + sFilter + ")" + m_sSentEndPart; // incluimos el filtro
            }
            }else{
                String sFilter = mydw.getFilter1(0);
                String bpart=m_sSentBeginPart;
                if (sFilter.length() != 0) {
                  bpart= m_sSentBeginPart + "(" + sFilter + ")" + m_sSentCenterPart; // incluimos el filtro
               }else{
                    bpart= m_sSentBeginPart + "(1=1)" + m_sSentCenterPart;
               }
                sFilter = mydw.getFilter1(1);
                if (sFilter.length() == 0) {
                   return bpart+"(1=1)"+m_sSentEndPart; // no hay filtro
               } else {
                  return bpart + "(" + sFilter + ")" + m_sSentEndPart; // incluimos el filtro
               }

            }
            //return null;
        }
    }  
    
    private static class QBFParameter implements DataWrite {
    
        private String[] m_asFindFields;
        private QBFCompareEnum[] m_aiCondFields;
        private String[] m_aParams;
        
        public QBFParameter(String[] asFindFields) {
            m_asFindFields = asFindFields;
            m_aiCondFields = new QBFCompareEnum[asFindFields.length];
            m_aParams = new String[asFindFields.length];
            
            for( int i = 0; i < m_aParams.length; i++) {
                m_aParams[i] = DataWriteUtils.getSQLValue((Object) null);
            }
        }
        
        public void setDouble(int paramIndex, Double dValue) throws BasicException {
            if ((paramIndex - 1) % 2 == 0) {
                throw new BasicException(LocalRes.getIntString("exception.nocompare"));
            } else {
                m_aParams[(paramIndex - 1) / 2] = DataWriteUtils.getSQLValue(dValue);
            }
        }        
        public void setBoolean(int paramIndex, Boolean bValue) throws BasicException {
            if ((paramIndex - 1) % 2 == 0) {
                throw new BasicException(LocalRes.getIntString("exception.nocompare"));
            } else {
                m_aParams[(paramIndex - 1) / 2] = DataWriteUtils.getSQLValue(bValue);
            }
        }        
        public void setInt(int paramIndex, Integer iValue) throws BasicException {
            if ((paramIndex - 1) % 2 == 0) {
                throw new BasicException(LocalRes.getIntString("exception.nocompare"));
            } else {
                m_aParams[(paramIndex - 1) / 2] = DataWriteUtils.getSQLValue(iValue);
            }
        }       
        public void setString(int paramIndex, String sValue) throws BasicException {
            if ((paramIndex - 1) % 2 == 0) {
                throw new BasicException(LocalRes.getIntString("exception.nocompare"));
            } else {
                m_aParams[(paramIndex - 1) / 2] = DataWriteUtils.getSQLValue(sValue);
            }
        }        
        public void setTimestamp(int paramIndex, java.util.Date dValue) throws BasicException {
            if ((paramIndex - 1) % 2 == 0) {
                throw new BasicException(LocalRes.getIntString("exception.nocompare"));
            } else {
                m_aParams[(paramIndex - 1) / 2] = DataWriteUtils.getSQLValue(dValue);
            }
        }
//        public void setBinaryStream(int paramIndex, java.io.InputStream in, int length) throws DataException{
//            if ((paramIndex - 1) % 2 == 0) {
//                throw new DataException("Expected comparator for QBF");
//            } else {
//                throw new DataException("Param type not allowed");
//            }            
//        }
        public void setBytes(int paramIndex, byte[] value) throws BasicException {
            if ((paramIndex - 1) % 2 == 0) {
                throw new BasicException(LocalRes.getIntString("exception.nocompare"));
            } else {
                throw new BasicException("Param type not allowed");
            }
        }
        public void setObject(int paramIndex, Object value) throws BasicException {
            if ((paramIndex - 1) % 2 == 0) {
                if (value instanceof QBFCompareEnum) {
                    m_aiCondFields[(paramIndex - 1) / 2] = (QBFCompareEnum) value;
                } else {
                    throw new BasicException(LocalRes.getIntString("exception.nocompare"));
                }
            } else {
                m_aParams[(paramIndex - 1) / 2] = DataWriteUtils.getSQLValue(value);
            }
        }
        
        public String getFilter() {
            // El retorno debe ser siempre una expresion valida puesto que no se donde sera insertada.
            
            StringBuffer sFilter = new StringBuffer();
            
            String sItem;                
            for (int i = 0; i < m_asFindFields.length; i ++) {
                sItem = m_aiCondFields[i].getExpression(m_asFindFields[i], m_aParams[i]);           
                if (sItem != null) {
                    if (sFilter.length() > 0) {
                        sFilter.append(" AND ");
                    }
                    sFilter.append(sItem);
                }                
            }

            return sFilter.toString();
        }
           public String getFilter1(int value) {
            // El retorno debe ser siempre una expresion valida puesto que no se donde sera insertada.

            StringBuffer sFilter = new StringBuffer();
            int initial=0,length=0;

            if(value==0){
              initial=0;
              length=m_asFindFields.length/2;
            }else{
                initial=m_asFindFields.length/2;
                length=m_asFindFields.length;
            }
            String sItem;
            for (int i = initial; i < length; i ++) {
                sItem = m_aiCondFields[i].getExpression(m_asFindFields[i], m_aParams[i]);
                if (sItem != null) {
                    if (sFilter.length() > 0) {
                        sFilter.append(" AND ");
                    }
                    sFilter.append(sItem);
                }
            }

            return sFilter.toString();
        }   
    }   
}
