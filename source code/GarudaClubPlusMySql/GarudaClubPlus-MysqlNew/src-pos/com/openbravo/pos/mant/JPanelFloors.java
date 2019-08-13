

package com.openbravo.pos.mant;

import com.openbravo.basic.BasicException;
import javax.swing.ListCellRenderer;
import com.openbravo.data.gui.ListCellRendererBasic;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.data.loader.TableDefinition;
import com.openbravo.format.Formats;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.Vectorer;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.data.user.SaveProvider;
import com.openbravo.data.user.ListProvider;
import com.openbravo.data.user.ListProviderCreator;
import com.openbravo.pos.panels.*;

/**
 *
 * @author adrianromero
 */
public class JPanelFloors extends JPanelTable {
    
    private TableDefinition tfloors;
    private FloorsEditor jeditor;
    
    /** Creates a new instance of JPanelFloors */
    public JPanelFloors() {
    }
    
    protected void init() {
        tfloors = new TableDefinition(app.getSession(),
            "FLOORS"
            , new String[] {"ID", "NAME","BILLSL", "LASTBILL", "IMAGE","PRODUCTCAT","ACCOUNTID"}
            , new String[] {"ID", AppLocal.getIntString("Label.Name"), AppLocal.getIntString("Label.BillSeries"), AppLocal.getIntString("Label.LastBill"), "IMAGE","PRODUCTCAT","ACCOUNTID"}
            , new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.IMAGE,Datas.STRING,Datas.STRING}
            , new Formats[] {Formats.NULL, Formats.STRING, Formats.STRING, Formats.STRING,Formats.STRING,Formats.STRING}
            , new int[] {0}
        );  
        jeditor = new FloorsEditor(dirty,app);
    }
    
    public ListProvider getListProvider() {
        return new ListProviderCreator(tfloors);
    }
    
    public Vectorer getVectorer() {
        return tfloors.getVectorerBasic(new int[]{1});
    }
     @Override
    public void activate() throws BasicException {

        jeditor.activate();
        super.activate();
    }
    public ListCellRenderer getListCellRenderer() {
        return new ListCellRendererBasic(tfloors.getRenderStringBasic(new int[]{1}));
    }
    
    public SaveProvider getSaveProvider() {
        return new SaveProvider(tfloors);      
    }
    
    public EditorRecord getEditor() {
        return jeditor;
    }
    
    public String getTitle() {
        return AppLocal.getIntString("Menu.Floors");
    }     
}
