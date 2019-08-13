/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

//import java.awt.List;
import java.util.List;
//import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author swathi
 */
public class ListModel extends DefaultListModel{
    private List list;
    
    public ListModel(List list) {
        this.list = list;
    }

    @Override
    public int getSize() {
       return list.size();
    }

    @Override
    public Object getElementAt(int index) {
        return list.get(index);
    }

    @Override
    public void addElement(Object obj){
        list.add(obj);
    }

    public void removeElement(int index){
        list.remove(index);
    }
    public List getList(){
       return list;
    }

}
