<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.LoginModel"%> 
<jsp:useBean id="login" class="model.LoginModel" scope="session"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=2>
<div align="center"><br><br><br><br>
<h2>欢迎来到考生首页</h2>
</div>
<div align="center">
 <Font size=4 color=red>
    <BR>登陆反馈:
    <jsp:getProperty name="login"  property="backNews"/>
    <BR>您的考试账号:
    <jsp:getProperty name="login"  property="account"/>
 </Font>
 </div>
 <div align="center">
	<form action="readTestServlet?id=<%=login.getAccount()%>"  method="post" >
	   <br><input type=submit name="submit" value="开始考试">
	</form>
 </div>
</body>
</html>
