<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.CompletionModel"%>
<jsp:useBean id="CompletionBean" class="model.CompletionModel" scope="request"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center"><br>
<form action="dropCompletion" method="post">
<p>������Ҫ���ҵ��������ţ�
<input type="number" name="titleId" placeholder="������Ŀ��Ų��������">&nbsp;
<input type="submit" value="�ύ" name="submit">
</p>
</form>
<p>���ҷ�����<jsp:getProperty name="CompletionBean"  property="backNews" /> 
<%
	if(CompletionBean.getSuccess()==true){
	          out.print("<table border=2>");
	          out.print("<tr width=100>");
	          out.print("<th>"+"��Ŀ���");
	          out.print("<th>"+"��������");
	          out.print("<th>"+"��ȷ��");
	          out.print("<th>"+"�𰸽���");
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
<p>�����ѯ�ɹ���ȷ��ɾ������Ŀ��</p>
<input type="submit" value="ȷ��" name="submit">
</form>
</div>
</font>
</body>
</html>
