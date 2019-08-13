/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.clubmang;

/**
 *
 * @author swathi
 */
import java.util.ArrayList;
import java.util.List;
//import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DataSourceMemberSummary implements JRDataSource
{

    private int m_nIdx;
    private List<MemberAddressModel.MemberCategoryLine> v;
    int i = 0;

    public DataSourceMemberSummary()
    {
        this(new ArrayList<MemberAddressModel.MemberCategoryLine>());
    }

    public DataSourceMemberSummary(List<MemberAddressModel.MemberCategoryLine> v)
    {
        m_nIdx = -1;
        this.v = v;
    }

    public Object getFieldValue(JRField field) throws JRException
    {
        Object o = null;

        String sName = field.getName();

        MemberAddressModel.MemberCategoryLine curr = v.get(m_nIdx);

        if (curr == null)
        {
            return null;
        }

        if (sName.equals("slno"))
        {
            o = ++i;
        } else if (sName.equals("categoryname"))
        {
            o = curr.getCatname();
        } else if (sName.equals("visiblenor"))
        {
            o = curr.getVisiblenor();
        } else if (sName.equals("invisiblenor"))
        {
            o = curr.getNonvisiblenor();
        }

        return o;
    }

    public boolean next() throws JRException
    {
        m_nIdx++;
        return (m_nIdx < v.size());
    }
}



