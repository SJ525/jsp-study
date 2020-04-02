<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.CompletionModel"%>
<jsp:useBean id="addCompletion" class="model.CompletionModel" scope="request"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center">
<form action="addCompletion" method="post">
<p>手动添加填空题(选项信息以对应的大写字母开头)</p>
<table>
	<tr><td>题目序号：<input type="number" name="titleId" placeholder="序号为主键，不可重复" ></td></tr>
	<tr><td>填空题题干：<input type="text" name="titleStem"></td></tr>
	<tr><td>正确答案:<input type="text" name="answerRight"></td></tr>
	<tr><td>答案解释:<input type="text" name="answerExplain"></td></tr>
	<tr align="center">
		<td>
		<input type="submit" value="提交" name="submit">
		&nbsp;&nbsp;
		<input type="reset" value="重置" name="reset">
		</td>
	</tr>
</table>
</form>
<form action="showCompletion" method="get">
	<input type="submit" value="点击查看已有填空题" name="submit">
</form>
</div>
</font>
</body>
</html>
