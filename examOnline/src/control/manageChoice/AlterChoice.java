package control.manageChoice;
import model.ChoiceModel;//��������ģ��ChoiceModel
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/adm/alterChoice")
public class AlterChoice extends HttpServlet{
	String uri="jdbc:mysql://127.0.0.1/onlineTest?"+
            "user=root&password=123456&characterEncoding=gb2312&useSSL=false";
	Connection con; 
	Statement sql1;//������ѯ
	PreparedStatement sql2;//��������
	String tempId="";
	//����ChoiceModel��javaBean����
	ChoiceModel choiceBean1=new ChoiceModel();
	ChoiceModel choiceBean2=new ChoiceModel();
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
	   request.setAttribute("choiceBean1",choiceBean1);//�������������һ������Ĳ���choiceBean1
	   try {
		con=DriverManager.getConnection(uri);
		//��ѯ��䣺��ѯ����ΪtitleId������ѡ������Ϣ
		String queryString="select * from tb_choice where titleId="+titleId;
		
		sql1=con.createStatement();
        ResultSet rs=sql1.executeQuery(queryString);
		//�����rs��Ϊ�գ���ȡ��rs����Ϣ,����choiceBean1�������Ϣ
		if(rs.next()) {
			tempId=rs.getString("titleId");//�����ݱ��е�titleId����ȫ�ֱ���tempId
			choiceBean1.setTitleId(rs.getString("titleId"));
			choiceBean1.setTitleStem(rs.getString("titleStem"));
			choiceBean1.setOptionA(rs.getString("optionA"));
			choiceBean1.setOptionB(rs.getString("optionB"));
			choiceBean1.setOptionC(rs.getString("optionC"));
			choiceBean1.setOptionD(rs.getString("optionD"));
			choiceBean1.setAnswerRight(rs.getString("answerRight"));
			choiceBean1.setAnswerExplain(rs.getString("answerExplain"));
			choiceBean1.setBackNews("��ѯ�ɹ�����ѯ������£�");
			choiceBean1.setSuccess(true);
		}else {
			choiceBean1.setBackNews("��ѯʧ��:��Ҫ��ѯ�����򲻴��ڣ�����������");
			choiceBean1.setSuccess(false);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		choiceBean1.setBackNews("��ѯʧ��:����Ϊ"+e.toString());
		choiceBean1.setSuccess(false);
	}
	   
	   //ת����ѯ�����alterChoice.jspҳ��
	   RequestDispatcher dispatcher=
       request.getRequestDispatcher("alterChoice.jsp");
       dispatcher.forward(request,response);
   }
   
   public void doPost(HttpServletRequest request,HttpServletResponse response) 
           throws ServletException,IOException{
	String titleId=tempId.trim();//��tempId��ֵ��titleId
	String titleStem=request.getParameter("titleStem").trim();//ѡ�������
	String optionA=request.getParameter("optionA").trim();//ѡ��A
	String optionB=request.getParameter("optionB").trim();//ѡ��B
	String optionC=request.getParameter("optionC").trim();//ѡ��C
	String optionD=request.getParameter("optionD").trim();//ѡ��D
	String answerRight=request.getParameter("answerRight").trim();//��ȷ��
	String answerExplain=request.getParameter("answerExplain").trim();//�𰸽���
	request.setAttribute("choiceBean2",choiceBean2);//�������������һ������Ĳ���choiceBean2
	try{
		con=DriverManager.getConnection(uri);    
    	//���²��֣�����titleId���������ֶ�
       String updateCondition="UPDATE tb_choice set titleStem="
       		+ titleStem
       		+",optionA="+ optionA+",optionB="+ optionB
       		+",optionC="+ optionC+",optionD="+ optionD
       		+",answerRight="+ answerRight
       		+",answerExplain="+answerExplain
       		+" where titleId="+titleId;
       sql2=con.prepareStatement(updateCondition);
       sql2.executeUpdate(); 
	   //��ѯ���֣�����titleId��ѯ�޸ĺ�ĸ�ѡ����������Ϣ
       String queryString="select * from tb_choice where titleId="+titleId;
	   sql1=con.createStatement();
       ResultSet rs=sql1.executeQuery(queryString); 
       if(rs.next()) {
    	   choiceBean2.setTitleId(rs.getString("titleId"));
           choiceBean2.setTitleStem(rs.getString("titleStem"));
           choiceBean2.setOptionA(rs.getString("optionA"));
           choiceBean2.setOptionB(rs.getString("optionB"));
           choiceBean2.setOptionC(rs.getString("optionC"));
           choiceBean2.setOptionD(rs.getString("optionD"));
           choiceBean2.setAnswerRight(rs.getString("answerRight"));
           choiceBean2.setAnswerExplain(rs.getString("answerExplain"));
           choiceBean2.setBackNews("�޸ĳɹ����޸Ľ�����£�");
           choiceBean2.setSuccess(true);
       }
	   con.close();
	}
	catch(SQLException exp){
		choiceBean2.setBackNews("�޸�ʧ��:����Ϊ"+exp.toString());
		choiceBean2.setSuccess(false);
	}
		//ת���޸Ľ����alterChoice.jspҳ��
		RequestDispatcher dispatcher=
	    request.getRequestDispatcher("alterChoice.jsp");
	    dispatcher.forward(request,response);
   }	
}
