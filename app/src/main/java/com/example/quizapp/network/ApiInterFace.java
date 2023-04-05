package com.example.quizapp.network;

import com.example.quizapp.model.AnalyticsProfile;
import com.example.quizapp.model.Contest;
import com.example.quizapp.model.DynamicContest;
import com.example.quizapp.model.LiveQuizzesModel;
import com.example.quizapp.model.LoginCredentials;
import com.example.quizapp.model.Ranking;
import com.example.quizapp.model.RegisterResponse;
import com.example.quizapp.model.UserRegister;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterFace {
    @GET("/questions/getContestById/{id}")
    Call<Contest> getById(@Path("id") String id);

    @GET("/contest/getAllApprovedContest")
    Call<List<Contest>> getAllContests();

    @GET("/ranking/getLeaderBoard/{contestId}")
    Call<Ranking> getLeaderBoardByContestId(@Path("contestId") String contestId);


    @POST("/oauth/api/auth/register")
    Call<RegisterResponse> registerUser(@Body UserRegister userRegister);

    @GET("/ranking/checkUserExistsOrNot/{userId}/{contestId}")
    Call<Boolean> checkUserInLeaderBoard(@Path("userId") String userId,@Path("contestId") String contestId);

    @POST("/QuizUser/saveUser/{userId}/{userEmail}")
    Call<Integer> sendUidandMailToDb(@Path("userId") String userId,@Path("userEmail") String userEmail);

    @POST("/oauth/api/auth/login")
    Call<RegisterResponse> loginUser(@Body LoginCredentials loginCredentials);

    @POST("player/addPlayer")
    Call<Boolean> addPlayer(@Body AnalyticsProfile analyticsProfile);

    @GET(value = "getAllContestWithUpcomingTime")
    Call<List<DynamicContest>> getAllDynamicContest();

    @GET("/getAllContestWithLiveTime")
    Call<List<LiveQuizzesModel>> getLiveQuizzes();

    @POST("/notify/saveDeviceDetails/{userId}/{token}")
    Call<String> saveDeviceDetails(@Path("userId") String userId,@Path("token") String token);

    @POST("/notify/notifyLogin/{userId}")
    Call<String> notifyLogin(@Path("userId") String userId);

    @POST("/notify/notifyLoginToken/{token}")
    Call<String> notifyLoginToken(@Path("token") String token);
}
