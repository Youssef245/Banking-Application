<%-- 

	Name : Youssef Mohammed Fathi Abdelrassoul
	ID : 20180352
	Group : IS-S3

 --%>


<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html>
<html>
<head>
<style>
.myDiv {
  background-color: whitesmoke;
  display: block;
  margin-left: 30%;
  margin-right: 30%;

}
body {
  background-color: rgb(28, 82, 145);
  background-repeat: no-repeat;
  background-attachment: fixed;  
  background-size: cover;
}
h1{
    text-align: center;
    color:white;
    font-size: 50px;
    font-weight: 1;
    font-family: Century Gothic;
}
form {
    display: block;
    text-align: center;
    color: white;
     font-weight: 1;
    font-family: Century Gothic;  
}
input[type=text]
{
  width: 35%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}
input[type=submit] {
  background-color :rgb(28, 82, 145);
  color:white;
  font-family: Century Gothic;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
input[type=submit]:hover {
  background-color : rgb(16, 50, 88);
}

a
{
	color:white;
	font-size : 15px;
	background-color: rgb(28, 82, 145);
  	padding: 5px 10px;
 	text-align: center;
 	text-decoration: none;
  	display: inline-block;
  	margin-left: 38%;
  	font-family: Century Gothic;
}
 a:hover
{
 	text-decoration: underline;
}

</style>
<meta charset="windows-1256">
<title>Make Transaction</title>
</head>
<body>
<h1>Make A Transaction</h1>
<div class="myDiv">
<form action="MakeTransaction" method="POST">
<input type="Text" id="Amount" name="Amount" placeholder="Amount" required><br>
<input type="Text" id="ToAcc" name="ToAcc" placeholder="To Account" required><br>
<input type="submit" value ="Execute">
</form>
<br><br>
<a href="Home.jsp" >Back to Home</a>
<br><br><br><br>
</div>
</body>
</html>