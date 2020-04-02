package control.manageCompletion;
import model.CompletionModel;//��������ģ��CompletionModel
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/adm/alterCompletion")
public class AlterCompletion extends HttpServlet{
	String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
            "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
	Connection con; 
	Statement sql1;//������ѯ
	PreparedStatement sql2;//��������
	String tempId="";
	//����CompletionModel��javaBean����
	CompletionModel CompletionBean1=new CompletionModel();
	CompletionModel CompletionBean2=new CompletionModel();
	public void init(ServletConfig config) throws ServletException{
	      super.init(config);
      try{ 
             Class.forName("com.mysql.jdbc.Driver");
      }
      catch(Exception e){} 
   }
	
   public  void  doGet(HttpServletRequest request,HttpServletResponse response) 
           throws ServletException,IOException{
	   String titleId=request.getParameter("titleId");//��ȡҪ���Ҷ��������Ŀ���   
	   request.setAttribute("CompletionBean1",CompletionBean1);//�������������һ������Ĳ���CompletionBean1
	   try {
		con=DriverManager.getConnection(uri);
		//��ѯ��䣺��ѯ����ΪtitleId�������������Ϣ
		String queryString="select * from tb_Completion where titleId="+titleId;
		
		sql1=con.createStatement();
        ResultSet rs=sql1.executeQuery(queryString);
		//�����rs��Ϊ�գ���ȡ��rs����Ϣ,����CompletionBean1�������Ϣ
		if(rs.next()) {
			tempId=rs.getString("titleId");//�����ݱ��е�titleId����ȫ�ֱ���tempId
			CompletionBean1.setTitleId(rs.getString("titleId"));
			CompletionBean1.setTitleStem(rs.getString("titleStem"));
			CompletionBean1.setAnswerRight(rs.getString("answerRight"));
			CompletionBean1.setAnswerExplain(rs.getString("answerExplain"));
			CompletionBean1.setBackNews("��ѯ�ɹ�����ѯ������£�");
			CompletionBean1.setSuccess(true);
		}else {
			CompletionBean1.setBackNews("��ѯʧ��:��Ҫ��ѯ�����򲻴��ڣ�����������");
			CompletionBean1.setSuccess(false);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		CompletionBean1.setBackNews("��ѯʧ��:����Ϊ"+e.toString());
		CompletionBean1.setSuccess(false);
	}
	   
	   //ת����ѯ�����alterCompletion.jspҳ��
	   RequestDispatcher dispatcher=
       request.getRequestDispatcher("alterCompletion.jsp");
       dispatcher.forward(request,response);
   }
   
   public void doPost(HttpServletRequest request,HttpServletResponse response) 
           throws ServletException,IOException{
	String titleId=tempId.trim();//��tempId��ֵ��titleId
	String titleStem=request.getParameter("titleStem").trim();//��������
	String answerRight=request.getParameter("answerRight").trim();//��ȷ��
	String answerExplain=request.getParameter("answerExplain").trim();//�𰸽���
	request.setAttribute("CompletionBean2",CompletionBean2);//�������������һ������Ĳ���CompletionBean2
	try{
		con=DriverManager.getConnection(uri);    
    	//���²��֣�����titleId���������ֶ�
       String updateCondition="UPDATE tb_Completion set titleStem="
       		+ titleStem
       		+",answerRight="+ answerRight
       		+",answerExplain="+answerExplain
       		+" where titleId="+titleId;
       sql2=con.prepareStatement(updateCondition);
       sql2.executeUpdate(); 
	   //��ѯ���֣�����titleId��ѯ�޸ĺ�ĸ������������Ϣ
       String queryString="select * from tb_Completion where titleId="+titleId;
	   sql1=con.createStatement();
       ResultSet rs=sql1.executeQuery(queryString); 
       if(rs.next()) {
    	   CompletionBean2.setTitleId(rs.getString("titleId"));
           CompletionBean2.setTitleStem(rs.getString("titleStem"));
           CompletionBean2.setAnswerRight(rs.getString("answerRight"));
           CompletionBean2.setAnswerExplain(rs.getString("answerExplain"));
           CompletionBean2.setBackNews("�޸ĳɹ����޸Ľ�����£�");
           CompletionBean2.setSuccess(true);
       }
	   con.close();
	}
	catch(SQLException exp){
		CompletionBean2.setBackNews("�޸�ʧ��:����Ϊ"+exp.toString());
		CompletionBean2.setSuccess(false);
	}
		//ת���޸Ľ����alterCompletion.jspҳ��
		RequestDispatcher dispatcher=
	    request.getRequestDispatcher("alterCompletion.jsp");
	    dispatcher.forward(request,response);
   }	
}
