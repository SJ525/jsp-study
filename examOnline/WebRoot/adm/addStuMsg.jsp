<%@ page contentType="text/html;charset=GB2312" %>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center"><br>
<form action="addStuMsg" method="post">
<p>��¼�뿼������Ϣ��</p>
<table>
	<tr><td>������<input type="text" name="name" ></td></tr>
	<tr><td>רҵ��<input type="text" name="major" ></td></tr>
	<tr><td>�༶��<input type="text" name="grade" ></td></tr>
	<tr><td>�˺ţ�<input type="text" name="account" placeholder="�˺�Ϊ�����������ظ�"></td></tr>
	<tr><td>���룺<input type="text" name="password"></td></tr>
	<tr align="center">
		<td>
		<input type="submit" value="�ύ">
		&nbsp;&nbsp;
		<input type="reset" value="����">
		</td>
	</tr>
</table>
</form>
<br>
<form action="showStuMsg" method="get">
	<input type="submit" value="����鿴���п���" name="submit">
</form>
</div>
</font>
</body>
</html>
