<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.ChoiceModel"%>
<jsp:useBean id="choiceBean1" class="model.ChoiceModel" scope="request"/>
<jsp:useBean id="choiceBean2" class="model.ChoiceModel" scope="request"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center">
<form action="alterChoice" method="get">
<p>请输入要查找的选择题序号：
<input type="number" name="titleId" placeholder="根据题目序号查找选择题">&nbsp;
<input type="submit" value="提交" name="submit">
</p>
</form>

<p>查找反馈：<jsp:getProperty name="choiceBean1"  property="backNews" /> 
<%
	if(choiceBean1.getSuccess()==true){
	          out.print("<table border=2>");
	          out.print("<tr width=100>");
	          out.print("<th>"+"题目序号");
	          out.print("<th>"+"选择题题干");
	          out.print("<th>"+"选项A");
	          out.print("<th>"+"选项B");
	          out.print("<th>"+"选项C");
	          out.print("<th>"+"选项D");
	          out.print("<th>"+"正确答案");
	          out.print("<th>"+"答案解释");
	          out.print("</tr>");
	          out.print("<tr align=center>");
              out.print("<td>"+choiceBean1.getTitleId()+"</td>"); 
              out.print("<td>"+choiceBean1.getTitleStem()+"</td>");
              out.print("<td>"+choiceBean1.getOptionA()+"</td>"); 
              out.print("<td>"+choiceBean1.getOptionB()+"</td>");
              out.print("<td>"+choiceBean1.getOptionC()+"</td>");
              out.print("<td>"+choiceBean1.getOptionD()+"</td>");
              out.print("<td>"+choiceBean1.getAnswerRight()+"</td>");
              out.print("<td>"+choiceBean1.getAnswerExplain()+"</td>");
	          out.print("</tr>") ; 
	          out.print("</table>");
	}
 %>
</div>
<div align="center">
<form action="alterChoice" method="post">
<p>手动修改查找到的选择题(选项信息以对应的大写字母开头)</p>
<table>
	<tr><td>修改选择题题干：<input type="text" name="titleStem"></td></tr>
	<tr><td>修改选项A：<input type="text" name="optionA"></td></tr>
	<tr><td>修改选项B：<input type="text" name="optionB"></td></tr>
	<tr><td>修改选项C：<input type="text" name="optionC"></td></tr>
	<tr><td>修改选项D：<input type="text" name="optionD"></td></tr>
	<tr><td>修改正确答案:<input type="text" name="answerRight"></td></tr>
	<tr><td>修改答案解释:<input type="text" name="answerExplain"></td></tr>
	<tr align="center">
		<td>
		<input type="submit" value="提交" name="submit">
		&nbsp;&nbsp;
		<input type="reset" value="重置" name="reset">
		</td>
	</tr>
</table>
</form>
<p>修改反馈：<jsp:getProperty name="choiceBean2"  property="backNews" /> 
<%
	if(choiceBean2.getSuccess()==true){
	          out.print("<table border=2>");
	          out.print("<tr width=100>");
	          out.print("<th>"+"题目序号");
	          out.print("<th>"+"选择题题干");
	          out.print("<th>"+"选项A");
	          out.print("<th>"+"选项B");
	          out.print("<th>"+"选项C");
	          out.print("<th>"+"选项D");
	          out.print("<th>"+"正确答案");
	          out.print("<th>"+"答案解释");
	          out.print("</tr>");
	          out.print("<tr align=center>");
              out.print("<td>"+choiceBean2.getTitleId()+"</td>"); 
              out.print("<td>"+choiceBean2.getTitleStem()+"</td>");
              out.print("<td>"+choiceBean2.getOptionA()+"</td>"); 
              out.print("<td>"+choiceBean2.getOptionB()+"</td>");
              out.print("<td>"+choiceBean2.getOptionC()+"</td>");
              out.print("<td>"+choiceBean2.getOptionD()+"</td>");
              out.print("<td>"+choiceBean2.getAnswerRight()+"</td>");
              out.print("<td>"+choiceBean2.getAnswerExplain()+"</td>");
	          out.print("</tr>") ; 
	          out.print("</table>");
	}
 %>
</div>
</font>
</body>
</html>
