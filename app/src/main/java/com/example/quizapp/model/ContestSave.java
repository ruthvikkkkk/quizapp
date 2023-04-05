package com.example.quizapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ContestSave{

	@SerializedName("index")
	private int index;

	@SerializedName("userId")
	private String userId;

	@SerializedName("timeLeft")
	private long timeLeft;

	@SerializedName("contestId")
	private String contestId;

	@SerializedName("remainingTime")
	private long remainingTime;

	@SerializedName("contestStatus")
	private String contestStatus;

	public String getContestStatus() {
		return contestStatus;
	}

	public void setContestStatus(String contestStatus) {
		this.contestStatus = contestStatus;
	}

	public void setIndex(int index){
		this.index = index;
	}

	public int getIndex(){
		return index;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setTimeLeft(long timeLeft){
		this.timeLeft = timeLeft;
	}

	public long getTimeLeft(){
		return timeLeft;
	}

	public void setContestId(String contestId){
		this.contestId = contestId;
	}

	public String getContestId(){
		return contestId;
	}

	public void setRemainingTime(long remainingTime){
		this.remainingTime = remainingTime;
	}

	public long getRemainingTime(){
		return remainingTime;
	}

	@Override
 	public String toString(){
		return 
			"ContestSave{" + 
			"index = '" + index + '\'' + 
			",userId = '" + userId + '\'' + 
			",timeLeft = '" + timeLeft + '\'' + 
			",contestId = '" + contestId + '\'' + 
			",remainingTime = '" + remainingTime + '\'' + 
			"}";
		}
}