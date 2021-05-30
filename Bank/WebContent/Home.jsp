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
.mydiv h1, .mydiv h2, .mydiv h3, .mydiv h5,.mydiv h4{
    text-align: center;
    color:rgb(28, 82, 145);
    font-weight: 1;
    font-family: Century Gothic;
}

.mydiv h2 {
	font-size : 30px;
}
.mydiv h3
{
	font-size : 25px;
}
.mydiv h4
{
	font-size : 22px;
}
.mydiv h5
{
	font-size : 20px;
}

h1{
    text-align: center;
    color:white;
    font-size: 50px;
    font-weight: 1;
    font-family: Century Gothic;
}
 a
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
 a:hover
{
 	text-decoration: underline;
}
input[type=submit] {
  background-color :rgb(28, 82, 145);
  color:white;
  font-family: Century Gothic;
  padding: 14px 20px;
  margin: 8px 0;
  margin-left : 40%;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
input[type=submit]:hover {
  background-color : rgb(16, 50, 88);
}
</style>
<% Customer currentCustomer = (Customer) session.getAttribute("SessionCustomer"); %>
<meta charset="windows-1256">
<title>Home</title>
</head>
<body>
<% if (currentCustomer.getAccount().getCreationDate().equalsIgnoreCase("0000")) { %>
<div class="mydiv">
<h1>You Don't Have Accounts.</h1>
<form action="addaccount" method="POST">
<input type="submit" value ="Add Account">
</form>
<br><br><br><br><br><br><br><br><br><br>
</div>
<% } else { %>
<h1>Welcome Mr. <% out.println(currentCustomer.getName()); %></h1>
<div class="mydiv">
<br><br>
<h2> Current Balance : <% out.println(currentCustomer.getAccount().getBalance()); %> EGP </h2>
<h3> Account Number :  <% out.println(currentCustomer.getAccount().getBankAccountNumber()); %></h3>
<h4> Account Creation Date :  <% out.println(currentCustomer.getAccount().getCreationDate().toString()); %></h4>
<h5> Address : <% out.println(currentCustomer.getAddress()); %></h5>
<h5> Mobile: <% out.println(currentCustomer.getMobile()); %></h5>
<br><br><br><br><br>
<a href="transactions" >List Of Transactions</a>
<input style="margin-left: 11%" type="submit" value ="Add Account" disabled>
<%} %>
<a style="margin-left: 18%" href="index.html" >Log Out</a>
<br><br><br><br>
</div>
</body>
</html>