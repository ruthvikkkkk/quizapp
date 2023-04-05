package com.example.quizapp.model;

import com.google.gson.annotations.SerializedName;

public class QuestionResponseListItem{

	@SerializedName("score")
	private int score;

	@SerializedName("questionId")
	private String questionId;

	public void setScore(int score){
		this.score = score;
	}

	public int getScore(){
		return score;
	}

	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return questionId;
	}

	@Override
 	public String toString(){
		return 
			"QuestionResponseListItem{" + 
			"score = '" + score + '\'' + 
			",questionId = '" + questionId + '\'' + 
			"}";
		}
}