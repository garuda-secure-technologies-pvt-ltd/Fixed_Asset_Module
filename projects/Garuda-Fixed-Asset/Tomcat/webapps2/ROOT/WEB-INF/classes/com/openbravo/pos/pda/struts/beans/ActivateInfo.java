package com.openbravo.pos.pda.struts.beans;

import java.util.HashMap;

public class ActivateInfo {
	
	private HashMap<String,String> success;
	private HashMap<String,String> failure;
	private String[] succ;
	private String[] fail;

	    public HashMap<String, String> getFailure() {
	        return failure;
	    }

	    public void setFailure(HashMap<String, String> failure) {
	        this.failure = failure;
	    }

	    public HashMap<String, String> getSuccess() {
	        return success;
	    }

	    public void setSuccess(HashMap<String, String> success) {
	        this.success = success;
	    }

	    

	    public String[] getFail() {
	        return fail;
	    }

	    public void setFail(String[] fail) {
	        this.fail = fail;
	    }

	    public String[] getSucc() {
	        return succ;
	    }

	    public void setSucc(String[] succ) {
	        this.succ = succ;
	    }


}
