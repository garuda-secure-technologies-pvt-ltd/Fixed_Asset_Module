/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.panels;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev1
 */
public class MemberListReport  extends BeanFactoryDataSingle {

    
   private Session s;
   private List<MemberListReport.GetSetMethod> Member_List;  
    
    
    public void init(Session s) {
         this.s=s;
    }
    
    //1
     public static  MemberListReport LoadAllMember(AppView app)throws BasicException{
          MemberListReport All_Members = new MemberListReport(); 
          
          
          try{
            All_Members.Member_List = new ArrayList<MemberListReport.GetSetMethod>();
            //Billed_Rooms.Member_List = new StaticSentence(app.getSession(), " SELECT C.NAME AS NAME,C.ADDRESS AS ADDRESS,C.PHONE AS CONTACT_NO,M.NAME AS TYPE_OF_MEM FROM CUSTOMERS C join MEMTYPE M on(C.MEMTYPE=M.ID) WHERE m.name=?", new SerializerWriteBasic() ,new SerializerReadClass(MemberListReport.GetSetMethod.class)).list();

            All_Members.Member_List = new StaticSentence(app.getSession(), " SELECT @rownum:=@rownum+1 ‘ROWNO’,C.NAME AS NAME,C.SEARCHKEY AS MEM_NO,CONCAT(C.ADDRESS,C.ADDRESS2) AS ADDRESS,CONCAT(C.CITY,C.POSTAL) AS CITY,COUNTRY AS COUNTRY,C.PHONE AS CONTACT_NO,M.NAME AS TYPE_OF_MEM,C.PHONE2 AS OFFICENO,C.MOBILE AS MOBILENO FROM CUSTOMERS C join MEMTYPE M on(C.MEMTYPE=M.ID),(SELECT @rownum:=0) r  ORDER BY C.SEARCHKEY" , null ,new SerializerReadClass(MemberListReport.GetSetMethod.class)).list();
     
     }
          
          catch(BasicException ex){
            
            Logger.getLogger(MemberListReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return All_Members;
     }
     
     //2
     
     public static  MemberListReport LoadAllMemberPerticularType(AppView app,String memty)throws BasicException{
          MemberListReport All_Members_Perticular_Type = new MemberListReport(); 
          
          
          try{
            All_Members_Perticular_Type.Member_List = new ArrayList<MemberListReport.GetSetMethod>();
            //Billed_Rooms.Member_List = new StaticSentence(app.getSession(), " SELECT C.NAME AS NAME,C.ADDRESS AS ADDRESS,C.PHONE AS CONTACT_NO,M.NAME AS TYPE_OF_MEM FROM CUSTOMERS C join MEMTYPE M on(C.MEMTYPE=M.ID) WHERE m.name=?", new SerializerWriteBasic() ,new SerializerReadClass(MemberListReport.GetSetMethod.class)).list();

  All_Members_Perticular_Type.Member_List = new StaticSentence(app.getSession(), " SELECT @rownum:=@rownum+1 ‘ROWNO’,C.NAME AS NAME,C.SEARCHKEY AS MEM_NO,CONCAT(C.ADDRESS,C.ADDRESS2) AS ADDRESS,CONCAT(C.CITY,C.POSTAL) AS CITY,COUNTRY AS COUNTRY,C.PHONE AS CONTACT_NO,M.NAME AS TYPE_OF_MEM,C.PHONE2 AS OFFICENO,C.MOBILE AS MOBILENO FROM CUSTOMERS C join MEMTYPE M on(C.MEMTYPE=M.ID),(SELECT @rownum:=0) r WHERE M.NAME in "+memty+ " ORDER BY C.SEARCHKEY"  , new SerializerWriteBasic(new Datas[]{  Datas.STRING }  )  ,new SerializerReadClass(MemberListReport.GetSetMethod.class)).list(new Object[]{  memty });
     
     }
        
          catch(BasicException ex){
            
            Logger.getLogger(MemberListReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return All_Members_Perticular_Type;
     }
     
     //3
     
      public static  MemberListReport LoadAllVisibleMembers(AppView app)throws BasicException{
          MemberListReport All_Visible_Members = new MemberListReport(); 
          
          
          try{
            All_Visible_Members.Member_List = new ArrayList<MemberListReport.GetSetMethod>();
            //Billed_Rooms.Member_List = new StaticSentence(app.getSession(), " SELECT C.NAME AS NAME,C.ADDRESS AS ADDRESS,C.PHONE AS CONTACT_NO,M.NAME AS TYPE_OF_MEM FROM CUSTOMERS C join MEMTYPE M on(C.MEMTYPE=M.ID) WHERE m.name=?", new SerializerWriteBasic() ,new SerializerReadClass(MemberListReport.GetSetMethod.class)).list();

  All_Visible_Members.Member_List = new StaticSentence(app.getSession(), " SELECT @rownum:=@rownum+1 ‘ROWNO’,C.NAME AS NAME,C.SEARCHKEY AS MEM_NO,CONCAT(C.ADDRESS,C.ADDRESS2) AS ADDRESS,CONCAT(C.CITY,C.POSTAL) AS CITY,COUNTRY AS COUNTRY,C.PHONE AS CONTACT_NO,M.NAME AS TYPE_OF_MEM,C.PHONE2 AS OFFICENO,C.MOBILE AS MOBILENO FROM CUSTOMERS C join MEMTYPE M on(C.MEMTYPE=M.ID),(SELECT @rownum:=0) r WHERE C.VISIBLE=TRUE  ORDER BY C.SEARCHKEY" , null  ,new SerializerReadClass(MemberListReport.GetSetMethod.class)).list( );
     
     }
          
          catch(BasicException ex){
            
            Logger.getLogger(MemberListReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return All_Visible_Members;
     }
     
     //4
      
      public static  MemberListReport LoadAllVisibleMembersPerticularType(AppView app,String memty)throws BasicException{
          MemberListReport All_Visible_Members_Perticular_Type = new MemberListReport(); 
          
          
          try{
            All_Visible_Members_Perticular_Type.Member_List = new ArrayList<MemberListReport.GetSetMethod>();
            //Billed_Rooms.Member_List = new StaticSentence(app.getSession(), " SELECT C.NAME AS NAME,C.ADDRESS AS ADDRESS,C.PHONE AS CONTACT_NO,M.NAME AS TYPE_OF_MEM FROM CUSTOMERS C join MEMTYPE M on(C.MEMTYPE=M.ID) WHERE m.name=?", new SerializerWriteBasic() ,new SerializerReadClass(MemberListReport.GetSetMethod.class)).list();

  All_Visible_Members_Perticular_Type.Member_List = new StaticSentence(app.getSession(), " SELECT @rownum:=@rownum+1 ‘ROWNO’,C.NAME AS NAME,C.SEARCHKEY AS MEM_NO,CONCAT(C.ADDRESS,C.ADDRESS2) AS ADDRESS,CONCAT(C.CITY,C.POSTAL) AS CITY,COUNTRY AS COUNTRY,C.PHONE AS CONTACT_NO,M.NAME AS TYPE_OF_MEM,C.PHONE2 AS OFFICENO,C.MOBILE AS MOBILENO FROM CUSTOMERS C join MEMTYPE M on(C.MEMTYPE=M.ID),(SELECT @rownum:=0) r WHERE C.VISIBLE=TRUE AND M.NAME in "+memty+ "  order by C.SEARCHKEY" , new SerializerWriteBasic(new Datas[]{  Datas.STRING }  )  ,new SerializerReadClass(MemberListReport.GetSetMethod.class)).list(new Object[]{ memty  });
     
     }
          
          catch(BasicException ex){
            
            Logger.getLogger(MemberListReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return All_Visible_Members_Perticular_Type;
     }
      
      
      
      
      
      public List<MemberListReport.GetSetMethod> getMemberList(){
           if(Member_List!=null)
        {
            return Member_List;
        }
        else
            return new ArrayList<MemberListReport.GetSetMethod>();
      } 
      
      
     
  public static class GetSetMethod implements SerializableRead,IKeyed  {
      
          private String CUSTOMER;
          private String ADDRESS;
          private String PHONE;
          private String Member_Type;
          private Integer sno;
          private String Member_No;
          private String PHONE2;
          private String MOBILE;
          private String CITY;
          private String COUNTRY;

        public String getCOUNTRY() {
            return COUNTRY;
        }

        public void setCOUNTRY(String COUNTRY) {
            this.COUNTRY = COUNTRY;
        }
          
          
        public String getCITY() {
            return CITY;
        }

        public void setCITY(String CITY) {
            this.CITY = CITY;
        }
           
          public String getCustomerName(){
              return CUSTOMER;
          }
          public void setCustomerName(String CUSTOMER){
              this.CUSTOMER=CUSTOMER;
          }
          
           public String getAddress(){
              return ADDRESS;
          }
          public void setAddress(String ADDRESS){
              this.ADDRESS=ADDRESS;
          }
          
           public String getPHONE(){
              return PHONE;
          }
          public void setPHONE(String PHONE){
              this.PHONE=PHONE;
          }
           public String getMemberType(){
              return Member_Type;
          }
          public void setMemberType(String Member_Type){
              this.Member_Type=Member_Type;
          }
          public Integer getsno(){
              return sno;
          }
          public void setsno(Integer sno){
              this.sno=sno;
          }
           public String getMemberNo(){
              return Member_No;
          }
          public void setMemberNo(String Member_No){
              this.Member_No=Member_No;
          }
           public String getPhone2(){
              return PHONE2;
          }
          public void setPhone2(String PHONE2){
              this.PHONE2=PHONE2;
          }
            public String getMobile(){
              return MOBILE;
          }
          public void setMobile(String MOBILE){
              this.MOBILE=MOBILE;
          }
          
          
          

        @Override
        public void readValues(DataRead dr) throws BasicException {
            
            sno = dr.getInt(1);
            CUSTOMER = dr.getString(2);
            ADDRESS = dr.getString(4);
            CITY = dr.getString(5);
            COUNTRY=dr.getString(6);
            PHONE= dr.getString(7);
            Member_Type = dr.getString(8);
            Member_No = dr.getString(3);
            PHONE2= dr.getString(9);
            MOBILE= dr.getString(10);
        
        }

        @Override
        public Object getKey() { 
            return this;
        }

      
      
  }  
    
}
