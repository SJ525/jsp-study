<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.LoginModel"%> 
<jsp:useBean id="login" class="model.LoginModel" scope="session"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=2>
<div align="center"><br><br><br><br>
<h2>��ӭ��������Ա��ҳ</h2>
<div align="center">
 <Font size=4 color=red>
    <BR>��½����:
    <jsp:getProperty name="login"  property="backNews"/>
    <BR>���Ĺ����˺�:
    <jsp:getProperty name="login"  property="account"/>
 </Font>
 </div>
</body>
</html>
