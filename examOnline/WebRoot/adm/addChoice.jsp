<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.ChoiceModel"%>
<jsp:useBean id="addChoice" class="model.ChoiceModel" scope="request"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center">
<form action="addChoice" method="post">
<p>�ֶ����ѡ����(ѡ����Ϣ�Զ�Ӧ�Ĵ�д��ĸ��ͷ)</p>
<table>
	<tr><td>��Ŀ��ţ�<input type="number" name="titleId" placeholder="���Ϊ�����������ظ�" ></td></tr>
	<tr><td>ѡ������ɣ�<input type="text" name="titleStem"></td></tr>
	<tr><td>ѡ��A��<input type="text" name="optionA"></td></tr>
	<tr><td>ѡ��B��<input type="text" name="optionB"></td></tr>
	<tr><td>ѡ��C��<input type="text" name="optionC"></td></tr>
	<tr><td>ѡ��D��<input type="text" name="optionD"></td></tr>
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
<form action="showChoice" method="get">
	<input type="submit" value="����鿴����ѡ����" name="submit">
</form>
</div>
</font>
</body>
</html>
