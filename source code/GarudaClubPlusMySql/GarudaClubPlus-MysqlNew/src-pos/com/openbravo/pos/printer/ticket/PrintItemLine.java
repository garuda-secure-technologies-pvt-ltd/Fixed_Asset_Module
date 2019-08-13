

package com.openbravo.pos.printer.ticket;

import com.openbravo.format.Formats;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import com.openbravo.pos.printer.screen.*;
import java.awt.Dimension;
import java.awt.TextArea;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class PrintItemLine implements PrintItem {
    
    private static final Font BASEFONT = new Font("Monospaced", Font.PLAIN, 12);
    private static final int FONTHEIGHT = 17; //
    private static final int FONTWIDTH = 7; //
    
    private int m_itextsize;
    private List<StyledText> m_atext;
    private int w;
    private JLabel label;
    private int cnt=0;
    
    /** Creates a new instance of PrinterItemLine */
    public PrintItemLine(int itextsize,int width) {
        m_itextsize = itextsize;
        m_atext = new ArrayList<StyledText>();
        w=width;
        //if(width!=0)
          //  w=width;
        label = new JLabel();
        label.setLocation(0, 0);



    }
    
    public void addText(int style, String text) {
        m_atext.add(new StyledText(style, text));
    }
    
    public void draw(Graphics2D g, int x, int y, int width) {
        
        MyPrinterState ps = new MyPrinterState(m_itextsize);
        int left = x;
        for (int i = 0; i < m_atext.size(); i++) {
            StyledText t = m_atext.get(i);
            
            label.setFont(ps.getFont(BASEFONT, t.style));
            label.setText(t.text);
            label.setSize(label.getPreferredSize());
           // int d2=label.getPreferredSize().height;
           // int d1=label.getPreferredSize().width;
            
           /* if(w==0){
                label.setSize(label.getPreferredSize());
                label.setBounds(0, 0, width, left+cnt);
            }else{
             int columncnt=Math.round(t.text.length()/w);
             Dimension d=new Dimension(FONTWIDTH * w, label.getPreferredSize().height);
             label.setSize(d);
             label.setBounds(0, 0, FONTWIDTH * w, left+columncnt);
             
             //label.setColumns(w);
             label.setLineWrap(true);
             cnt=cnt+columncnt;
            }*/

           
            
            g.translate(left, y);
            label.paint(g);
            g.translate(-left, -y);
            
            // left += label.getWidth();
            left += FONTWIDTH * t.text.length();
        }
    }
    
    public int getHeight() {
        // int cnt1=1;
       /* if(w!=0){
           String text=m_atext.get(0).text;
           double value=text.length()/w +0.5;
           cnt1=Integer.valueOf(String.valueOf(Math.round(value)));
        }*/
        return FONTHEIGHT * MyPrinterState.getLineMult(m_itextsize) ;
    }    
    
    private static class StyledText {
        
        public StyledText(int style, String text) {
            this.style = style;
            this.text = text;
        }
        public int style;
        public String text;
    }
}
