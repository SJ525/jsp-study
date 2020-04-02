package control.manageCompletion;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/adm/addCompletion")
public class AddCompletion extends HttpServlet{
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

	String titleId=request.getParameter("titleId").trim();//题目序号
	String titleStem=request.getParameter("titleStem").trim();//填空题题干
	String answerRight=request.getParameter("answerRight").trim();//正确答案
	String answerExplain=request.getParameter("answerExplain").trim();//答案解释

	boolean boo=titleId.length()>0;

	try{   con=DriverManager.getConnection(uri);
	    String insertCondition="INSERT INTO tb_completion VALUES (?,?,?,?)";
	    sql=con.prepareStatement(insertCondition);
	    if(boo)
	    {  sql.setString(1, handleString(titleId));
	       sql.setString(2,handleString(titleStem));
		   sql.setString(3,handleString(answerRight));
		   sql.setString(4,handleString(answerExplain));
	       sql.executeUpdate(); 
	    }
	    else {
	    	fail(request,response,"题目序号不可以为空");
	    }
	    try {
      	  response.setContentType("text/html;charset=gb2312");
		  PrintWriter out=response.getWriter();
		  out.println("<html><body>");
		  out.println("<div align=\"center\">");
		  out.println("<h2>"+"添加题目成功"+"</h2>");
		  out.println("<a href =addCompletion.jsp>返回添加页面</a><br>");
		  out.println("<br><a href =showCompletion.jsp>显示所有填空题</a>");
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
	    
   }
   public void fail(HttpServletRequest request,HttpServletResponse response,
           String backNews) {
	response.setContentType("text/html;charset=gb2312");
	try {
		PrintWriter out=response.getWriter();
		out.println("<html><body><br>");
		out.println("<div align=\"center\">");
		out.println("<h2>"+"添加失败！"+"</h2>") ;
		out.println("<h2>原因：</h2>"+backNews) ;
		out.println("<a href =addCompletion.jsp>重新添加</a>");
		out.println("</div>");
		out.println("</body></html>");
	}
		catch(IOException exp){} 
	}
}
