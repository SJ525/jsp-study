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
	//��s����iso-8859-1��׼���±��룬���ر������ַ���
	public String handleString(String s){
	      try{  byte bb[]=s.getBytes("iso-8859-1");
	            s=new String(bb);
	      }
	      catch(Exception ee){} 
	      return s;  
	   }
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
                      throws ServletException,IOException{
		String name=request.getParameter("name").trim();//����
		String major=request.getParameter("major").trim();//רҵ
		String grade=request.getParameter("grade").trim();//�༶
		String account=request.getParameter("account").trim();//�˺�
		String password=request.getParameter("password").trim();//����

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
		    	fail(request,response,"�����˺Ų�����Ϊ��");
		    }
		    try {
	      	  response.setContentType("text/html;charset=gb2312");
			  PrintWriter out=response.getWriter();
			  out.println("<html><body>");
			  out.println("<div align=\"center\">");
			  out.println("<h2>"+"��ӿ�����Ϣ�ɹ�"+"</h2>");
			  out.println("<a href =addQuestion.jsp>�������ҳ��</a>");
			  out.println("<a href =showStuMsg.jsp>��ʾ���п�����Ϣ</a>");
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
			out.println("<h2>"+"���ʧ�ܣ�"+"</h2>") ;
			out.println("ԭ��:"+backNews);
			out.println("<a href =AddStuMsg.jsp>�������</a>");
			out.println("</div>");
			out.println("</body></html>");
		}
			catch(IOException exp){} 
		}
}
