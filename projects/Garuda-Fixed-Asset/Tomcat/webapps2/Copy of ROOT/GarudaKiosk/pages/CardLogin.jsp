<%-- 
    Document   : index
    Created on : Mar 8, 2013, 2:43:49 PM
    Author     : user
--%>

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
        <title img src="resources/Garuda.png"  size ="1" border="0"/>Welcome to Garuda </title>
        <link href="http://code.jquery.com/ui/1.9.0/themes/ui-darkness/jquery-ui.css" rel="stylesheet">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.js"></script>
	<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.min.js"></script>
        
        
<link rel=StyleSheet href="css/mystyle.css" type='text/css'>

   <script type="text/javascript"  src="javascript/panel.js">
   </script>
   
  
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
	windowOnloadFunction(panelOneObjList, panelTwoObjList, advPanelListt)
	var myVar=setInterval(function(){myTimer()},1000);
	}


function myTimer()
{
	document.getElementById('keyboard').focus()
}
	

    </script>
    </head>
     <body style="text-align: center; widows: inherit; background-image: url(resources/backgroundImage.jpg) " id="body"  > 
    
        <table border="0" >
                 <tr>
                     <th class="logo">
                     <s:a href="" onclick="PopWin('%{session.home.webSite}')" >
                     <img src="img\<s:property value="#session.home.logoPath"/>" title="Visit Club Web Site...!!! " style="border:6px outset #545565;" size ="30" border="0"/>
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
                    <div style="color: teal; font-size: xx-large; font-style: italic; font-family: monospace; text-align: right;  " >
                    <marquee behavior="alternate"> 
						<label id="userName" ><s:text   name="Show your card to Card Reader "/></label><br />
						
						</marquee>
						<br />
                    </div>
                    <table>
                        <tr>
                            <th>
                            					
                            						
                                        <table>    
                                            <tr>
                                               <th><s:form name = "cardLoginAction" action="cardLoginAction.action" method="post" theme="simple" >    
                            
                            	<div>
								<div class="label"><s:text   name=""/></div>
								<div style="width: 0;overflow: hidden;"><input type="password" name="bean.searchkey"  theme="simple"  id="keyboard" name="login" value="" onmouseover="keyUsername()" style="width: 150px; height: 30px; border:5px outset #545565;"/></div>
								<div> <s:fielderror fieldName="bean.searchkey" theme="simple"/></div>
								<div id="MemNumbError"></div>
								</div>
                            	<br />
                            	<br />
                            	<br />
                            	<s:actionerror  />
								
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href='homePage.action'><img src="resources/home.png" onmouseover="this.src='resources/home_mo.png';" onmouseout="this.src='resources/home.png';" alt="Home" /></a> &nbsp;&nbsp;
                  			   
                  
                              <div class="errorMessage">          
                           
                             </div>
                             </s:form><div class="errorMessage" >
                                               			
                                               			<s:actionmessage/>
                                               			</div>
                                               </tr>
                                                
                                        </tr>

                                    </table>
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
                    
                  <font size="3" color="red"><label id="panelThreeLabel">Advertizement..! </label>  </font><br/>
                  <div  >
                  <s:a id = "linkToWebAd1"  href="" class="advrtClass" value="" onclick="PopUpAdv(this.value)" ><img src="" id="adv1" class="advrtClass"  alt="adver/Alt_Advertise1.jpg" onclick="" /></s:a> &nbsp;&nbsp;&nbsp;&nbsp; <s:a id = "linkToWebAd2"  href="" class="advrtClass" onclick="PopUpAdv(this.value)" ><img src="" id="adv2" class="advrtClass"   alt="adver/Alt_Advertise3.jpg" /></s:a>
                   </div>
            </center>
                </td>     
            </tr>
        </table>    
                
           
        

         

    </body>
</html>
