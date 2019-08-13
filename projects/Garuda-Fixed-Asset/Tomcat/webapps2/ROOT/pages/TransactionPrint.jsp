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

</head>
<body  style="width: 97%;" onload="window.print(); window.close()" >
<% 
SimpleDateFormat s = new SimpleDateFormat("dd MMM, yyyy hh:mm:ss aaa"); 
                    
%>


<div id="pBody" >
<table border="0" >
                 <tr>
                    
                     <th > 
                     <font size="3" color="black" style="text-align: center;"><s:property value="#session.home.desc"/></font>  <br/>
                     <font size="1" style="color:black; text-align: center;" > <s:property value="#session.home.address"/></font><br/>
                     <font style="color:black; text-align: center; font-size:13px; "  > <s:text name="------------------------------------------------------------"/></font><br/>
                     <font style="color:black; float: left; font-size:13px;  text-align: left; " > <s:text name="Membership No:  "/> <s:property value="#session.user.searchkey" /></font><br/>
                     <font style="color:black; float: left; font-size:13px;  text-align: left;" > <s:text name="Member Name:  "/><s:property value="#session.user.name"/></font><br/>
                     <font style="color:black; float: left; font-size:13px;  text-align: left;" > <s:text name="Date:  "/> <%= s.format(new Date()) %></font><br /><br />																																			
                     <font style="color:black; text-align: center; font-size:13px; "  > <s:text name="Last Five Transaction Details"/></font><br/>
                     <!--  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
                     </th>
                	
                </tr>     
        </table>
<center>
<%ArrayList<TransactionInfo> transactions = (ArrayList<TransactionInfo>) request.getSession().getAttribute("tinfo");
        String facility = "";
        String ttype = "";%>

<div id="transactions" ><div >
            <table align="center" border="1"  style="word-wrap:'break-word'; border-collapse:collapse; text-align: center; "  >
            
                <tr style="font-size:13px; text-align: center; "  ><th >Date</th><th>Trans No</th><th >Type</th><th >Amount</th></tr>
			
                <%
        for (TransactionInfo t : transactions) {
                %>
                <tr style="font-size:14px;" >
					
                    <td align="center" >&nbsp;<%=t.getTdate()%>&nbsp;</td>
                    <td align="center" >&nbsp;<%=t.getReceiptno()%>&nbsp;</td>
                    <td align="center" >&nbsp;<%=t.getFacility()%>&nbsp;</td>
                    <%-- <td><%=facility%>&nbsp;<%=ttype%></td>--%>
					<td align="center" >&nbsp;<%=t.getAmount()%>&nbsp;</td>

                </tr>
                <% }%>

            </table>
            <font style="color:black; float: left; font-size:10px; " > <s:text name="E&OE"/></font><br/><br />
            </div>
            </div>
</center>
</div>
</body>
</html>