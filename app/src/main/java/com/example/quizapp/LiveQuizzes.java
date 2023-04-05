package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.quizapp.adapter.LiveContestAdapter;
import com.example.quizapp.application.ApplicationClass;
import com.example.quizapp.model.LiveQuizzesModel;
import com.example.quizapp.network.ApiInterFace;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveQuizzes extends AppCompatActivity implements LiveContestAdapter.LiveQuizRedirect, BottomNavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;

    ApiInterFace apiInterFace;

    List<LiveQuizzesModel> liveQuizzesModelList =new ArrayList<>();

    public  void loadDynamicQuizzes()
    {

        apiInterFace.getLiveQuizzes().enqueue(new Callback<List<LiveQuizzesModel>>() {
            @Override
            public void onResponse(Call<List<LiveQuizzesModel>> call, Response<List<LiveQuizzesModel>> response) {
                if(response.body()!=null)
                {
                    liveQuizzesModelList.addAll(response.body());
                    recyclerView.setAdapter(new LiveContestAdapter(liveQuizzesModelList, LiveQuizzes.this));
                    recyclerView.setLayoutManager(new LinearLayoutManager(LiveQuizzes.this, LinearLayoutManager.VERTICAL, false));

                }
            }

            @Override
            public void onFailure(Call<List<LiveQuizzesModel>> call, Throwable t) {
                Toast.makeText(LiveQuizzes.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(t.getLocalizedMessage());

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_quizes);
        recyclerView=findViewById(R.id.rv_live_quiz);
        apiInterFace=((ApplicationClass)getApplication()).DynamicContestRetrofit.create(ApiInterFace.class);
        loadDynamicQuizzes();
    }

    @Override
    public void onLivePlayRedirectDynamic(LiveQuizzesModel liveQuiz) {

        Intent newContest = new Intent(LiveQuizzes.this, DynamicQuizPlaying.class);
        newContest.putExtra("newContest",liveQuiz);
        startActivity(newContest);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}