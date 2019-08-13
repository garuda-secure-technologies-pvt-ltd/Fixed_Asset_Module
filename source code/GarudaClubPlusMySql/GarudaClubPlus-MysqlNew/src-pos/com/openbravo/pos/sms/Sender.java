/*
 * 
 * @author : William Alexander
 *
*/
package com.openbravo.pos.sms;
import java.util.Date;

public class Sender  {
//implements Runnable
//  private static final long STANDARD=500;
//  private static final long LONG=2000;
//  private static final long  VERYLONG=20000;
   private static final long STANDARD=500;
   private static final long LONG=2000;
   private static final long  VERYLONG=20000;

  SerialConnection mySerial =null;

  static final private char cntrlZ=(char)26;
  String in, out;
  Thread aThread=null;
  //private long delay=STANDARD;
 // String recipient=null;
  String message=null;

  private String csca; // the message center
  private SerialParameters defaultParameters= new SerialParameters ("COM2",9600,0,0,8,1,0);
  public int step;
  public int status=-1;
  public long messageNo=-1;

  public Sender(String recipient, String message,String portname,String msgc){
    this.message=message;
    defaultParameters= new SerialParameters (portname,9600,0,0,8,1,0);
    csca=msgc;
  }
  /**
   * connect to the port and start the dialogue thread
   */
 
  public void ConfigureSerialPort () throws Exception{

    SerialParameters params = defaultParameters;

    mySerial =new SerialConnection (params);

    mySerial.openConnection();

   // aThread=new Thread(this);
     //run();
   // aThread.start() ;
    //log("start");

    //return 0;
  }

  /**
   * implement the dialogue thread,
   * message / response via steps,
   * handle time out
   */

  public void run(Object[] recp){
    String recipient=String.valueOf(recp[1]);
    long delay=STANDARD;
    step=0;
    status=-1;
    messageNo=-1;
    boolean timeOut=false;
    long startTime=(new Date()).getTime();
   String msg="Dear "+"Member"+",\n"+message;
   // String msg=message;


    while ((step <7) && (!timeOut)){
//      log(""+((new Date()).getTime() - startTime);
      //check where we are in specified delay
      timeOut=((new Date()).getTime() - startTime)>delay;
      //timeOut=false;
     // timeOut=false;
      //if atz does not work, type to send cntrlZ and retry, in case a message was stuck
      if (timeOut && (step==1)) {
          step=-1;
          mySerial.send(        ""+cntrlZ);
      }

      //read incoming string
      String result=  mySerial.getIncommingString() ;

//    log ("<- "+result+"\n--------");
      int expectedResult=-1;

      try{
        //log ("Step:"+step);

        switch (step){
          case 0:

            mySerial.send("atz");
            delay=LONG;
            startTime=(new Date()).getTime();
            break;

          case 1:
            delay=STANDARD;
            //checks  if the modem is ready
            mySerial.send("ath0");
            startTime=(new Date()).getTime();
            break;
          case 2:
            expectedResult=result.indexOf("OK");

            //log ("received ok ="+expectedResult);
            if (expectedResult>-1){
              mySerial.send("at+cmgf=1");
              startTime=(new Date()).getTime();
            }else{
                step=step-1;
            }
            break;
          case 3:
            expectedResult=result.indexOf("OK");

           // log ("received ok ="+expectedResult);
            if (expectedResult>-1){
              mySerial.send("at+csca=\""+csca+"\"");
              startTime=(new Date()).getTime();
            }else{
              step=step-1;
            }

            break;
          case 4:
            expectedResult=result.indexOf("OK");

           // log ("received ok ="+expectedResult);
            if (expectedResult>-1){
              mySerial.send("at+cmgs=\""+recipient+"\"");
              startTime=(new Date()).getTime();
            }else{
              step=step-1;
            }

            break;
          case 5:
            expectedResult=result.indexOf(">");

           // log ("received ok ="+expectedResult);
            if (expectedResult>-1){
              mySerial.send(msg+cntrlZ);
              startTime=(new Date()).getTime();
            }else{
              step=step-1;
            }
            delay=VERYLONG;//waitning for message ack

            break;

          case 6:
            expectedResult=result.indexOf("OK");
            //read message number
            if (expectedResult>-1){
              int n=result.indexOf("CMGS:");
              result=result.substring(n+5);
              n=result.indexOf("\n");
              status=0;
              messageNo=Long.parseLong(result.substring(0,n).trim() );

              log ("sent message no:"+messageNo);


            }else{
              step=step-1;
            }

          break;
        }
        step=step+1;

        Thread.currentThread().sleep(100);

      }catch (Exception e){
          e.printStackTrace();
      }
    }

   

    //if timed out set status

    if (timeOut ) {
        status=-2;
        log("*** time out at step "+step+"***");
    }
  }
  public void closeconnection(){
      mySerial.closeConnection() ;
  }
/**
 * logging function, includes date and class name
 */
  private void log(String s){
    System.out.println (new java.util.Date()+":"+this.getClass().getName()+":"+s);
  }
}