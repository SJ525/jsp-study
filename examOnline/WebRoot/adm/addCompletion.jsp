<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.CompletionModel"%>
<jsp:useBean id="addCompletion" class="model.CompletionModel" scope="request"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center">
<form action="addCompletion" method="post">
<p>�ֶ���������(ѡ����Ϣ�Զ�Ӧ�Ĵ�д��ĸ��ͷ)</p>
<table>
	<tr><td>��Ŀ��ţ�<input type="number" name="titleId" placeholder="���Ϊ�����������ظ�" ></td></tr>
	<tr><td>�������ɣ�<input type="text" name="titleStem"></td></tr>
	<tr><td>��ȷ��:<input type="text" name="answerRight"></td></tr>
	<tr><td>�𰸽���:<input type="text" name="answerExplain"></td></tr>
	<tr align="center">
		<td>
		<input type="submit" value="�ύ" name="submit">
		&nbsp;&nbsp;
		<input type="reset" value="����" name="reset">
		</td>
	</tr>
</table>
</form>
<form action="showCompletion" method="get">
	<input type="submit" value="����鿴���������" name="submit">
</form>
</div>
</font>
</body>
</html>
