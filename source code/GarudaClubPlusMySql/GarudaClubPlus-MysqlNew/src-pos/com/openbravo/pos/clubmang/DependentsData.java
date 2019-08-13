/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

/**
 *
 * @author swathi
 */
 public class DependentsData{
        private String field;
        private String oldvalue;
        private String newvalue;
        private String parent;
        private boolean allow;
        public DependentsData(String field,String oldval,String newval,String parent,boolean allow){
            this.field=field;
            this.oldvalue=oldval;
            this.newvalue=newval;
            this.parent=parent;
            this.allow=allow;
        }
        public String getField(){
          return field;
        }
        public String getOldValue(){
          return oldvalue;
        }
        public String getNewValue(){
          return newvalue;
        }
        public String getParent(){
          return parent;
        }
        public boolean isAllowed(){
            return allow;
        }
        public void setallowed(boolean flag){
          allow=flag;
        }
        @Override
        public String toString(){
           return field;
        }
    }
