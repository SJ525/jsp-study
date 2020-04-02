package control.manageChoice;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/adm/showChoice")
public class ShowChoice extends HttpServlet{
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
	          rs=sql.executeQuery("SELECT * FROM tb_choice");
	          out.print("<div align=\"center\">");
	          out.println("<p>����ѡ���⣺</p>"); 
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
	          while(rs.next()){
	            out.print("<tr align=center>");
	              out.print("<td>"+rs.getString("titleId")+"</td>"); 
	              out.print("<td>"+rs.getString("titleStem")+"</td>");
	              out.print("<td>"+rs.getString("optionA")+"</td>"); 
	              out.print("<td>"+rs.getString("optionB")+"</td>");
	              out.print("<td>"+rs.getString("optionC")+"</td>");
	              out.print("<td>"+rs.getString("optionD")+"</td>");
	              out.print("<td>"+rs.getString("answerRight")+"</td>");
	              out.print("<td>"+rs.getString("answerExplain")+"</td>");
	            out.print("</tr>") ; 
	          }
	          out.print("</table>");
	          out.print("<br><a href =addChoice.jsp>����</a>");
	          out.print("</div>");
	          con.close();
	    }
	    catch(SQLException e){ 
	          out.print(e);
	    }
 }
}
