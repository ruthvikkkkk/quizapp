package com.example.quizapp.network;
import com.example.quizapp.model.ContestSave;

import com.example.quizapp.model.GetUserContestState;
import com.example.quizapp.model.UserData;
import com.example.quizapp.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApiInterface {

    @POST("QuizUser/saveContext/{userId}")
    Call<GetUserContestState> putContext(@Body ContestSave contestSave, @Path("userId") String userId);

    @POST("QuizUser/getQuestionResponse")
    Call<Integer> getQuestionResponse(@Body UserResponse userResponse);

    @GET("QuizUser/getIndex/{userId}/{contestId}")
    Call<GetUserContestState> getContestState(@Path("userId") String userId, @Path("contestId") String contestId);

    @POST("QuizUser/countOfCategory/{userId}/{category}")
    Call<Boolean> updateCountOfUser(@Path("userId") String userId, @Path("category") String category);


    @GET("QuizUser/getListOfCategory/{userId}")
    Call<UserData> getUserData(@Path("userId") String userId);
}
