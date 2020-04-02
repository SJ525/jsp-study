package control.manageChoice;

import model.ChoiceModel;//��������ģ��ChoiceModel
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/adm/dropChoice")
public class DropChoice extends HttpServlet{
	String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
            "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
	Connection con; 
	Statement sql1;//��ѯ
	PreparedStatement sql2;//ɾ��
	String tempId="";
	//����ChoiceModel��javaBean����
	ChoiceModel choiceBean=new ChoiceModel();
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
	   request.setAttribute("choiceBean",choiceBean);//�������������һ������Ĳ���choiceBean
	   try {
		con=DriverManager.getConnection(uri);
		//��ѯ��䣺��ѯ����ΪtitleId������ѡ������Ϣ
		String queryString="select * from tb_choice where titleId="+titleId;	
		sql1=con.createStatement();
        ResultSet rs=sql1.executeQuery(queryString);
		//�����rs��Ϊ�գ���ȡ��rs����Ϣ,����choiceBean�������Ϣ
		if(rs.next()) {
			tempId=rs.getString("titleId");//�����ݱ��е�titleId����ȫ�ֱ���tempId
			choiceBean.setTitleId(rs.getString("titleId"));
			choiceBean.setTitleStem(rs.getString("titleStem"));
			choiceBean.setOptionA(rs.getString("optionA"));
			choiceBean.setOptionB(rs.getString("optionB"));
			choiceBean.setOptionC(rs.getString("optionC"));
			choiceBean.setOptionD(rs.getString("optionD"));
			choiceBean.setAnswerRight(rs.getString("answerRight"));
			choiceBean.setAnswerExplain(rs.getString("answerExplain"));
			choiceBean.setBackNews("��ѯ�ɹ�����ѯ������£�");
			choiceBean.setSuccess(true);
		}else {
			choiceBean.setBackNews("��ѯʧ��:��Ҫ��ѯ�����򲻴��ڣ�����������");
			choiceBean.setSuccess(false);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		choiceBean.setBackNews("��ѯʧ��:����Ϊ"+e.toString());
		choiceBean.setSuccess(false);
	}   
	   //ת����ѯ�����dropChoice.jspҳ��
	   RequestDispatcher dispatcher=
       request.getRequestDispatcher("dropChoice.jsp");
       dispatcher.forward(request,response);
    
 }
		
	 public  void  doGet(HttpServletRequest request,HttpServletResponse response) 
	                      throws ServletException,IOException{
		 String titleId=tempId.trim();//��tempId��ֵ��titleId
		 
		 try {
			con=DriverManager.getConnection(uri);    
			//ɾ�����֣�����titleId��������
		   String dropCondition="DELETE FROM tb_choice WHERE titleId=" +titleId;
		   sql2=con.prepareStatement(dropCondition);
		   sql2.executeUpdate();
		   try {
		      	  response.setContentType("text/html;charset=gb2312");
				  PrintWriter out=response.getWriter();
				  out.println("<html><body>");
				  out.println("<div align=\"center\">");
				  out.println("<h2>"+"ɾ����Ŀ�ɹ�"+"</h2>");
				  out.println("<a href =dropChoice.jsp>����ɾ��ҳ��</a><br>");
				  out.println("<br><a href =showChoice.jsp>�鿴ʣ��ѡ����</a>");
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
			out.println("<a href =dropChoice.jsp>��������</a>");
			out.println("</div>");
			out.println("</body></html>");
		}
		catch(IOException exp){} 
	}
}
