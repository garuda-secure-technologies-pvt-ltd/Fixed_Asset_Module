/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.ESSLDisplay;

//import DAO.Logs;
import com.openbravo.data.loader.Session;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author user
 */
public class RetrieveData implements Runnable {
  
    private UpdateListener ul;
    private ESSLDBSession eSession = null;
    private Connection con;
   

    public RetrieveData(Connection con) {
       this.con = con;
    }
  public RetrieveData(UpdateListener ul, ESSLDBSession eSession, Connection con) {
        this.ul = ul;
        this.eSession = eSession;
        this.con = con;
    }
   
    @SuppressWarnings("static-access")
    public void doTask() throws IOException, SQLException {

        String name = null;
        int id=0;
        int temp=0;
        InputStream in = null;
        BufferedImage image = null;
        Member member = null;
        System.out.println("##### Task Executing####");

        //Timestamp ts = Current(con);
        Date ts=new Date();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");

        cal.setTimeInMillis(ts.getTime());
        long intialDiff = 0;
        long finalDiff = 0;
        Timestamp memberLogTime = null;
        int seq = 1;
        PreparedStatement st = con.prepareStatement("select s.name,a.id,a.attendancedate,s.logo from staff s,attendancelog a where s.id=a.staffid and a.attendancedate >=?  order by attendancedate");
        while (true) {
            intialDiff = (cal.getTimeInMillis() - (new Date().getTime())) % 1000;
            String dt = formatter.format(cal.getTime());
            cal.add(Calendar.SECOND, 1);
            System.out.println("Time:"+cal.getTime());
            st.setTimestamp(1, new Timestamp(cal.getTimeInMillis()));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                name = rs.getString(1);
              //  System.out.println(name);
                id=rs.getInt(2);
                memberLogTime = rs.getTimestamp(3);
                 in = rs.getBinaryStream(4);
                if (in != null) {
                    try {
                        image = ImageIO.read(in);
                        
                        if (image == null) {
                            in = getClass().getResourceAsStream("/bci/noimg.bmp");
                            image = ImageIO.read(in);
                        }
                    
                    } catch (IOException ex) {
                          ex.printStackTrace();

                    }
//                    if(temp!=id){
                   member = new Member(name, image, seq);
                    seq++;

                    ul.updateImage(member);
                //    }
                    try {
                        Thread.currentThread().sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
                    }




                }
               

            }
           // temp=id;
            finalDiff = (cal.getTimeInMillis() - (new Date().getTime())) / 1000;
            System.out.println("intialDiff : " + intialDiff);
            System.out.println("finalDiff : " + finalDiff);
            if ((intialDiff + 20) > finalDiff) {
                long i = (intialDiff + 20) - finalDiff;
                if (i < 0) {
                    i = i * -1;
                }
                try {
                    Thread.currentThread().sleep(i);
                    System.out.println("Sleep : " + i);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            
            System.out.println(name);
            System.out.println(dt);
            if (memberLogTime != null) {
                cal.setTimeInMillis(memberLogTime.getTime());
            }
       
        }
    }

    public Timestamp Current(
            Connection con) {
        Timestamp ts = new Timestamp(new Date().getTime());
        try {

            Statement s = con.createStatement();
            ResultSet r = s.executeQuery("select current_timestamp date"); //getting server time for the first time when app is started
            if (r.next()) {
                ts = r.getTimestamp("date");
                System.out.println(ts);
            }


        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ts;

    }

    public void run() {
        try {
            doTask();
        } catch (IOException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RetrieveData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
