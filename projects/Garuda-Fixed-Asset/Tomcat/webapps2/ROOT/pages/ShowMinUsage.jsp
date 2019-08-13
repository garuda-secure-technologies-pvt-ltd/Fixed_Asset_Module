<%-- 
    Document   : index
    Created on : Mar 8, 2013, 2:43:49 PM
    Author     : user
--%>
<%@page import="com.openbravo.pos.pda.struts.beans.MinimumUsageInfo,java.util.ArrayList"%>
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
                        
                            <table border="0" >
                                <tr style="height: 40%">
                                   
                                    <td ><font size="3" color="red"> <label id="panelOneLabel" ><s:property value="#session.home.desc"/></label><br/>
                                        <img src="resources/previous_icon.png"   onclick="PreviousImage()" />
                                        <s:a id = "linkToWeb"  href="" class="panel">
                                        <img src="" id="events"  class="panel" onclick="linkToWeb()" alt="upcoming/Alt_Panel1.jpg"  onError="this.onerror=null;this.src='upcoming/Alt_Panel1.jpg';"/>
                                        </s:a>
                                         <img src="resources/next_icon.png"   onclick="NextImage()" /><br/>
                                     	 <label id="panelOneDate" style="color: green; "></label>    <label id="panelOneNotes" style="color: green; "></label></font>
                                    
                                    </td>
                                </tr>
                                <tr style="height: 40%">
                                    
                
                                 <td> <font size="3" color="red"><label id="panelTwoLabel"><s:property value="#session.home.desc"/></label> <br/>
                                      <img src="resources/previous_icon.png"   onclick="PreviousPhoto()" /> 
                                      <s:a id = "linkToWeb2"  href="" class="panel">
                                      
                                     <img src=""  id="photos" class="panel" onclick="linkToWebForPhotos()"   alt="photos/Alt_Panel2.jpg" onError="this.onerror=null;this.src='photos/Alt_Panel2.jpg';" />
                                     
                                     </s:a>
                                     <img src="resources/next_icon.png"   onclick="NextPhoto()" /><br />
                                     <label id="panelTwoDate" style="color: green; "></label>   <label id="panelTwoNotes" style="color: green; "></label></font>
                                     
                                 </td>
                                 </tr> 
                                 <tr style="height: 40%">
                                     <th class="main" align="justify"> 
                    
                                           <div class="NewsText">
                                         <s:text name="Click here for Current News Letter"></s:text>
                                         </div> 
                                         <br/>
                                             <a href=""><img src="resources/news.jpg" title="Go To News Letter "style="max-width:95%;border:6px outset #545565;" onclick="callBrowseHierarchy()"></a>
                   
                                     </th>
                                 </tr>
                            </table>
                     </th>
                     
                     <th>
                        
                <table>
                    <tr>
                    <th class="login" style="height:60%" >
                    <div style="color: teal; font-size: xx-large; font-style: italic; font-family: monospace; " >
                    <marquee behavior="alternate"> 
						<label id="userName" ><s:text   name="Welcome  "/><s:property value="#session.user.name"/></label>
						</marquee>
                    </div>
                                                <br/>
                    </th>
                    </tr>
                    
                        <tr>
                            <th>
                                <table>
                                    <tr>
                                        <th>
                          
                                                    	<%ArrayList<MinimumUsageInfo> minfos = (ArrayList<MinimumUsageInfo>) request.getSession().getAttribute("muinfo");
        double deficit = 0.0, limit = 0.0, usage = 0.0;
        String msg=(String)request.getSession().getAttribute("msg");

        if(minfos.size()==0)
            out.println("No Minimum Usages");

        else if (minfos.size() > 1) {
            out.println("<table border='2' align='left' style='background-color:activeborder; color: blue;' >");
            out.println("<tr><th>MinUsage</th><th>Limit</th></tr>");
            for (MinimumUsageInfo minfo : minfos) {
                limit += minfo.getLimit();
                usage = minfo.getUsage();
                /*deficit = minfo.getLimit() - minfo.getUsage();
                if (deficit < 0) {
                deficit = 0.0;
                }*/


        %>
                            </th>
                        </tr>
                        <tr>
            <td><%=minfo.getMname()%></td>
            <td><%=minfo.getLimit()%></td>

        </tr>

        <% }
            deficit = limit - usage;
            if (deficit < 0) {
                deficit = 0.0;
            }

            // out.println("<tr ><td colspan='2'>Deficit =" + limit + "-" + usage + "=" + deficit + "</td></tr>");
            out.println("<tr ><td colspan='2' align='right'>" + limit + "</td></tr>");
            out.println("<tr ><td >Usage   </td><td>" + usage + "</td></tr>");
            out.println("<tr ><td >Deficit    </td><td>" + deficit + "</td></tr>");
            out.println("</table >");
        } else {
            out.println("<table border='2' align='left' style='background-color:activeborder; color: blue;'>");
            out.println("<tr><th>MinUsage</th><th>Limit</th><th>Usage</th><th>Deficit</th></tr>");
            for (MinimumUsageInfo minfo : minfos) {

                deficit = minfo.getLimit() - minfo.getUsage();
                if (deficit < 0) {
                    deficit = 0.0;
                }


        %>
        <tr>
            <td><%=minfo.getMname()%></td>
            <td><%=minfo.getLimit()%></td>
            <td><%=minfo.getUsage()%></td>
            <td><%=deficit%></td>

        </tr>

        <% }


            out.println("</table>");
        }
        %>
                                </table>
          
          <tr>
              <th>
        
        <%=msg%>
        <br />
        <br />
            <a href='userHome.action'><img src="resources/home.png" onmouseover="this.src='resources/home_mo.png';" onmouseout="this.src='resources/home.png';" alt="Home" /></a>&nbsp;&nbsp;
                <a href="logout.action"><img src="resources/logoutm.png" onmouseover="this.src='resources/logoutm_mo.png';" onmouseout="this.src='resources/logoutm.png';" alt="LogOut"/> </a>           	
            </center> 
                                                    	
                                                    	<div class="errorMessage" >
                                               			<s:actionerror/>
                
                  <br/><br/><br/>                                     </div>
              </th>
          </tr>
           <tr>
                    <table border="1">
            <tr>
                
                
                <td>   <center>
                    
                  
                  <div  >
                  <s:a id = "linkToWebAd1"  href="" class="advrtClass" value="" onclick="PopUpAdv(this.value)" ><img src="" id="adv1" class="advrtClass"  alt="adver/Alt_Advertise1.jpg" onclick="" /></s:a> <s:a id = "linkToWebAd2"  href="" class="advrtClass" onclick="PopUpAdv(this.value)" ><img src="" id="adv2" class="advrtClass"   alt="adver/Alt_Advertise3.jpg" /></s:a>
                   </div>
            </center>
                </td>     
            </tr>
        </table>    
                </tr>
        </table>
                     </th>
                </tr>
        </table>
               
           
        

         

    </body>
</html>
