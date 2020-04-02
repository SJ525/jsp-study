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
		   <br><b>一、选择题（共有<%=testBean1.getTextAmount()%>道题，每道题5分）
		   <br><br><jsp:getProperty name="testBean1" property="mess"/>：
		   <b>目前答对<%=String.valueOf(testBean1.getScore()).substring(0, 1)%>条题<br> 
		   <br><b> 题干：<jsp:getProperty name="testBean1" property="questions"/></b>
		   <form action="" method=post name=form>
	   		<br><input type="radio" name="R" value=A> 
			<jsp:getProperty name="testBean1" property="choiceA"/>
	        <br><input type="radio" name="R" value=B>
	        <jsp:getProperty name="testBean1" property="choiceB"/>
	        <br><input type="radio" name="R" value=C>
	        <jsp:getProperty name="testBean1" property="choiceC"/>
	        <br><input type="radio" name="R" value=D>
	        <jsp:getProperty name="testBean1" property="choiceD"/><br>
   		<br><input type="submit" value="确认" name="submit">
 	</form>
    
  <% String studentAnswer= request.getParameter("R");  
     if(studentAnswer!=null&&studentAnswer.length()>=1){
         testBean1.setAnswer(studentAnswer.trim());
     }
  %>
 <b>在读取下一题之前，可反复确认.你目前给出的选择是：<%=studentAnswer%>
 	<form action="readTestServlet" method=post name=form>
   			<br><input type= "hidden" value="<%=testBean1.getId()%>" name ="id">
         <input type="submit" value="下一道选择题" name="submit">
    </form> 
 </div>
 	<form action="endTestServlet" method=post name=form>
 		<br><input type="submit" value="提交选择题" name="submit">
	</form>
	<p>反馈信息：<%=testBean1.getBackNews()%>
	 <form action="readTestServlet1?id=<%=login.getAccount()%>" method=get name=form>
     <input type="submit" value="完成选择题，去做填空题" name="submit">
    </form>
</div>
</font>
</body>
</html>
