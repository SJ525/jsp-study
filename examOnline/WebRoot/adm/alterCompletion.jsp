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
<p>������Ҫ���ҵ��������ţ�
<input type="number" name="titleId" placeholder="������Ŀ��Ų��������">&nbsp;
<input type="submit" value="�ύ" name="submit">
</p>
</form>

<p>���ҷ�����<jsp:getProperty name="CompletionBean1"  property="backNews" /> 
<%
	if(CompletionBean1.getSuccess()==true){
	          out.print("<table border=2>");
	          out.print("<tr width=100>");
	          out.print("<th>"+"��Ŀ���");
	          out.print("<th>"+"��������");
	          out.print("<th>"+"��ȷ��");
	          out.print("<th>"+"�𰸽���");
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
<p>�ֶ��޸Ĳ��ҵ��������(ѡ����Ϣ�Զ�Ӧ�Ĵ�д��ĸ��ͷ)</p>
<table>
	<tr><td>�޸��������ɣ�<input type="text" name="titleStem"></td></tr>
	<tr><td>�޸���ȷ��:<input type="text" name="answerRight"></td></tr>
	<tr><td>�޸Ĵ𰸽���:<input type="text" name="answerExplain"></td></tr>
	<tr align="center">
		<td>
		<input type="submit" value="�ύ" name="submit">
		&nbsp;&nbsp;
		<input type="reset" value="����" name="reset">
		</td>
	</tr>
</table>
</form>
<p>�޸ķ�����<jsp:getProperty name="CompletionBean2"  property="backNews" /> 
<%
	if(CompletionBean2.getSuccess()==true){
	          out.print("<table border=2>");
	          out.print("<tr width=100>");
	          out.print("<th>"+"��Ŀ���");
	          out.print("<th>"+"��������");
	          out.print("<th>"+"��ȷ��");
	          out.print("<th>"+"�𰸽���");
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
