<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.StuModel"%>
<jsp:useBean id="stuBean" class="model.StuModel" scope="request"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=2>
<div align="center">
	<form action="dropStuMsg" method="post">
		<p>������Ҫ���ҵĿ����˺ţ�
		<input type="number" name="account" placeholder="�����˺Ų��ҿ�����Ϣ">&nbsp;
		<input type="submit" value="�ύ" name="submit">
		</p>
	</form>
	<p>���ҷ�����<jsp:getProperty name="stuBean"  property="backNews" />
	<%
	if(stuBean.getSuccess()==true){
       out.print("<table border=2>");
       out.print("<tr width=100>");
       out.print("<th>"+"����");
       out.print("<th>"+"רҵ");
       out.print("<th>"+"�༶");
       out.print("<th>"+"�˺�");
       out.print("<th>"+"����");
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
<p>�����ѯ�ɹ���ȷ��ɾ���ÿ�������Ϣ��</p>
<input type="submit" value="ȷ��" name="submit">
</form>
</div>
</font>
</body>
</html>
