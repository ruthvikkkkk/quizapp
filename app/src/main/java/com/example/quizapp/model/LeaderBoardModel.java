package com.example.quizapp.model;

import com.google.gson.annotations.SerializedName;

public class LeaderBoardModel {

	@SerializedName("score")
	private int score;

	@SerializedName("timing")
	private long timing;

	@SerializedName("userId")
	private String userName;

	public void setScore(int score){
		this.score = score;
	}

	public int getScore(){
		return score;
	}

	public void setTiming(long timing){
		this.timing = timing;
	}

	public long getTiming(){
		return timing;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	@Override
 	public String toString(){
		return 
			"LeaderBoard{" + 
			"score = '" + score + '\'' + 
			",timing = '" + timing + '\'' + 
			",userId = '" + userName + '\'' +
			"}";
		}
}