<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.StuModel"%>
<jsp:useBean id="stuBean" class="model.StuModel" scope="request"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=2>
<div align="center">
	<form action="dropStuMsg" method="post">
		<p>请输入要查找的考生账号：
		<input type="number" name="account" placeholder="根据账号查找考生信息">&nbsp;
		<input type="submit" value="提交" name="submit">
		</p>
	</form>
	<p>查找反馈：<jsp:getProperty name="stuBean"  property="backNews" />
	<%
	if(stuBean.getSuccess()==true){
       out.print("<table border=2>");
       out.print("<tr width=100>");
       out.print("<th>"+"姓名");
       out.print("<th>"+"专业");
       out.print("<th>"+"班级");
       out.print("<th>"+"账号");
       out.print("<th>"+"密码");
       out.print("</tr>");
       out.print("<tr align=center>");
          out.print("<td>"+stuBean.getName()+"</td>"); 
          out.print("<td>"+stuBean.getMajor()+"</td>");
          out.print("<td>"+stuBean.getGrade()+"</td>"); 
          out.print("<td>"+stuBean.getAccount()+"</td>");
          out.print("<td>"+stuBean.getPassword()+"</td>");
       out.print("</tr>") ; 
       out.print("</table>");
	}
 %> 
</div>

<div align="center"><br>
<form action="dropStuMsg" method="get">
<p>如果查询成功，确认删除该考生的信息？</p>
<input type="submit" value="确认" name="submit">
</form>
</div>
</font>
</body>
</html>
