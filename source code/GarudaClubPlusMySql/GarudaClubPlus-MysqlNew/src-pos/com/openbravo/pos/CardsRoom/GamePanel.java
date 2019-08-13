/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.CardsRoom;

import com.openbravo.data.gui.ListCellRendererBasic;
import com.openbravo.data.loader.ComparatorCreator;
import com.openbravo.data.loader.TableDefinition;
import com.openbravo.data.loader.Vectorer;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.data.user.ListProvider;
import com.openbravo.data.user.ListProviderCreator;
import com.openbravo.data.user.SaveProvider;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.panels.JPanelTable;
import javax.swing.ListCellRenderer;

/**
 *
 * @author swathi
 */
public class GamePanel extends JPanelTable{
    private TableDefinition tgames;
    private GameMaster jeditor;
    @Override
    protected void init() {
        DataLogicFacilities dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
       // tgames = dlfac.getTableGames();
       // jeditor = new GameMaster(dirty);
    }

    @Override
    public EditorRecord getEditor() {
        return null;
    }

    @Override
    public ListProvider getListProvider() {
       return new ListProviderCreator(tgames);
    }

    @Override
    public SaveProvider getSaveProvider() {
        return new SaveProvider(tgames);
    }

    public String getTitle() {
        return "Game List";
    }
    @Override
    public Vectorer getVectorer() {
        return tgames.getVectorerBasic(new int[]{1, 2});
    }

    @Override
    public ComparatorCreator getComparatorCreator() {
        return tgames.getComparatorCreator(new int[] {1, 2});
    }

    @Override
    public ListCellRenderer getListCellRenderer() {
        return new ListCellRendererBasic(tgames.getRenderStringBasic(new int[]{1}));
    }

}
