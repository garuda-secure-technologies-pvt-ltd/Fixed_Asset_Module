
package com.openbravo.pos.admin;

import com.openbravo.data.loader.DataRead;
import java.awt.Component;
import javax.swing.*;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.util.Hashcypher;
import java.awt.image.BufferedImage;
import java.util.UUID;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.user.*;
import com.openbravo.format.Formats;
//import com.openbravo.pos.clubmang.AccountMaster;
import com.openbravo.pos.admin.PeopleView.role;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.CardSwipeNotifier;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.mant.WaiterInfo;
import com.openbravo.pos.ticket.CategoryInfo;
//import com.openbravo.pos.ticket.PrintCategoryInfo;
import com.openbravo.pos.util.StringUtils;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adrianromero
 */
public class PeopleView extends JPanel implements EditorRecord,CardSwipeNotifier  {

    private Object m_oId;
    private String m_sPassword;
    
    private DirtyManager m_Dirty;
    
    private SentenceList m_sentrole;
    private ComboBoxValModel m_RoleModel;
    private ComboBoxValModel accModel;
   // private SentenceList m_sentacc;
    private ComboBoxValModel cheaccModel;
    private DataLogicFacilities dlfac;
    private DataLogicAdmin dladmin;
    private DataLogicCustomers dlcus;
    private String cashinhand;
    private String chequeinhand;
    private AppView m_App;
    private CardReader cr;
   
    private StringBuffer inputtext;
    /** Creates new form PeopleEditor */
    public PeopleView(AppView app,DataLogicAdmin dlAdmin, DirtyManager dirty) {
        initComponents();
        dladmin=dlAdmin;
         dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
          dlcus=(DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
        // El modelo de roles
        m_sentrole = dlAdmin.getRolesList();
        accModel=new ComboBoxValModel();
        m_RoleModel = new ComboBoxValModel();
        cheaccModel=new ComboBoxValModel();
        
        m_Dirty = dirty;
        m_jName.getDocument().addDocumentListener(dirty);
        m_jRole.addActionListener(dirty);
        m_txtKeys.addActionListener(dirty);
        j_cash.addActionListener(dirty);
        j_cheque.addActionListener(dirty);
        m_jVisible.addActionListener(dirty);
        m_jImage.addPropertyChangeListener("image", dirty);
        jTextArea1.getDocument().addDocumentListener(dirty);
        jComboBox1.addActionListener(dirty);
         jComboBox2.addActionListener(dirty);   
         m_App = app;
          startCardReader();
        writeValueEOF();
        
        inputtext =
                new StringBuffer();
        m_txtKeys.setText(null);
        
    }

    public void writeValueEOF() {
        m_jName.setEditable(true);
        m_oId = null;
        m_jName.setText(null);
        m_sPassword = null;
        m_RoleModel.setSelectedKey(null);
        accModel.setSelectedKey(null);
        cheaccModel.setSelectedKey(null);
        m_jVisible.setSelected(false);
        jcard.setText(null);
        m_jImage.setImage(null);
        m_jName.setEnabled(false);
        m_jRole.setEnabled(false);
       j_cash.setEnabled(false);
       j_cheque.setEnabled(false);
        m_jVisible.setEnabled(false);
        jcard.setEnabled(false);
        m_jImage.setEnabled(false);
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
         jTextArea1.setText(null);
         jTextArea1.setEnabled(false);
         jTextArea1.setEditable(false);
        jComboBox1.setEnabled(false);
        jButton4.setEnabled(false);
         jComboBox2.setEnabled(false);
        jButton5.setEnabled(false);
        cashinhand=null;
        chequeinhand=null;
    }
    
    public void writeValueInsert() {
        m_jName.setEditable(true);
        m_oId = null;
        m_jName.setText(null);
        m_sPassword = null;
        m_RoleModel.setSelectedKey(null);
        accModel.setSelectedKey(null);
        cheaccModel.setSelectedKey(null);
        m_jVisible.setSelected(true);
        jcard.setText(null);
        m_jImage.setImage(null);
        jTextArea1.setText(null);
        m_jName.setEnabled(true);
        m_jRole.setEnabled(true);
       j_cash.setEnabled(true);
       j_cheque.setEnabled(true);
        m_jVisible.setEnabled(true);
        jcard.setEnabled(true);
        m_jImage.setEnabled(true);
        jButton1.setEnabled(true);
        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
         jTextArea1.setEnabled(true);
          jTextArea1.setEditable(false);
        jComboBox1.setEnabled(true);
        jButton4.setEnabled(true);
        jComboBox2.setEnabled(true);
        jButton5.setEnabled(true);
        cashinhand=null;
        chequeinhand=null;
    }
    
    public void writeValueDelete(Object value) {
        m_jName.setEditable(true);
        Object[] people = (Object[]) value;
        m_oId = people[0];
        m_jName.setText(Formats.STRING.formatValue(people[1]));
        m_sPassword = Formats.STRING.formatValue(people[2]);
        m_RoleModel.setSelectedKey(people[3]);
        m_jVisible.setSelected(((Boolean) people[4]).booleanValue());
        jcard.setText(Formats.STRING.formatValue(people[5]));
        m_jImage.setImage((BufferedImage) people[6]);
        String data="";
         try{
        if(people[7] != null)
        {
        String[] strtemp=people[7].toString().split("#");

        for(int i=0;i<strtemp.length;i++)
        {
          //  for(int i=0;i< obj.length;i++)
      //  {
            AppView m_App=LookupUtilityImpl.getInstance(null).getAppView();
         Object[] obj1=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT NAME FROM CATEGORIES WHERE ID = ?"
                       ,SerializerWriteString.INSTANCE
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(strtemp[i]);
        if(obj1!=null)
        {
            data=data+obj1[0]+"\r\n";
        }

        }
        jTextArea1.setText(data);
        }

        else
           jTextArea1.setText(null);
        }
        catch(Exception e)
        {
        }
        accModel.setSelectedKey(people[8]);
        cheaccModel.setSelectedKey(people[9]);
      /*   Object pid="";
        try{
         if(people[8]!=null)
         pid=dlcus.getparentaccountbyid(people[8].toString());
        }catch(Exception e){
          e.printStackTrace();
        }
         if(!pid.equals(""))
              accModel.setSelectedKey(pid);
         else
              accModel.setSelectedKey(null);

         Object pid1="";
        try{
         if(people[9]!=null)
         pid1=dlcus.getparentaccountbyid(people[8].toString());
        }catch(Exception e){
          e.printStackTrace();
        }
         if(!pid1.equals(""))
              cheaccModel.setSelectedKey(pid1);
         else
              cheaccModel.setSelectedKey(null);
        */
        if(people[8]==null)
            cashinhand=null;
        else
           cashinhand=people[8].toString();
         if(people[9]==null)
            chequeinhand=null;
        else
           chequeinhand=people[9].toString();
        m_jName.setEnabled(false);
        m_jRole.setEnabled(false);
       // accounttype.setEnabled(false);
      //  chequeacctype.setEnabled(false);
        m_jVisible.setEnabled(false);
        jcard.setEnabled(false);
        m_jImage.setEnabled(false);        
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
         jTextArea1.setEnabled(false);
        jComboBox1.setEnabled(false);
        jButton4.setEnabled(false);
        jComboBox2.setEnabled(false);
        jButton5.setEnabled(false);
        jTextArea1.setEditable(false);
         String textareadata=jTextArea1.getText();
      String[] strings=textareadata.split("\r\n");
      jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(strings));
    }    
    
    public void writeValueEdit(Object value) {
        m_jName.setEditable(false);
        Object[] people = (Object[]) value;
        m_oId = people[0];
        m_jName.setText(Formats.STRING.formatValue(people[1]));
        m_sPassword = Formats.STRING.formatValue(people[2]);
        m_RoleModel.setSelectedKey(people[3]);
        m_jVisible.setSelected(((Boolean) people[4]).booleanValue());
        jcard.setText(Formats.STRING.formatValue(people[5]));
        m_jImage.setImage((BufferedImage) people[6]);
        String data="";
        try{
        if(people[7] != null)
        {
        String[] strtemp= Formats.STRING.formatValue(people[7]).split("#");


        for(int i=0;i<strtemp.length;i++)
        {
          //  for(int i=0;i< obj.length;i++)
      //  {
            AppView m_App=LookupUtilityImpl.getInstance(null).getAppView();
         Object[] obj1=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT NAME FROM CATEGORIES WHERE ID = ?"
                       ,SerializerWriteString.INSTANCE
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(strtemp[i]);
        if(obj1!=null)
        {
             data=data+obj1[0]+"\r\n";
        }

        }
        jTextArea1.setText(data);
        }

        else
           jTextArea1.setText(null);
        }
        catch(Exception e)
        {
        }
          /*   Object pid="";
        try{
         if(people[8]!=null)
         pid=dlcus.getparentaccountbyid(people[8].toString());
        }catch(Exception e){
          e.printStackTrace();
        }
         if(!pid.equals(""))
              accModel.setSelectedKey(pid);
         else
              accModel.setSelectedKey(null);

         Object pid1="";
        try{
         if(people[9]!=null)
         pid1=dlcus.getparentaccountbyid(people[8].toString());
        }catch(Exception e){
          e.printStackTrace();
        }
         if(!pid1.equals(""))
              cheaccModel.setSelectedKey(pid1);
         else
              cheaccModel.setSelectedKey(null);
        */
        accModel.setSelectedKey(people[8]);
        cheaccModel.setSelectedKey(people[9]);
        if(people[8]==null)
            cashinhand=null;
        else
           cashinhand=people[8].toString();
         if(people[9]==null)
            chequeinhand=null;
        else
           chequeinhand=people[9].toString();

        m_jName.setEnabled(true);
        m_jRole.setEnabled(true);
      j_cash.setEnabled(true);
       j_cheque.setEnabled(true);
        m_jVisible.setEnabled(true);
        jcard.setEnabled(true);
        m_jImage.setEnabled(true);
        jButton1.setEnabled(true);
        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
        jTextArea1.setEnabled(true);
        jComboBox1.setEnabled(true);
        jButton4.setEnabled(true);
        jComboBox2.setEnabled(true);
        jButton5.setEnabled(true);
        jTextArea1.setEditable(false);
         String textareadata=jTextArea1.getText();
     String[] strings=textareadata.split("\r\n");
      jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(strings));
    }
    
    public Object createValue() throws BasicException {
        Object[] people = new Object[10];
        String m_id=UUID.randomUUID().toString();
        people[0] = m_oId == null ? m_id : m_oId;
        people[1] = Formats.STRING.parseValue(m_jName.getText());
        people[2] = Formats.STRING.parseValue(m_sPassword);
        people[3] = m_RoleModel.getSelectedKey();
        people[4] = Boolean.valueOf(m_jVisible.isSelected());
        people[5] = Formats.STRING.parseValue(jcard.getText());
        people[6] = m_jImage.getImage();
        String prdata=jTextArea1.getText();
        Object[] obj=prdata.split("\r\n");
        AppView m_App=LookupUtilityImpl.getInstance(null).getAppView();
        String prid="";
        for(int i=0;i< obj.length;i++)
        {
         Object[] obj1=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT ID FROM CATEGORIES WHERE NAME = ?"
                       ,SerializerWriteString.INSTANCE
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(obj[i].toString());

        if(obj1== null)
            prid += "";
        else{
             if(prid.equals(""))
           prid = obj1[0].toString();
             else
                 prid=prid+ "#" +obj1[0].toString();
        }
        }
        people[7]= Formats.STRING.parseValue(prid);
        people[8]=accModel.getSelectedKey();
        people[9]=cheaccModel.getSelectedKey();
        System.out.println(m_RoleModel.getSelectedKey().toString());
        //praveen:allowed to user to select account name for cash and cheque so commented below code.....


        
//        role ro = (role) new StaticSentence(m_App.getSession()
//                                        ,"SELECT name,FLAG FROM ROLES WHERE ID=?"
//                                        ,SerializerWriteString.INSTANCE
//                                        ,new SerializerReadClass(role.class)).find(m_RoleModel.getSelectedKey().toString());

//        boolean accountFlag = false;
//        if(ro!=null){
//            accountFlag = ro.isFlag();
//        }
//        if(m_RoleModel.getSelectedKey().toString().equals("5") || m_RoleModel.getSelectedKey().toString().equals("6") || m_RoleModel.getSelectedKey().toString().equals("7")){
//        //if(accountFlag){
//        if(cashinhand==null && chequeinhand==null){
//           Date dnow=new Date();
//           String id=UUID.randomUUID().toString();
//           String parentKey="1.1.1.2";
//           String searchkey;
//           int temp = 0;
//           people[8]=id;
//            Object obj1[]=(Object[])new StaticSentence(m_App.getSession()
//                                        ,"SELECT MAXIMUM FROM ACCOUNTMASTER WHERE SEARCHKEY=? "
//                                        ,SerializerWriteString.INSTANCE
//                                        ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(parentKey);
//            if(obj1==null || obj1[0]==null){
//                        // Double temp=(Double.parseDouble(acc.getSerachkey()) + 1);
//
//                         searchkey="1110000";
//                        // searchkey1=cashacc.getSerachkey()+"0001";
//                     }else{
//                         temp=(Integer.parseInt(obj1[0].toString()) + 1);
//                         searchkey = parentKey + "." + temp;
//                        //searchkey=String.valueOf(searchkey+1);
//                     }
//             Object[] value=new Object[]{id,searchkey,"Cash in hand "+m_jName.getText(),"Cash in hand "+m_jName.getText(),"Asset","D",false,false,"1.1.1.2","S",dnow,0.0,true};
//
//             new PreparedSentence(m_App.getSession()
//                  , "INSERT INTO ACCOUNTMASTER(ID,SEARCHKEY,NAME,DESCRIPTION,TYPE_,SIGN,DOCUMENT,SUMMARY,PARENT,LEVEL_,STARTDATE,OPENINGBALANCE,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"
//                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.DOUBLE,Datas.BOOLEAN})
//                  ).exec(value);
//            //praveen:added to update max.value
//             new StaticSentence(m_App.getSession()
//                                        ,"UPDATE  ACCOUNTMASTER SET MAXIMUM=? WHERE SEARCHKEY=? "
//                                        ,new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})
//                                        ,null).exec(new Object[]{temp,parentKey});
//
//
//
//              String id1=UUID.randomUUID().toString();
//              //sanjeev:renamed type to type_,level to level_
//           String searchkey1 = null;
//           people[9]=id1;
//           Object obj2[]=(Object[])new StaticSentence(m_App.getSession()
//                                        ,"SELECT MAXIMUM FROM ACCOUNTMASTER WHERE SEARCHKEY=? "
//                                        ,SerializerWriteString.INSTANCE
//                                        ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(parentKey);
//           if(obj2==null || obj2[0]==null){
//                        // Double temp=(Double.parseDouble(acc.getSerachkey()) + 1);
//                         searchkey1="1120000";
//                        // searchkey1=cashacc.getSerachkey()+"0001";
//                     }else{
//                          temp=(Integer.parseInt(obj2[0].toString()) + 1);
//                         searchkey = parentKey + "." + temp;
//                        // searchkey1=String.valueOf(temp+1);
//                     }
//
//
//         Object[] value1=new Object[]{id1,searchkey,"Cheque in hand "+m_jName.getText(),"Cheque in hand "+m_jName.getText(),"Asset","D",false,false,"1.1.1.2","S",dnow,0.0,true};
//         new PreparedSentence(m_App.getSession()
//                  , "INSERT INTO ACCOUNTMASTER(ID,SEARCHKEY,NAME,DESCRIPTION,TYPE_,SIGN,DOCUMENT,SUMMARY,PARENT,LEVEL_,STARTDATE,OPENINGBALANCE,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"
//                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.DOUBLE,Datas.BOOLEAN})
//                  ).exec(value1);
//         //praveen:added to update max.value
//         new StaticSentence(m_App.getSession()
//                                        ,"UPDATE  ACCOUNTMASTER SET MAXIMUM=? WHERE SEARCHKEY=? "
//                                        ,new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})
//                                        ,null).exec(new Object[]{temp,parentKey});
//         //sanjeev:renamed type to type_ ,level to level_
//
//
//        }
//      else{
//        people[8]=cashinhand;
//        people[9]=chequeinhand;
//      }
//
//        }else{
//         people[8]=null;
//         people[9]=null;
//        }
        return people;
      //  }
    }    
    
    public Component getComponent() {
        return this;
    }    
    
    public void activate() throws BasicException {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                m_txtKeys.requestFocus();
            }
        });
        m_RoleModel = new ComboBoxValModel(m_sentrole.list());
        accModel=new ComboBoxValModel(dlfac.getaccounts());
        cheaccModel=new ComboBoxValModel(dlfac.getaccounts());
        m_jRole.setModel(m_RoleModel);
        j_cash.setModel(accModel);
        j_cheque.setModel(cheaccModel);
        AppView m_App=LookupUtilityImpl.getInstance(null).getAppView();
        if (m_App.getAppUserView().getUser().hasPermission("cashANDchequeAccount")) {
            j_cash.setVisible(true);
            j_cheque.setVisible(true);
            jLabel6.setVisible(true);
            jLabel7.setVisible(true);
        } else {
           j_cash.setVisible(false);
            j_cheque.setVisible(false);
            jLabel6.setVisible(false);
            jLabel7.setVisible(false);
        }
        List<CategoryInfo> prinfo=   new StaticSentence(m_App.getSession()
                        , "SELECT ID,NAME,PARENTID,IMAGE FROM CATEGORIES WHERE PARENTID IS NULL"
                       ,null
                       ,new SerializerReadClass(CategoryInfo.class)).list();
        if(prinfo!=null)
        {
       String[] strings=new String[prinfo.size()];
      int i=0;
            for(CategoryInfo p: prinfo)
            {
                strings[i]=p.getName();
                i++;
            }
      jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(strings));
      String textareadata=jTextArea1.getText();
      strings=textareadata.split("\r\n");
      jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(strings));
        /*     jList1.setModel(new javax.swing.AbstractListModel() {
            // = 
                 
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });*/
            
        }

    }
     
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        m_jName = new javax.swing.JTextField();
        m_jVisible = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        m_jImage = new com.openbravo.data.gui.JImageEditor();
        jButton1 = new javax.swing.JButton();
        m_jRole = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jcard = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        j_cash = new javax.swing.JComboBox();
        j_cheque = new javax.swing.JComboBox();
        m_txtKeys = new javax.swing.JTextField();

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });
        setLayout(null);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileclose.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);
        jButton3.setBounds(360, 50, 49, 25);

        jLabel1.setText(AppLocal.getIntString("label.peoplename")); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(20, 20, 90, 14);
        add(m_jName);
        m_jName.setBounds(110, 20, 180, 20);
        add(m_jVisible);
        m_jVisible.setBounds(110, 110, 180, 20);

        jLabel3.setText(AppLocal.getIntString("label.peoplevisible")); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(20, 110, 80, 20);

        jLabel4.setText(AppLocal.getIntString("label.peopleimage")); // NOI18N
        add(jLabel4);
        jLabel4.setBounds(20, 240, 90, 14);

        m_jImage.setMaxDimensions(new java.awt.Dimension(32, 32));
        add(m_jImage);
        m_jImage.setBounds(110, 240, 230, 140);

        jButton1.setText(AppLocal.getIntString("button.peoplepassword")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(300, 20, 110, 23);
        add(m_jRole);
        m_jRole.setBounds(110, 80, 180, 20);

        jLabel2.setText(AppLocal.getIntString("label.role")); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(20, 80, 90, 14);

        jcard.setEditable(false);
        add(jcard);
        jcard.setBounds(110, 50, 180, 20);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/color_line16.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(300, 50, 49, 25);

        jLabel5.setText(AppLocal.getIntString("label.card")); // NOI18N
        add(jLabel5);
        jLabel5.setBounds(20, 50, 90, 14);

        add(jComboBox1);
        jComboBox1.setBounds(300, 410, 150, 20);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        add(jScrollPane1);
        jScrollPane1.setBounds(110, 400, 170, 120);

        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4);
        jButton4.setBounds(300, 440, 60, 23);

        add(jComboBox2);
        jComboBox2.setBounds(300, 470, 150, 20);

        jButton5.setText("Remove");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        add(jButton5);
        jButton5.setBounds(300, 500, 90, 23);

        jLabel6.setText("Cash Acct.");
        add(jLabel6);
        jLabel6.setBounds(20, 150, 90, 20);

        jLabel7.setText("Cheque Acct.");
        add(jLabel7);
        jLabel7.setBounds(20, 190, 100, 20);

        add(j_cash);
        j_cash.setBounds(110, 150, 230, 20);

        add(j_cheque);
        j_cheque.setBounds(110, 190, 230, 20);

        m_txtKeys.setText("jTextField1");
        m_txtKeys.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                m_txtKeysKeyTyped(evt);
            }
        });
        add(m_txtKeys);
        m_txtKeys.setBounds(480, 10, 72, 20);
        m_txtKeys.setBounds(0, 0, 9, 0);
    }// </editor-fold>//GEN-END:initComponents

   

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String sNewPassword = Hashcypher.changePassword(this);
        if (sNewPassword != null) {
            m_sPassword = sNewPassword;
            m_Dirty.setDirty(true);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        
        if (JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.cardnew"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {  
            jcard.setText("c" + StringUtils.getCardNumber());
            m_Dirty.setDirty(true);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.cardremove"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {  
            jcard.setText(null);
            m_Dirty.setDirty(true);
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String selectedpr=jComboBox1.getSelectedItem().toString();
        int flag=0;
        String data=jTextArea1.getText();
        String[] temparr=data.split("\r\n");
        for(int i=0;i<temparr.length; i++)
        {
            if(temparr[i].equals(selectedpr))
            {
                 JOptionPane.showMessageDialog(this,AppLocal.getIntString("message.prcatwarning"), AppLocal.getIntString("message.prcattitle"), JOptionPane.WARNING_MESSAGE);
                 flag=1;
            }
        }
        if(flag==0){
        data += selectedpr+"\r\n";
        jTextArea1.setText(data);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
          String selectedpr=jComboBox2.getSelectedItem().toString();
      //  int flag=0;
        String data=jTextArea1.getText();
        String newdata="";
        String[] temparr=data.split("\r\n");
        for(int i=0;i<temparr.length; i++)
        {
            if(!temparr[i].equals(selectedpr))
                newdata += temparr[i]+"\r\n";

        }
       // if(flag==0){
      //  data += selectedpr+"\r\n";
        jTextArea1.setText(newdata);
     //   }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
       // processKey(evt.getKeyChar());
    }//GEN-LAST:event_formKeyTyped

    private void m_txtKeysKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_txtKeysKeyTyped
        m_txtKeys.setText("0");

        processKey(evt.getKeyChar());
    }//GEN-LAST:event_m_txtKeysKeyTyped
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JComboBox j_cash;
    private javax.swing.JComboBox j_cheque;
    private javax.swing.JTextField jcard;
    private com.openbravo.data.gui.JImageEditor m_jImage;
    private javax.swing.JTextField m_jName;
    private javax.swing.JComboBox m_jRole;
    private javax.swing.JCheckBox m_jVisible;
    private javax.swing.JTextField m_txtKeys;
    // End of variables declaration//GEN-END:variables

    public void cardswiped(WaiterInfo waiter) {
       JOptionPane.showMessageDialog(this, "This card is already registered for " + waiter.getName() + " .", "Already Registered!!!", JOptionPane.ERROR_MESSAGE); //To change body of generated methods, choose Tools | Templates.
    }

    public void cardswiped(String custCard) {
        jcard.setText(custCard);
    }

    private void startCardReader() {
        try {
          
            String portNumber =  m_App.getProperties().getProperty("card.portnumber");
            boolean cardAccessOnlyFlag = false; 
            if ( m_App.getProperties().getProperty("cardAccessOnly") != null) {
                cardAccessOnlyFlag = Boolean.valueOf( m_App.getProperties().getProperty("cardAccessOnly"));
            }
            cr = new CardReader(portNumber, cardAccessOnlyFlag);
           // cr =  m_App.getReader();
            cr.setCardSwipeNotifier(this);

           // m_App.getReader()
            System.out.println(portNumber);
            System.out.println(m_App.getProperties().getProperty("cardAccessOnly"));
            cr.ConfigurePort();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

       private void processKey(char c) {
           
        if (c == '\n') {
            try {
                String receptionString = inputtext.toString();
                WaiterInfo waiterinfo = (WaiterInfo) new PreparedSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession(), "SELECT W.ID, W.NAME,W.COUNTER,P.NAME FROM WAITER W,PEOPLE P WHERE P.ROLE=W.COUNTER AND W.CARDNO = ? AND P.VISIBLE=TRUE", SerializerWriteString.INSTANCE, new SerializerReadClass(WaiterInfo.class)).find(receptionString);
                
                if(waiterinfo==null)
                {
                jcard.setText(receptionString);
                
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "This card is already registered for " + waiterinfo.getName() +".", "Already Registered!!", JOptionPane.ERROR_MESSAGE);
                    jcard.setText(null);
                }
                inputtext = new StringBuffer();
                } catch (BasicException ex) {
                Logger.getLogger(PeopleView.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        else
        {
            inputtext.append(c);
        }

            }


    public static class role implements SerializableRead{
        private String id;
        private boolean flag;


        public void readValues(DataRead dr) throws BasicException {
            id=dr.getString(1);
            flag=dr.getBoolean(2);

        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
        
      
}
}
