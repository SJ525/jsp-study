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
		<p>������Ҫ���ҵĿ����˺ţ�
		<input type="number" name="account" placeholder="�����˺Ų��ҿ�����Ϣ">&nbsp;
		<input type="submit" value="�ύ" name="submit">
		</p>
	</form>
	<p>���ҷ�����<jsp:getProperty name="stuBean1"  property="backNews" />
	<%
	if(stuBean1.getSuccess()==true){
	          out.print("<table border=2>");
	          out.print("<tr width=100>");
	          out.print("<th>"+"����");
	          out.print("<th>"+"רҵ");
	          out.print("<th>"+"�༶");
	          out.print("<th>"+"�˺�");
	          out.print("<th>"+"����");
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
<p>��������ѯ���˺ţ��޸Ŀ�������Ϣ��</p>
<table>
	<tr><td>�޸�������<input type="text" name="name" ></td></tr>
	<tr><td>�޸�רҵ��<input type="text" name="major" ></td></tr>
	<tr><td>�޸İ༶��<input type="text" name="grade" ></td></tr>
	<tr><td>�޸����룺<input type="text" name="password"></td></tr>
	<tr align="center">
		<td>
		<input type="submit" value="�ύ">
		&nbsp;&nbsp;
		<input type="reset" value="����">
		</td>
	</tr>
</table>
</form>
<p>�޸ķ�����<jsp:getProperty name="stuBean2"  property="backNews" />
	<%
	if(stuBean2.getSuccess()==true){
	          out.print("<table border=2>");
	          out.print("<tr width=100>");
	          out.print("<th>"+"����");
	          out.print("<th>"+"רҵ");
	          out.print("<th>"+"�༶");
	          out.print("<th>"+"�˺�");
	          out.print("<th>"+"����");
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
