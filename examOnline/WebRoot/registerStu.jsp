<%@ page contentType="text/html;charset=GB2312" %>
<jsp:useBean id="stuBean" class="model.StuModel" scope="request"/>

<html>
<head><%@ include file="head.txt" %></head>
<title>首页</title>
<body>
<div align="center"><br>
<form action="handleStuRegister" method="post">
	<table border=2 cellpadding="3">
		<tr align="center">
			<td  colspan="2">
			<b><p>考生注册</p>
			</td>
		</tr>
		<tr>
			<td>用户账号：</td>
			<td><input type=text name="account" size=36></td>
		</tr>
		<tr>
			<td>用户密码：</td>
			<td><input type=password name="password" size=38></td>
		</tr>
		<tr>
			<td>确认密码：</td>
			<td><input type=password name="repassword" size=38></td>
		</tr>
		<tr>
			<td>姓名：</td>
			<td><input type=text name="name" size=36></td>
		</tr>
		<tr>
			<td>专业：</td>
			<td><input type=text name="major" size=36></td>
		</tr>
		<tr>
			<td>班级：</td>
			<td><input type=text name="grade" size=36></td>
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
<jsp:getProperty name="stuBean"  property="backNews" /> 
<table border=2 id="stuRegBack" cellpadding="10" width="200">
     <tr><td>考生账号:</td>
     <td><jsp:getProperty name="stuBean" property="account"/></td>
     </tr>
     <tr ><td>考生姓名:</td>
     <td><jsp:getProperty name="stuBean" property="name"/></td>
     </tr>
     <tr><td>考生专业:</td>
     <td><jsp:getProperty name="stuBean" property="major"/></td>
     </tr>
     <tr><td>所在班级:</td>
     <td><jsp:getProperty name="stuBean" property="grade"/></td>
     </tr>
</table>
</div>
</body></html>
