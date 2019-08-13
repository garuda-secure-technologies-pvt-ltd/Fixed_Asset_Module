/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddMemberCR.java
 *
 * Created on 06-Feb-2010, 12:08:25
 */

package com.openbravo.pos.CardsRoom;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.catalog.CatalogSelector;
import com.openbravo.pos.catalog.JCatalog;
import com.openbravo.pos.clubmang.AbstractTreeTableModel;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.clubmang.JTreeTable;
import com.openbravo.pos.clubmang.TreeTableModel;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.customers.JCustomerFinder;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.ProductInfoExt;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class AddMemberCR extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    /** Creates new form AddMemberCR */
    private double caltotal;
    private int status1;
    private int operator;
    private String[] Header=new String[]{"Product Name","Quantity","Rate","Amount"};
    private String[] Header1=new String[]{"Name","Qty","Rate","Total"};
    private AppView m_App;
    private List<TokenElements> finalList;
    private CatalogSelector m_cat;
    private DataLogicSales m_dlSales;
    private Component component;
    private DataLogicFacilities dmang;
    private CustomerInfo customerInfo;
    private DataLogicCustomers dlCustomers;
    private int gameID;
    private GameInfoExt selectedGame;
    private CardsRoomMain panel;
    private int line=0;
    private ComboBoxValModel cmodel;
    private int mode;//mode=0 indicates create mode and mode=1 indicates edit mode;
    private int playerIndex;
    private double tokenValue;
    private List<TokenElements> pdtList=new ArrayList<TokenElements>();
    private GameLog gameLog;
    private CatalogListener catalogListener;
    private JTable jTable1;
    private JTreeTable treetable;
    private List<TokenCombinationInfo> tokenCombList=new ArrayList<TokenCombinationInfo>();
    private Map<String,TokenCombinationInfo> map=new HashMap<String,TokenCombinationInfo>();
    private PlayeresData player;
    private int status=0;
    private Boolean flag;

    public AddMemberCR() {
        initComponents();
    }

    public String getTitle() {
        return null;
    }
    public void setPlayerIndex(int index){
       playerIndex=index;
    }
    private String createSentence(List<TokenElements> teleList,Object[] obj,Datas[] wdatas,Datas[] rdatas){
       String begining="SELECT  ";//
       String middle="";//
       String endPart="as subquery1 group by ";
       int i=0;
       for(TokenElements ele:teleList){
           if(begining.equals("SELECT  ")){
               begining+=" SUM(S"+i+") ";
               endPart+=" S"+i;
           }else{
               begining+=" , SUM(S"+i+") ";
               endPart+=" , S"+i;
           }
           String sent="SELECT  ";//each line
           for(int j=0;j<teleList.size();j++){
             if(i==j){
               if(sent.equals("SELECT  "))
                  sent+=" CASE WHEN S.UNITS IS NULL THEN 0.0 ELSE S.UNITS END AS S"+j;
               else
                  sent+=" ,CASE WHEN S.UNITS IS NULL THEN 0.0 ELSE S.UNITS END AS S"+j;
             }else{
               if(sent.equals("SELECT  "))
                   sent+=" 0.0 AS S"+j;
               else
                   sent+=" ,0.0 AS S"+j;
             }
           }
           sent+=" FROM PRODUCTS P LEFT OUTER JOIN STOCKCURRENT S ON P.ID=S.PRODUCT AND P.ID=? ";
           if(i==0){
               middle+=sent;
           }else
               middle+=" UNION ALL "+sent;
           
           obj[i]=ele.getProductID();
           wdatas[i]=Datas.STRING;
           rdatas[i]=Datas.INT;
           i++;
       }
       return begining+" FROM ( "+middle+" ) "+endPart;
    }
    private void calculate(List<TokenElements> teleList,Object[] qtys) throws BasicException{
        finalList=new ArrayList<TokenElements>();
         pdtList=new ArrayList<TokenElements>();
        int i=0;
        int status=-1;
        double rate=-1;
        for(TokenElements ele :teleList){
              if(qtys[i]==null || ele.getQty()>Integer.parseInt(String.valueOf(qtys[i]))){
                     //status=1;
                     rate=ele.getRate();
                     status=i;
                     break;
                   }
                     i++;
               }
               if(status>-1){

                    Object[] objtemp=(Object[]) new StaticSentence(m_App.getSession(),"SELECT PRCATEGORIES FROM PEOPLE WHERE ID = ?"
                                     , SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(m_App.getAppUserView().getUser().getId());
                    String[] prcat;
                    if(objtemp!=null && objtemp[0]!=null){
                        prcat=objtemp[0].toString().split("#");
                        int t=0;

                        for(int j=0;j<prcat.length;j++){
                          List<TokenElements> obj1= new StaticSentence(m_App.getSession(),
                                             "SELECT P.NAME,P.ID,P.PRICESELL,CASE WHEN S.UNITS IS NULL THEN 0.0 ELSE S.UNITS END  FROM PRODUCTS P LEFT OUTER JOIN STOCKCURRENT S ON P.ID=S.PRODUCT  WHERE P.PRICESELL <= ?    ORDER BY P.PRICESELL DESC"
                                             , new SerializerWriteBasic(new Datas[]{Datas.DOUBLE}),new SerializerReadClass(TokenElements.class)).list(new Object[]{rate});
                          if(obj1!=null){
                              pdtList.addAll(obj1);
                          }
                        }
                    }
                  double tempamt=0.0;
                  List<TokenElements> tempList=new ArrayList<TokenElements>();
                  int flag=-1;
                  int k=0;
                  //start from first the token with insufficient stock
                  for(int j=i;j<teleList.size();j++){
                      TokenElements token=teleList.get(j);
                      tempList=new ArrayList<TokenElements>();
                      double amount=token.getQty()*token.getRate();
                    for(int l=k;l<pdtList.size();l++){//k indicates
                        flag=-1;
                        TokenElements pele=pdtList.get(l);
                        if(token.getRate()==pele.getRate()){
                            if(teleList.get(j).getProductID().equals(pele.getProductID())){
                               flag=l;
                               k=l;
                               tempList.add(token);
                               //break;
                            }else if(pele.getQty()>0){
                              //tempList.add(pele);
                            }
                        }
                        if(flag>-1){
                           if(tempList.size()>0){//if product with same rate exist
                              int qty=token.getQty();
                              //finalList.remove(token);
                              for(TokenElements ele:tempList){
                               if(ele.getQty()>0 && ele.getRate()>0.0){
                                 if(qty>ele.getQty() ){
                                     qty-=ele.getQty();
                                     tokenValue+=(ele.getQty()*ele.getRate());
                                     amount-=(qty*ele.getRate());
                                     finalList.add(ele);
                                 }else{
                                     
                                     tokenValue+=(qty*ele.getRate());
                                     amount-=(qty*ele.getRate());
                                     ele.setQty(qty);
                                     qty=0;
                                     finalList.add(ele);
                                     break;
                                 }
                                  }
                              }
                              if(qty>0){
                                   amount=calc(amount, flag);
                              }

                           }else{
                               amount=calc(amount,  flag);
                           }
                        }
                        if(amount<=0){
                          break;
                        }
                    }
                  }
               }
    }
    private double calc(double amount,int index){
           for(int m=index;m<pdtList.size();m++){
               TokenElements ele=pdtList.get(m);
               if(ele.getQty()>0 && ele.getRate()<=amount && ele.getRate()>0){
                  if(amount>(ele.getQty()*ele.getRate())){
                         amount-=(ele.getQty()*ele.getRate());
                         tokenValue+=(ele.getQty()*ele.getRate());
                         finalList.add(ele);
                   }else{
                         double val=0;
                         int qtyval=0;
                         if(ele.getRate()>0){
                                qtyval=new Double(amount/ele.getRate()).intValue();
                                val=amount%ele.getRate();
                          }
                          if(val>0){
                                amount=val;
                                ele.setQty(qtyval);
                                tokenValue+=(qtyval*ele.getRate());
                                finalList.add(ele);
                           }else{
                                amount=0;
                                ele.setQty(qtyval);
                                tokenValue+=(qtyval*ele.getRate());
                                finalList.add(ele);
                                break;
                             }
                           }
                        }
         }
           return amount;
    }
    private void generateTokenSequence(){
         try{
               List<TokenElements> teleList=new PreparedSentence(m_App.getSession(), "SELECT P.NAME,TD.PDTID,P.PRICESELL,TD.QTY FROM TOKENCOMBINATIONDETAIL TD JOIN PRODUCTS P ON TD.PDTID=P.ID WHERE TD.PARENT=? ORDER BY P.PRICESELL DESC "
                       , SerializerWriteString.INSTANCE, new SerializerReadClass(TokenElements.class)).list(selectedGame.getTokenref());
               double tokenAmt=0.0;
               for(TokenElements ele:teleList){
                   tokenAmt+=(ele.getRate()*ele.getQty());
               }
               tokenValue=0.0;
               Object[] obj=new Object[teleList.size()];
               Datas[] wdatas=new Datas[teleList.size()];
               Datas[] rdatas=new Datas[teleList.size()];
               String sent=createSentence(teleList,obj,wdatas,rdatas);
               Object[] qtys=(Object[])new PreparedSentence(m_App.getSession(), sent
                             , new SerializerWriteBasic(wdatas),new SerializerReadBasic(rdatas) ).find(obj);
               calculate(teleList, qtys);
               if(tokenAmt!=tokenValue){
                   double amt=tokenAmt-tokenValue;
                    calc(amt, 0);
               }
               jTextField4.setText(tokenValue+"");
               if(tokenValue!=tokenAmt){
                   JOptionPane.showMessageDialog(null, "Suggested token amount is less than Original value ");
               }

           }catch(Exception e){
              e.printStackTrace();
           }
    }
   /* public void setCustomerInfo(String id,String name,String searchkey){
        customerInfo=new CustomerInfo(id);
        customerInfo.setName(name);
        customerInfo.setSearchkey(searchkey);
    }*/
    public void setActivePlayer(PlayeresData player){
        this.player=player;
        customerInfo=new CustomerInfo(player.getMemberID());
        customerInfo.setName(player.getMemberName());
        customerInfo.setSearchkey(player.getMemberNo());
        customerInfo.setGuestName(player.getGuestName());
    }
    public void activate(CardsRoomMain panel,GameInfoExt selectedGame,GameLog glog,int mode) throws BasicException {
       this.panel=panel;
       this.gameID=selectedGame.getID();
       this.selectedGame=selectedGame;
       this.gameLog=glog;
       this.mode=mode;
       jButton4.setText("Generate TokenSequence");

       if(mode==0 && selectedGame.getTokenref()!=null){
          generateTokenSequence();
       }else{
           jTextField2.setText(customerInfo.getName());
           memno.setText(customerInfo.getSearchkey());
           cmodel=new ComboBoxValModel(getGuestList(customerInfo.getId()));
           jComboBox1.setModel(cmodel);
           if(customerInfo.getGuestName()!=null){
               cmodel.setSelectedItem(customerInfo.getGuestName());
           //jComboBox1.setSelectedItem(customerInfo.getGuestName());
           }else
               jComboBox1.setSelectedIndex(-1);
           if(selectedGame.getTokenref()==null){
              jButton4.setEnabled(false);
           }//else{
              
          // }
           getIssuedTokens();
          m_cat.removeActionListener(catalogListener);
          memno.setEditable(false);
          jTextField2.setEditable(false);
          jButton1.setEnabled(false);
          jComboBox1.setEnabled(false);
       }
       List<CategoryInfo> cinfolist=new ArrayList();
       Object[] obj=(Object[]) new StaticSentence(m_App.getSession(),
                "SELECT PRCATEGORIES FROM PEOPLE WHERE ID = ?"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(m_App.getAppUserView().getUser().getId());
        String[] prcat;
        if(obj!=null && obj[0]!=null){
            prcat=obj[0].toString().split("#");
            CategoryInfo temp=new CategoryInfo();
            int t=0;
            for(int i=0;i<prcat.length;i++){
              CategoryInfo obj1=(CategoryInfo) new StaticSentence(m_App.getSession(),
                                 "SELECT ID,NAME,PARENTID,IMAGE FROM CATEGORIES WHERE ID = ?"
                                 , SerializerWriteString.INSTANCE,new SerializerReadClass(CategoryInfo.class)).find(prcat[i]);
             if(obj1!=null){
                        temp=obj1;
                         cinfolist.add(obj1);
                         t=1;
               }
              }
          }
          m_cat.loadCatalog(cinfolist);
          setValues();
    }

    private void setValues(){
      if(mode==0){
       cmodel=new ComboBoxValModel();
       jComboBox1.setModel(cmodel);
       memno.setText(null);
       jTextField2.setText(null);
       jTextField3.setText(null);
       //jCheckBox1.setSelected(true);
       
       jTable1=new JTable();
       jScrollPane1.getViewport().setView(jTable1);
       jTable1.setModel(getTableModel());
       }
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        jLabel1.setText("Member No");
        jLabel2.setText("Member Name");
        jLabel3.setText("Guest Name");
        //jLabel6.setText("Is Jack");
        jLabel4.setText("Token Detail");
        jLabel5.setText("Total");
        jButton2.setText("Save");
        jButton3.setText("Enter");
        jButton1.setText("Search");
        jTextField4.setEditable(false);
        m_App=app;
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        dmang=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        dlCustomers = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomersCreate");
        
        m_cat = new JCatalog(m_dlSales);
        m_cat.getComponent().setPreferredSize(new Dimension(0, 245));
        catalogListener=new CatalogListener();
        m_cat.addActionListener(catalogListener);
        component=m_cat.getComponent();

        jPanel4.add(component, BorderLayout.CENTER);
    }

    public Object getBean() {
        return this;
    }

    public void activate() throws BasicException {
        jButton4.setEnabled(false);
        jTextField2.setText(null);
        memno.setText(null);
        jComboBox1.setSelectedIndex(-1);
        status=1;
        finalList=new ArrayList<TokenElements>();
        TokenElements ele=new TokenElements(null, null, 0.0, 0);
        finalList.add(ele);
        List<CategoryInfo> cinfolist=new ArrayList();
       Object[] obj=(Object[]) new StaticSentence(m_App.getSession(),
                "SELECT PRCATEGORIES FROM PEOPLE WHERE ID = ?"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(m_App.getAppUserView().getUser().getId());
        String[] prcat;
        if(obj!=null && obj[0]!=null){
            prcat=obj[0].toString().split("#");
            CategoryInfo temp=new CategoryInfo();
            int t=0;
            for(int i=0;i<prcat.length;i++){
              CategoryInfo obj1=(CategoryInfo) new StaticSentence(m_App.getSession(),
                                 "SELECT ID,NAME,PARENTID,IMAGE FROM CATEGORIES WHERE ID = ?"
                                 , SerializerWriteString.INSTANCE,new SerializerReadClass(CategoryInfo.class)).find(prcat[i]);
             if(obj1!=null){
                        temp=obj1;
                         cinfolist.add(obj1);
                         t=1;
               }
              }
          }
        m_cat.loadCatalog(cinfolist);
        jTable1=new JTable();
        jTable1.setModel(getTableModel());
        jTable1.setRowSelectionInterval(0, 0);
        jScrollPane1.getViewport().setView(jTable1);
    }

     private class CatalogListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int row=jTable1.getSelectedRow();
            if(row>-1){
            int column=jTable1.getSelectedColumn();
            double amt=0.0;
            ProductInfoExt pinfo=(ProductInfoExt)e.getSource();
            TokenElements tEle=finalList.get(row);
            tEle.setProductName(pinfo.getName());
            tEle.setProductID(pinfo.getID());
            tEle.setRate(pinfo.getPriceSell());
            jTable1.setModel(getTableModel());
            jTable1.setRowSelectionInterval(row, row);
            for(TokenElements ele:finalList){
             amt+=ele.getTotalAmount();
            }
            jTextField4.setText(amt+"");
          }
        }
    }

    private AbstractTableModel getTableModel(){
        return new AbstractTableModel() {

            public int getRowCount() {
                return finalList.size();
            }

            public int getColumnCount() {
                return Header.length;
            }

            @Override
            public String getColumnName(int column) {
                return Header[column];
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                TokenElements ts=finalList.get(rowIndex);
                switch(columnIndex){
                    case 0  : return ts.getProductName();
                    case 1  : return ts.getQty();
                    case 2  : return ts.getRate();
                    case 3  : return ts.getTotalAmount();
                    default : return null;
                }
            }
        };
    }

     private void equals(){
      if(operator==1 && status1==0)
             caltotal +=Double.parseDouble(jTextField2.getText());
      else if(operator==2 && status1==0)
             caltotal -=Double.parseDouble(jTextField2.getText());
      else if(operator==3 && status1==0)
             caltotal *=Double.parseDouble(jTextField2.getText());
      else if(operator==0 )
          caltotal =Double.parseDouble(jTextField2.getText());
       jTextField3.setText(String.valueOf(dmang.roundTwoDecimals(caltotal)));

   }
     private void stateTransition(char cTrans) {
        if (cTrans == '\u007f') {
           jTextField3.setText(null);
           caltotal=0;
           operator=0;
           status1= 1;
        } else if (cTrans == '+' ) {
           equals();
           jTextField3.setText(String.valueOf(caltotal));
           operator =1;
           status1=1;
        } else if (cTrans == '-'){
             equals();
             jTextField3.setText(null);
             jTextField3.setText(String.valueOf(caltotal));
             operator= 2;
             status1=1;
        } else if (cTrans == '*'){
             equals();
             jTextField3.setText(null);
             jTextField3.setText(String.valueOf(caltotal));
             operator= 3;
             status1=1;
        }
        else if ( cTrans == '=') {
            equals();
            operator=0;
            caltotal=0;
            status1=1;
        } else {
             if(status1==1)
                 jTextField3.setText( String.valueOf(cTrans));
            else
                 jTextField3.setText( jTextField3.getText() + cTrans);
             status1=0;
        }
    }

    private List<String> getGuestList(String memid) throws BasicException{
       List<String> list=new ArrayList<String>();
       Map<String,GeneralSettingInfo> gs=LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
       GeneralSettingInfo sinfo=gs.get("CardsRoomGuestCategories");
       String data=sinfo.getValue();
       List<String> guestcatList=new ArrayList<String>();
       if(data!=null){
          String[] arr=data.split("#:#");
          for(int i=0;i<arr.length;i++){
             guestcatList.add(arr[i]);
          }
          list=dmang.getGuestDetail(guestcatList,memid);
       }
       return list;
    }
    private void getIssuedTokens() throws BasicException{
            tokenCombList.clear();
            List<Object[]> objList=dmang.getTokenCombinationDetailByTokenLogID(player.getTokenLogID());
            String tokenLine="";
            String combName="";
            double amount=0.0,total=0.0;
            List<TokenElements> tele=new ArrayList<TokenElements>();
            for(Object[] obj:objList){
                if(tokenLine.equals(String.valueOf(obj[9]))){
                    tokenLine=String.valueOf(obj[9]);
                    combName=String.valueOf(obj[1]);
                    tele.add(new TokenElements(String.valueOf(obj[5]),String.valueOf(obj[4]), Double.valueOf(String.valueOf(obj[7])),  Integer.valueOf(String.valueOf(obj[6]))));
                    amount+=Double.valueOf(String.valueOf(obj[2]));
                    total+=Double.valueOf(String.valueOf(obj[2]));
                }else{
                    if(!tokenLine.equals("")){

                     TokenCombinationInfo tokenInfo=new TokenCombinationInfo(tokenLine, combName,amount, tele);
                     tokenCombList.add(tokenInfo);
                     map.put(tokenLine, tokenInfo);
                     amount=0;
                     //total+=Double.valueOf(String.valueOf(obj[2]));
                     //amount=0;
                    }
                    tokenLine=String.valueOf(obj[9]);
                    combName=String.valueOf(obj[1]);
                    amount+=Double.valueOf(String.valueOf(obj[2]));
                    total+=Double.valueOf(String.valueOf(obj[2]));
                    tele.clear();
                    tele.add(new TokenElements(String.valueOf(obj[5]),String.valueOf(obj[4]), Double.valueOf(String.valueOf(obj[7])),  Integer.valueOf(String.valueOf(obj[6]))));
                }

            }
            if(!tokenLine.equals("")){
                //amount+=Double.valueOf(String.valueOf(obj[2]));
                     TokenCombinationInfo tokenInfo=new TokenCombinationInfo(tokenLine, combName, amount, tele);
                     tokenCombList.add(tokenInfo);
                     map.put(tokenLine, tokenInfo);
                     //amount=0;
            }
            TokenCombTemp temp=new TokenCombTemp(tokenCombList,"Token Combinations");
            player.setTokenCombCnt(tokenCombList.size());
            treetable=new JTreeTable(new treetableModel(temp));
            jScrollPane1.getViewport().setView(treetable);
            jTextField4.setText(total+"");
    }

    private class treetableModel extends AbstractTreeTableModel implements TreeTableModel{
       // return new AbstractTreeTableModel(root) {

            public int getColumnCount() {
               return Header1.length;
            }
            public treetableModel(Object data) {
              super(data);
            }
            public String getColumnName(int column) {
                return Header1[column];
            }
            Class[] types = new Class [] {
                  TreeTableModel.class, java.lang.String.class, java.lang.String.class,java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
         boolean[] canEdit = new boolean [] {
                 true, false, false, false, false,false
            };
             @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }


        @Override
            public boolean isCellEditable(Object node, int columnIndex) {
                return canEdit [columnIndex];
            }
            public Object getValueAt(Object node, int column) {
                if(node instanceof TokenCombinationInfo){
                   TokenCombinationInfo tifo=(TokenCombinationInfo) node;
                   switch(column){
                       case 0: return tifo.getTokenName();
                       case 3: return tifo.getTotalVAlue();
                       default : return null;
                   }
                }else if(node instanceof TokenElements){
                   TokenElements tele=(TokenElements) node;
                   switch(column){
                       case 0: return tele.getProductName();
                       case 1: return tele.getQty();
                       case 2: return tele.getRate();
                       default : return null;
                   }
                }else if(node instanceof TokenCombTemp){
                   TokenCombTemp tifo=(TokenCombTemp) node;
                   switch(column){
                       case 0  :return tifo.getName();
                       default :return null;
                   }
                }else
                    return null;

            }

            public Object getChild(Object parent, int index) {
                if(parent instanceof TokenCombinationInfo){
                   TokenCombinationInfo tifo=(TokenCombinationInfo) parent;
                   return tifo.getTokenElements().get(index);
                }else if(parent instanceof TokenCombTemp){
                   TokenCombTemp tifo=(TokenCombTemp) parent;
                   return tifo.getTokenList().get(index);
                }else
                    return null;
            }

            public int getChildCount(Object parent) {
                if(parent instanceof TokenCombinationInfo){
                   TokenCombinationInfo tifo=(TokenCombinationInfo) parent;
                   return tifo.getTokenElements().size();
                }else if(parent instanceof TokenCombTemp){
                   TokenCombTemp tifo=(TokenCombTemp) parent;
                   return tifo.getTokenList().size();
                }else
                    return 0;
            }
     }

    private class TokenCombTemp{
        private List<TokenCombinationInfo> tlist;
        private String name;
        public TokenCombTemp(List<TokenCombinationInfo> tlist,String name) {
            this.tlist = new ArrayList<TokenCombinationInfo>(tlist);
            this.name=name;
        }
        public String getName(){
          return name;
        }
        public List<TokenCombinationInfo> getTokenList(){
          return tlist;
        }

        @Override
        public String toString() {
            return name;
        }

     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        memno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jNumberKeys1 = new com.openbravo.beans.JNumberKeys();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();

        jPanel1.setLayout(null);

        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 10, 70, 14);

        memno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                memnoKeyReleased(evt);
            }
        });
        jPanel1.add(memno);
        memno.setBounds(90, 10, 190, 20);

        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(290, 10, 90, 14);
        jPanel1.add(jTextField2);
        jTextField2.setBounds(390, 10, 170, 20);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/kuser.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(570, 10, 50, 25);

        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 40, 70, 20);

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(540, 40, 90, 23);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(90, 40, 190, 20);

        jPanel2.setLayout(null);

        jPanel3.setLayout(null);
        jPanel3.add(jTextField3);
        jTextField3.setBounds(10, 230, 100, 20);

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3);
        jButton3.setBounds(120, 230, 100, 23);

        jNumberKeys1.addJNumberEventListener(new com.openbravo.beans.JNumberEventListener() {
            public void keyPerformed(com.openbravo.beans.JNumberEvent evt) {
                jNumberKeys1KeyPerformed(evt);
            }
        });
        jPanel3.add(jNumberKeys1);
        jNumberKeys1.setBounds(10, 10, 210, 210);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(410, 10, 230, 260);

        jLabel4.setText("jLabel4");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 10, 160, 14);

        jLabel5.setText("jLabel5");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(190, 250, 100, 14);
        jPanel2.add(jTextField4);
        jTextField4.setBounds(299, 250, 100, 20);

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);
        jButton4.setBounds(10, 250, 160, 23);
        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 30, 390, 210);

        jPanel4.setLayout(new java.awt.BorderLayout());

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 278, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      if(gameLog.getPalyersList().size()<selectedGame.getMax()){
        JCustomerFinder finder = JCustomerFinder.getCustomerFinder(this, dlCustomers);
        finder.setVisible(true);
        customerInfo = finder.getSelectedCustomer();
        if (customerInfo != null) {
            try {
                jTextField2.setText(customerInfo.toString());
                memno.setText(customerInfo.getSearchkey());
                cmodel=new ComboBoxValModel(getGuestList(customerInfo.getId()));
               jComboBox1.setModel(cmodel);
               jComboBox1.setSelectedIndex(-1);
            } catch (Exception e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                msg.show(this);
            }
        }
      }else{
                 JOptionPane.showMessageDialog(null, "The Maximum players for the game is met", "Cannot add new players", status1);
      }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      try{
           Boolean flag=dmang.checkForMembersAlreadyAdded(customerInfo.getId());
          if(flag==false)
          {
          if(status==0){
          if(customerInfo!=null && !(jScrollPane1.getViewport().getView() instanceof JTreeTable) ){
          Transaction t=new Transaction(m_App.getSession()) {

                @Override
                protected Object transact() throws BasicException {
                   if(customerInfo!=null){
                        String tokenLogID=null;
                        String CloseCashRef =null;
                        Date crdate=new Date();
                        String crby=m_App.getAppUserView().getUser().getName();
                       // String play = player.getTokenLogID();
                        if(player == null){
                           tokenLogID=UUID.randomUUID().toString();
                           line=1;
                         }else{
                              tokenLogID=player.getTokenLogID();
                              line=player.getTokenCombCnt()+1;
                         }
                       
                     List<TokenElements> tokenList=new ArrayList<TokenElements>();
                    if(player==null){
                       Object[] obj=new Object[]{tokenLogID,customerInfo.getId(),Double.valueOf(jTextField4.getText()),crdate,crby,gameLog.getID(),0,customerInfo.getGuestName()};
                       dmang.insertIntoTokenLog(obj);
                       gameLog.getPalyersList().add(new PlayeresData(customerInfo.getName(),customerInfo.getId(),tokenList,Double.valueOf(jTextField4.getText()),customerInfo.getGuestName(),tokenLogID,customerInfo.getSearchkey()));
                       player=new PlayeresData(customerInfo.getName(), customerInfo.getId(), new ArrayList<TokenElements>(), Double.valueOf(jTextField4.getText()),customerInfo.getGuestName(), tokenLogID, customerInfo.getSearchkey());
                       if(selectedGame.isCCperPlayer()){
                           dmang.updateGamesPlayercountWithCC(new Object[]{1,1,gameLog.getClubCollection(),gameLog.getID()});
                       }else{
                           dmang.updateGamesPlayercount(new Object[]{1,gameLog.getID()});
                       }
                       for(TokenElements tele:finalList){
                         if(tele.getProductID()!=null){

                            Object[] obj1=new Object[]{UUID.randomUUID().toString(),tokenLogID,tele.getProductID(),tele.getRate(),tele.getQty(),line};
                            dmang.insertIntoTokenLogDetail(obj1);
                            String a="out";
                             Object[] obj2=new Object[]{UUID.randomUUID().toString(),tele.getProductID(),tele.getRate(),tele.getQty(),a};
                             dmang.insertIntoTokenStockDetail(obj2);
                            TokenElements t=new TokenElements(tele.getProductID(), tele.getProductName(), tele.getRate(), tele.getQty());
                            t.setLine(line);
                            //player.getTokenList().add(t);
                            tokenList.add(t);
                            int cnt=m_dlSales.updateStockVolume3(Double.valueOf(String.valueOf(tele.getQty())), tele.getProductID());
                            if(cnt==0){
                                JOptionPane.showMessageDialog(null, "No Stock ***"+tele.getProductName()+"***", "No Stock", JOptionPane.ERROR_MESSAGE);
                               throw new BasicException();
                            }
                         }
                        //amount+=tele.getTotalAmount();
                     }
                     
                      }else{
                        Object[] obj=new Object[]{Double.valueOf(jTextField4.getText()),tokenLogID};
                        dmang.updateTokenLog(obj);
                        for(TokenElements tele:finalList){
                         if(tele.getProductID()!=null){

                            Object[] obj1=new Object[]{UUID.randomUUID().toString(),tokenLogID,tele.getProductID(),tele.getRate(),tele.getQty(),line};
                            dmang.insertIntoTokenLogDetail(obj1);
                            String a="out";
                             Object[] obj2=new Object[]{UUID.randomUUID().toString(),tele.getProductID(),tele.getRate(),tele.getQty(),a};
                             dmang.insertIntoTokenStockDetail(obj2);
                            TokenElements t=new TokenElements(tele.getProductID(), tele.getProductName(), tele.getRate(), tele.getQty());
                            t.setLine(line);
                            //player.getTokenList().add(t);
                            tokenList.add(t);
                            int cnt=m_dlSales.updateStockVolume3(Double.valueOf(String.valueOf(tele.getQty())), tele.getProductID());
                            if(cnt==0){
                                JOptionPane.showMessageDialog(null, "No Stock ***"+tele.getProductName()+"***", "No Stock", JOptionPane.ERROR_MESSAGE);
                               throw new BasicException();
                            }
                         }
                        //amount+=tele.getTotalAmount();
                     }
                      }


                   
                   }

                  
                     activate(panel, selectedGame, gameLog, mode);
                     
                     return null;
                }
            };
            t.execute();
            panel.func();
          }
          }else{
              Transaction t1=new Transaction(m_App.getSession()) {

                    @Override
                    protected Object transact() throws BasicException {
                        Date crdate=new Date();
                        String crby=m_App.getAppUserView().getUser().getName();
               //         if(player==null){
                        String tokenLogID=UUID.randomUUID().toString();
                        Object[] obj=new Object[]{tokenLogID,customerInfo.getId(),Double.valueOf(jTextField4.getText()),crdate,crby,null,1,customerInfo.getGuestName()};
                         dmang.insertIntoTokenLog(obj);
                        for(TokenElements tele:finalList){
                           if(tele.getProductID()!=null){
                               obj=new Object[]{UUID.randomUUID().toString(),tokenLogID,tele.getProductID(),tele.getRate(),tele.getQty(),line};
                               dmang.insertIntoTokenLogDetail(obj);
                               String a="out";
                             Object[] obj1=new Object[]{UUID.randomUUID().toString(),tele.getProductID(),tele.getRate(),-tele.getQty(),a};
                             dmang.insertIntoTokenStockDetail(obj1);
                            //TokenElements t=new TokenElements(tele.getProductID(), tele.getProductName(), tele.getRate(), tele.getQty());
                            //t.setLine(line);
                            //player.getTokenList().add(t);
                           // tokenList.add(t);
                                m_dlSales.updateStockVolume1(Double.valueOf(String.valueOf(tele.getQty())), tele.getProductID());

                         }
                           memno.setText(null);
                           jTextField2.setText(null);
                           finalList=new ArrayList<TokenElements>();
                            TokenElements ele=new TokenElements(null, null, 0.0, 0);
                            finalList.add(ele);
                            jTable1.setModel(getTableModel());
                        //amount+=tele.getTotalAmount();
                     }
                        return null;
                    }
                   // return null;
                };
                t1.execute();
               
          }
          }
          else{
               JOptionPane.showMessageDialog(null, "Member Already playing","press ok to continue", JOptionPane.ERROR_MESSAGE);
          }
      }catch(Exception e){
         //JOptionPane.showMessageDialog(null, "");
          e.printStackTrace();
      }
    // }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void memnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memnoKeyReleased
         if(evt.getKeyText(evt.getKeyCode()).equals("Enter")){

            try{
               Object[] obj= dmang.getMamberbySkey(memno.getText().toUpperCase());
            if(obj==null){
                JOptionPane.showMessageDialog(this,AppLocal.getIntString("message.custnotpresent"), AppLocal.getIntString("message.custtitle"), JOptionPane.WARNING_MESSAGE);
            }else{
            if(status==0){
              if(gameLog.getPalyersList().size()<selectedGame.getMax()){
               customerInfo=new CustomerInfo(obj[0].toString());
               customerInfo.setName(obj[1].toString());
               customerInfo.setSearchkey(memno.getText().toUpperCase());
               customerInfo.setMobile(String.valueOf(obj[3]));
               jTextField2.setText(obj[1].toString());
               cmodel=new ComboBoxValModel(getGuestList(customerInfo.getId()));
               jComboBox1.setModel(cmodel);
               jComboBox1.setSelectedIndex(-1);
              }else{
                 JOptionPane.showMessageDialog(null, "The Maximum players for the game is met", "Cannot add new players", status1);
              }
            }else{
                 customerInfo=new CustomerInfo(obj[0].toString());
               customerInfo.setName(obj[1].toString());
               customerInfo.setSearchkey(memno.getText().toUpperCase());
               customerInfo.setMobile(String.valueOf(obj[3]));
               jTextField2.setText(obj[1].toString());
               cmodel=new ComboBoxValModel(getGuestList(customerInfo.getId()));
               jComboBox1.setModel(cmodel);
               jComboBox1.setSelectedIndex(-1);
            }
            }
           }
          catch(Exception e){
          }
        }else{
            jTextField2.setText(null);
            customerInfo=null;
            cmodel=new ComboBoxValModel();
            jComboBox1.setModel(cmodel);
        }
}//GEN-LAST:event_memnoKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      try{
         int row=jTable1.getSelectedRow();
         if(row>=0){
            TokenElements tele=finalList.get(row);
            if(!tele.getProductName().equals("") && jTextField3.getText().length()>0){
             double totalval=Double.parseDouble(jTextField4.getText());
             totalval=totalval-tele.getTotalAmount();
             tele.setQty(Integer.parseInt(jTextField3.getText()));
             //tele.setTotalAmount(tele.getQty()*tele.getRate());
             finalList.remove(row);
             finalList.add(row, tele);
             totalval=totalval+tele.getTotalAmount();
             jTextField4.setText(Formats.ConvertDoubleToString(totalval));
             if(row==jTable1.getRowCount()-1){
                 finalList.add(new TokenElements());
             }
             jTable1.setModel(getTableModel());
             jTable1.setRowSelectionInterval(row+1, row+1);
             jTextField3.setText(null);
            }
        }
       }catch(NumberFormatException e){
           JOptionPane.showMessageDialog(this, "Please enter a valid number", "Error Occured", JOptionPane.ERROR_MESSAGE);
       }catch(Exception e){
       }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      try{
        if(jScrollPane1.getViewport().getView() instanceof JTreeTable){
            generateTokenSequence();
            jButton4.setText("List Of Issued Token");
            m_cat.addActionListener(catalogListener);
            if(jTable1==null)
               jTable1=new JTable();
               jTable1.setModel(getTableModel());
               jScrollPane1.getViewport().setView(jTable1);
        }else
          if(jScrollPane1.getViewport().getView() instanceof JTable){
            
            if(mode==0){
                jButton4.setText("Generate TokenSequence");
                m_cat.removeActionListener(catalogListener);
                getIssuedTokens();
            }else{
                jButton4.setText("List Of Issued Token");
                m_cat.addActionListener(catalogListener);
               generateTokenSequence();
               if(jTable1==null)
               jTable1=new JTable();
               jTable1.setModel(getTableModel());
               jScrollPane1.getViewport().setView(jTable1);
            }
        }
      }catch(Exception e){
          e.printStackTrace();
      }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jNumberKeys1KeyPerformed(com.openbravo.beans.JNumberEvent evt) {//GEN-FIRST:event_jNumberKeys1KeyPerformed
         stateTransition(evt.getKey());
    }//GEN-LAST:event_jNumberKeys1KeyPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if(jComboBox1.getSelectedIndex()!=-1 && customerInfo!=null && jComboBox1.getSelectedItem()!=null){
            customerInfo.setGuestName(jComboBox1.getSelectedItem()+"");
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private com.openbravo.beans.JNumberKeys jNumberKeys1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField memno;
    // End of variables declaration//GEN-END:variables



}
