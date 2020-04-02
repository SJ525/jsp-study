package control.manageStuMsg;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/adm/addStuMsg")
public class AddStuMsg extends HttpServlet{
	
	String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
            "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
	Connection con; 
	PreparedStatement sql;
	public void init(ServletConfig config) throws ServletException{
	      super.init(config);
	    try{ 
	           Class.forName("com.mysql.jdbc.Driver");
	    }
	    catch(Exception e){} 
	 }
	//将s按照iso-8859-1标准重新编码，返回编码后的字符串
	public String handleString(String s){
	      try{  byte bb[]=s.getBytes("iso-8859-1");
	            s=new String(bb);
	      }
	      catch(Exception ee){} 
	      return s;  
	   }
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
                      throws ServletException,IOException{
		String name=request.getParameter("name").trim();//姓名
		String major=request.getParameter("major").trim();//专业
		String grade=request.getParameter("grade").trim();//班级
		String account=request.getParameter("account").trim();//账号
		String password=request.getParameter("password").trim();//密码

		boolean boo=account.length()>0;

		try{   con=DriverManager.getConnection(uri);
		    String insertCondition="INSERT INTO tb_student VALUES (?,?,?,?,?)";
		    sql=con.prepareStatement(insertCondition);
		    if(boo)
		    {  sql.setString(1, handleString(name));
		       sql.setString(2,handleString(major));
			   sql.setString(3,handleString(grade));
			   sql.setString(4,handleString(account));
			   sql.setString(5,handleString(password));
		       sql.executeUpdate(); 
		    }
		    else {
		    	fail(request,response,"考生账号不可以为空");
		    }
		    try {
	      	  response.setContentType("text/html;charset=gb2312");
			  PrintWriter out=response.getWriter();
			  out.println("<html><body>");
			  out.println("<div align=\"center\">");
			  out.println("<h2>"+"添加考生信息成功"+"</h2>");
			  out.println("<a href =addQuestion.jsp>返回添加页面</a>");
			  out.println("<a href =showStuMsg.jsp>显示所有考生信息</a>");
			  out.println("</div>");
			  out.println("</body></html>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		    con.close();
		      
		}
		catch(SQLException exp){
			fail(request,response,exp.toString());
		}
    
	}
		
	 public  void  doGet(HttpServletRequest request,HttpServletResponse response) 
	                      throws ServletException,IOException{
	    doPost(request,response);
	 }
	 public void fail(HttpServletRequest request,HttpServletResponse response,
	           String backNews) {
		response.setContentType("text/html;charset=gb2312");
		try {
			PrintWriter out=response.getWriter();
			out.println("<html><body>");
			out.println("<div align=\"center\">");
			out.println("<h2>"+"添加失败："+"</h2>") ;
			out.println("原因:"+backNews);
			out.println("<a href =AddStuMsg.jsp>重新添加</a>");
			out.println("</div>");
			out.println("</body></html>");
		}
			catch(IOException exp){} 
		}
}
