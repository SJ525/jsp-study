<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.StuModel"%>
<jsp:useBean id="stuBean1" class="model.StuModel" scope="request"/>
<jsp:useBean id="stuBean2" class="model.StuModel" scope="request"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center">
	<form action="alterStuMsg" method="get">
		<p>请输入要查找的考生账号：
		<input type="number" name="account" placeholder="根据账号查找考生信息">&nbsp;
		<input type="submit" value="提交" name="submit">
		</p>
	</form>
	<p>查找反馈：<jsp:getProperty name="stuBean1"  property="backNews" />
	<%
	if(stuBean1.getSuccess()==true){
	          out.print("<table border=2>");
	          out.print("<tr width=100>");
	          out.print("<th>"+"姓名");
	          out.print("<th>"+"专业");
	          out.print("<th>"+"班级");
	          out.print("<th>"+"账号");
	          out.print("<th>"+"密码");
	          out.print("</tr>");
	          out.print("<tr align=center>");
              out.print("<td>"+stuBean1.getName()+"</td>"); 
              out.print("<td>"+stuBean1.getMajor()+"</td>");
              out.print("<td>"+stuBean1.getGrade()+"</td>"); 
              out.print("<td>"+stuBean1.getAccount()+"</td>");
              out.print("<td>"+stuBean1.getPassword()+"</td>");
	          out.print("</tr>") ; 
	          out.print("</table>");
	}
 %> 
</div>
<div align="center">
<form action="alterStuMsg" method="post">
<p>根据您查询的账号，修改考生的信息：</p>
<table>
	<tr><td>修改姓名：<input type="text" name="name" ></td></tr>
	<tr><td>修改专业：<input type="text" name="major" ></td></tr>
	<tr><td>修改班级：<input type="text" name="grade" ></td></tr>
	<tr><td>修改密码：<input type="text" name="password"></td></tr>
	<tr align="center">
		<td>
		<input type="submit" value="提交">
		&nbsp;&nbsp;
		<input type="reset" value="重置">
		</td>
	</tr>
</table>
</form>
<p>修改反馈：<jsp:getProperty name="stuBean2"  property="backNews" />
	<%
	if(stuBean2.getSuccess()==true){
	          out.print("<table border=2>");
	          out.print("<tr width=100>");
	          out.print("<th>"+"姓名");
	          out.print("<th>"+"专业");
	          out.print("<th>"+"班级");
	          out.print("<th>"+"账号");
	          out.print("<th>"+"密码");
	          out.print("</tr>");
	          out.print("<tr align=center>");
              out.print("<td>"+stuBean2.getName()+"</td>"); 
              out.print("<td>"+stuBean2.getMajor()+"</td>");
              out.print("<td>"+stuBean2.getGrade()+"</td>"); 
              out.print("<td>"+stuBean2.getAccount()+"</td>");
              out.print("<td>"+stuBean2.getPassword()+"</td>");
	          out.print("</tr>") ; 
	          out.print("</table>");
	}
 %> 
</div>
</font>
</body>
</html>
