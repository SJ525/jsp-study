<%@ page contentType="text/html;charset=GB2312" %>

<jsp:useBean id="testBean2" class="model.TestBean" scope="session"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>

<div align="center" style="border-bottom:1px solid #000;">
	<div align="left" style="width:50%;border-bottom:1px solid #000;">
		   <br><b>��������⣨����<%=testBean2.getTextAmount()%>���⣬ÿ����5�֣�
		   <br><br><jsp:getProperty name="testBean2" property="mess"/>��
		  <b>Ŀǰ���<%=String.valueOf(testBean2.getScore()).substring(0, 1)%>����<br>  
		   <br><b> ��ɣ�<jsp:getProperty name="testBean2" property="questions"/></b>
	  <form action="" method=post name=form>
	   		<br><input type="text" name="Input"><br> 
   		    <br><input type="submit" value="ȷ��" name="submit">
 	  </form>
    
  <% String studentAnswer = request.getParameter("Input");  
     if(studentAnswer!=null&&studentAnswer.length()>=1){
         testBean2.setAnswer(studentAnswer.trim());
     }
  %>
 <b>�ڶ�ȡ��һ��֮ǰ���ɷ���ȷ��.��Ŀǰ����Ľ���ǣ�<%=studentAnswer %>
 	<form action="readTestServlet1" method="get" name=form>
   			<br><input type= "hidden" value="<%=testBean2.getId()%>" name ="id">
         <input type="submit" value="��һ�������" name="submit">
    </form> 
 </div>
 	<form action="endTestServlet1" method=get name=form>
 		<br><input type="submit" value="�ύ�����" name="submit">
	</form>
	<p>������Ϣ��<%=testBean2.getBackNews()%>
</div>
</font>
</body>
</html>
