<%-- 
    Document   : index
    Created on : Mar 8, 2013, 2:43:49 PM
    Author     : user
--%>
<%@page  import="com.openbravo.pos.pda.struts.beans.EventsInfo,java.util.ArrayList,java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
    <%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
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
                    <div style="color: teal; font-size: xx-large; font-style: italic; font-family: monospace; " >
                    <marquee behavior="alternate"> 
						<label id="userName" ><s:text   name="Welcome  "/><s:property value="#session.user.name"/></label>
						</marquee>
                    </div>
                    <table>
                        <tr>
                            <th>
                            					
                            						
                                        <table>    
                                            <tr>
                                               <th>
                                                    	
                                                    	

            <% ArrayList<EventsInfo> eventsinf = (ArrayList<EventsInfo>) request.getSession().getAttribute("eventsInfo");
                
            %>
            <%
        String temp = null;
        for (EventsInfo eventinfo : eventsinf) {

            if (temp == null) {
                out.println("<table border='2' align='left' style='background-color:activeborder; color: blue;' max-width: '150px;' word-wrap:'break-word'; >");
                out.println("<thead>" + eventinfo.getEdate() + "</thead>");
                out.println("<tr><th>Name</th><th>Description</th><th title='StartTime'>STime</th><th title='EndTime'>ETime</th></tr>");
                out.println("<tr>");
                out.println("<td>" + eventinfo.getEname() + "</td>");
                out.println("<td >" + eventinfo.getDescription() + "</td>");
                out.println("<td>" + eventinfo.getStime() + "</td>");
                out.println("<td>" + eventinfo.getEtime() + "</td>");
                out.println("</tr>");
                temp = eventinfo.getEdate();
            } else if (temp.equals(eventinfo.getEdate())) {
                out.println("<tr  >");
                out.println("<td  > " + eventinfo.getEname() + "</td>");
                out.println("<td >" + eventinfo.getDescription() + "</td>");
                out.println("<td  >" + eventinfo.getStime() + "</td>");
                out.println("<td >" + eventinfo.getEtime() + "</td>");
                out.println("</tr>");
                temp = eventinfo.getEdate();
            } else {
                out.println("</table>");
                out.println("<table>");
                out.println("<thead>" + eventinfo.getEdate() + "</thead>");
                out.println("<tr><th>Name</th><th>Description</th><th title='StartTime'>STime</th><th title='EndTime'>ETime</th></tr>");
                out.println("<tr>");
                out.println("<td > " + eventinfo.getEname() + "</td>");
                out.println("<td >" + eventinfo.getDescription() + "</td>");
                out.println("<td>" + eventinfo.getStime() + "</td>");
                out.println("<td>" + eventinfo.getEtime() + "</td>");
                out.println("</tr>");
                temp = eventinfo.getEdate();
            }
        }
        out.println("</table>");
        if(temp==null)
                 out.println("<p style='background-color:activeborder; color: blue;' >No Events </p>");
            %>

                                         
                                               </tr>
                                                
                                        </tr>

                                    </table>
                      </tr>
                      
                   
                </table>
                <br/>
                <a href='userHome.action'><img src="resources/home.png" onmouseover="this.src='resources/home_mo.png';" onmouseout="this.src='resources/home.png';" alt="Home" /></a>&nbsp;&nbsp;
                <a href="logout.action"><img src="resources/logoutm.png" onmouseover="this.src='resources/logoutm_mo.png';" onmouseout="this.src='resources/logoutm.png';" alt="LogOut"/> </a>           	
                                                    	<div class="errorMessage" >
                                               			<s:actionerror/>
                                               			</div>
                    <tr>
                    <table border="1">
            <tr>
                
                <th class="main"> 
                    
            <center>
                    <a href="http://www.google.com"><img src="resources/news.jpg" title="Go To News Letter "style="max-width:95%;border:6px outset #545565;" ></a>
                   
            </center>
                </th>
                <td>   <center>
                    
                  
                  <div  >
                  <s:a id = "linkToWebAd1"  href="" class="advrtClass" value="" onclick="PopUpAdv(this.value)" ><img src="" id="adv1" class="advrtClass"  alt="adver/Alt_Advertise1.jpg" onclick="" /></s:a>  <s:a id = "linkToWebAd2"  href="" class="advrtClass" onclick="PopUpAdv(this.value)" ><img src="" id="adv2" class="advrtClass"   alt="adver/Alt_Advertise3.jpg" /></s:a>
                   </div>
            </center>
                </td>     
            </tr>
        </table>    
    </body>
</html>
