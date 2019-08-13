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

public class DataSourceMemberAddrss implements JRDataSource
{

    private int m_nIdx;
    private List<MemberStatementModel.addressline> v;
    int i = 0;

    public DataSourceMemberAddrss()
    {
        this(new ArrayList<MemberStatementModel.addressline>());
    }

    public DataSourceMemberAddrss(List<MemberStatementModel.addressline> v)
    {
        m_nIdx = -1;
        this.v = v;
    }

    public Object getFieldValue(JRField field) throws JRException
    {
        Object o = null;

        String sName = field.getName();

        MemberStatementModel.addressline curr = v.get(m_nIdx);

        if (curr == null)
        {
            return null;
        }

        if (sName.equals("address"))
        {
            o = curr.getFaddress();
        }
        return o;
    }

    public boolean next() throws JRException
    {
        m_nIdx++;
        return (m_nIdx < v.size());
    }
}



