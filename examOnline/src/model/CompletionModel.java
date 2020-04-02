package model;

public class CompletionModel {
	String titleId;//题目序号	 
	String titleStem;//填空题题题干
	String answerRight;//正确答案	
	String answerExplain;//答案解释
	String backNews="";//存放反馈信息
	boolean success=false;//布尔值初始为false
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
