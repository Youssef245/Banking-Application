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
body {
	margin-top:10%;
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
.myDiv {

margin-left: 45%;

}
.myDiv button {
  background-color :white;
  color:rgb(28, 82, 145);
  font-family: Century Gothic;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
.myDiv button:hover {
   background-color : rgb(246, 246, 246);
}
</style>
<%String s=request.getParameter("val");%>
<meta charset="windows-1256">
<title>Confirm</title>
</head>
<body>
<h1> Do You Want to Cancel </h1>
<div class="myDiv">
<button onclick="window.location.href='CancelTransaction?val=<%=s%>'">Yes</button>
<button onclick="window.location.href='transactions.jsp'">No</button>
</div>
</body>
</html>