<%@ page contentType="text/html;charset=GB2312" %>
<jsp:useBean id="admBean" class="model.AdmModel" scope="request"/>
<html>
<head><%@ include file="head.txt" %></head>
<title>��ҳ</title>
<body>
<div align="center"><br>
<form action="handleAdmRegister" method="post">
	<table border=2 cellpadding="3">
		<tr align="center">
			<td  colspan="2">
			<b><p>����Աע��</p>
			</td>
		</tr>	
		<tr>
			<td>������</td>
			<td><input type=text name="name" size=35></td>
		</tr>
		<tr>
			<td>�˺ţ�</td>
			<td><input type=text name="account" size=35></td>
		</tr>
		<tr>
			<td>���룺</td>
			<td><input type=password name="password" size=37></td>
		</tr>
		<tr>
			<td>ȷ�����룺</td>
			<td><input type=password name="repassword" size=37></td>
		</tr>
	</table>
	<br>
	<input type="submit" name="register" value="ע��">
	&nbsp;&nbsp;&nbsp;
	<input type="reset" value="����" name="reset">
</form>
</div>
<div align="center">
<p> ע�ᷴ����
<jsp:getProperty name="admBean"  property="backNews" /> 
<table border=2 cellpadding="10" width="200">
     <tr><td>����Ա�˺�:</td>
     <td><jsp:getProperty name="admBean" property="account"/></td>
     </tr>
     <tr><td>����Ա����:</td>
     <td><jsp:getProperty name="admBean" property="name"/></td>
     </tr>
</table>
</div>
</body></html>
