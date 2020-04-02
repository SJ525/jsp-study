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
	          out.println("<p>��������⣺</p>"); 
	          out.print("<table border=2>");
	          out.print("<tr width=100>");
	          out.print("<th>"+"��Ŀ���");
	          out.print("<th>"+"��������");
	          out.print("<th>"+"��ȷ��");
	          out.print("<th>"+"�𰸽���");
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
	          out.print("<br><a href =addCompletion.jsp>����</a>");
	          out.print("</div>");
	          con.close();
	    }
	    catch(SQLException e){ 
	          out.print(e);
	    }
 }
}
