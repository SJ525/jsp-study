<%@ page contentType="text/html;charset=GB2312" %>
<jsp:useBean id="admBean" class="model.AdmModel" scope="request"/>
<html>
<head><%@ include file="head.txt" %></head>
<title>首页</title>
<body>
<div align="center"><br>
<form action="handleAdmRegister" method="post">
	<table border=2 cellpadding="3">
		<tr align="center">
			<td  colspan="2">
			<b><p>管理员注册</p>
			</td>
		</tr>	
		<tr>
			<td>姓名：</td>
			<td><input type=text name="name" size=35></td>
		</tr>
		<tr>
			<td>账号：</td>
			<td><input type=text name="account" size=35></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input type=password name="password" size=37></td>
		</tr>
		<tr>
			<td>确认密码：</td>
			<td><input type=password name="repassword" size=37></td>
		</tr>
	</table>
	<br>
	<input type="submit" name="register" value="注册">
	&nbsp;&nbsp;&nbsp;
	<input type="reset" value="重置" name="reset">
</form>
</div>
<div align="center">
<p> 注册反馈：
<jsp:getProperty name="admBean"  property="backNews" /> 
<table border=2 cellpadding="10" width="200">
     <tr><td>管理员账号:</td>
     <td><jsp:getProperty name="admBean" property="account"/></td>
     </tr>
     <tr><td>管理员姓名:</td>
     <td><jsp:getProperty name="admBean" property="name"/></td>
     </tr>
</table>
</div>
</body></html>
