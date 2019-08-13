
package com.openbravo.pos.CardsRoom;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.catalog.CatalogSelector;
import com.openbravo.pos.catalog.JCatalog;
import com.openbravo.pos.clubmang.AbstractTreeTableModel;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.JTreeTable;
import com.openbravo.pos.clubmang.TreeTableModel;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.ProductInfoExt;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Object;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.swing.ComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class TokenCombination extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    private double caltotal;
    private int status;
    private int operator;
    private DataLogicFacilities dmang;
    private AppView m_App;
    private CatalogSelector m_cat;
    private DataLogicSales m_dlSales;
    private Component component;
    private ComboBoxModel wmodel;
    private List<TokenElements> tokenEleList;
    private String[] Header=new String[]{"Product Name","Qty","Rate","Amount"};
    private String[] Header1=new String[]{"Combination Name","Product Name","Qty","Rate","Total"};
    private Map<String,TokenCombinationInfo> map=new HashMap<String,TokenCombinationInfo>();
    private List<TokenCombinationInfo> tokenCombList=new ArrayList<TokenCombinationInfo>();
    private JTreeTable treeTable;
    public TokenCombination() {
        initComponents();
    }
    public String getTitle() {
        return "Create Token Combination";
    }

    public void activate() throws BasicException {
      // wmodel=new ComboBoxValModel();
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
       jTextField1.setText("0.0");
       jTextField2.setText(null);
       name.setText(null);
       tokenEleList.clear();
       tokenEleList.add(new TokenElements());
       jTable1.setModel(getTableModel());
       jTable1.setRowSelectionInterval(0, 0);
       jImageEditor1.setImage(null);
    }
    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        m_App=app;
        jTabbedPane1.setTitleAt(0,"Create New");
        jTabbedPane1.setTitleAt(1,"List View");
        jTabbedPane1.setSelectedIndex(0);
        dmang=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        tokenEleList=new ArrayList<TokenElements>();
        m_cat = new JCatalog(m_dlSales);
        m_cat.getComponent().setPreferredSize(new Dimension(0, 245));
        m_cat.addActionListener(new CatalogListener());
        component=m_cat.getComponent();
        jPanel3.add(component, BorderLayout.CENTER);
        jButton1.setText("Enter");
        jButton2.setText("Save");
        jLabel1.setText("Name");
        jLabel2.setText("Detail List");
        jLabel3.setText("Total");
        jTextField1.setEditable(false);
        jLabel5.setText("Image");
    }

     private class CatalogListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int row=jTable1.getSelectedRow();
            int column=jTable1.getSelectedColumn();
            ProductInfoExt pinfo=(ProductInfoExt)e.getSource();
            TokenElements tEle=tokenEleList.get(row);
            tEle.setProductName(pinfo.getName());
            tEle.setProductID(pinfo.getID());
            tEle.setRate(pinfo.getPriceSell());
            jTable1.setModel(getTableModel());
            jTable1.setRowSelectionInterval(row, row);
        }
    }

    public Object getBean() {
        return this;
    }
    private void equals(){
      if(operator==1 && status==0)
             caltotal +=Double.parseDouble(jTextField2.getText());
      else if(operator==2 && status==0)
             caltotal -=Double.parseDouble(jTextField2.getText());
      else if(operator==3 && status==0)
             caltotal *=Double.parseDouble(jTextField2.getText());
      else if(operator==0 )
          caltotal =Double.parseDouble(jTextField2.getText());
       jTextField2.setText(String.valueOf(dmang.roundTwoDecimals(caltotal)));

   }
     private void stateTransition(char cTrans) {
        if (cTrans == '\u007f') {
           jTextField2.setText(null);
           caltotal=0;
           operator=0;
           status= 1;
        } else if (cTrans == '+' ) {
           equals();
           jTextField2.setText(String.valueOf(caltotal));
           operator =1;
           status=1;
        } else if (cTrans == '-'){
             equals();
             jTextField2.setText(null);
             jTextField2.setText(String.valueOf(caltotal));
             operator= 2;
             status=1;
        } else if (cTrans == '*'){
             equals();
             jTextField2.setText(null);
             jTextField2.setText(String.valueOf(caltotal));
             operator= 3;
             status=1;
        }
        else if ( cTrans == '=') {
            equals();
            operator=0;
            caltotal=0;
            status=1;
        } else {
             if(status==1)
                 jTextField2.setText( String.valueOf(cTrans));
            else
                 jTextField2.setText( jTextField2.getText() + cTrans);
             status=0;
        }
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
                       case 4: return tifo.getTotalVAlue();
                       default : return null;
                   }
                }else if(node instanceof TokenElements){
                   TokenElements tele=(TokenElements) node;
                   switch(column){
                       case 1: return tele.getProductName();
                       case 2: return tele.getQty();
                       case 3: return tele.getRate();
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
                   //System.out.println(tifo.getTokenElements().size()+"");
                   return tifo.getTokenElements().size();
                }else if(parent instanceof TokenCombTemp){
                   TokenCombTemp tifo=(TokenCombTemp) parent;
                  // System.out.println(tifo.getTokenList().size()+"");
                   return tifo.getTokenList().size();
                }else
                    return 0;
            }
       // };
     }
     private AbstractTableModel getTableModel(){
         return new AbstractTableModel() {

            public int getRowCount() {
                return tokenEleList.size();
            }

            public int getColumnCount() {
                return Header.length;
            }

            @Override
            public String getColumnName(int column) {
                return Header[column];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                TokenElements tEle=tokenEleList.get(rowIndex);
                switch(columnIndex){
                    case 1: tEle.setQty(Integer.parseInt(String.valueOf(aValue)));
                            //tEle.setTotalAmount(tEle.getQty()* tEle.getRate());
                            break;
                   /* case 2: tEle.setRate(Integer.parseInt(String.valueOf(aValue)));
                           tEle.setTotalAmount(tEle.getQty()* tEle.getRate());
                            break;
                    case 3: tEle.setTotalAmount(Integer.parseInt(String.valueOf(aValue)));
                            tEle.setTotalAmount(tEle.getQty()* tEle.getRate());
                            break;*/
                }
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                 TokenElements tEle=tokenEleList.get(rowIndex);
                 switch(columnIndex){
                     case 0 : return tEle.getProductName();
                     case 1 : return tEle.getQty();
                     case 2 : return tEle.getRate();
                     case 3 : return tEle.getTotalAmount();
                     default : return null;
                 }
            }
        };
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jNumberKeys1 = new com.openbravo.beans.JNumberKeys();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jImageEditor1 = new com.openbravo.data.gui.JImageEditor();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();

        setLayout(null);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel4.setLayout(null);

        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel4.add(jPanel3);
        jPanel3.setBounds(60, 400, 660, 240);

        jLabel1.setText("jLabel1");

        name.setNextFocusableComponent(jTextField2);

        jLabel2.setText("jLabel2");

        jButton1.setText("jButton1");
        jButton1.setNextFocusableComponent(jTextField2);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField2.setNextFocusableComponent(jButton1);

        jNumberKeys1.addJNumberEventListener(new com.openbravo.beans.JNumberEventListener() {
            public void keyPerformed(com.openbravo.beans.JNumberEvent evt) {
                jNumberKeys1KeyPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jTextField2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                        .add(18, 18, 18)
                        .add(jButton1))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jNumberKeys1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .add(jNumberKeys1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jTextField2)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setText("jLabel3");

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jImageEditor1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jImageEditor1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 113, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("jLabel5");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("jRadioButton1");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("jRadioButton2");

        jLabel6.setText("Make Combi");

        jLabel7.setText("Return");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 61, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(name, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 241, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(32, 32, 32)
                                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(57, 57, 57)))
                        .add(18, 18, 18)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jRadioButton1, 0, 0, Short.MAX_VALUE)
                            .add(jRadioButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 67, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(58, 58, 58)
                        .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(23, 23, 23))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 358, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 33, Short.MAX_VALUE)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel1)
                        .add(name, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jRadioButton1)
                            .add(jLabel6))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jRadioButton2)
                            .add(jLabel7))))
                .add(2, 2, 2)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel5)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jLabel2))
                            .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel3)
                            .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(29, 29, 29)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel4.add(jPanel1);
        jPanel1.setBounds(0, 0, 660, 390);

        jTabbedPane1.addTab("tab1", jPanel4);

        jLabel4.setText("jLabel4");

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 626, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 246, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 530, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel5);

        add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 0, 670, 680);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
        int row=jTable1.getSelectedRow();
        if(row>=0){
            TokenElements tele=tokenEleList.get(row);
            if(!tele.getProductName().equals("") && jTextField2.getText().length()>0){
             double totalval=Double.parseDouble(jTextField1.getText());
             totalval=totalval-tele.getTotalAmount();
             tele.setQty(Integer.parseInt(jTextField2.getText()));
             tokenEleList.remove(row);
             tokenEleList.add(row, tele);
             totalval=totalval+tele.getTotalAmount();
             jTextField1.setText(String.valueOf(totalval));
             //jTextField1.setText(Formats.ConvertDoubleToString(totalval));
             if(row==jTable1.getRowCount()-1){
                 tokenEleList.add(new TokenElements());
             }
             jTable1.setModel(getTableModel());
             jTable1.setRowSelectionInterval(row+1, row+1);
             jTextField2.setText(null);
            }
        }
       }catch(NumberFormatException e){
           JOptionPane.showMessageDialog(this, "Please enter a valid number", "Error Occured", JOptionPane.ERROR_MESSAGE);
       }catch(Exception e){
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       if(JOptionPane.showConfirmDialog(null, "Do you want to save ?", "Save", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            try {
                Transaction t = new Transaction(m_App.getSession()) {

                    @Override
                    protected Object transact() throws BasicException {
                       try {
                          if(name.getText().length()>0 && tokenEleList.size()>1){
                           String id=UUID.randomUUID().toString();
                           if(jRadioButton1.isSelected()==true)
                           {
                           Object[] obj=new Object[]{id,name.getText().trim(),Formats.ConvertStringToDouble(jTextField1.getText()),m_App.getAppUserView().getUser().getName(),new Date(),true,jImageEditor1.getImage()};
                           dmang.insertTokenCombination(obj);
                           }
                           for(int i=0;i<tokenEleList.size()-1;i++){
                              TokenElements tele=tokenEleList.get(i);
                              if(jRadioButton1.isSelected()==true)
                              {
                              Object[] obj1=new Object[]{UUID.randomUUID().toString(),tele.getProductID(),tele.getQty(),id};
                              dmang.insertTokenCombinationDetail(obj1);
                              }
                              else if(jRadioButton2.isSelected()==true)
                              {
                            String a="in";
                             Object[] obj1=new Object[]{UUID.randomUUID().toString(),tele.getProductID(),tele.getRate(),tele.getQty(),a};
                             dmang.insertIntoTokenStockDetail(obj1);
                              }
                           }
                           setValues();
                          }else{
                             JOptionPane.showMessageDialog(null, "Incomplete from", null, JOptionPane.OK_OPTION);
                          }
                       } catch (Exception ex) {
                           ex.printStackTrace();
                           throw new BasicException();
                       }
                       return null;
                    }
                };
                t.execute();
            } catch (BasicException ex) {
                ex.printStackTrace();
            }
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jNumberKeys1KeyPerformed(com.openbravo.beans.JNumberEvent evt) {//GEN-FIRST:event_jNumberKeys1KeyPerformed
        stateTransition(evt.getKey());
    }//GEN-LAST:event_jNumberKeys1KeyPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        try{
           if(jTabbedPane1.getSelectedIndex()==1){
               tokenCombList.clear();
            List<Object[]> objList=dmang.getTokenCombinationDetail();
            String combID="";
            String combName="";
            double amount=0.0;
            List<TokenElements> tele=new ArrayList<TokenElements>();
            for(Object[] obj:objList){
                if(combID.equals(String.valueOf(obj[0]))){
                    combID=String.valueOf(obj[0]);
                    combName=String.valueOf(obj[1]);
                    tele.add(new TokenElements(String.valueOf(obj[5]),String.valueOf(obj[4]), Double.valueOf(String.valueOf(obj[7])),  Integer.valueOf(String.valueOf(obj[6]))));
                    amount=Double.valueOf(String.valueOf(obj[2]));
                }else{
                    if(!combID.equals("")){
                     TokenCombinationInfo tokenInfo=new TokenCombinationInfo(combID, combName,amount, tele);
                     tokenCombList.add(tokenInfo);
                     map.put(combID, tokenInfo);
                     //amount=0;
                    }
                    combID=String.valueOf(obj[0]);
                    combName=String.valueOf(obj[1]);
                    amount=Double.valueOf(String.valueOf(obj[2]));
                    tele.clear();
                    tele.add(new TokenElements(String.valueOf(obj[5]),String.valueOf(obj[4]), Double.valueOf(String.valueOf(obj[7])),  Integer.valueOf(String.valueOf(obj[6]))));
                }

            }
            if(!combID.equals("")){
                     TokenCombinationInfo tokenInfo=new TokenCombinationInfo(combID, combName, amount, tele);
                     tokenCombList.add(tokenInfo);
                     map.put(combID, tokenInfo);
                     amount=0;
            }
            TokenCombTemp temp=new TokenCombTemp(tokenCombList,"Token Combinations");
            treeTable=new JTreeTable(new treetableModel(temp));
            jScrollPane2.setViewportView(treeTable);
           }
        }catch(Exception e){
           e.printStackTrace();
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.openbravo.data.gui.JImageEditor jImageEditor1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private com.openbravo.beans.JNumberKeys jNumberKeys1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField name;
    // End of variables declaration//GEN-END:variables



}
