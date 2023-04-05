package com.example.quizapp.model;
import com.google.gson.annotations.SerializedName;
public class AnalyticsProfile{

    @SerializedName("quizId")
    private String quizId;

    @SerializedName("startTime")
    private long startTime;

    @SerializedName("endTime")
    private long endTime;

    @SerializedName("userId")
    private String userId;

    public void setQuizId(String quizId){
        this.quizId = quizId;
    }

    public String getQuizId(){
        return quizId;
    }

    public void setStartTime(long startTime){
        this.startTime = startTime;
    }

    public long getStartTime(){
        return startTime;
    }

    public void setEndTime(long endTime){
        this.endTime = endTime;
    }

    public long getEndTime(){
        return endTime;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }

    @Override
    public String toString(){
        return
                "AnalyticsProfile{" +
                        "quizId = '" + quizId + '\'' +
                        ",startTime = '" + startTime + '\'' +
                        ",endTime = '" + endTime + '\'' +
                        ",userId = '" + userId + '\'' +
                        "}";
    }
}

