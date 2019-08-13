/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.panels;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class EventTableModel {

    private List<EventsTable> events;
    private final static String[] EVENTSHEADERS = {"Event No", "Event name", "Description","Event Date", "Start Time", "End Time"};

    private EventTableModel() {
    }

    public static EventTableModel emptyinstance() {
        EventTableModel d = new EventTableModel();
        d.events = new ArrayList<EventsTable>();
        return d;
    }

    public static EventTableModel loadInstance(AppView app) throws BasicException {
        EventTableModel d = new EventTableModel();

        List dlist = new StaticSentence(app.getSession(), "SELECT ID,NAME,DESCRIPTION,EDATE,STIME,ETIME FROM EVENTS WHERE  ACTIVE=TRUE ORDER BY EDATE", null, new SerializerReadClass(EventTableModel.EventsTable.class)).list();
        if (dlist == null) {
            d.events = new ArrayList<EventsTable>();
        } else {
            d.events = dlist;
        }



        return d;

    }

    public List<EventsTable> getEventsTable() {
        return events;
    }

    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {

            @Override
            public String getColumnName(int column) {
                return EVENTSHEADERS[column];
            }

            public int getRowCount() {
                return events.size();
            }

            public int getColumnCount() {

                return EVENTSHEADERS.length;
            }

            public Object getValueAt(int row, int column) {
                EventsTable l = events.get(row);

                switch (column) {

                    case 0:
                        return l.getEno();
                    case 1:
                        return l.getEname();
                    case 2:
                        return l.getDescription();
                    case 3:
                    return Formats.DATE.formatValue(l.getEdate());
                    case 4:
                        return l.getStime();
                     case 5:
                        return l.getEtime();


                    default:
                        return null;
                }
            }
        };
    }

    public static class EventsTable implements SerializableRead {

        private String eno;
        private String ename;
        private String description;
        private Date edate;
        private String stime;
        private String etime;
        

        public void readValues(DataRead dr) throws BasicException {

            eno = dr.getString(1);
            ename = dr.getString(2);
            description = dr.getString(3);
            edate = dr.getTimestamp(4);
            stime = dr.getString(5);
            etime = dr.getString(6);

        }

        public Date getEdate() {
            return edate;
        }

        public String getDescription() {
            return description;
        }

        

        public String getEname() {
            return ename;
        }

        public String getEno() {
            return eno;
        }

        public String getEtime() {
            return etime;
        }

        public String getStime() {
            return stime;
        }


        
    }
}
