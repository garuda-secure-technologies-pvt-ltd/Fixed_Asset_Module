

package com.openbravo.pos.ticket;

import javax.swing.*;
import java.awt.*;

import com.openbravo.pos.util.ThumbNailBuilder;
import com.openbravo.format.Formats;

public class ProductRenderer extends DefaultListCellRenderer {
                
    ThumbNailBuilder tnbprod;

    /** Creates a new instance of ProductRenderer */
    public ProductRenderer() {   
        tnbprod = new ThumbNailBuilder(64, 32, "com/openbravo/images/package.png");
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, null, index, isSelected, cellHasFocus);
        
        ProductInfoExt prod = (ProductInfoExt) value;
        if (prod != null) {
            
            String tax1 = prod.getTaxCategoryName();                                                                                                                                                                        // edited by aakash 
            if(prod.getTaxCategoryName2()!=null) {
                tax1 =(tax1+" - "+ prod.getTaxCategoryName2());
            }
            if(prod.getTaxCategoryName3()!=null ){
                String tax3 = prod.getTaxCategoryName3();
                tax1 = (tax1 + " - "  +  tax3);     
                        
            }
            
            
            setText("<html>" + prod.getReference() + " - " + prod.getName() + "<br>&nbsp;&nbsp;&nbsp;&nbsp;" + tax1 +  " " + Formats.CURRENCY.formatValue(new Double(prod.getPriceSell())));
            Image img = tnbprod.getThumbNail(prod.getImage());
            setIcon(img == null ? null :new ImageIcon(img));
        }
        return this;
    }      
}
