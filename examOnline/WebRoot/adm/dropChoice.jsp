<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.ChoiceModel"%>
<jsp:useBean id="choiceBean" class="model.ChoiceModel" scope="request"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center"><br>
<form action="dropChoice" method="post">
<p>������Ҫ���ҵ�ѡ������ţ�
<input type="number" name="titleId" placeholder="������Ŀ��Ų���ѡ����">&nbsp;
<input type="submit" value="�ύ" name="submit">
</p>
</form>
<p>���ҷ�����<jsp:getProperty name="choiceBean"  property="backNews" /> 
<%
	if(choiceBean.getSuccess()==true){
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
              out.print("<td>"+choiceBean.getTitleId()+"</td>"); 
              out.print("<td>"+choiceBean.getTitleStem()+"</td>");
              out.print("<td>"+choiceBean.getOptionA()+"</td>"); 
              out.print("<td>"+choiceBean.getOptionB()+"</td>");
              out.print("<td>"+choiceBean.getOptionC()+"</td>");
              out.print("<td>"+choiceBean.getOptionD()+"</td>");
              out.print("<td>"+choiceBean.getAnswerRight()+"</td>");
              out.print("<td>"+choiceBean.getAnswerExplain()+"</td>");
	          out.print("</tr>") ; 
	          out.print("</table>");
	}
 %>
</div>

<div align="center"><br>
<form action="dropChoice" method="get">
<p>�����ѯ�ɹ���ȷ��ɾ������Ŀ��</p>
<input type="submit" value="ȷ��" name="submit">
</form>
</div>
</font>
</body>
</html>
