package com.example.quizapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Ranking implements Serializable {

    @SerializedName("contestId")
    private String contestId;

    @SerializedName("usersList")
    private List<LeaderBoardModel> listLeaderBoard;

    public String getContestId() {
        return contestId;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public List<LeaderBoardModel> getListLeaderBoard() {
        return listLeaderBoard;
    }

    public void setListLeaderBoard(List<LeaderBoardModel> listLeaderBoard) {
        this.listLeaderBoard = listLeaderBoard;
    }
}
