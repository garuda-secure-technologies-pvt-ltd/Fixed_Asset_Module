

package com.openbravo.pos.printer.escpos;

public abstract class PrinterWritter {
    
    private static final Integer ACTION_FLUSH = new Integer(0);
    private static final Integer ACTION_CLOSE = new Integer(1);
    
    // private OutputStream m_out = null;
    private PrinterBuffer m_buff = null;    
    private MyDaemon m_daemon = null;
    private boolean initialized = false;
    
    public PrinterWritter() {
        m_buff = new PrinterBuffer();
        m_daemon = new MyDaemon();        
        m_daemon.start();
    }
    
    protected abstract void daemonWrite(byte[] data);
    protected abstract void daemonFlush();
    protected abstract void daemonClose();
    
    public void init(byte[] data) {
        if (!initialized) {
            m_buff.putData(data);
            initialized = true;
        }
    }
    
    public void write(byte[] data) {
        m_buff.putData(data);
    }
    
    public void write(String sValue) {
        m_buff.putData(sValue.getBytes());
    }
    
    public void flush() {
        m_buff.putData(ACTION_FLUSH);
    }
    
    public void close() {
        m_buff.putData(ACTION_CLOSE);
    }

    private class MyDaemon extends Thread {

        public void run() {

            boolean bItsRunning = true;

            while (bItsRunning) {               
                Object data = m_buff.getData();                   
                // esperemos un poco que estoy vago
                //try {
                //    this.sleep(1000);
                //} catch (InterruptedException ei) {
                //}

                // Que hacemos con ese objeto tan raro?
                if (data instanceof byte[]) {
                    // m_out.write((byte[]) data); // Lo imprimimos
                    daemonWrite((byte[]) data);
                } else if (data == ACTION_FLUSH) { 
                    // m_out.flush(); // flush
                    daemonFlush();
                } else if (data == ACTION_CLOSE) {
                    // m_out.flush(); // flush y terminamos con el demonio
                    daemonFlush();
                    daemonClose();
                    bItsRunning = false;
                }
            }
        }
    }   
}
