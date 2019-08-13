/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author swathi
 */
 public class Dependents{
        private String name;
        private String id;
        private String type;
        private boolean allow;
        private String old;
        private String newv;
        private List<DependentsData> ddata=new ArrayList<DependentsData>();
        public Dependents(String name,String id,String type,boolean allow,List<DependentsData> ddata){
            this.name=name;
            this.id=id;
            this.type=type;
            this.allow=allow;
            this.ddata=ddata;
        }
        public Dependents(String name,String id,String type,boolean allow,String old,String newv){
            this.name=name;
            this.id=id;
            this.type=type;
            this.allow=allow;
            this.old=old;
            this.newv=newv;
        }
        public String getName(){
           return name;
        }
        public String getType(){
          return type;
        }
        public String getID(){
          return id;
        }
        public List<DependentsData> getDataList(){
           return ddata;
        }
        public boolean isallowed(){
          return allow;
        }
        public void setallowed(boolean flag){
          allow=flag;
        }
        //protected Object[] getChildren() {
        //  return new Object[]{"a","aa"};
       // }
        public String toString(){
           return name;
        }
    }
