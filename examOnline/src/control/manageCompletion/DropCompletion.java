package control.manageCompletion;

import model.CompletionModel;//��������ģ��CompletionModel
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/adm/dropCompletion")
public class DropCompletion extends HttpServlet{
	String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
            "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
	Connection con; 
	Statement sql1;//��ѯ
	PreparedStatement sql2;//ɾ��
	String tempId="";
	//����CompletionModel��javaBean����
	CompletionModel CompletionBean=new CompletionModel();
	public void init(ServletConfig config) throws ServletException{
	      super.init(config);
    try{ 
           Class.forName("com.mysql.jdbc.Driver");
    }
    catch(Exception e){} 
 }
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
                      throws ServletException,IOException{
	   String titleId=request.getParameter("titleId").trim();//��ȡҪ���Ҷ��������Ŀ���   
	   request.setAttribute("CompletionBean",CompletionBean);//�������������һ������Ĳ���CompletionBean
	   try {
		con=DriverManager.getConnection(uri);
		//��ѯ��䣺��ѯ����ΪtitleId�������������Ϣ
		String queryString="select * from tb_completion where titleId="+titleId;	
		sql1=con.createStatement();
        ResultSet rs=sql1.executeQuery(queryString);
		//�����rs��Ϊ�գ���ȡ��rs����Ϣ,����CompletionBean�������Ϣ
		if(rs.next()) {
			tempId=rs.getString("titleId");//�����ݱ��е�titleId����ȫ�ֱ���tempId
			CompletionBean.setTitleId(rs.getString("titleId"));
			CompletionBean.setTitleStem(rs.getString("titleStem"));
			CompletionBean.setAnswerRight(rs.getString("answerRight"));
			CompletionBean.setAnswerExplain(rs.getString("answerExplain"));
			CompletionBean.setBackNews("��ѯ�ɹ�����ѯ������£�");
			CompletionBean.setSuccess(true);
		}else {
			CompletionBean.setBackNews("��ѯʧ��:��Ҫ��ѯ�����򲻴��ڣ�����������");
			CompletionBean.setSuccess(false);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		CompletionBean.setBackNews("��ѯʧ��:����Ϊ"+e.toString());
		CompletionBean.setSuccess(false);
	}   
	   //ת����ѯ�����dropCompletion.jspҳ��
	   RequestDispatcher dispatcher=
       request.getRequestDispatcher("dropCompletion.jsp");
       dispatcher.forward(request,response);
    
 }
		
	 public  void  doGet(HttpServletRequest request,HttpServletResponse response) 
	                      throws ServletException,IOException{
		 String titleId=tempId.trim();//��tempId��ֵ��titleId
		 
		 try {
			con=DriverManager.getConnection(uri);    
			//ɾ�����֣�����titleId��������
		   String dropCondition="DELETE FROM tb_completion WHERE titleId=" +titleId;
		   sql2=con.prepareStatement(dropCondition);
		   sql2.executeUpdate();
		   try {
		      	  response.setContentType("text/html;charset=gb2312");
				  PrintWriter out=response.getWriter();
				  out.println("<html><body>");
				  out.println("<div align=\"center\">");
				  out.println("<h2>"+"ɾ����Ŀ�ɹ�"+"</h2>");
				  out.println("<a href =dropCompletion.jsp>����ɾ��ҳ��</a><br>");
				  out.println("<br><a href =showCompletion.jsp>�鿴ʣ�������</a>");
				  out.println("</div>");
				  out.println("</body></html>");
				} catch (Exception e) {
					e.printStackTrace();
					fail(request,response,e.toString());
				}
		   con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			fail(request,response,e.toString());
		} 
	 }
	 public void fail(HttpServletRequest request,HttpServletResponse response,
	           String backNews) {
		response.setContentType("text/html;charset=gb2312");
		try {
			PrintWriter out=response.getWriter();
			out.println("<html><body align=\"center\">");
			out.println("<div align=\"center\">");
			out.println("<h2>"+"ɾ��ʧ�ܣ�"+"</h2>") ;
			out.println("<h2>ԭ��</h2>"+backNews) ;
			out.println("<a href =dropCompletion.jsp>��������</a>");
			out.println("</div>");
			out.println("</body></html>");
		}
		catch(IOException exp){} 
	}
}
