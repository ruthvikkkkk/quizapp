package com.example.quizapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetUserContestState implements Serializable {

	@SerializedName("index")
	private int index;

	@SerializedName("timeLeft")
	private long timeLeft;

	@SerializedName("remainingTime")
	private long remainingTime;

	@SerializedName("contestStatus")
	private String status;

	public void setIndex(int index){
		this.index = index;
	}

	public int getIndex(){
		return index;
	}

	public void setTimeLeft(long timeLeft){
		this.timeLeft = timeLeft;
	}

	public long getTimeLeft(){
		return timeLeft;
	}

	public void setRemainingTime(long remainingTime){
		this.remainingTime = remainingTime;
	}

	public long getRemainingTime(){
		return remainingTime;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"GetUserContestState{" + 
			"index = '" + index + '\'' + 
			",timeLeft = '" + timeLeft + '\'' + 
			",remainingTime = '" + remainingTime + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}