<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.LoginModel"%> 
<jsp:useBean id="login" class="model.LoginModel" scope="session"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=2>
<div align="center"><br><br><br><br>
<h2>欢迎来到管理员首页</h2>
<div align="center">
 <Font size=4 color=red>
    <BR>登陆反馈:
    <jsp:getProperty name="login"  property="backNews"/>
    <BR>您的管理账号:
    <jsp:getProperty name="login"  property="account"/>
 </Font>
 </div>
</body>
</html>
