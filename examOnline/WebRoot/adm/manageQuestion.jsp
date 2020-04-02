<%@ page contentType="text/html;charset=GB2312" %>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center"> 
  <h3>一、管理选择题：请选择某项进行操作</h3>
  <table border=2 cellpadding="10" width="200">
	   <tr align="center">
		   <td><A href="addChoice.jsp">新增选择题</font></A></td>
	   </tr>  
	   <tr align="center">
		   <td><A href="showChoice.jsp">显示所有选择题</A></td>
	   </tr>
	   <tr align="center">
		   <td><A href="alterChoice.jsp">查找和修改选择题</A></td>
	   </tr>  
	   <tr align="center"> 
		   <td><A href="dropChoice.jsp">查找和删除选择题</A></td>
	   </tr> 
</table>
</div>
<div align="center"> 
  <h3>二、管理填空题：请选择某项进行操作</h3>
  <table border=2 cellpadding="10" width="200">
	   <tr align="center">
		   <td><A href="addCompletion.jsp">新增填空题</font></A></td>
	   </tr>  
	   <tr align="center">
		   <td><A href="showCompletion.jsp">显示所有填空题</A></td>
	   </tr>
	   <tr align="center">
		   <td><A href="alterCompletion.jsp">查找和修改填空题</A></td>
	   </tr>  
	   <tr align="center"> 
		   <td><A href="dropCompletion.jsp">查找和删除填空题</A></td>
	   </tr> 
</table>
</div>
</font>
</body>
</html>
