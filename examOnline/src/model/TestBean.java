package model;
public class TestBean{
   String id;           //存放考号
   float score;         //存放分数
   String questions;    //存放题目
   int number;         //存放题号
   int textAmount; //题目数量
   String choiceA,choiceB,choiceC,choiceD;//存放选择
   String answer;       //存放用户给出的答案
   String correctAnswer; //存放正确答案
   String explainAnswer;//存放答案解释
   String mess;       //存放提示信息 
   String backNews;       //存放反馈 
   boolean success=false;//存放布尔值
   
	public String getBackNews() {
		return backNews;
	}
	public void setBackNews(String backNews) {
		this.backNews = backNews;
	}
	public int getTextAmount() {
		return textAmount;
    }
	public void setTextAmount(int textAmount) {
		this.textAmount = textAmount;
	}
	public String getExplainAnswer() {
		return explainAnswer;
	}
	public void setExplainAnswer(String explainAnswer) {
		this.explainAnswer = explainAnswer;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getCorrectAnswer() {
	      return correctAnswer;
   }
   public void setCorrectAnswer(String s){
      correctAnswer =s;
   }
   public void setId(String s){
      id=s;
   }
   public String getId(){
      return id;
   }
   public void setScore(float s) {
      score = s;
   }
   public float getScore() {
      return score;
   }
   public void setQuestions(String s){
      questions=s;
   }
   public String getQuestions(){
      return questions;
   }
   public void setNumber(int s){
      number=s;
   }
   public int getNumber(){
      return number;
   }
   public void setChoiceA(String s){
      choiceA=s;
   }
   public String getChoiceA(){
      return choiceA;
   }
    public void setChoiceB(String s){
      choiceB=s;
   }
   public String getChoiceB(){
      return choiceB;
   }
    public void setChoiceC(String s){
      choiceC=s;
   }
   public String getChoiceC(){
      return choiceC;
   }
   public void setChoiceD(String s){
      choiceD=s;
   }
   public String getChoiceD(){
      return choiceD;
   } 
   public void setAnswer(String s){
      answer=s;
   }
   public String getAnswer(){
      return answer;
   } 
   public void setMess(String s){
      mess=s;
   }
   public String getMess(){
      return mess;
   }
}
