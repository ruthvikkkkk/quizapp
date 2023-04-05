package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.quizapp.adapter.ContestsAdapter;
import com.example.quizapp.application.ApplicationClass;
import com.example.quizapp.model.Contest;
import com.example.quizapp.network.ApiInterFace;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContestsActivity extends AppCompatActivity implements ContestsAdapter.RedirectToContest, BottomNavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;

    private ApiInterFace apiInterFace;

    private List<Contest> contestList=new ArrayList<>();

    BottomNavigationView bottomNavigationView;
    public void loadContests()
    {

        apiInterFace.getAllContests().enqueue(new Callback<List<Contest>>() {
            @Override
            public void onResponse(Call<List<Contest>> call, Response<List<Contest>> response) {
                if(response.body()!=null)
                {
                    contestList.addAll(response.body());
                    recyclerView.setAdapter(new ContestsAdapter(contestList,ContestsActivity.this));
                    recyclerView.setLayoutManager(new LinearLayoutManager(ContestsActivity.this, LinearLayoutManager.VERTICAL, false));

                }else{
                    Toast.makeText(ContestsActivity.this, "empty response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Contest>> call, Throwable t) {
                Toast.makeText(ContestsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contests);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(ContestsActivity.this);
        bottomNavigationView.setSelectedItemId(R.id.quiz);
        recyclerView=findViewById(R.id.rv_contest);
        apiInterFace=((ApplicationClass)getApplication()).retrofit.create(ApiInterFace.class);
        loadContests();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(getSharedPreferences("LoginCredentialsPref", MODE_PRIVATE).getAll().size() == 0)
            finish();
    }

    //
//    public void onPointerCaptureChanged(boolean hasCapture) {
//        super.onPointerCaptureChanged(hasCapture);
//    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.quiz:

                break;
            case R.id.upcomingcontests:
                startActivity(new Intent(ContestsActivity.this,DynamicQuizActivity.class));

                break;
            case R.id.dynamic:
                startActivity(new Intent(ContestsActivity.this, LiveQuizzes.class));
                break;

            case R.id.profile:
                Intent cartIntent=new Intent(ContestsActivity.this,UserProfileActivity.class);
                startActivity(cartIntent);
                break;
        }

        return true;
    }

    @Override
    public void onPlayRedirect(Contest contest) {
        Intent newContest=new Intent(ContestsActivity.this,MainActivity.class);
        newContest.putExtra("newContest",contest);
        startActivity(newContest);
    }
}