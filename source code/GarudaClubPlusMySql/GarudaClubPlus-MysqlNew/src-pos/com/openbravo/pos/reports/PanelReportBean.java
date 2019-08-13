

package com.openbravo.pos.reports;

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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adrianromero
 */
public class PanelReportBean extends JPanelReport {
    
    private String title;
    private String report;
    private String subreport;
    
    private String resourcebundle = null;
    
    private String sentence;
    
    private List<Datas> fielddatas = new ArrayList<Datas>();
    private List<String> fieldnames = new ArrayList<String>();
    
    private List<String> paramnames = new ArrayList<String>();
    
    private JParamsComposed qbffilter = new JParamsComposed();
    private int nos=1;
    
    @Override
    public void init(AppView app) throws BeanFactoryException {   
        
        qbffilter.init(app);       
        super.init(app,0);
    }
     public void init1(AppView app,int temp) throws BeanFactoryException {

       // qbffilter.init(app);
        super.init(app,temp);
    }
    @Override
    public void activate() throws BasicException {
        
        qbffilter.activate();
        super.activate();
        
        if (qbffilter.isEmpty()) {
            setVisibleFilter(false);
            setVisibleButtonFilter(false);
        }
    }
    
    @Override
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
    public void removeParameter() {
        paramnames.remove(0);
    }
    
    protected BaseSentence getSentence() {
        StaticSentence s = new StaticSentence(m_App.getSession()
            , new QBFBuilder(sentence, paramnames.toArray(new String[paramnames.size()]),nos)
            , qbffilter.getSerializerWrite()
            , new SerializerReadBasic(fielddatas.toArray(new Datas[fielddatas.size()])));
        return s;
    }
    
    protected ReportFields getReportFields() {
        ReportFieldsArray r = new ReportFieldsArray(fieldnames.toArray(new String[fieldnames.size()]));
        return r;
    }       
    
    public void addQBFFilter(ReportEditorCreator qbff) {
        qbffilter.addEditor(qbff);
    }
     public void addQBFFilter(ReportEditorCreator qbff,int nos) {
        if(nos>1)
            this.nos=2;
        else
            this.nos=1;

        qbffilter.addEditor(qbff,nos);
    }
}
