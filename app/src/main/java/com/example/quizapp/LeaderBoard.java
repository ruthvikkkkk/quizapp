package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.quizapp.adapter.LeaderBoardAdapter;
import com.example.quizapp.application.ApplicationClass;
import com.example.quizapp.model.LeaderBoardModel;
import com.example.quizapp.model.Ranking;
import com.example.quizapp.network.ApiInterFace;
import com.google.android.gms.common.api.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderBoard extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<LeaderBoardModel> listLeaderBoard=new ArrayList<>();

    private ApiInterFace apiInterFace;

    private void loadLeaderBoard(String contestId)
    {

        apiInterFace.getLeaderBoardByContestId(contestId).enqueue(new Callback<Ranking>() {
            @Override
            public void onResponse(Call<Ranking> call, Response<Ranking> response) {
                if(response.body()!=null)
                {
                    listLeaderBoard.addAll(response.body().getListLeaderBoard());
                    recyclerView.setAdapter(new LeaderBoardAdapter(listLeaderBoard));
                    recyclerView.setLayoutManager(new LinearLayoutManager(LeaderBoard.this, LinearLayoutManager.VERTICAL, false));
                }else{
                    Toast.makeText(LeaderBoard.this, "emptyResponse", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Ranking> call, Throwable t) {

                Toast.makeText(LeaderBoard.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView=findViewById(R.id.rv_leader);
        apiInterFace=((ApplicationClass)getApplication()).leaderBoardRetrofit.create(ApiInterFace.class);
        Intent contestIntent=getIntent();
        loadLeaderBoard(contestIntent.getStringExtra("contestId"));
    }
}