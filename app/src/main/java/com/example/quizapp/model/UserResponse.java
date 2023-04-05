package com.example.quizapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UserResponse{

	@SerializedName("timeStamp")
	private long timeStamp;

	@SerializedName("contestId")
	private String contestId;

	@SerializedName("contestStatus")
	private String contestStatus;

	@SerializedName("userId")
	private String userId;

	@SerializedName("questionResponseList")
	private List<QuestionResponseListItem> questionResponseList;

	public void setTimeStamp(long timeStamp){
		this.timeStamp = timeStamp;
	}

	public long getTimeStamp(){
		return timeStamp;
	}

	public void setContestId(String contestId){
		this.contestId = contestId;
	}

	public String getContestId(){
		return contestId;
	}

	public void setContestStatus(String contestStatus){
		this.contestStatus = contestStatus;
	}

	public String getContestStatus(){
		return contestStatus;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setQuestionResponseList(List<QuestionResponseListItem> questionResponseList){
		this.questionResponseList = questionResponseList;
	}

	public List<QuestionResponseListItem> getQuestionResponseList(){
		return questionResponseList;
	}

	@Override
 	public String toString(){
		return 
			"UserResponse{" + 
			"timeStamp = '" + timeStamp + '\'' + 
			",contestId = '" + contestId + '\'' + 
			",contestStatus = '" + contestStatus + '\'' + 
			",userId = '" + userId + '\'' + 
			",questionResponseList = '" + questionResponseList + '\'' + 
			"}";
		}
}