/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

//import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
//import com.openbravo.data.loader.BaseSentence;
import com.openbravo.data.loader.SentenceList;
//import com.openbravo.data.user.EditorCreator;
//import com.openbravo.pos.forms.AppLocal;
//import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.reports.JRDataSourceBasic;
import com.openbravo.pos.sales.TaxesLogic;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.BaseSentence;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.QBFBuilder;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.user.EditorCreator;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.reports.JParamsComposed;
import com.openbravo.pos.reports.ReportEditorCreator;
import com.openbravo.pos.reports.ReportFields;
import com.openbravo.pos.reports.ReportFieldsArray;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JasperPrintManager;

/**
 *
 * @author swathi
 */
public class JasperReportPrinter {
//    private Object editor;
     private JasperReport jr = null;
    private EditorCreator editor = null;
    private int nos=1;
    protected AppView m_App;
    /*
     * public static void runReport(String databaseName, String userName, String password,String reportFile) {
    try{
           JasperDesign jasperDesign = JRXmlLoader.load(reportFile);
           JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
           Connection jdbcConnection = connectDB(databaseName, userName, password);
           JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, jdbcConnection);
           JasperViewer.viewReport(jasperPrint);
     }catch(Exception ex) {
           String connectMsg = "Could not create the report " + ex.getMessage() + " " + ex.getLocalizedMessage();
           System.out.println(connectMsg);
     }
}
     * */

    protected SentenceList taxsent;
    protected TaxesLogic taxeslogic;
       public JasperReportPrinter(){
          try {

            InputStream in = getClass().getResourceAsStream(getReport() + ".jasper");
                    if (in == null) {
                // read and compile the report
                JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream(getReport() + ".jrxml"));
                jr = JasperCompileManager.compileReport(jd);
            } else {
                // read the compiled report
                ObjectInputStream oin = new ObjectInputStream(in);
                jr = (JasperReport) oin.readObject();
                oin.close();
            }
        } catch (Exception e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotloadreport"), e);
          //  msg.show(null);
            e.printStackTrace();
            jr = null;
        }
       }
       private void printdata(final JasperPrint jasperPrint){
         Thread thread =
			new Thread(
				new Runnable()
				{
					public void run()
					{
						try
						{
							JasperPrintManager.printReport(jasperPrint, true);
						}
						catch (Exception ex)
						{
							ex.printStackTrace();
							//JOptionPane.showMessageDialog(JRViewer300.this, getBundleString("error.printing"));
						}
					}
				}
			);

		thread.start();
       }
       public void print(){
            if (jr != null) {
            try {

                // Archivo de recursos
                String res = getResourceBundle();

                // Parametros y los datos
                  Object params = (editor == null) ? null : editor.createValue();
                 Map reportparams = new HashMap();
                   reportparams.put("ARG", params);
                if (res != null) {
                      reportparams.put("REPORT_RESOURCE_BUNDLE", ResourceBundle.getBundle(res));
                }
                reportparams.put("TAXESLOGIC", taxeslogic);
                reportparams.put("REPORT_CONNECTION", m_App.getSession().getConnection());
                 // JParamsDatesInterval dateobj=new JParamsDatesInterval();

            //    Date date= (Date)JParamsDatesInterval.sstartdate;

           //     Date edate=(Date)JParamsDatesInterval.senddate;

            /*     if(date!=null){
                   Timestamp ts=new Timestamp(date.getTime());
                   reportparams.put("startdate",ts);
                 }
                 if(edate!=null){
                   Timestamp ets=new Timestamp(edate.getTime());
                   reportparams.put("enddate",ets);
                 }*/
             //   reportparams.put("product", StockDiaryReportType.pdtname);
           //     String cat=(String)StockDiaryReportType.category;
          //      reportparams.put("category", cat);
                // m_App.getSession().getConnection();
                JRDataSource data = new JRDataSourceBasic(getSentence(), getReportFields(), params);

                // Construyo el mapa de los parametros.

             //   reportparams.put("narration",JParamsDate.narration);


                JasperPrint jp = JasperFillManager.fillReport(jr, reportparams, data);

              //  reportviewer.loadJasperPrint(jp);
              printdata(jp);
             //   setVisibleFilter(false);

            } catch (SQLException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotloadreportdata"), e);
                 e.printStackTrace();
            } catch (MissingResourceException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotloadresourcedata"), e);
                e.printStackTrace();
            } catch (JRException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfillreport"), e);
                e.printStackTrace();
            } catch (BasicException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotloadreportdata"), e);
                e.printStackTrace();
            }
        }
       }
         private String title;
    private String report;
    private String subreport;

    private String resourcebundle = null;

    private String sentence;

    private List<Datas> fielddatas = new ArrayList<Datas>();
    private List<String> fieldnames = new ArrayList<String>();

    private List<String> paramnames = new ArrayList<String>();

    private JParamsComposed qbffilter = new JParamsComposed();

 
    public void init(AppView app) throws BeanFactoryException {

        qbffilter.init(app);
        
      //  super.init(app,0);
    }
  //   public void init1(AppView app,int temp) throws BeanFactoryException {

       // qbffilter.init(app);
 //     //  super.init(app,temp);
  //  }
   
    public void activate() throws BasicException {

        qbffilter.activate();
      //  super.activate();

       
    }

  //  @Override
    protected EditorCreator getEditorCreator() {

        return qbffilter;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitleKey(String titlekey) {
        title = AppLocal.getIntString(titlekey);
    }

    public String getTitle() {
        return title;
    }

    public void setReport(String report) {
        this.report = report;
    }
    public void setreporttoempty()
    {
        this.report="";
    }
    protected String getReport() {
        return report;
    }

    public String getSubReport() {
        return subreport;
    }

    public void setSubReport(String subreport) {
        this.subreport = subreport;
    }

    public void setResourceBundle(String resourcebundle) {
        this.resourcebundle = resourcebundle;
    }

    protected String getResourceBundle() {
        return resourcebundle == null
                ? report
                : resourcebundle;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void addField(String name, Datas data) {
        fieldnames.add(name);
        fielddatas.add(data);
    }
    public void clearlist()
    {
        fieldnames.clear();
        fielddatas.clear();

    }
    public void addParameter(String name) {
        paramnames.add(name);
    }

    protected BaseSentence getSentence() {
        StaticSentence s = new StaticSentence(m_App.getSession()
            , new QBFBuilder(sentence, paramnames.toArray(new String[paramnames.size()]))
            , qbffilter.getSerializerWrite()
            , new SerializerReadBasic(fielddatas.toArray(new Datas[fielddatas.size()])));
        return s;
    }

    protected ReportFields getReportFields() {
        ReportFieldsArray r = new ReportFieldsArray(fieldnames.toArray(new String[fieldnames.size()]));
        return r;
    }

    public void addQBFFilter(ReportEditorCreator qbff,int nos) {
        if(nos>1)
            this.nos=2;
        else
            this.nos=1;
        qbffilter.addEditor(qbff,nos);
    }
    public void addQBFFilter(ReportEditorCreator qbff) {
        qbffilter.addEditor(qbff);
    }
}
