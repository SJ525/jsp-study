<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.LoginModel"%> 
<jsp:useBean id="login" class="model.LoginModel" scope="session"/>
<html>
<head><%@ include file="head.txt" %></head>
<title>首页</title>
<script type="text/javascript" src="js/check.js" charset="utf-8"></script>
<body>
<div align="center"><br><br>
<form action="handleAdmLogin" method="post">
	<table border=2 cellpadding="8">
		<tr align="center"><th>
			<p>管理员登陆</p>
		</th></tr>
		<tr><td><input type=text name="account" placeholder="请输入账号"  size=35></td></tr>
		<tr><td><input type=password name="password" placeholder="请输入密码" size=37></td></tr>
	</table>
	<br>
	<input type="submit" name="login" value="登陆" onclick=login_Check()>
</form>
<a href="registerAdm.jsp" >没有账号？点击注册</a>
</div>

<div align="center">
 <Font size=4 color=red>
    <BR>登陆反馈:
    <jsp:getProperty name="login"  property="backNews"/>
 </Font>
 </div>
</body></html>
