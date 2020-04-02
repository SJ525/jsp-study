package control.manageChoice;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/adm/addChoice")
public class AddChoice extends HttpServlet{
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

	String titleId=request.getParameter("titleId").trim();//��Ŀ���
	String titleStem=request.getParameter("titleStem").trim();//ѡ�������
	String optionA=request.getParameter("optionA").trim();//ѡ��A
	String optionB=request.getParameter("optionB").trim();//ѡ��B
	String optionC=request.getParameter("optionC").trim();//ѡ��C
	String optionD=request.getParameter("optionD").trim();//ѡ��D
	String answerRight=request.getParameter("answerRight").trim();//��ȷ��
	String answerExplain=request.getParameter("answerExplain").trim();//�𰸽���

	boolean boo=titleId.length()>0;

	try{   con=DriverManager.getConnection(uri);
	    String insertCondition="INSERT INTO tb_choice VALUES (?,?,?,?,?,?,?,?)";
	    sql=con.prepareStatement(insertCondition);
	    if(boo)
	    {  sql.setString(1, handleString(titleId));
	       sql.setString(2,handleString(titleStem));
	       sql.setString(3,handleString(optionA));
		   sql.setString(4,handleString(optionB));
		   sql.setString(5,handleString(optionC));
		   sql.setString(6,handleString(optionD));
		   sql.setString(7,handleString(answerRight));
		   sql.setString(8,handleString(answerExplain));
	       sql.executeUpdate(); 
	    }
	    else {
	    	fail(request,response,"��Ŀ��Ų�����Ϊ��");
	    }
	    try {
      	  response.setContentType("text/html;charset=gb2312");
		  PrintWriter out=response.getWriter();
		  out.println("<html><body>");
		  out.println("<div align=\"center\">");
		  out.println("<h2>"+"�����Ŀ�ɹ�"+"</h2>");
		  out.println("<a href =addChoice.jsp>�������ҳ��</a><br>");
		  out.println("<br><a href =showChoice.jsp>��ʾ����ѡ����</a>");
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
		out.println("<h2>"+"���ʧ�ܣ�"+"</h2>") ;
		out.println("<h2>ԭ��</h2>"+backNews) ;
		out.println("<a href =addChoice.jsp>�������</a>");
		out.println("</div>");
		out.println("</body></html>");
	}
		catch(IOException exp){} 
	}
}
