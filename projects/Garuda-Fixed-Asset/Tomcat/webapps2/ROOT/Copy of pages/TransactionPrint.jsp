<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.openbravo.pos.pda.struts.beans.TransactionInfo"  %>
    <%@ page import="java.util.ArrayList"  %>
     <%@ taglib uri="/struts-tags" prefix="s" %>
    <%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
window.onload = function(){
	setInterval(window.close(),	1000);
	
}
</script>

<style type="text/css">
table.myTable { border-collapse:collapse; }
table.myTable td, table.myTable th { border:1px solid black;padding:5px; }
</style>

</head>
<body  style="width: 90%;" onload="window.print(); window.close()" >
<% 
SimpleDateFormat s = new SimpleDateFormat("dd-MMM-yyyy h:mm:ss a"); 
                    
%>

<center>
<div id="pBody" >
<table border="0" >
                 <tr>
                    
                     <th > 
                     <font style=" text-align: center; font: bold; font-family:Times New Roman; " size="5" ><s:property value="#session.home.desc"/></font>  <br/>
                     <font style=" text-align: center;  " size="2" > <s:property value="#session.home.address"/></font><br/><br/>
                     <font style=" text-align: left; float: left;  " size="3" > <s:text name="Membership No:  "/> <s:property value="#session.user.searchkey" /></font><br/><br/>
                     <font style=" text-align: left; float: left; " size="3" > <s:text name="Member Name  :  "/> </font> <font style=" text-align: left; " size="3" ><s:property value="#session.user.name"/></font><br/><br/>
                     <font style=" text-align: right; float: left; " size="3"  > <s:text name="Date:  "/> <%= s.format(new Date()) %></font><br /><br/>																																			
                     <font style=" text-align: center;" size="3"   > <s:text name="Last Five Transaction Details"/></font><br/><br/>
                     <!--  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
                     </th>
                	
                </tr>     
        </table>

<%ArrayList<TransactionInfo> transactions = (ArrayList<TransactionInfo>) request.getSession().getAttribute("tinfo");
        String facility = "";
        String ttype = "";%>

<div id="transactions" >
            <table  class="myTable"   >
           
                <tr style=" text-align: left; border:1px solid black; " size="3"  ><th >Date</th><th>TransactionNo</th><th >Type</th><th >Amount</th></tr>
			
                <%
        for (TransactionInfo t : transactions) {
                %>
                <tr  >
					
                    <td align="center"  size="3" >&nbsp;<%=t.getTdate()%>&nbsp;</td>
                    <td align="center" >&nbsp;<%=t.getReceiptno()%>&nbsp;</td>
                    <td align="center" >&nbsp;<%=t.getFacility()%>&nbsp;</td>
                    <%-- <td><%=facility%>&nbsp;<%=ttype%></td>--%>
					<td align="center" >&nbsp;<%=t.getAmount()%>&nbsp;</td>

                </tr>
                <% }%>

            </table>
            </div>
            </div>
</center>

</body>
</html>