<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.ChoiceModel"%>
<jsp:useBean id="addChoice" class="model.ChoiceModel" scope="request"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center">
<form action="addChoice" method="post">
<p>手动添加选择题(选项信息以对应的大写字母开头)</p>
<table>
	<tr><td>题目序号：<input type="number" name="titleId" placeholder="序号为主键，不可重复" ></td></tr>
	<tr><td>选择题题干：<input type="text" name="titleStem"></td></tr>
	<tr><td>选项A：<input type="text" name="optionA"></td></tr>
	<tr><td>选项B：<input type="text" name="optionB"></td></tr>
	<tr><td>选项C：<input type="text" name="optionC"></td></tr>
	<tr><td>选项D：<input type="text" name="optionD"></td></tr>
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
<form action="showChoice" method="get">
	<input type="submit" value="点击查看已有选择题" name="submit">
</form>
</div>
</font>
</body>
</html>
