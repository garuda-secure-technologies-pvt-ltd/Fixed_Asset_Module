<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<META HTTP-EQUIV="Refresh" CONTENT="3;URL=homePage.action">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.openbravo.pos.pda.struts.beans.GuestEntryBillPrint"  %>
    <%@ page import="java.util.ArrayList"  %>
     <%@ taglib uri="/struts-tags" prefix="s" %>
    <%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">

</script>
<!--  -->
</head>
<body  style="width: 96%;" onload="window.print();"  >
<% 
SimpleDateFormat s = new SimpleDateFormat("dd MMM, yyyy HH:mm:ss aaa"); 
                    
%>


<div id="pBody" >
<%GuestEntryBillPrint gebp = (GuestEntryBillPrint) request.getSession().getAttribute("gbp");
        String facility = "";
        String ttype = "";%>
<table border="0" >
                 <tr>
                    
                     <th > 
                     <font size="3" color="black" style="text-align: center;"><s:property value="#session.home.desc"/></font>  <br/>
                     <font size="1" style="color:black; text-align: center;" > <s:property value="#session.home.address"/></font><br/>
                     <font style="color:black; text-align: center; font-size:13px; "  > <s:text name="-----------------------------------------------------------"/></font><br/>
                     <font style="color:black; text-align: center; font-size:13px; font: bold; "  > <s:text name="Receipt"/></font><br/>
                     <font style="color:black; text-align: center; font-size:13px; "  > <s:text name="-----------------------------------------------------------"/></font><br/>
  
                     <font style="color:black; float: left; font-size:13px;  text-align: left; " > <s:text name="Receipt No:  "/> <%= gebp.getRecNo() %></font><br/>
                     <font style="color:black; float: left; font-size:13px;  text-align: left; " > <s:text name="Date:  "/> <%= s.format(new Date()) %></font><br/><br/>
                     
                     <font style="color:black; float: left; font-size:13px;  text-align: left; " > <s:text name="Membership No:  "/> <s:property value="#session.user.searchkey" /></font><br/>
                     <font style="color:black; float: left; font-size:13px;  text-align: left; " > <s:text name="Member Name  :  "/><s:property value="#session.user.name"/></font><br/>
                     <font style="color:black; text-align: center; font-size:13px; "  > <s:text name="-----------------------------------------------------------"/></font><br/>																																			
                     
                     <!--  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
                     </th>
                	
                </tr>     
        </table>



<div id="transactions" ><div >
				
            <table align="center" border="1"  style="word-wrap:'break-word'; border-collapse:collapse; text-align: center; " >
            	<tr style="font-size:15px; text-align: center; font: bold;"  ><th >Guest Type</th><th>Qty</th><th >Rate</th><th >Amount</th></tr>
            	
                <tr style="font-size:15px;" >
                    <td align="center" >&nbsp;<%=gebp.getgType()%>&nbsp;</td>
                    <td align="center" >&nbsp;<%=gebp.getQty()%>&nbsp;</td>
                    <td align="center" >&nbsp;<%=gebp.getRate()%>&nbsp;</td>
					<td align="center" >&nbsp;<%=gebp.getAmount()%>&nbsp;</td>

                </tr>
             

            </table> <br />
            </div>
            </div>
           
            <font style="color:black; float: left; font-size:18px; font: bold; text-align: left; font: bolder;" > <s:text name="Total :  "/></font><font style="color:black; float: left; font-size:18px; font: bold; text-align: left; " ><%=  "  "+gebp.getTotal()%></font><br/><br />
            <font style="color:black; float: left; font-size:13px; text-align: left; font: bold; " > <s:text  name="Name List :  "/></font><font style="color:black; float: left; font-size:13px; text-align: left; " >&nbsp; <%=" " + gebp.getNameList()%></font><br /><br />
            <font style="color:black; float: left; font-size:10px; " > <s:text name="EOE"/></font><br/><br />
            
            


</body>
</html>