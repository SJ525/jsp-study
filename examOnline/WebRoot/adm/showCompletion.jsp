<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.*" %>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center">
	<% 	Connection con;
	    Statement sql; 
	    ResultSet rs;
	    try{  Class.forName("com.mysql.jdbc.Driver");
	    }
	    catch(Exception e){
	    }
	    try { String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
	            "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
	          con=DriverManager.getConnection(uri);
	          sql=con.createStatement();
	          rs=sql.executeQuery("SELECT * FROM tb_Completion");
	          
	          out.println("<p>所有填空题：</p>");
	          out.print("<table border=2>");
	          out.print("<tr width=100>");
	          out.print("<th>"+"题目序号");
	          out.print("<th>"+"填空题题干");
	          out.print("<th>"+"正确答案");
	          out.print("<th>"+"答案解释");
	          out.print("</tr>");
	          while(rs.next()){
	            out.print("<tr align=center>");
	              out.print("<td>"+rs.getString("titleId")+"</td>"); 
	              out.print("<td>"+rs.getString("titleStem")+"</td>");
	              out.print("<td>"+rs.getString("answerRight")+"</td>");
	              out.print("<td>"+rs.getString("answerExplain")+"</td>");
	            out.print("</tr>") ; 
	          }
	          out.print("</table>");
	          con.close();
	    }
	    catch(SQLException e){ 
	          out.print(e);
	    }
 %>
</div>
</font>
</body>
</html>
