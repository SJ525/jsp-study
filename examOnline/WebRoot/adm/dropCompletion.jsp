<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.CompletionModel"%>
<jsp:useBean id="CompletionBean" class="model.CompletionModel" scope="request"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center"><br>
<form action="dropCompletion" method="post">
<p>请输入要查找的填空题序号：
<input type="number" name="titleId" placeholder="根据题目序号查找填空题">&nbsp;
<input type="submit" value="提交" name="submit">
</p>
</form>
<p>查找反馈：<jsp:getProperty name="CompletionBean"  property="backNews" /> 
<%
	if(CompletionBean.getSuccess()==true){
	          out.print("<table border=2>");
	          out.print("<tr width=100>");
	          out.print("<th>"+"题目序号");
	          out.print("<th>"+"填空题题干");
	          out.print("<th>"+"正确答案");
	          out.print("<th>"+"答案解释");
	          out.print("</tr>");
	          out.print("<tr align=center>");
              out.print("<td>"+CompletionBean.getTitleId()+"</td>"); 
              out.print("<td>"+CompletionBean.getTitleStem()+"</td>");
              out.print("<td>"+CompletionBean.getAnswerRight()+"</td>");
              out.print("<td>"+CompletionBean.getAnswerExplain()+"</td>");
	          out.print("</tr>") ; 
	          out.print("</table>");
	}
 %>
</div>

<div align="center"><br>
<form action="dropCompletion" method="get">
<p>如果查询成功，确认删除该题目？</p>
<input type="submit" value="确认" name="submit">
</form>
</div>
</font>
</body>
</html>
