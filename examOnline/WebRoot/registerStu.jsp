<%@ page contentType="text/html;charset=GB2312" %>
<jsp:useBean id="stuBean" class="model.StuModel" scope="request"/>

<html>
<head><%@ include file="head.txt" %></head>
<title>��ҳ</title>
<body>
<div align="center"><br>
<form action="handleStuRegister" method="post">
	<table border=2 cellpadding="3">
		<tr align="center">
			<td  colspan="2">
			<b><p>����ע��</p>
			</td>
		</tr>
		<tr>
			<td>�û��˺ţ�</td>
			<td><input type=text name="account" size=36></td>
		</tr>
		<tr>
			<td>�û����룺</td>
			<td><input type=password name="password" size=38></td>
		</tr>
		<tr>
			<td>ȷ�����룺</td>
			<td><input type=password name="repassword" size=38></td>
		</tr>
		<tr>
			<td>������</td>
			<td><input type=text name="name" size=36></td>
		</tr>
		<tr>
			<td>רҵ��</td>
			<td><input type=text name="major" size=36></td>
		</tr>
		<tr>
			<td>�༶��</td>
			<td><input type=text name="grade" size=36></td>
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
<jsp:getProperty name="stuBean"  property="backNews" /> 
<table border=2 id="stuRegBack" cellpadding="10" width="200">
     <tr><td>�����˺�:</td>
     <td><jsp:getProperty name="stuBean" property="account"/></td>
     </tr>
     <tr ><td>��������:</td>
     <td><jsp:getProperty name="stuBean" property="name"/></td>
     </tr>
     <tr><td>����רҵ:</td>
     <td><jsp:getProperty name="stuBean" property="major"/></td>
     </tr>
     <tr><td>���ڰ༶:</td>
     <td><jsp:getProperty name="stuBean" property="grade"/></td>
     </tr>
</table>
</div>
</body></html>
