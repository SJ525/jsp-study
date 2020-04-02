package control.manageCompletion;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/adm/showCompletion")
public class ShowCompletion extends HttpServlet{
	public void init(ServletConfig config) throws ServletException{
	      super.init(config);
    try{ 
           Class.forName("com.mysql.jdbc.Driver");
    }
    catch(Exception e){} 
 }
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
                      throws ServletException,IOException{  
 }
		
 public  void  doGet(HttpServletRequest request,HttpServletResponse response) 
                      throws ServletException,IOException{
	 	response.setContentType("text/html;charset=gb2312");
	 	Connection con;
	    Statement sql; 
	    ResultSet rs;
	    PrintWriter out=response.getWriter();
	    try{  Class.forName("com.mysql.jdbc.Driver");
	    }
	    catch(Exception e){
	    }
	    try { String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
	            "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
	          con=DriverManager.getConnection(uri);
	          sql=con.createStatement();
	          rs=sql.executeQuery("SELECT * FROM tb_completion");
	          out.print("<div align=\"center\">");
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
	          out.print("<br><a href =addCompletion.jsp>返回</a>");
	          out.print("</div>");
	          con.close();
	    }
	    catch(SQLException e){ 
	          out.print(e);
	    }
 }
}
