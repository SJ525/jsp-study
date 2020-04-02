<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.*" %>
<html>
<head><%@ include file="head.txt" %></head>
<body>
<font size=3>
<div align="center">
	<% Connection con;
    Statement sql; 
    ResultSet rs1,rs;
    try{  Class.forName("com.mysql.jdbc.Driver");
    }
    catch(Exception e){
    }
    try { String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
            "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
          con=DriverManager.getConnection(uri);
          sql=con.createStatement();  
          rs=sql.executeQuery("SELECT * FROM tb_student");
          out.println("<p>所有考生信息：</p>");
          out.print("<table border=2>");
          out.print("<tr width=100>");
          out.print("<th>"+"姓名");
          out.print("<th>"+"专业");
          out.print("<th>"+"班级");
          out.print("<th>"+"账号");
          out.print("<th>"+"密码");
          out.print("</TR>");
          while(rs.next()){
            out.print("<tr>");
              out.print("<td>"+rs.getString("name")+"</td>"); 
              out.print("<td>"+rs.getString("major")+"</td>");
              out.print("<td>"+rs.getString("grade")+"</td>");
              out.print("<td>"+rs.getString("account")+"</td>");
              out.print("<td>"+rs.getString("password")+"</td>");
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
