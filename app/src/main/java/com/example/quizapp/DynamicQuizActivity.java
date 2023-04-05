package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.quizapp.adapter.ContestsAdapter;
import com.example.quizapp.adapter.DynamicContestAdapter;
import com.example.quizapp.application.ApplicationClass;
import com.example.quizapp.model.Contest;
import com.example.quizapp.model.DynamicContest;
import com.example.quizapp.network.ApiInterFace;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DynamicQuizActivity extends AppCompatActivity implements DynamicContestAdapter.RedirectToContest,ContestsAdapter.RedirectToContest, BottomNavigationView.OnNavigationItemSelectedListener{

    private RecyclerView recyclerView;

    private ApiInterFace apiInterFace;

    private List<DynamicContest> contestList=new ArrayList<>();

    BottomNavigationView bottomNavigationView;
    public void loadContests()
    {

        apiInterFace.getAllDynamicContest().enqueue(new Callback<List<DynamicContest>>() {
            @Override
            public void onResponse(Call<List<DynamicContest>> call, Response<List<DynamicContest>> response) {
                if(response.body()!=null)
                {
                    contestList.addAll(response.body());
                    recyclerView.setAdapter(new DynamicContestAdapter(contestList,DynamicQuizActivity.this));
                    recyclerView.setLayoutManager(new LinearLayoutManager(DynamicQuizActivity.this, LinearLayoutManager.VERTICAL, false));

                }else{
                    Toast.makeText(DynamicQuizActivity.this, "empty response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DynamicContest>> call, Throwable t) {

                Toast.makeText(DynamicQuizActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_quiz);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        bottomNavigationView = findViewById(R.id.bottomNavigationView_dynamic);
        bottomNavigationView.setOnNavigationItemSelectedListener(DynamicQuizActivity.this);
        bottomNavigationView.setSelectedItemId(R.id.upcomingcontests);
            recyclerView=findViewById(R.id.rv_contest);
            apiInterFace=((ApplicationClass)getApplication()).DynamicContestRetrofit.create(ApiInterFace.class);
            loadContests();
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.quiz:
                startActivity(new Intent(DynamicQuizActivity.this,ContestsActivity.class));

                break;
            case R.id.upcomingcontests:

                break;
            case R.id.dynamic:
               startActivity(new Intent(DynamicQuizActivity.this,DynamicQuizActivity.class));
                break;

            case R.id.profile:
                Intent cartIntent=new Intent(DynamicQuizActivity.this,UserProfileActivity.class);
                startActivity(cartIntent);
                break;
        }

        return true;
    }

    @Override
    public void onPlayRedirectDynamic(DynamicContest contest) {
        Intent newContest=new Intent(DynamicQuizActivity.this,DynamicQuizPlaying.class);
        newContest.putExtra("newContest",contest);
        startActivity(newContest);
    }




    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


    @Override
    public void onPlayRedirect(Contest contest) {

    }
}