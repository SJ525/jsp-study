<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.ChoiceModel"%>
<jsp:useBean id="choiceBean" class="model.ChoiceModel" scope="request"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center"><br>
<form action="dropChoice" method="post">
<p>请输入要查找的选择题序号：
<input type="number" name="titleId" placeholder="根据题目序号查找选择题">&nbsp;
<input type="submit" value="提交" name="submit">
</p>
</form>
<p>查找反馈：<jsp:getProperty name="choiceBean"  property="backNews" /> 
<%
	if(choiceBean.getSuccess()==true){
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
              out.print("<td>"+choiceBean.getTitleId()+"</td>"); 
              out.print("<td>"+choiceBean.getTitleStem()+"</td>");
              out.print("<td>"+choiceBean.getOptionA()+"</td>"); 
              out.print("<td>"+choiceBean.getOptionB()+"</td>");
              out.print("<td>"+choiceBean.getOptionC()+"</td>");
              out.print("<td>"+choiceBean.getOptionD()+"</td>");
              out.print("<td>"+choiceBean.getAnswerRight()+"</td>");
              out.print("<td>"+choiceBean.getAnswerExplain()+"</td>");
	          out.print("</tr>") ; 
	          out.print("</table>");
	}
 %>
</div>

<div align="center"><br>
<form action="dropChoice" method="get">
<p>如果查询成功，确认删除该题目？</p>
<input type="submit" value="确认" name="submit">
</form>
</div>
</font>
</body>
</html>
