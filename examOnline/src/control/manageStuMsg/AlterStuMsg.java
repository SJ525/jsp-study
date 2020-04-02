package control.manageStuMsg;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.StuModel;
@WebServlet("/adm/alterStuMsg")
public class AlterStuMsg extends HttpServlet{
	
	String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
            "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
	Connection con; 
	Statement sql1;
	PreparedStatement sql2;
	String tempAccount="";
	//����ChoiceModel��javaBean����
	StuModel stuBean1=new StuModel();
	StuModel stuBean2=new StuModel();
	public void init(ServletConfig config) throws ServletException{
	      super.init(config);
	    try{ 
	           Class.forName("com.mysql.jdbc.Driver");
	    }
	    catch(Exception e){} 
	 }
	
	public  void  doGet(HttpServletRequest request,HttpServletResponse response) 
            throws ServletException,IOException{
	   String account=request.getParameter("account");//��ȡҪ���Ҷ�����Ŀ����˺�   
	   request.setAttribute("stuBean1",stuBean1);//�������������һ������Ĳ���stuBean1
	   try {
		con=DriverManager.getConnection(uri);
		//��ѯ��䣺��ѯ�����˺�Ϊaccount�����п�����Ϣ
		String queryString="select * from tb_student where account="+account;
		
		sql1=con.createStatement();
        ResultSet rs=sql1.executeQuery(queryString);
		//�����rs��Ϊ�գ���ȡ��rs����Ϣ,����stuBean1�������Ϣ
		if(rs.next()) {
			tempAccount=rs.getString("account");//�����ݱ��е�account����ȫ�ֱ���tempAccount
			stuBean1.setName(rs.getString("name"));
			stuBean1.setMajor(rs.getString("major"));
			stuBean1.setGrade(rs.getString("grade"));
			stuBean1.setAccount(rs.getString("account"));
			stuBean1.setPassword(rs.getString("password"));
			stuBean1.setBackNews("��ѯ�ɹ�����ѯ������£�");
			stuBean1.setSuccess(true);
		}else {
			stuBean1.setBackNews("��ѯʧ��:��Ҫ��ѯ�Ŀ����˺Ų����ڣ�����������");
			stuBean1.setSuccess(false);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		stuBean1.setBackNews("��ѯʧ��:����Ϊ"+e.toString());
		stuBean1.setSuccess(false);
	}
	   
	   //ת����ѯ�����alterStuMsg.jspҳ��
	   RequestDispatcher dispatcher=
       request.getRequestDispatcher("alterStuMsg.jsp");
       dispatcher.forward(request,response);
  }
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
                      throws ServletException,IOException{
		String account=tempAccount.trim();//��tempId��ֵ��titleId
		String name=request.getParameter("name").trim();//��������
		String major=request.getParameter("major").trim();//רҵ
		String grade=request.getParameter("grade").trim();//�༶
		String password=request.getParameter("password").trim();//����
		request.setAttribute("stuBean2",stuBean2);//�������������һ������Ĳ���stuBean2
		try{
			con=DriverManager.getConnection(uri);    
	    	//���²��֣�����titleId���������ֶ�
	       String updateCondition="UPDATE tb_student set name="
	       		+ name
	       		+",major="+ major+",grade="+ grade
	       		+",password="+password
	       		+" where account="+account;
	       sql2=con.prepareStatement(updateCondition);
	       sql2.executeUpdate(); 
		   //��ѯ���֣�����tempAccount��ѯ�޸ĺ�ĸ��˺ŵ�������Ϣ
	       String queryString="select * from tb_student where account="+account;
		   sql1=con.createStatement();
	       ResultSet rs=sql1.executeQuery(queryString); 
	       if(rs.next()) {
	    	   stuBean2.setName(rs.getString("name"));
	    	   stuBean2.setMajor(rs.getString("major"));
	    	   stuBean2.setGrade(rs.getString("grade"));
	    	   stuBean2.setAccount(rs.getString("account"));
	    	   stuBean2.setPassword(rs.getString("password"));
	           stuBean2.setBackNews("�޸ĳɹ����޸Ľ�����£�");
	           stuBean2.setSuccess(true);
	       }
		   con.close();
		}
		catch(SQLException exp){
			stuBean2.setBackNews("�޸�ʧ��:����Ϊ"+exp.toString());
			stuBean2.setSuccess(false);
		}
			//ת���޸Ľ����alterStuMsg.jspҳ��
			RequestDispatcher dispatcher=
		    request.getRequestDispatcher("alterStuMsg.jsp");
		    dispatcher.forward(request,response);
    
	}
		
}
