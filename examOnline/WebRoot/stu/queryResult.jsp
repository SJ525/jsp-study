<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.LoginModel"%> 
<jsp:useBean id="login" class="model.LoginModel" scope="session"/>
<jsp:useBean id="testBean1" class="model.TestBean" scope="session"/>
<jsp:useBean id="testBean2" class="model.TestBean" scope="session"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=2>
<div align="center"><br><br>
	<% 
		float score1=testBean1.getScore()*5;
		float score2=testBean2.getScore()*5;
		float total=score1+score2;
		out.println("<p>�����εĿ��Գɼ���</p>");
          out.print("<table border=2>");
          out.print("<tr width=100>");
          out.print("<th>"+"���Ŀ����˺�"+"</th>");
          out.print("<th>"+"ѡ�������"+"</th>");
          out.print("<th>"+"��������"+"</th>");
          out.print("<th>"+"�ܷ���"+"</th>");
          out.print("</tr>");
          out.print("<tr width=100>");
          out.print("<th>"+login.getAccount()+"</th>");
          out.print("<th>"+score1+"</th>");
          out.print("<th>"+score2+"</th>");
          out.print("<th>"+total+"</th>");
          out.print("</tr>");
       out.print("</table>");
	 %>
</div><br>
<div align="center"><br><br>
	<form action="newTest" method="post">
		<input type="submit" value="���¿���">
	</form>
</div>
</font>
</body>
</html>
