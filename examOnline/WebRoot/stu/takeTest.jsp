<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.LoginModel"%> 
<jsp:useBean id="login" class="model.LoginModel" scope="session"/>
<jsp:useBean id="testBean1" class="model.TestBean" scope="session"/>
<jsp:useBean id="testBean2" class="model.TestBean" scope="session"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center" style="border-bottom:1px solid #000;">
	<div align="left" style="width:50%;border-bottom:1px solid #000;">	
		   <br><b>һ��ѡ���⣨����<%=testBean1.getTextAmount()%>���⣬ÿ����5�֣�
		   <br><br><jsp:getProperty name="testBean1" property="mess"/>��
		   <b>Ŀǰ���<%=String.valueOf(testBean1.getScore()).substring(0, 1)%>����<br> 
		   <br><b> ��ɣ�<jsp:getProperty name="testBean1" property="questions"/></b>
		   <form action="" method=post name=form>
	   		<br><input type="radio" name="R" value=A> 
			<jsp:getProperty name="testBean1" property="choiceA"/>
	        <br><input type="radio" name="R" value=B>
	        <jsp:getProperty name="testBean1" property="choiceB"/>
	        <br><input type="radio" name="R" value=C>
	        <jsp:getProperty name="testBean1" property="choiceC"/>
	        <br><input type="radio" name="R" value=D>
	        <jsp:getProperty name="testBean1" property="choiceD"/><br>
   		<br><input type="submit" value="ȷ��" name="submit">
 	</form>
    
  <% String studentAnswer= request.getParameter("R");  
     if(studentAnswer!=null&&studentAnswer.length()>=1){
         testBean1.setAnswer(studentAnswer.trim());
     }
  %>
 <b>�ڶ�ȡ��һ��֮ǰ���ɷ���ȷ��.��Ŀǰ������ѡ���ǣ�<%=studentAnswer%>
 	<form action="readTestServlet" method=post name=form>
   			<br><input type= "hidden" value="<%=testBean1.getId()%>" name ="id">
         <input type="submit" value="��һ��ѡ����" name="submit">
    </form> 
 </div>
 	<form action="endTestServlet" method=post name=form>
 		<br><input type="submit" value="�ύѡ����" name="submit">
	</form>
	<p>������Ϣ��<%=testBean1.getBackNews()%>
	 <form action="readTestServlet1?id=<%=login.getAccount()%>" method=get name=form>
     <input type="submit" value="���ѡ���⣬ȥ�������" name="submit">
    </form>
</div>
</font>
</body>
</html>
