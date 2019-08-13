/*
 *
 * @author : William Alexander
 *
*/
package com.openbravo.pos.sms;

//import com.openbravo.pos.sms.Sender;

import java.util.ArrayList;
import java.util.List;


public class SMSClient implements Runnable{

  public final static int SYNCHRONOUS=0;
  public final static int ASYNCHRONOUS=1;
  private Thread myThread=null;

  private int mode=-1;
  private List<Object[]> recipient=new ArrayList();
  private String message=null;
  private String portname;

  //public int status=-1;
  public long messageNo=-1;
  private String msgcenter;
  private List unsent=new ArrayList();


  public SMSClient(int mode,String portname,String msgc) {
      this.mode=mode;
      this.portname=portname;
      this.msgcenter=msgc;
    }

  public List sendMessage (List<Object[]> recipient, String message){
    this.recipient=recipient;
    this.message=message;
    myThread = new Thread(this);
    myThread.start();
    return unsent;
    }
    public void run(){
    String recp=null;
    Sender aSender = new Sender(recp,message,portname,msgcenter);

    try{
      //send message
          aSender.ConfigureSerialPort();
          int z=0;
     for(Object[] rno:recipient){
         z++;
         System.out.println(z+"");
        aSender.run(rno);
         // System.out.println("sending ... ");

      //in SYNCHRONOUS mode wait for return : 0 for OK, -2 for timeout, -1 for other errors
      if (mode==SYNCHRONOUS) {
          while (aSender.status == -1){
            myThread.sleep (1000);
          }
         // synchronized{
            if(aSender.status!=0){
                unsent.add(rno[1]);
            }
         // }

      }
      if (aSender.status == 0) messageNo=aSender.messageNo ;
      }
     aSender.closeconnection();

    }catch (Exception e){

        e.printStackTrace();
        aSender.closeconnection();
    }

    //this.status=aSender.status ;
     for(Object s:unsent){
        System.out.println(""+s);
     }
    aSender=null;


  }
    public List getUnSentList(){
      return unsent;
    }
}