<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.ChoiceModel"%>
<jsp:useBean id="choiceBean1" class="model.ChoiceModel" scope="request"/>
<jsp:useBean id="choiceBean2" class="model.ChoiceModel" scope="request"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center">
<form action="alterChoice" method="get">
<p>������Ҫ���ҵ�ѡ������ţ�
<input type="number" name="titleId" placeholder="������Ŀ��Ų���ѡ����">&nbsp;
<input type="submit" value="�ύ" name="submit">
</p>
</form>

<p>���ҷ�����<jsp:getProperty name="choiceBean1"  property="backNews" /> 
<%
	if(choiceBean1.getSuccess()==true){
	          out.print("<table border=2>");
	          out.print("<tr width=100>");
	          out.print("<th>"+"��Ŀ���");
	          out.print("<th>"+"ѡ�������");
	          out.print("<th>"+"ѡ��A");
	          out.print("<th>"+"ѡ��B");
	          out.print("<th>"+"ѡ��C");
	          out.print("<th>"+"ѡ��D");
	          out.print("<th>"+"��ȷ��");
	          out.print("<th>"+"�𰸽���");
	          out.print("</tr>");
	          out.print("<tr align=center>");
              out.print("<td>"+choiceBean1.getTitleId()+"</td>"); 
              out.print("<td>"+choiceBean1.getTitleStem()+"</td>");
              out.print("<td>"+choiceBean1.getOptionA()+"</td>"); 
              out.print("<td>"+choiceBean1.getOptionB()+"</td>");
              out.print("<td>"+choiceBean1.getOptionC()+"</td>");
              out.print("<td>"+choiceBean1.getOptionD()+"</td>");
              out.print("<td>"+choiceBean1.getAnswerRight()+"</td>");
              out.print("<td>"+choiceBean1.getAnswerExplain()+"</td>");
	          out.print("</tr>") ; 
	          out.print("</table>");
	}
 %>
</div>
<div align="center">
<form action="alterChoice" method="post">
<p>�ֶ��޸Ĳ��ҵ���ѡ����(ѡ����Ϣ�Զ�Ӧ�Ĵ�д��ĸ��ͷ)</p>
<table>
	<tr><td>�޸�ѡ������ɣ�<input type="text" name="titleStem"></td></tr>
	<tr><td>�޸�ѡ��A��<input type="text" name="optionA"></td></tr>
	<tr><td>�޸�ѡ��B��<input type="text" name="optionB"></td></tr>
	<tr><td>�޸�ѡ��C��<input type="text" name="optionC"></td></tr>
	<tr><td>�޸�ѡ��D��<input type="text" name="optionD"></td></tr>
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
<p>�޸ķ�����<jsp:getProperty name="choiceBean2"  property="backNews" /> 
<%
	if(choiceBean2.getSuccess()==true){
	          out.print("<table border=2>");
	          out.print("<tr width=100>");
	          out.print("<th>"+"��Ŀ���");
	          out.print("<th>"+"ѡ�������");
	          out.print("<th>"+"ѡ��A");
	          out.print("<th>"+"ѡ��B");
	          out.print("<th>"+"ѡ��C");
	          out.print("<th>"+"ѡ��D");
	          out.print("<th>"+"��ȷ��");
	          out.print("<th>"+"�𰸽���");
	          out.print("</tr>");
	          out.print("<tr align=center>");
              out.print("<td>"+choiceBean2.getTitleId()+"</td>"); 
              out.print("<td>"+choiceBean2.getTitleStem()+"</td>");
              out.print("<td>"+choiceBean2.getOptionA()+"</td>"); 
              out.print("<td>"+choiceBean2.getOptionB()+"</td>");
              out.print("<td>"+choiceBean2.getOptionC()+"</td>");
              out.print("<td>"+choiceBean2.getOptionD()+"</td>");
              out.print("<td>"+choiceBean2.getAnswerRight()+"</td>");
              out.print("<td>"+choiceBean2.getAnswerExplain()+"</td>");
	          out.print("</tr>") ; 
	          out.print("</table>");
	}
 %>
</div>
</font>
</body>
</html>
