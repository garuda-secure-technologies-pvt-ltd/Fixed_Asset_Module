
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JFlowPanel;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


public class GuestRoomStatus extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private AppView m_App;
    int B=2;
    int F1 = 5;
    int F2= 6;
    private BuildingTableModel buldge_table_model;
    
    public GuestRoomStatus() {
        initComponents();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
                .addContainerGap())
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel main_panel;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
         return "";
    }

    public void activate() throws BasicException {
       loaddata();
       loadPanel();
    }

    public boolean deactivate() {
           return true;
    }

    public JComponent getComponent() {
         return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        this.m_App = app;
        buldge_table_model = (BuildingTableModel) m_App.getBean("com.openbravo.pos.Booking.BuildingTableModel");
    }

    public Object getBean() {
        return this;
    }
    
    
    public void loaddata(){
        
          
    }
    
   
    
    
    
    public void loadPanel(){
        
        List<Object> getBuildingNames_list = new ArrayList<Object>();
        int No_of_Buldg = buldge_table_model.getNo_of_Buldg(m_App);
        getBuildingNames_list = buldge_table_model.getBuildingNames(m_App);
        
              jScrollPane1.getViewport().setView(null);
              JFlowPanel jPeople = new JFlowPanel();
              
              JFlowPanel jPeople2 = new JFlowPanel();
              jPeople.applyComponentOrientation(getComponentOrientation()); 
              int K=0;
                for(int i=0;i<getBuildingNames_list.size();i++){
                    
                   JScrollPane Scroll1 = new JScrollPane();
                    JLabel jbl = new JLabel(getBuildingNames_list.get(i).toString());
                    Scroll1.applyComponentOrientation(getComponentOrientation());
                    //btn.setFocusPainted(false);
                    Scroll1.setFocusable(false);
                    Scroll1.setRequestFocusEnabled(false);
                   // btn.setHorizontalAlignment(SwingConstants.LEADING);
                    Scroll1.setMaximumSize(new Dimension(390, 500));
                    Scroll1.setPreferredSize(new Dimension(390, 500));
                    Scroll1.setMinimumSize(new Dimension(390, 500));
                    Scroll1.setBackground(Color.GRAY);
                    Scroll1.setForeground(Color.MAGENTA);
                    jbl.setForeground(Color.BLUE);
                    jPeople.add(jbl);
                    
                    int No_of_floors = buldge_table_model.getNo_of_Floors(m_App ,getBuildingNames_list.get(i).toString() );
                    String FloorNames  = buldge_table_model.getFloorNames(m_App, getBuildingNames_list.get(i).toString());
                    String [] Floor_Arry = FloorNames.split(",");
                    
                    if(i==K){
                       JFlowPanel jPeople1 = new JFlowPanel();   
                           int K1=0; 
                                 
                                 
                                  for(int j=0;j<No_of_floors;j++){
                                   
                                   
                                   String floor  = Floor_Arry[j] ;
                                   JScrollPane scr1 = new JScrollPane();
                                    JLabel jbl1 = new JLabel("Floor :- "+floor);  
                                    jbl1.setForeground(Color.DARK_GRAY);
                                   scr1.applyComponentOrientation(getComponentOrientation());
                                  // btn.setFocusPainted(false);
                                   scr1.setFocusable(false);
                                   scr1.setRequestFocusEnabled(false);
                                  // btn.setHorizontalAlignment(SwingConstants.LEADING);
                                   scr1.setMaximumSize(new Dimension(380, 150));
                                   scr1.setPreferredSize(new Dimension(380, 150));
                                   scr1.setMinimumSize(new Dimension(380, 150));
                                   scr1.setBackground(Color.GRAY);
                                   scr1.setForeground(Color.RED);
                                   jPeople1.add(jbl1);
                                   
                                  
                                   
                                 List<Object> RoomName_List = new ArrayList<Object>();
                                   
                                   int No_of_Rooms = buldge_table_model.getno_of_Rooms(m_App, floor);
                                   RoomName_List = buldge_table_model.getRoomsNames_list(m_App, floor);
                                   
                                   
                                            if(j==K1){
                                                JFlowPanel jPeople4 = new JFlowPanel();
                                                for(int j1=0;j1<No_of_Rooms;j1++){
                                                
                                                String name4  = RoomName_List.get(j1).toString();
                                                JButton btn1 = new JButton( new OnclickAction(name4));
                                                 btn1.applyComponentOrientation(getComponentOrientation());
                                                // btn1.setFocusPainted(false);
                                                 btn1.setFocusable(false);
                                                 btn1.setRequestFocusEnabled(false);
                                                // btn.setHorizontalAlignment(SwingConstants.LEADING);
                                                 btn1.setMaximumSize(new Dimension(100, 50));
                                                 btn1.setPreferredSize(new Dimension(100, 50));
                                                 btn1.setMinimumSize(new Dimension(100, 50));
                                                 int Status = buldge_table_model.getStatusRoom(m_App, name4);
                                                 if(Status==1){
                                                      btn1.setBackground(Color.RED);
                                                      btn1.setForeground(Color.BLACK);
                                                 }
                                                 else{
                                                    btn1.setBackground(Color.GREEN);
                                                  
                                                            
                                                }
                                                 
                                                 jPeople4.add(btn1);
                                                 scr1.add(jPeople4);
                                                scr1.getViewport().setView(jPeople4);
                                                }
                                            } 
                                            
                                            K1++;
                                         
                                   
                                   jPeople1.add(scr1);
                                   Scroll1.add(jPeople1);
                                  Scroll1.getViewport().setView(jPeople1);
                                } 
                    }
                    
                    K++;
                    jPeople.add(Scroll1);
                   
                   
                    
                } 
              
            
            jScrollPane1.getViewport().setView(jPeople);
        
        
    }
    
    
    
      private class OnclickAction extends AbstractAction {

                    private String bValue = null;

                   public OnclickAction(String name) {
                       super(name);
                       bValue = name;
                   }



                   public void actionPerformed(ActionEvent e) {
                     List<Object[]> Details = new ArrayList<Object[]>();
                     int Status = buldge_table_model.getStatusRoom(m_App, bValue);
                     Object[] obj1=null;
                     String BillName;
                     String CheckIN_Date;
                     String CheckOut_Date;
                     int no_of_days;
                     String Mem_No;
                     String GUEST_N;
                     Double ADV_RECV ;
                     Double TOT_AMT ;
                     Double RM_SERV_CHRG;
                     String RoomType;
                     
                     Details =  buldge_table_model.getRoomBookedDetails(m_App, bValue);
                     if(Status==1){
                         
                         for (Object[] detlist : Details) {
                              obj1=detlist; 
                         }
                         RoomType = obj1[0].toString();
                         Mem_No = obj1[1].toString();
                         if(obj1[2] !=null){
                             GUEST_N = obj1[2].toString();
                         }
                         no_of_days = Integer.parseInt(obj1[3].toString());
                         CheckIN_Date = Formats.TIMESTAMP.formatValue(obj1[4]);
                         CheckOut_Date = Formats.TIMESTAMP.formatValue(obj1[5]);
                         BillName = obj1[9].toString();
                         
                         
                         UIManager UI = new UIManager();
                         UI.put("OptionPane.messageForeground", Color.RED);
                         JOptionPane.showMessageDialog(main_panel, "Checked In by :- "+BillName+".  \n  From : "+CheckIN_Date+ " To "+CheckOut_Date+ ". " , "Room Type :- "+RoomType+  ".   Room No:- "+bValue , JOptionPane.INFORMATION_MESSAGE);    
                     }
                     else{
                         
                         UIManager UI = new UIManager();
                         UI.put("OptionPane.messageForeground", Color.BLACK);
                         JOptionPane.showMessageDialog(main_panel, "Room is Available..!! ", "Room No:- "+bValue , JOptionPane.INFORMATION_MESSAGE);    
                     }
                     
                  

                   }
           }
      
    
    
    
    
    
}
    
    

