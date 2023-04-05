package com.example.quizapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginCredentials implements Serializable {

	@SerializedName("password")
	private String password;

	@SerializedName("platformId")
	private String platformId;

	@SerializedName("username")
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setPlatformId(String platformId){
		this.platformId = platformId;
	}

	public String getPlatformId(){
		return platformId;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"LoginCredentials{" + 
			"password = '" + password + '\'' + 
			",platformId = '" + platformId + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}