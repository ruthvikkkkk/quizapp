package com.example.quizapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserData implements Serializable {

	@SerializedName("isSports")
	private int isSports;

	@SerializedName("isEntertainment")
	private int isEntertainment;

	@SerializedName("isMusic")
	private int isMusic;

	@SerializedName("isFashion")
	private int isFashion;

	@SerializedName("isTravel")
	private int isTravel;

	@SerializedName("isFood")
	private int isFood;

	@SerializedName("quizUserEmail")
	private String quizUserEmail;

	public void setIsSports(int isSports){
		this.isSports = isSports;
	}

	public int getIsSports(){
		return isSports;
	}

	public void setIsEntertainment(int isEntertainment){
		this.isEntertainment = isEntertainment;
	}

	public int getIsEntertainment(){
		return isEntertainment;
	}

	public void setIsMusic(int isMusic){
		this.isMusic = isMusic;
	}

	public int getIsMusic(){
		return isMusic;
	}

	public void setIsFashion(int isFashion){
		this.isFashion = isFashion;
	}

	public int getIsFashion(){
		return isFashion;
	}

	public void setIsTravel(int isTravel){
		this.isTravel = isTravel;
	}

	public int getIsTravel(){
		return isTravel;
	}

	public void setIsFood(int isFood){
		this.isFood = isFood;
	}

	public int getIsFood(){
		return isFood;
	}

	public void setQuizUserEmail(String quizUserEmail){
		this.quizUserEmail = quizUserEmail;
	}

	public String getQuizUserEmail(){
		return quizUserEmail;
	}

	@Override
	public String toString(){
		return
				"UserData{" +
						"isSports = '" + isSports + '\'' +
						",isEntertainment = '" + isEntertainment + '\'' +
						",isMusic = '" + isMusic + '\'' +
						",isFashion = '" + isFashion + '\'' +
						",isTravel = '" + isTravel + '\'' +
						",isFood = '" + isFood + '\'' +
						",quizUserEmail = '" + quizUserEmail + '\'' +
						"}";
	}
}