<%@ page contentType="text/html;charset=GB2312" %>

<jsp:useBean id="testBean2" class="model.TestBean" scope="session"/>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>

<div align="center" style="border-bottom:1px solid #000;">
	<div align="left" style="width:50%;border-bottom:1px solid #000;">
		   <br><b>二、填空题（共有<%=testBean2.getTextAmount()%>道题，每道题5分）
		   <br><br><jsp:getProperty name="testBean2" property="mess"/>：
		  <b>目前答对<%=String.valueOf(testBean2.getScore()).substring(0, 1)%>条题<br>  
		   <br><b> 题干：<jsp:getProperty name="testBean2" property="questions"/></b>
	  <form action="" method=post name=form>
	   		<br><input type="text" name="Input"><br> 
   		    <br><input type="submit" value="确认" name="submit">
 	  </form>
    
  <% String studentAnswer = request.getParameter("Input");  
     if(studentAnswer!=null&&studentAnswer.length()>=1){
         testBean2.setAnswer(studentAnswer.trim());
     }
  %>
 <b>在读取下一题之前，可反复确认.你目前输入的结果是：<%=studentAnswer %>
 	<form action="readTestServlet1" method="get" name=form>
   			<br><input type= "hidden" value="<%=testBean2.getId()%>" name ="id">
         <input type="submit" value="下一道填空题" name="submit">
    </form> 
 </div>
 	<form action="endTestServlet1" method=get name=form>
 		<br><input type="submit" value="提交填空题" name="submit">
	</form>
	<p>反馈信息：<%=testBean2.getBackNews()%>
</div>
</font>
</body>
</html>
