/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author swathi
 */
public class FacilityLogic {
      private DataLogicFacilities dmang;
      private Date temp;
      private int pnum;
      public FacilityLogic(){
      }
      public FacilityLogic(DataLogicFacilities dlfac) {
        this.dmang=dlfac;
        this.temp=new Date();
        pnum=0;
      }
      public void setTemp(Date temp){
         this.temp.setTime(temp.getTime());
      }
      public void setPnum(int num){
        pnum=num;
      }
      public int getPnum(){
        return pnum;
      }
      public Date getTemp(){
         return temp;
      }
      public Date calculateEndDate(Date d,Periodicity p,int billabledate,int num,Date edate){
        //  Date edate=new Date();
          pnum=0;
          Calendar cal1=Calendar.getInstance();
          cal1.setTimeInMillis(d.getTime());
          cal1.set(Calendar.HOUR_OF_DAY, 00);
          cal1.set(Calendar.MINUTE, 00);
          cal1.set(Calendar.SECOND, 00);
          cal1.set(Calendar.MILLISECOND, 00);
          Calendar cal=Calendar.getInstance();
          cal.setTimeInMillis(edate.getTime());
          
         if(p.getdoj()==true){
           while(cal1.before(cal)){
              temp.setTime(cal1.getTimeInMillis());
             if(p.gettype().equals("Days")){
              cal1.add(Calendar.DATE, p.getno()*num);
              pnum++;
             }else if(p.gettype().equals("Months")){
              cal1.add(Calendar.MONTH, p.getno()*num);
              pnum++;
             }else if(p.gettype().equals("Quaterly")){
              cal1.add(Calendar.MONTH, 3);
              pnum++;
             }else if(p.gettype().equals("Years")){
                cal1.add(Calendar.YEAR, p.getno()*num);
                pnum++;
             }
           }
          }else{
            while(cal1.before(cal)){
              temp.setTime(cal1.getTimeInMillis());
             if(p.gettype().equals("Days")){
              cal1.add(Calendar.DATE, p.getno()* num);
              pnum++;
             }else if(p.gettype().equals("Months")){
              cal1.set(Calendar.DATE, billabledate);
              cal1.add(Calendar.MONTH, p.getno()* num);
              pnum++;
             }else if(p.gettype().equals("Quaterly")){
              cal1.set(Calendar.DATE, billabledate);
              cal1.add(Calendar.MONTH, 3);
              pnum++;
             }else if(p.gettype().equals("Years")){
               cal1.set(Calendar.DATE, billabledate);
               cal1.set(Calendar.MONTH, p.getmonth());
               cal1.add(Calendar.YEAR, p.getno() * num);
               pnum++;
             }
           }
          }
          cal1.add(Calendar.DATE, -1);
          
          edate.setTime(cal1.getTimeInMillis());
          return edate;
      }
      public Date calculateEndDate1(Date d,Periodicity p,int billabledate,int num){
          Date edate=new Date();
          int i=0;
          Calendar cal1=Calendar.getInstance();
          cal1.setTimeInMillis(d.getTime());
          Calendar cal=Calendar.getInstance();
          cal.setTimeInMillis(edate.getTime());
         if(p.getdoj()==true){
          while(i<num){
              temp.setTime(cal1.getTimeInMillis());
             if(p.gettype().equals("Days")){
              cal1.add(Calendar.DATE, p.getno());
             // pnum++;
             }else if(p.gettype().equals("Months")){
              cal1.add(Calendar.MONTH, p.getno());
            //  pnum++;
             }else if(p.gettype().equals("Quaterly")){
              cal1.add(Calendar.MONTH, 3);
             // pnum++;
             }else if(p.gettype().equals("Years")){
                cal1.add(Calendar.YEAR, p.getno());
             //   pnum++;
             }
              i++;
           }
          }else{
            while(i<num){
              temp.setTime(cal1.getTimeInMillis());
             if(p.gettype().equals("Days")){
              cal1.add(Calendar.DATE, p.getno());
             // pnum++;
             }else if(p.gettype().equals("Months")){
              cal1.set(Calendar.DATE, billabledate);
              cal1.add(Calendar.MONTH, p.getno());
             // pnum++;
             }else if(p.gettype().equals("Quaterly")){
              cal1.set(Calendar.DATE, billabledate);
              cal1.add(Calendar.MONTH, 3);
            //  pnum++;
             }else if(p.gettype().equals("Years")){
               cal1.set(Calendar.DATE, billabledate);
               cal1.set(Calendar.MONTH, p.getmonth());
               cal1.add(Calendar.YEAR, p.getno() );
             //  pnum++;
             }
              i++;
           }
          }
          cal1.add(Calendar.DATE, -1);
          edate.setTime(cal1.getTimeInMillis());
          return edate;
      }
      public Date calculateEndDate2(Date d,Periodicity p){
          Date edate=new Date();
          
          int i=0;
          Calendar cal1=Calendar.getInstance();
          cal1.setTimeInMillis(d.getTime());
           cal1.add(Calendar.MONTH, 1);
           cal1.add(Calendar.DATE, -1);
          edate.setTime(cal1.getTimeInMillis());
         
         return edate;
      }
      public Date calculateEndDate1forbilling(Date d, Periodicity p, int billabledate, int num) {
        Date edate = new Date();
        int i = 0;
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(d.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(edate.getTime());
        if (p.getdoj() == true) {
            while (i < num) {
                temp.setTime(cal1.getTimeInMillis());
                if (p.gettype().equals("Days")) {
                    cal1.add(Calendar.DATE, p.getno());
                // pnum++;
                } else if (p.gettype().equals("Months")) {
                    cal1.add(Calendar.MONTH, p.getno());
                //  pnum++;
                } else if (p.gettype().equals("Quaterly")) {
                    cal1.add(Calendar.MONTH, 3);
                // pnum++;
                } else if (p.gettype().equals("Years")) {
                    cal1.add(Calendar.YEAR, p.getno());
                //   pnum++;
                }
                i++;
            }
        } else {
            while (i < num) {
                temp.setTime(cal1.getTimeInMillis());
                if (p.gettype().equals("Days")) {
                    cal1.add(Calendar.DATE, p.getno());
                // pnum++;
                } else if (p.gettype().equals("Months")) {
                    cal1.set(Calendar.DATE, billabledate);
                    cal1.add(Calendar.MONTH, p.getno());
                // pnum++;
                } else if (p.gettype().equals("Quaterly")) {
                    cal1.set(Calendar.DATE, billabledate);
                    cal1.add(Calendar.MONTH, 3);
                //  pnum++;
                } else if (p.gettype().equals("Years")) {
                    cal1.set(Calendar.DATE, billabledate);
                    cal1.set(Calendar.MONTH, p.getmonth());
                    cal1.add(Calendar.YEAR, p.getno());
                //  pnum++;
                }
                i++;
            }
        }
        cal1.set(Calendar.DATE, 30);
        cal1.add(Calendar.MONTH, -1);
        edate.setTime(cal1.getTimeInMillis());
        return edate;
    }
     public Date calculateStratDate1forbilling(Date d, Periodicity p, int billabledate, int num) {
        Date edate = new Date();
        int i = 0;
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(d.getTime());
        cal1.set(Calendar.DATE, 1);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(edate.getTime());
        cal.set(Calendar.DATE, 1);
        if (p.getdoj() == true) {
            while (i < num) {
                temp.setTime(cal1.getTimeInMillis());
                if (p.gettype().equals("Days")) {
                    cal1.add(Calendar.DATE, -p.getno());
                // pnum++;
                } else if (p.gettype().equals("Months")) {
                    cal1.add(Calendar.MONTH, -p.getno());
                //  pnum++;
                } else if (p.gettype().equals("Quaterly")) {
                    cal1.add(Calendar.MONTH, -3);
                // pnum++;
                } else if (p.gettype().equals("Years")) {
                    cal1.add(Calendar.YEAR, -p.getno());
                //   pnum++;
                }
                i++;
            }
        } else {
            while (i < num) {
                temp.setTime(cal1.getTimeInMillis());
                if (p.gettype().equals("Days")) {
                    cal1.add(Calendar.DATE, -p.getno());
                // pnum++;
                } else if (p.gettype().equals("Months")) {
                    cal1.set(Calendar.DATE, billabledate);
                    cal1.add(Calendar.MONTH, -p.getno());
                // pnum++;
                } else if (p.gettype().equals("Quaterly")) {
                    cal1.set(Calendar.DATE, billabledate);
                    cal1.add(Calendar.MONTH, -3);
                //  pnum++;
                } else if (p.gettype().equals("Years")) {
                    cal1.set(Calendar.DATE, billabledate);
                    cal1.set(Calendar.MONTH, p.getmonth());
                    cal1.add(Calendar.YEAR, -p.getno());
                //  pnum++;
                }
                i++;
            }
        }
       // if(Calendar.DATE==30||Calendar.DATE==31){
               //cal1.add(Calendar.DATE, 1);
        //}else{
            //cal1.set(Calendar.DATE, 1);

        //}

        //cal1.add(Calendar.DATE, -1);
        edate.setTime(cal1.getTimeInMillis());
        return edate;
    }
       public Date calculateStartDate(Date d,Periodicity p,int billabledate,int num){
          Date edate=new Date();
          int i=0;
          Calendar cal1=Calendar.getInstance();
          cal1.setTimeInMillis(d.getTime());
          {
            while(num<0){
              temp.setTime(cal1.getTimeInMillis());
             if(p.gettype().equals("Days")){
              cal1.add(Calendar.DATE, p.getno() * -1);
             // pnum++;
             }else if(p.gettype().equals("Months")){
              cal1.add(Calendar.MONTH, p.getno() * -1);
             // pnum++;
             }else if(p.gettype().equals("Quaterly")){
              cal1.add(Calendar.MONTH, -3);
            //  pnum++;
             }else if(p.gettype().equals("Years")){
               cal1.set(Calendar.MONTH, p.getmonth() );
               cal1.add(Calendar.YEAR, p.getno()  * -1);
             //  pnum++;
             }
              num++;
           }
          }
          if(!p.gettype().equals("Days"))
           cal1.set(Calendar.DATE, cal1.getActualMaximum(Calendar.DATE));
          edate.setTime(cal1.getTimeInMillis());
          return edate;
      }
       public Date calculateStartDate1(Periodicity p,Date lbilldate){
          Date d=new Date();
          Date edate=new Date();
          int i=0;
            if( lbilldate.compareTo(d)<0 )
              {
              Calendar cal1=Calendar.getInstance();
              cal1.setTimeInMillis(lbilldate.getTime());
              cal1.set(Calendar.HOUR_OF_DAY, 00);
              cal1.set(Calendar.MINUTE, 00);
               cal1.set(Calendar.SECOND, 00);
               cal1.set(Calendar.MILLISECOND, 00);
                cal1.add(Calendar.DATE,+1);
              
            /*while(num<0){
              temp.setTime(cal1.getTimeInMillis());
             if(p.gettype().equals("Days")){
              cal1.add(Calendar.DATE, p.getno() * -1);
             // pnum++;
             }else if(p.gettype().equals("Months")){
              cal1.add(Calendar.MONTH, p.getno() * -1);
             // pnum++;
             }else if(p.gettype().equals("Quaterly")){
              cal1.add(Calendar.MONTH, -3);
            //  pnum++;
             }else if(p.gettype().equals("Years")){
               cal1.set(Calendar.MONTH, p.getmonth() );
               cal1.add(Calendar.YEAR, p.getno()  * -1);
             //  pnum++;
             }
              num++;
           }*/edate.setTime(cal1.getTimeInMillis());
          return edate;
          }

          return edate;
      }
       public Double calulaterenewalamt(Date lbdate,Date edate,Double ramt){
         Calendar caltemp1=Calendar.getInstance();
         Calendar caltemp2=Calendar.getInstance();
         caltemp1.setTimeInMillis(lbdate.getTime());
         caltemp2.setTimeInMillis(edate.getTime());
         Date d=new Date();
         long difMil1 = caltemp2.getTimeInMillis() - caltemp1.getTimeInMillis();
         int days1 = (int)((difMil1 + 12 * 3600000L) / (24 * 3600000L));
         caltemp1.set(Calendar.DATE, caltemp2.get(Calendar.DATE));
         long difMil = d.getTime() - caltemp1.getTimeInMillis();
         int days = (int)((difMil + 12 * 3600000L) / (24 * 3600000L));
         return dmang.roundTwoDecimals(Math.round(ramt * days/days1));
        }
        public Date getDueDate(DebtTypeTableModel.DebtTypeline dueperiod,Date d){
           String type=dueperiod.getperiod();
           int num=dueperiod.getNum();
           Date duedate=new Date();
           duedate.setTime(d.getTime());
           Calendar cal=Calendar.getInstance();
           cal.setTimeInMillis(duedate.getTime());
           if(type.equals("Days"))
            cal.add(Calendar.DATE, num);
           if(type.equals("Months"))
            cal.add(Calendar.MONTH, num);
           if(type.equals("Years"))
             cal.add(Calendar.YEAR, num);
            cal.set(Calendar.HOUR_OF_DAY, 00);
            cal.set(Calendar.MINUTE, 00);
            cal.set(Calendar.SECOND, 00);
            cal.set(Calendar.MILLISECOND, 00);
            cal.add(Calendar.DATE, -1);
            duedate.setTime(cal.getTimeInMillis());
           return duedate;
         }

        public Calendar getBillableDate(Periodicity p,int billabledate,Calendar cal1){
                 Object pdate=(Object)p.getdate();
                            if(p.gettype().equals("Years") || p.gettype().equals("Months")){
                             if(pdate!=null){
                              if(pdate.equals("Last Day")){
                                 billabledate=cal1.getActualMaximum(Calendar.DATE);
                              }else{
                                if(p.getdoj()==false){
                                  billabledate=Integer.parseInt(pdate.toString());
                                 if(billabledate==29 || billabledate==30){
                                   int dmax=cal1.getActualMaximum(Calendar.DATE);
                                    if(dmax==29 || dmax==30)
                                      billabledate=dmax;
                                  }
                        }
                      }
                    }

                  if(p.gettype().equals("Years")){
                    if(p.getmonth()!=-1){
                      if(cal1.get(Calendar.MONTH)<p.getmonth()) {
                         cal1.add(Calendar.YEAR,-1);
                      }
                        cal1.set(Calendar.MONTH,p.getmonth());
                    }
                  }
                   cal1.set(Calendar.DATE, billabledate);
                }else if(p.gettype().equals("Quaterly")){
                       int month= cal1.get(Calendar.MONTH);
                       int bmonth=0;
                       if(month <= 3   && month >= 1){
                           bmonth=1;
                       } else
                      if(month <= 6   && month >= 3){
                        bmonth=3;
                      } else
                     if(month <= 9   && month >= 6){
                           bmonth=6;
                      } else
                     if(month <= 12   && month >= 9){
                        bmonth=9;
                     }
                       cal1.set(Calendar.MONTH, bmonth);
                 }
             return  cal1;
      }
     public Date addOnePeriod(Date d,Periodicity p){
          Date edate=new Date();
          pnum=0;
          Calendar cal1=Calendar.getInstance();
          cal1.setTimeInMillis(d.getTime());
         // Calendar cal=Calendar.getInstance();
        //  cal.setTimeInMillis(edate.getTime());
       //  cal.set(Calendar.HOUR, 00);
       //   cal.set(Calendar.MINUTE, 00);
        //  cal.set(Calendar.SECOND, 00);
       //   cal.set(Calendar.MILLISECOND, 00);
     //   {
        //    while(cal1.before(cal)){
            //  temp.setTime(cal1.getTimeInMillis());
             if(p.gettype().equals("Days")){
              cal1.add(Calendar.DATE, p.getno());
             }else if(p.gettype().equals("Months")){
              cal1.add(Calendar.MONTH, p.getno());
             }else if(p.gettype().equals("Quaterly")){
              cal1.add(Calendar.MONTH, 3);
             }else if(p.gettype().equals("Years")){
               cal1.set(Calendar.MONTH, p.getmonth());

             }
          // }
        //  }
          //cal1.add(Calendar.DATE, -1);
          edate.setTime(cal1.getTimeInMillis());
          return edate;
      }
}
