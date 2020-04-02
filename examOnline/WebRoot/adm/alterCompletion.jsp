<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.CompletionModel"%>
<jsp:useBean id="CompletionBean1" class="model.CompletionModel" scope="request"/>
<jsp:useBean id="CompletionBean2" class="model.CompletionModel" scope="request"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center">
<form action="alterCompletion" method="get">
<p>请输入要查找的填空题序号：
<input type="number" name="titleId" placeholder="根据题目序号查找填空题">&nbsp;
<input type="submit" value="提交" name="submit">
</p>
</form>

<p>查找反馈：<jsp:getProperty name="CompletionBean1"  property="backNews" /> 
<%
	if(CompletionBean1.getSuccess()==true){
	          out.print("<table border=2>");
	          out.print("<tr width=100>");
	          out.print("<th>"+"题目序号");
	          out.print("<th>"+"填空题题干");
	          out.print("<th>"+"正确答案");
	          out.print("<th>"+"答案解释");
	          out.print("</tr>");
	          out.print("<tr align=center>");
              out.print("<td>"+CompletionBean1.getTitleId()+"</td>"); 
              out.print("<td>"+CompletionBean1.getTitleStem()+"</td>");
              out.print("<td>"+CompletionBean1.getAnswerRight()+"</td>");
              out.print("<td>"+CompletionBean1.getAnswerExplain()+"</td>");
	          out.print("</tr>") ; 
	          out.print("</table>");
	}
 %>
</div>
<div align="center">
<form action="alterCompletion" method="post">
<p>手动修改查找到的填空题(选项信息以对应的大写字母开头)</p>
<table>
	<tr><td>修改填空题题干：<input type="text" name="titleStem"></td></tr>
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
<p>修改反馈：<jsp:getProperty name="CompletionBean2"  property="backNews" /> 
<%
	if(CompletionBean2.getSuccess()==true){
	          out.print("<table border=2>");
	          out.print("<tr width=100>");
	          out.print("<th>"+"题目序号");
	          out.print("<th>"+"填空题题干");
	          out.print("<th>"+"正确答案");
	          out.print("<th>"+"答案解释");
	          out.print("</tr>");
	          out.print("<tr align=center>");
              out.print("<td>"+CompletionBean2.getTitleId()+"</td>"); 
              out.print("<td>"+CompletionBean2.getTitleStem()+"</td>");
              out.print("<td>"+CompletionBean2.getAnswerRight()+"</td>");
              out.print("<td>"+CompletionBean2.getAnswerExplain()+"</td>");
	          out.print("</tr>") ; 
	          out.print("</table>");
	}
 %>
</div>
</font>
</body>
</html>
