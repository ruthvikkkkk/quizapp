package com.example.quizapp.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class QuestionsItem implements Serializable {

	@SerializedName("questionUrl")
	private String questionUrl;

	@SerializedName("optionFour")
	private String optionFour;

	@SerializedName("questionId")
	private String questionId;

	@SerializedName("answer")
	private List<String> answer;

	@SerializedName("question")
	private String question;

	@SerializedName("difficultyLevel")
	private String difficultyLevel;

	@SerializedName("optionTwo")
	private String optionTwo;

	@SerializedName("marks")
	private int marks;

	@SerializedName("optionThree")
	private String optionThree;

	@SerializedName("typeOfQuestion")
	private String typeOfQuestion;

	@SerializedName("optionOne")
	private String optionOne;

	@SerializedName("typeOfAnswer")
	private String typeOfAnswer;

	public void setQuestionUrl(String questionUrl){
		this.questionUrl = questionUrl;
	}

	public String getQuestionUrl(){
		return questionUrl;
	}

	public void setOptionFour(String optionFour){
		this.optionFour = optionFour;
	}

	public String getOptionFour(){
		return optionFour;
	}

	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return questionId;
	}

	public void setAnswer(List<String> answer){
		this.answer = answer;
	}

	public List<String> getAnswer(){
		return answer;
	}

	public void setQuestion(String question){
		this.question = question;
	}

	public String getQuestion(){
		return question;
	}

	public void setDifficultyLevel(String difficultyLevel){
		this.difficultyLevel = difficultyLevel;
	}

	public String getDifficultyLevel(){
		return difficultyLevel;
	}

	public void setOptionTwo(String optionTwo){
		this.optionTwo = optionTwo;
	}

	public String getOptionTwo(){
		return optionTwo;
	}

	public void setMarks(int marks){
		this.marks = marks;
	}

	public int getMarks(){
		return marks;
	}

	public void setOptionThree(String optionThree){
		this.optionThree = optionThree;
	}

	public String getOptionThree(){
		return optionThree;
	}

	public void setTypeOfQuestion(String typeOfQuestion){
		this.typeOfQuestion = typeOfQuestion;
	}

	public String getTypeOfQuestion(){
		return typeOfQuestion;
	}

	public void setOptionOne(String optionOne){
		this.optionOne = optionOne;
	}

	public String getOptionOne(){
		return optionOne;
	}

	public void setTypeOfAnswer(String typeOfAnswer){
		this.typeOfAnswer = typeOfAnswer;
	}

	public String getTypeOfAnswer(){
		return typeOfAnswer;
	}

	@Override
 	public String toString(){
		return 
			"QuestionsItem{" + 
			"questionUrl = '" + questionUrl + '\'' + 
			",optionFour = '" + optionFour + '\'' + 
			",questionId = '" + questionId + '\'' + 
			",answer = '" + answer + '\'' + 
			",question = '" + question + '\'' + 
			",difficultyLevel = '" + difficultyLevel + '\'' + 
			",optionTwo = '" + optionTwo + '\'' + 
			",marks = '" + marks + '\'' + 
			",optionThree = '" + optionThree + '\'' + 
			",typeOfQuestion = '" + typeOfQuestion + '\'' + 
			",optionOne = '" + optionOne + '\'' + 
			",typeOfAnswer = '" + typeOfAnswer + '\'' + 
			"}";
		}
}