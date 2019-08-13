/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.sms;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializableWrite;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.FacilityInfo;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.util.AltEncrypter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.net.*; 
import java.io.*;


/**
 *
 * @author user
 */
public class SmsConnection {
    private HttpURLConnection urlconnection;
    private OutputStreamWriter out;
    private  BufferedReader in ;
    private AppView m_App;
    public List<DelRepBean> delperlist=new ArrayList<DelRepBean>();

    public List<DelRepBean> getDelperlist() {
        return delperlist;
    }

    
    

    public SmsConnection(AppView m_App) {
        this.m_App = m_App;
    }

    public SmsConnection() {
       // throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public String connectionCreditCheck()
    {
        String postData="";
        String retval = "";
        String delrep="";
        List<Object[]> urlbean=null;
        Object[] obj=null;
        Object[] obj1=null;
        try {
           urlbean=urlbean=(List<Object[]>)new PreparedSentence(m_App.getSession(), "SELECT S.ID,S.URL,S.USERNAME,S.PASSWORD,S.ACTIVE,S.API_KEY,S.SENDERID,S.URLREF FROM SMSURL_TABLE S WHERE S.ACTIVE=TRUE AND S.URLREF='CRCHK_URL'",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.STRING})).list();
        } catch (BasicException ex) {
            System.out.println(ex);
            Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       for (Object[] url1 : urlbean) {
            //urlBean=(UrlBean)url;
            obj=url1;
       }
        URL url=null;
        String User="";
         String decryptpass="";
         String passwd="";
        String url1="";
        String API_KEY="";
         String decodedString;
        if(obj!=null)
        {
            
        
         User =obj[2].toString();
         decryptpass =obj[3].toString();
         passwd=new AltEncrypter("key").decrypt(decryptpass);
        System.out.println(passwd);
       
         url1=obj[1].toString();
         API_KEY=obj[5].toString();
       // String urlpath=url1+"?username="+User+"&password="+passwd+"";
        postData +="username="+User+"&pass="+passwd ;
        
        
        //String urlpath=url1+"?workingkey="+API_KEY+"";
        
        //postData +="workingkey="+API_KEY;
        
        
        String urlpath=url1;
        
        
        
         System.out.println(urlpath+""+postData);
        try {
            /*
            url=new URL(urlpath);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("POST");
            urlconnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            urlconnection.setDoOutput(true);
             OutputStreamWriter out = new OutputStreamWriter(urlconnection.getOutputStream());
             out.write(postData);
             out.close();
            BufferedReader in = new BufferedReader( new 
            InputStreamReader(urlconnection.getInputStream()));
             
             while ((decodedString = in.readLine()) != null) {
             retval= decodedString;
            }
            in.close();

            System.out.println(retval);
        
           return retval; 
           
           */
            
            String urlString = urlpath+""+postData; 
            URL oracle = new URL(urlString); 
            URLConnection yc = oracle.openConnection(); 
            BufferedReader in = new BufferedReader(new InputStreamReader( 
                                    yc.getInputStream())); 
            String inputLine; 
            while ((inputLine = in.readLine()) != null)  {
               retval= inputLine; 
            }
            
                System.out.println(inputLine); 
           
            
            in.close(); 
           
           return retval;
           
           
           
        } catch (MalformedURLException ex) {
            System.out.println("malformedURLException");
            Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
                Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    
     public String connectionDelReport(String mobileno,Date fromDate,Date toDate)
    {
        String postData="";
        String retval = "";
        String delrep="";
        List<Object[]> urlbean=null;
        Object[] obj=null;
        Object[] obj1=null;
        
        DelRepBean bean=null;
         try {
           urlbean=(List<Object[]>)new PreparedSentence(m_App.getSession(), "SELECT S.ID,S.URL,S.USERNAME,S.PASSWORD,S.ACTIVE,S.API_KEY,S.SENDERID,S.URLREF FROM SMSURL_TABLE S WHERE S.ACTIVE=TRUE AND S.URLREF='DELREP_URL'",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.STRING})).list();
        } catch (BasicException ex) {
            System.out.println(ex);
            Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
       for (Object[] url1 : urlbean) {
            //urlBean=(UrlBean)url;
            obj=url1;
       }
        URL url=null;
         String User="";
         String decryptpass="";
         String passwd="";
        String url1="";
        String API_KEY="";
         String decodedString;
        if(obj!=null)
        {
         User =obj[2].toString();
         passwd =obj[3].toString();
        System.out.println(passwd);
         decryptpass=new AltEncrypter("key").decrypt(passwd);
        System.out.println(decryptpass);
         API_KEY=obj[5].toString();
        
       
        String schId="";
       String schIdlist="";
        List<Object[]> schIdList=getsentdetails(mobileno,fromDate,toDate);
        for (Object[] sch : schIdList) {
            //urlBean=(UrlBean)url;
             schId=sch[0].toString();
             String []temp ;
             String MainID ;
            // if(!schId.equals("Failed"))
            // {
            //  temp = schId.split("ID=");
             // MainID = temp[2];
            // }
            // else{
             //   MainID = schId;
           // }
            // if(schIdList.size()>1 && schIdList.indexOf(schId)!=schIdList.size()-1)
            // {
            //   schIdlist+=schId + ",";
            //  }
            // else{
             //       schIdlist=schId;
             //}
          
         bean=new DelRepBean();
         url1=obj[1].toString();
        //postData +="username="+User+"&password="+ decryptpass+"&messageid="+schId ;
        postData +="%20Scheduleid="+schId ;
        
       // String urlpath=url1+"?username="+User+"&password="+ decryptpass+"&messageid="+schId+"";
        
        String urlpath=url1;
       // postData +="workingkey="+API_KEY+"&messageid="+MainID;
        // System.out.println(urlpath+""+postData);
          //String urlpath="http://203.129.203.254/sms/user/responce.php?Scheduleid="+ schIdlist+"";
       // String urlpath="http://instant.kapsystem.com/sms/user/responce.php?Scheduleid=112740091-2013_10_02";
        
        try {
            url=new URL(urlpath);
           // HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
           // urlconnection.setRequestMethod("POST");
           // urlconnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
           // urlconnection.setDoOutput(true);
            // OutputStreamWriter out = new OutputStreamWriter(urlconnection.getOutputStream());
            // out.write(postData);
            // out.close();
           // BufferedReader in = new BufferedReader( new 
           // InputStreamReader(urlconnection.getInputStream()));
             
            // while ((decodedString = in.readLine()) != null) {
            // retval= decodedString;
           // }
             
             
             
           // in.close();
            
            
            
            String urlString = urlpath+""+postData; 
            URL oracle = new URL(urlString); 
            URLConnection yc = oracle.openConnection(); 
            BufferedReader in = new BufferedReader(new InputStreamReader( 
                                    yc.getInputStream())); 
            String inputLine; 
            while ((inputLine = in.readLine()) != null)  {
               retval= inputLine; 
            }
            
            
              String splitstr[]=retval.split("<");
              //System.out.println(splitstr[0].toString());
            System.out.println(splitstr[0].toString());
           bean.setScheduleid(schId);
           //bean.setDeliveryReport(splitstr[0].toString());
           bean.setDeliveryReport(retval);
           bean.setMemName(sch[1].toString());
           bean.setPhoneNo(sch[2].toString());
           bean.setMemno(sch[3].toString());
           
           bean.setSentDate((Timestamp) sch[4]);
           bean.setMessage(sch[5].toString());
          // return retval;
        delperlist.add(bean);
           
        } catch (MalformedURLException ex) {
            System.out.println("malformedURLException");
            Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
                Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retval;
        }
        return null;
        
    }
    
     public String connectionPasswordChange(Object[] obj1,String newpassword)
     {
         String postData="";
         String retval="";
         String delrep="";
         String decodedString;
         URL url=null;
         String User =obj1[2].toString();
         String passwd =obj1[3].toString();
         System.out.println(passwd);
        String oldpass=new AltEncrypter("key").decrypt(passwd);
         String url1=obj1[1].toString();
         String urlpath=url1+"?username="+User+"&oldpassword="+oldpass+"&newpassword="+newpassword+"";
         
         try {
            url=new URL(urlpath);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("POST");
            urlconnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            urlconnection.setDoOutput(true);
             OutputStreamWriter out = new OutputStreamWriter(urlconnection.getOutputStream());
             out.write(postData);
             out.close();
            BufferedReader in = new BufferedReader( new 
            InputStreamReader(urlconnection.getInputStream()));
             
             while ((decodedString = in.readLine()) != null) {
             retval+= decodedString;
            }
            in.close();

            System.out.println(retval);
        
          // return retval;
        
           
        } catch (MalformedURLException ex) {
            System.out.println("malformedURLException");
            Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
                Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        
     
         return retval;
     }
     
     
  /*  public void Connection(){
        String postData="";
        String retval = "";
        String delrep="";
        List<Object[]> urlbean=null;
         //List<UrlBean> urlbean=null;
        //UrlBean urlBean=null;
       Object[] obj=null;
       Object[] obj1=null;
        try {
           urlbean=geturl();
        } catch (BasicException ex) {
            System.out.println(ex);
            Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
       for (Object[] url1 : urlbean) {
            //urlBean=(UrlBean)url;
            obj=url1;
       }
        //for (UrlBean url : urlbean) {
          //  urlBean=(UrlBean)url;
            //obj=url;
        //}
        
        
        URL url=null;
        String User =obj[2].toString();
        String passwd =obj[3].toString();
        //get msg and phno
         List<Object[]>msgobj=getmsg();
         for (Object[] msglist : msgobj) {
            //urlBean=(UrlBean)url;
            obj1=msglist;
        
       // String mobilenumber=phno;
       // String message=msg;
        String mobilenumber =obj1[2].toString(); 
        String message =obj1[1].toString();
        String sid = "060000";
        String decodedString;
        String urlpath=obj[1].toString();
       // String delreportpath="http://203.129.203.254/sms/user/responce.php";
        postData += "username=" + User + "&pass=" + passwd + "&senderid=" + sid +"&message=" + message + "&dest_mobileno=" + mobilenumber + "&response=Y";
        try {
            url=new URL(urlpath);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
           urlconnection.setRequestMethod("POST");
            urlconnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            urlconnection.setDoOutput(true);
             OutputStreamWriter out = new OutputStreamWriter(urlconnection.getOutputStream());
             out.write(postData);
             out.close();
            BufferedReader in = new BufferedReader( new 
            InputStreamReader(urlconnection.getInputStream()));
             
             while ((decodedString = in.readLine()) != null) {
             retval= decodedString;
            }
            in.close();

            System.out.println(retval);
           if(!retval.contains("ES1003 Insufficient Balance/Account Expired")&&!retval.contains("message is blank")&&!retval.contains("invalid username and password")&&!retval.contains("you have exceeded your sms limit")&&!retval.contains("invalid senderID")){
             updateToSentMsgTable(retval,obj1);
             deletefromactivemsg(obj1[0].toString());    
           }
          
         

        } catch (MalformedURLException ex) {
            System.out.println("malformedURLException");
            Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
                Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
       
         }
    }
    */
   public List<Object[]> getsentdetails(String mobileno,Date fromDate,Date toDate )
     {
        try {
           // List<Object[]> obj=(List<Object[]>)new PreparedSentence(m_App.getSession(), "SELECT sentdetails FROM sentmsgtable where sentto=? order by sentdetails desc limit 100 ",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).list(mobileno);
          
            
            List<Object[]> obj=(List<Object[]>)new PreparedSentence(m_App.getSession(), "SELECT S.sentdetails,C.Name,C.Mobile,C.SEARCHKEY , S.DATE , S.MESSAGE FROM customers C join sentmsgtable S on C.mobile=S.sentto where s.sentto=? and date>=? and date<=? order by s.sentdetails desc  ",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING ,Datas.TIMESTAMP , Datas.STRING })).list(new Object[]{mobileno,fromDate,toDate});
            return obj;
        } catch (BasicException ex) {
            Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
    
    public List<Object[]> geturl() throws BasicException{
         List<Object[]> obj=(List<Object[]>)new PreparedSentence(m_App.getSession(), "SELECT S.ID,S.URL,S.USERNAME,S.PASSWORD,S.ACTIVE,S.API_KEY FROM SMSURL_TABLE S WHERE S.ACTIVE=TRUE ",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING})).list();
    
       // List<UrlBean> url=(List<UrlBean>)new StaticSentence(m_App.getSession(), "SELECT S.ID,S.URL,S.USERNAME,S.PASSWORD,S.ACTIVE FROM SMSURL_TABLE S WHERE S.ACTIVE=TRUE ",SerializerWriteString.INSTANCE, new SerializerReadClass(UrlBean.class)).list();
       
       // System.out.println(url.size());
       // return url;
        return obj;
    }
    
    public List<Object[]> getmsg()
    {
        List<Object[]> obj=null;
        try {
          obj=new PreparedSentence(m_App.getSession(), "SELECT A.ID,A.MESSAGE,A.SENDTO FROM activemsgtable A ",SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).list();
       
         
        } catch (BasicException ex) {
            Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
    
    public void updateToSentMsgTable(String details, Object[] obj)
    {
        try {
            new PreparedSentence(m_App.getSession(), "INSERT INTO sentmsgtable(ID, MESSAGE, SENTTO, SENTDETAILS) VALUES (?,?,?,?) "
                           ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})
                           ).exec(new Object[]{obj[0],obj[1],obj[2],details});
        } catch (BasicException ex) {
            Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void deletefromactivemsg(String msgid)
    {
        try {
            new StaticSentence(m_App.getSession(), "DELETE FROM activemsgtable WHERE ID = ?", SerializerWriteString.INSTANCE).exec(msgid);
        } catch (BasicException ex) {
            Logger.getLogger(SmsConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public class UrlBean implements SerializableRead
    {
      
        private String URL;
        private String uesrname;
        private String password;
        private String id;
        private Boolean active;

        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }

       

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
        public String getURL() {
            return URL;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUesrname() {
            return uesrname;
        }

        public void setUesrname(String uesrname) {
            this.uesrname = uesrname;
        }

        public void readValues(DataRead dr) throws BasicException {
            
            id=dr.getString(1);
            URL=dr.getString(2);
            uesrname=dr.getString(3);
            password=dr.getString(4);
            active=dr.getBoolean(5).booleanValue();
        
          //throw new UnsupportedOperationException("Not supported yet.");
        }

       
        
    }
    
    public class DelRepBean
    {
        private  String Scheduleid;
        private  String deliveryReport;
        private  String MessageId;
        private  String memName;
        private  String phoneNo;
        private  String memno;
        private String SentDate;
        
        private String Message;

        public String getMemno() {
            return memno;
        }

        public void setMemno(String memno) {
            this.memno = memno;
        }
     
         public String getMessage() {
            return Message;
        }

        public void setMessage(String Message) {
            this.Message = Message;
        }
        
        public String getMemName() {
            return memName;
        }

        public void setMemName(String memName) {
            this.memName = memName;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }
        

        public String getMessageId() {
            return MessageId;
        }

        public void setMessageId(String MessageId) {
            this.MessageId = MessageId;
        }

        public String getScheduleid() {
            return Scheduleid;
        }

        public void setScheduleid(String Scheduleid) {
            this.Scheduleid = Scheduleid;
        }

        public String getDeliveryReport() {
            return deliveryReport;
        }

        public void setDeliveryReport(String deliveryReport) {
            this.deliveryReport = deliveryReport;
        }
        
        public String getSentDate(){
            return SentDate;
        }
        
        public void setSentDate(Date date)
        {
            String temp = Formats.TIMESTAMP.formatValue(date);
            this.SentDate = temp;
        }
    }
}
