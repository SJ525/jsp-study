package model;

public class ChoiceModel {
	String titleId;//题目序号	
	String titleStem;//选择题题干
	String optionA;//选项A
	String optionB;//选项B	
	String optionC;//选项C	
	String optionD;//选项D
	String answerRight;//正确答案	
	String answerExplain;//答案解释
	String backNews="";
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
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
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
