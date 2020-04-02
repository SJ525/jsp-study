package control.manageStuMsg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adm/showStuMsg")
public class ShowStuMsg extends HttpServlet{
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
	          rs=sql.executeQuery("SELECT * FROM tb_student");
	          out.println("<div align=\"center\">");
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
	          out.print("<br><a href =addStuMsg.jsp>返回</a>");
	          out.println("</div>");
	          con.close();
	    }
	    catch(SQLException e){ 
	          out.print(e);
	    }
	 }
}
