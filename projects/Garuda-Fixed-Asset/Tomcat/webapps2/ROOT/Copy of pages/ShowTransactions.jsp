<%-- 
    Document   : index
    Created on : Mar 8, 2013, 2:43:49 PM
    Author     : user
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
    <%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
    <%@ page import="com.openbravo.pos.pda.struts.beans.TransactionInfo"  %>
    <%@ page import="java.util.ArrayList"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <sx:head />
    <head>
        <link rel="SHORTCUT ICON" href="images/favicon.ico">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name = "viewport" content = "user-scalable=no, width=device-width">
        <title img src="resources/Garuda.png"  size ="1" border="0"/>User Home Page</title>
        
<link rel=StyleSheet href="css/mystyle.css" type='text/css'>

   <script type="text/javascript"  src="javascript/panel.js">
   </script>
   <script type="text/javascript" src="javascript/canvas.js"></script>
  
   <script type="text/javascript" >
   
   var panelOneObjList = new Array();
   var panelTwoObjList = new Array();
   var advPanelListt = new Array();
   var panelOne = new Object();
   var panelTwo = new Object();
   var advt = new Object();
   
   function printDiv(divID) {
	   <%ArrayList<TransactionInfo> transac = (ArrayList<TransactionInfo>) request.getSession().getAttribute("tinfo");%>
	  <% if(transac.size()!=0)
		   {%>
		  
		   var popup = window.open("pages/TransactionPrint.jsp", "","fullscreen=1");
		   if (window.focus) 
		   {
			   popup.focus();
			}
		   <%  }
	   else
		   {%>
		   alert("No Transaction Details to print");
		   <%   }%>
	   
   }
   
   
   <s:subset  source="#session.home.upcmngEvts">
   
   <s:iterator >
    panelOne = new panelOneObjectCreator('<s:property value ="path"/>','<s:property value ="linkToWeb"/>','<s:property value ="panelName"/>' ,'<s:property value ="timeInSec"/>', '<s:property value ="evtDate"/>','<s:property value ="notes"/>' )
   	panelOneObjList.push(panelOne)
 	</s:iterator>
	</s:subset>  

<s:subset  source="#session.home.pastEvt">
<s:iterator >
panelTwo = new panelTwoObjectCreator('<s:property value ="path"/>','<s:property value ="linkToWeb"/>','<s:property value ="panelName"/>' ,'<s:property value ="timeInterval"/>','<s:property value ="evtDate"/>','<s:property value ="notes"/>'  )
panelTwoObjList.push(panelTwo)
</s:iterator>
</s:subset>  

<s:subset  source="#session.home.advt">
<s:iterator >

advt = new advertisementBean('<s:property value ="path"/>','<s:property value ="linkToWeb"/>','<s:property value ="panelName"/>' ,'<s:property value ="timeinterval"/>' )
advPanelListt.push(advt)
</s:iterator>
</s:subset>  

window.onload = function(){
	autologout()
	windowOnloadFunction(panelOneObjList, panelTwoObjList, advPanelListt)
	}
   
    </script>
    </head>
     <body style="text-align: center; widows: inherit; background-image: url(resources/backgroundImage.jpg) " id="body"  > 
    
       <table border="0" >
                 <tr>
                     <th class="logo">
                     <s:a href="" onclick="PopWin('%{session.home.webSite}')" >
                     <img src="img\<s:property value="#session.home.logoPath"/>" title="Visit Club Web Site...!!! " class="clogo"/>
                     </s:a>
                     </th>
                     
                     <th class="cname"> <font size="7" color="red" &nbsp&nbsp&nbsp&nbsp><s:property value="#session.home.desc"/></font>  <br/>
                        <font size="2" style="color: silver;" > <s:property value="#session.home.address"/></font></th>
                	<th class="logo1">
                     <s:a href="" >
                           <img src = "resources/GarudaNew.png" onclick="PopWin('http://www.garudasecuretech.com')" style="height: 100px; width: 180px;"/>
                            </s:a>
                     </th>
                </tr>     
        </table>
  
        <table border="1" >
                <tr>
                    <th class="main"> 
                        
                            <table border="1" >
                                <tr>
                                   
                                    <td style="height:30%"><font size="3" color="red"> <label id="panelOneLabel" ><s:property value="#session.home.desc"/></label><br/>
                                        <img src="resources/previous_icon.png"   onclick="PreviousImage()" />
                                        <s:a id = "linkToWeb"  href="" class="panel">
                                        <img src="" id="events"  class="panel" onclick="linkToWeb()" alt="upcoming/Alt_Panel1.jpg" />
                                        </s:a>
                                         <img src="resources/next_icon.png"   onclick="NextImage()" /><br/>
                                     	 <label id="panelOneDate" style="color: green; "></label>  <label id="panelOneNotes" style="color: green; "></label></font>
                                    
                                    </td>
                                </tr>
                                <tr>
                                    
                
                                 <td style="height:30px"> <font size="3" color="red"><label id="panelTwoLabel"><s:property value="#session.home.desc"/></label> <br/>
                                      <img src="resources/previous_icon.png"   onclick="PreviousPhoto()" /> 
                                      <s:a id = "linkToWeb2"  href="" class="panel">
                                      
                                     <img src=""  id="photos" class="panel" onclick="linkToWebForPhotos()"   alt="photos/Alt_Panel2.jpg"  />
                                     
                                     </s:a>
                                     <img src="resources/next_icon.png"   onclick="NextPhoto()" /><br />
                                     <label id="panelTwoDate" style="color: green; "></label>   <label id="panelTwoNotes" style="color: green; "></label></font>
                                     
                                 </td>
                                 </tr> 
                            </table>
                     </th>
                    <th class="login" style="height:60%" >
                    <table>
                        <tr>
                            <th>
                                        <table align="center" >    
                                            <tr>
                                                <th>
                                                <center>
                                                		<%ArrayList<TransactionInfo> transactions = (ArrayList<TransactionInfo>) request.getSession().getAttribute("tinfo");
        String facility = "";
        String ttype = "";
        if(transactions.size()==0)
            out.println("<p>No Transactions</p>");
        else
            {
            %>
			<div id="transactions" >
            <table align="center" border="10"  style=" background-color: activeborder; " >
            <div  >
                <tr><th style="color: navy;">Date</th><th style="color: navy;">TransNo</th><th style="color: navy;">Type</th><th style="color: navy;">Amt</th></tr>
			</div>


                <%
        for (TransactionInfo t : transactions) {
            /*
            if (t.getFacility() == null) {
            facility = "";
            } else {
            facility = t.getFacility();
            }

            if (t.getTranstype().equals("C")) {
            ttype = "Receipt";
            } else if (t.getTranstype().equals("D")) {
            ttype = "Bill";
            }*/

                %>
                <tr style="color: maroon;" >

                    <td align="center"><%=t.getTdate()%></td>
                    <td align="center"><%=t.getReceiptno()%></td>
                    <td align="center"><%=t.getFacility()%></td>
                    <%-- <td><%=facility%>&nbsp;<%=ttype%></td>--%>
					<td align="center"><%=t.getAmount()%></td>

                </tr>
                <% }}%>

            </table>
            </div>


</center>

            <br/>
                                           		
                                                		    	
                                                </th>
                                                
                                        </tr>

                                    </table>
                                    
                                    
            <a href='userHome.action'><img src="resources/home.png" onmouseover="this.src='resources/home_mo.png';" onmouseout="this.src='resources/home.png';" alt="Home" /></a>
            <a href="logout.action"><img src="resources/logoutm.png" onmouseover="this.src='resources/logoutm_mo.png';" onmouseout="this.src='resources/logoutm.png';" alt="LogOut"/> </a>
            <img src="resources/print.png" onmouseover="this.src='resources/print_mo.png';" onmouseout="this.src='resources/print.png';" alt="Print" onclick="javascript:printDiv('transactions')"" /> <br/>
<div class="errorMessage" >
                                               			<s:actionerror/>
                                               			</div>
                 
                      </tr>
                   
                </table>
                    <tr>
                    <table border="1">
            <tr>
                
                <th class="main"> 
                    
            <center>
                   
                    
                    
                 
                    <a href="news.action"><img src="resources/news.jpg" title="Go To News Letter "style="max-width:95%;border:6px outset #545565;" ></a>
                   
            </center>
                    
                  
           
                    
                </th>
                <td>   <center>
                    
                 
                  <div  >
                  <s:a id = "linkToWebAd1"  href="" class="advrtClass" value="" onclick="PopUpAdv(this.value)" ><img src="" id="adv1" class="advrtClass"  alt="adver/Alt_Advertise1.jpg" onclick="" /></s:a> <s:a id = "linkToWebAd2"  href="" class="advrtClass" onclick="PopUpAdv(this.value)" ><img src="" id="adv2" class="advrtClass"   alt="adver/Alt_Advertise3.jpg" /></s:a>
                   </div>
            </center>
                </td>     
            </tr>
        </table>    
                
           
        

         

    </body>
</html>
