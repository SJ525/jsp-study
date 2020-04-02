<%@ page contentType="text/html;charset=GB2312" %>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center"><br>
<form action="addStuMsg" method="post">
<p>请录入考生的信息：</p>
<table>
	<tr><td>姓名：<input type="text" name="name" ></td></tr>
	<tr><td>专业：<input type="text" name="major" ></td></tr>
	<tr><td>班级：<input type="text" name="grade" ></td></tr>
	<tr><td>账号：<input type="text" name="account" placeholder="账号为主键，不可重复"></td></tr>
	<tr><td>密码：<input type="text" name="password"></td></tr>
	<tr align="center">
		<td>
		<input type="submit" value="提交">
		&nbsp;&nbsp;
		<input type="reset" value="重置">
		</td>
	</tr>
</table>
</form>
<br>
<form action="showStuMsg" method="get">
	<input type="submit" value="点击查看已有考生" name="submit">
</form>
</div>
</font>
</body>
</html>
