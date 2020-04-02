package control.manageStuMsg;
import model.StuModel;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/adm/dropStuMsg")
public class DropStuMsg extends HttpServlet{
	String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
            "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
	Connection con; 
	Statement sql1;//��ѯ
	PreparedStatement sql2;//ɾ��
	String tempAccount="";
	//����StuModel��javaBean����
	StuModel stuBean=new StuModel();
	public void init(ServletConfig config) throws ServletException{
	      super.init(config);
	    try{ 
	           Class.forName("com.mysql.jdbc.Driver");
	    }
	    catch(Exception e){} 
	 }
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
                      throws ServletException,IOException{
		   String account=request.getParameter("account");//��ȡҪ���Ҷ�����Ŀ����˺�   
		   request.setAttribute("stuBean",stuBean);//�������������һ������Ĳ���stuBean
		   try {
			con=DriverManager.getConnection(uri);
			//��ѯ��䣺��ѯ�����˺�Ϊaccount�����п�����Ϣ
			String queryString="select * from tb_student where account="+account;
			
			sql1=con.createStatement();
	        ResultSet rs=sql1.executeQuery(queryString);
			//�����rs��Ϊ�գ���ȡ��rs����Ϣ,����stuBean�������Ϣ
			if(rs.next()) {
				tempAccount=rs.getString("account");//�����ݱ��е�account����ȫ�ֱ���tempAccount
				stuBean.setName(rs.getString("name"));
				stuBean.setMajor(rs.getString("major"));
				stuBean.setGrade(rs.getString("grade"));
				stuBean.setAccount(rs.getString("account"));
				stuBean.setPassword(rs.getString("password"));
				stuBean.setBackNews("��ѯ�ɹ�����ѯ������£�");
				stuBean.setSuccess(true);
			}else {
				stuBean.setBackNews("��ѯʧ��:��Ҫ��ѯ�Ŀ����˺Ų����ڣ�����������");
				stuBean.setSuccess(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			stuBean.setBackNews("��ѯʧ��:����Ϊ"+e.toString());
			stuBean.setSuccess(false);
		}
		   
		   //ת����ѯ�����dropStuMsg.jspҳ��
		   RequestDispatcher dispatcher=
	       request.getRequestDispatcher("dropStuMsg.jsp");
	       dispatcher.forward(request,response);
    
	}
		
	 public  void  doGet(HttpServletRequest request,HttpServletResponse response) 
	                      throws ServletException,IOException{
		 String account=tempAccount.trim();//��tempAccount��ֵ��account
		 
		 try {
			con=DriverManager.getConnection(uri);    
			//ɾ�����֣�����account��������
		   String dropCondition="DELETE FROM tb_student WHERE account=" +account;
		   sql2=con.prepareStatement(dropCondition);
		   sql2.executeUpdate();
		   try {
		      	  response.setContentType("text/html;charset=gb2312");
				  PrintWriter out=response.getWriter();
				  out.println("<html><body>");
				  out.println("<div align=\"center\">");
				  out.println("<h2>"+"ɾ�������ɹ�"+"</h2>");
				  out.println("<a href =dropStuMsg.jsp>����ɾ��ҳ��</a><br>");
				  out.println("<br><a href =showStuMsg.jsp>�鿴ʣ�࿼��</a>");
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
			out.println("<h2>"+"ɾ������ʧ�ܣ�"+"</h2>") ;
			out.println("<h2>ԭ��</h2>"+backNews) ;
			out.println("<a href =dropStuMsg.jsp>��������</a>");
			out.println("</div>");
			out.println("</body></html>");
		}
		catch(IOException exp){} 
	}
}
