<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.LoginModel"%> 
<jsp:useBean id="login" class="model.LoginModel" scope="session"/>
<html>
<head><%@ include file="head.txt" %></head>
<title>��ҳ</title>
<script type="text/javascript" src="js/check.js" charset="utf-8"></script>
<body>
<div align="center"><br><br>
<form action="handleAdmLogin" method="post">
	<table border=2 cellpadding="8">
		<tr align="center"><th>
			<p>����Ա��½</p>
		</th></tr>
		<tr><td><input type=text name="account" placeholder="�������˺�"  size=35></td></tr>
		<tr><td><input type=password name="password" placeholder="����������" size=37></td></tr>
	</table>
	<br>
	<input type="submit" name="login" value="��½" onclick=login_Check()>
</form>
<a href="registerAdm.jsp" >û���˺ţ����ע��</a>
</div>

<div align="center">
 <Font size=4 color=red>
    <BR>��½����:
    <jsp:getProperty name="login"  property="backNews"/>
 </Font>
 </div>
</body></html>
