package model;

public class CompletionModel {
	String titleId;//��Ŀ���	 
	String titleStem;//����������
	String answerRight;//��ȷ��	
	String answerExplain;//�𰸽���
	String backNews="";//��ŷ�����Ϣ
	boolean success=false;//����ֵ��ʼΪfalse
	public String getTitleId() {
		return titleId;
	}
	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}
	public String getTitleStem() {
		return titleStem;
	}
	public void setTitleStem(String titleStem) {
		this.titleStem = titleStem;
	}
	public String getAnswerRight() {
		return answerRight;
	}
	public void setAnswerRight(String answerRight) {
		this.answerRight = answerRight;
	}
	public String getAnswerExplain() {
		return answerExplain;
	}
	public void setAnswerExplain(String answerExplain) {
		this.answerExplain = answerExplain;
	}
	public String getBackNews() {
		return backNews;
	}
	public void setBackNews(String backNews) {
		this.backNews = backNews;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

}
