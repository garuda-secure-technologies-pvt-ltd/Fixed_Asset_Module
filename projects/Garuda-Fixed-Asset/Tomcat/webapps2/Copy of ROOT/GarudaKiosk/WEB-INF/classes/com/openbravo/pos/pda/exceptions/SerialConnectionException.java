package com.openbravo.pos.pda.exceptions;

public class SerialConnectionException extends Exception {
	
	public SerialConnectionException(String str) {
        super(str);
    }

    /**
     * Constructs a <code>SerialConnectionException</code>
     * with no detail message.
     */
    public SerialConnectionException() {
        super();
    }

}
