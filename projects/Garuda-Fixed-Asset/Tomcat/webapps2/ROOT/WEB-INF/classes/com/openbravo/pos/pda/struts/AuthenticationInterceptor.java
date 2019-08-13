/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.pda.struts;

import java.util.Map;

import com.openbravo.pos.pda.struts.actions.ActivateFacilityAction;
import com.openbravo.pos.pda.struts.actions.ActiveFacilitiesAction;
import com.openbravo.pos.pda.struts.actions.CancelRequestAction;
import com.openbravo.pos.pda.struts.actions.ChangePassword;
import com.openbravo.pos.pda.struts.actions.DeactivateFacilityAction;
import com.openbravo.pos.pda.struts.actions.LogOutAction;
import com.openbravo.pos.pda.struts.actions.MinUsageAction;
import com.openbravo.pos.pda.struts.actions.OtherFacilityAction;
import com.openbravo.pos.pda.struts.actions.ResetPassword;
import com.openbravo.pos.pda.struts.actions.RoomAndPartyHallBookingAction;
import com.openbravo.pos.pda.struts.actions.ShowBalance;
import com.openbravo.pos.pda.struts.actions.ShowEventsAction;
import com.openbravo.pos.pda.struts.actions.TransactionAction;
import com.openbravo.pos.pda.struts.actions.UserHomeAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
public class AuthenticationInterceptor implements Interceptor {

    public void destroy() {
        System.out.println("Inside Authentication Interceptor destroy()");
    }

    public void init() {
        System.out.println("Inside Authentication Interceptor Init()");
    }

    public String intercept(ActionInvocation ai) throws Exception {
        System.out.println("Inside Authentication Interceptor intercept()");
        
        if(ai.getAction() instanceof ActivateFacilityAction)
		{
			Map session = ai.getInvocationContext().getSession();
			if(session.get("user")!=null)
			{
				return ai.invoke();
			}
			else
			{
				return Constants.LOGIN;
			}
		}
        
        if(ai.getAction() instanceof ActiveFacilitiesAction)
		{
			Map session = ai.getInvocationContext().getSession();
			if(session.get("user")!=null)
			{
				return ai.invoke();
			}
			else
			{
				return Constants.LOGIN;
			}
		}

        if(ai.getAction() instanceof CancelRequestAction)
		{
			Map session = ai.getInvocationContext().getSession();
			if(session.get("user")!=null)
			{
				return ai.invoke();
			}
			else
			{
				return Constants.LOGIN;
			}
		}
        if(ai.getAction() instanceof ChangePassword)
		{
			Map session = ai.getInvocationContext().getSession();
			if(session.get("user")!=null)
			{
				return ai.invoke();
			}
			else 
			{
				return Constants.LOGIN;
			}
		}
        if(ai.getAction() instanceof DeactivateFacilityAction)
		{
			Map session = ai.getInvocationContext().getSession();
			if(session.get("user")!=null)
			{
				return ai.invoke();
			}
			else
			{
				return Constants.LOGIN;
			}
		}
        
        if(ai.getAction() instanceof LogOutAction)
		{
			Map session = ai.getInvocationContext().getSession();
			if(session.get("user")!=null)
			{
				return ai.invoke();
			}
			else
			{
				return Constants.LOGIN;
			}
		}
		
		if(ai.getAction() instanceof MinUsageAction)
		{
			Map session = ai.getInvocationContext().getSession();
			if(session.get("user")!=null)
			{
				return ai.invoke();
			}
			else
			{
				return Constants.LOGIN;
			}
		}
		
		if(ai.getAction() instanceof ResetPassword)
		{
			Map session = ai.getInvocationContext().getSession();
			if(session.get("user")!=null)
			{
				return ai.invoke();
			}
			else
			{
				return Constants.LOGIN;
			}
		}
		
		if(ai.getAction() instanceof OtherFacilityAction)
		{
			Map session = ai.getInvocationContext().getSession();
			if(session.get("user")!=null)
			{
				return ai.invoke();
			}
			else
			{
				return Constants.LOGIN;
			}
		}
		
		if(ai.getAction() instanceof RoomAndPartyHallBookingAction)
		{
			Map session = ai.getInvocationContext().getSession();
			if(session.get("user")!=null)
			{
				return ai.invoke();
			}
			else
			{
				return Constants.LOGIN;
			}
		}
		
		if(ai.getAction() instanceof ShowBalance)
		{
			Map session = ai.getInvocationContext().getSession();
			if(session.get("user")!=null)
			{
				return ai.invoke();
			}
			else
			{
				return Constants.LOGIN;
			}
		}
		
		if(ai.getAction() instanceof ShowEventsAction)
		{
			Map session = ai.getInvocationContext().getSession();
			if(session.get("user")!=null)
			{
				return ai.invoke();
			}
			else
			{
				return Constants.LOGIN;
			}
		}
		
		if(ai.getAction() instanceof TransactionAction)
		{
			Map session = ai.getInvocationContext().getSession();
			if(session.get("user")!=null)
			{
				return ai.invoke();
			}
			else
			{
				return Constants.LOGIN;
			}
		}
		
		if(ai.getAction() instanceof UserHomeAction)
		{
			Map session = ai.getInvocationContext().getSession();
			if(session.get("user")!=null)
			{
				return ai.invoke();
			}
			else
			{
				return Constants.LOGIN;
			}
		}
		
        return ai.invoke();
    }
    
}
