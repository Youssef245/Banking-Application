<%-- 

	Name : Youssef Mohammed Fathi Abdelrassoul
	ID : 20180352
	Group : IS-S3

 --%>


<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html>
<html>
<%@ page import ="Banking.Customer" %>
<%@ page import ="Banking.BankAccount" %>
<%@ page import ="Banking.BankTransaction" %>
<head>
<style>
body {
		  background-color: rgb(28, 82, 145);
		  background-repeat: no-repeat;
		  background-attachment: fixed;  
		  background-size: cover;
}
.mydiv {
  background-color: whitesmoke;
  display: block;
  margin-left: 20%;
  margin-right: 20%;

}
table, th, td { 
	margin-left : 25%;
    border: 1px solid rgb(28, 82, 145);
    color:rgb(28, 82, 145);
    border-collapse: collapse;
    text-align: center;  
}
th, td {
	padding: 8px;  
}
th{
	background-color: rgb(28, 82, 145);
	color: white;

}
tr:nth-child(even){background-color: #f2f2f2;}
tr {background-color: white}
tr:hover {background-color: #ddd;}
.mydiv a{
	text-decoration: none;
	color:rgb(28, 82, 145);
}
.mydiv a:hover
{
 	text-decoration: underline;
}
.aydiv a
{
	color:white;
	font-size : 20px;
	background-color: rgb(28, 82, 145);
  	padding: 14px 25px;
 	text-align: center;
 	text-decoration: none;
  	display: inline-block;
  	margin-left: 5%;
  	font-family: Century Gothic;
}

.aydiv a:hover
{
 	text-decoration: underline;
}
.NoTransaction a
{
	color:rgb(28, 82, 145);
	font-size : 20px;
	background-color: white;
  	padding: 14px 25px;
 	text-align: center;
 	text-decoration: none;
  	display: inline-block;
  	margin-left: 5%;
  	font-family: Century Gothic;
}

 .NoTransaction a:hover
{
 	text-decoration: underline;
}



h1{
    text-align: center;
    color:white;
    font-size: 50px;
    font-weight: 1;
    font-family: Century Gothic;
}
</style>
<% Customer currentCustomer = (Customer) session.getAttribute("SessionCustomer"); %>
<meta charset="windows-1256">
<title>Transactions</title>
</head>
<body>
<%if (currentCustomer.getAccount().getTransactions().isEmpty()) { %>
<h1> You Don't Have Any Transactions</h1>
<div class="NoTransaction">
<a style="margin-left: 30%" href="MakeTransaction.jsp" >Make Transaction</a>
<a href="Home.jsp" >Back to Home</a>
</div>
<%} else { %>
<h1> Transactions </h1>
<div class="mydiv">
<br><br>
<table>
	<tr>
		<th>Creation Date</th>
		<th>Amount</th>
		<th>From Account</th>
		<th>To Account</th>
	</tr>
		
<%for (int i=0;i<currentCustomer.getAccount().getTransactions().size();i++) { %>
	<tr>
		<td><% out.println(currentCustomer.getAccount().getTransaction(i).getCreationDate()); %></td>
		<td><% out.println(currentCustomer.getAccount().getTransaction(i).getAmount()); %></td>
		<td><% out.println(currentCustomer.getAccount().getTransaction(i).getFromAccount());%></td>
		<td><% out.println(currentCustomer.getAccount().getTransaction(i).getToAccount());%></td>
		<td> <a class="adiv" href="Confirm.jsp?val=<%=currentCustomer.getAccount().getTransaction(i).getID()%>">Cancel</a></td>
	</tr>
	
<%} %>
</table>
<br><br><br><br><br><br><br><br><br>
<div class="aydiv">
<a href="MakeTransaction.jsp" >Make Transaction</a>
<a style="margin-left: 40%;" href="Home.jsp" >Back to Home</a>
<br><br><br>
</div>
<%} %>
</div>
</body>
</html>