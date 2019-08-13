/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.ESSLDisplay;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class DisplayImg {

    private static ESSLDBSession mses;
    private static int c;
    private static int temp=0;
    public static void show(List al,UpdateListener f){

        c=temp;
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
         BufferedImage img=null;
        try {
             mses = DBConnection.getEsslConnection();
             con=mses.getConnection();
             for(int i=0;i<al.size();i++){
                 //TODO change the below query
              ps=con.prepareStatement("select * from staff where staffcode=?");
              ps.setString(1,(String)al.get(i));
              rs=ps.executeQuery();
          
              if(rs.next()){
                  
                    InputStream in=rs.getBinaryStream("image");
                     
                 // System.out.println(rs.getString("name")+" "+in);
                   
                  if(in!=null){
                  
                    try {
                        img = ImageIO.read(in);
                        c++;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                
                
                  }

                 // f.updateImage(img,c,rs.getString("name"));
                  //in=null;
              }
              else
                  JOptionPane.showMessageDialog(null, "Member Not Present ","", JOptionPane.ERROR_MESSAGE);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

             }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        temp=c;

    }
}
