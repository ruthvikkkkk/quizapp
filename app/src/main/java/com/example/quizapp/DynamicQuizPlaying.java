package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.quizapp.application.ApplicationClass;
import com.example.quizapp.model.AnalyticsProfile;
import com.example.quizapp.model.Contest;
import com.example.quizapp.model.LiveQuizzesModel;
import com.example.quizapp.model.QuestionResponseListItem;
import com.example.quizapp.model.QuestionsItem;
import com.example.quizapp.model.UserResponse;
import com.example.quizapp.network.ApiInterFace;
import com.example.quizapp.network.UserApiInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DynamicQuizPlaying extends AppCompatActivity {

    private TextView mTextField,questionView,difficultyLevel, marks, imageQuestion, videoQuestion, audioQuestion;
    private Button nextButton;
    private long qnCurrentTime, adCurrentTime, leaderCurrentTime;
    private VideoView videoView;
    List<QuestionResponseListItem> questionResponseListItems = new ArrayList<>();
    UserResponse userResponse = new UserResponse();
    private int i=0;
    private LiveQuizzesModel contest;
    private Contest thisContest;
    private long startingTime;
    CountDownTimer qnTimer, adTimer, leaderTimer;
    private MediaController mediaController;
    private ApiInterFace apiInterFace,leaderApiInterFace,analyticsApiInterface;
    private  UserApiInterface userApiInterface;
    private RadioButton option1,option2,option3,option4;
    private RadioGroup radioGroup;
    private CheckBox chOption1,chOption2,chOption3,chOption4;
    private ImageView imageView,playPause;
    private LinearLayout layout, image_linear, video_linear, audio_linear;
    private MediaPlayer mp;
    List<QuestionsItem> questionList;
    private ProgressBar progressBar;
    long duration;
    String userId ;
    String token;
    private int noOfQuestions ;
    private long questionDuration;
    private  void addAnswer(){
        List<String> answerList = new ArrayList<>();
        if(radioGroup.getVisibility() == View.VISIBLE){
            RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
            if(radioButton != null) {
                answerList.add(radioButton.getText().toString());
            }else{
                Toast.makeText(this, "Select Answer!", Toast.LENGTH_SHORT).show();
            }
            System.out.println(answerList);
        }else{
            if(chOption1.isChecked()) {
                answerList.add(chOption1.getText().toString());
                chOption1.setChecked(false);
                //radioGroup.clearCheck();
            }
            else if(chOption2.isChecked()) {
                answerList.add(chOption2.getText().toString());
                chOption2.setChecked(false);
                //radioGroup.clearCheck();
            }
            else if(chOption3.isChecked()) {
                answerList.add(chOption3.getText().toString());
                chOption3.setChecked(false);
                //radioGroup.clearCheck();
            }
            else if(chOption4.isChecked()) {
                answerList.add(chOption4.getText().toString());
                chOption4.setChecked(false);
                //radioGroup.clearCheck();
            }else{
            }
        }

        QuestionResponseListItem responseItem = new QuestionResponseListItem();
        responseItem.setQuestionId(questionList.get(i).getQuestionId());

        if (questionList.get(i).getAnswer().containsAll(answerList)) {
            responseItem.setScore(Integer.parseInt(marks.getText().toString()));
        } else {
            responseItem.setScore(0);
        }
        questionResponseListItems.add(responseItem);
        //i++;
        radioGroup.clearCheck();
    }
    public void setScreen(QuestionsItem question){
        if(mp.isPlaying())
            mp.stop();

//        counter(duration-10);
//        leaderTimer.cancel();

        if(videoView.isPlaying())
            videoView.stopPlayback();

        QuestionsItem questionsItem = question;
        String questionStatement=questionsItem.getQuestion();
        String optionOne=questionsItem.getOptionOne();
        String optionTwo=questionsItem.getOptionTwo();
        String optionThree=questionsItem.getOptionThree();
        String optionFour=questionsItem.getOptionFour();
        String url=questionsItem.getQuestionUrl();
        String questionType=questionsItem.getTypeOfQuestion();
        String answerType=questionsItem.getTypeOfAnswer();
        int marks = questionsItem.getMarks();
        String difficulty=questionsItem.getDifficultyLevel();

        nextButton.setVisibility(View.VISIBLE);

        layout.setVisibility(View.GONE);
        questionView.setVisibility(View.GONE);
        radioGroup.setVisibility(View.GONE);
        image_linear.setVisibility(View.GONE);
        video_linear.setVisibility(View.GONE);
        audio_linear.setVisibility(View.GONE);
        //radioGroup.clearCheck();

        this.marks.setText(marks+"");
        difficultyLevel.setText(difficulty);
        if(answerType.equals("SINGLE")){
            radioGroup.setVisibility(View.VISIBLE);
            option1.setText(optionOne);
            option2.setText(optionTwo);
            option3.setText(optionThree);
            option4.setText(optionFour);

        } else if (answerType.equals("MULTI")) {

            layout.setVisibility(View.VISIBLE);
            chOption1.setText(optionOne);
            chOption2.setText(optionTwo);
            chOption3.setText(optionThree);
            chOption4.setText(optionFour);
        }else{
            // layout.setVisibility(View.VISIBLE);
        }

        if(questionType.equals("IMAGE")){
            imageView.setVisibility(View.VISIBLE);
            image_linear.setVisibility(View.VISIBLE);
//            Toast.makeText(this, questionStatement, Toast.LENGTH_SHORT).show();
            imageQuestion.setText(questionStatement);
            Glide.with(imageView.getContext())
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.baseline_downloading_24)
                    .into(imageView);
        }else if(questionType.equals("VIDEO")){

            videoView.setVisibility(View.VISIBLE);
            video_linear.setVisibility(View.VISIBLE);
            videoQuestion.setText(questionStatement);
//            Toast.makeText(this, questionStatement, Toast.LENGTH_SHORT).show();
            mediaController = new MediaController(this);
            mediaController.setAnchorView(mTextField);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(Uri.parse(url));
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                        @Override
                        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                            mediaController = new MediaController(DynamicQuizPlaying.this);
                            videoView.setMediaController(mediaController);
                            mediaController.setAnchorView(videoView);
                        }
                    });
                }
            });
            videoView.start();


        }else if(questionType.equals("AUDIO")){
//            Toast.makeText(this, questionsItem.getQuestion(), Toast.LENGTH_SHORT).show();
            playPause.setVisibility(View.VISIBLE);
            audioQuestion.setText(questionStatement);
            audio_linear.setVisibility(View.VISIBLE);
            //stop.setVisibility(View.VISIBLE);
            try{
                mp.setDataSource(url);//Write your location here
                mp.prepare();
                mp.start();
                //mp.setLooping(true);

                playPause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (playPause.getDrawable().getConstantState().equals(getDrawable(R.drawable.baseline_pause_24).getConstantState())) {
                            mp.pause();
                            playPause.setImageDrawable(getDrawable(R.drawable.baseline_play_arrow_24));
                        } else {
                            mp.start();
                            playPause.setImageDrawable(getDrawable(R.drawable.baseline_pause_24));
                        }

                    }
                });

            }catch(Exception e){e.printStackTrace();}



        }else{
//            Toast.makeText(this, questionStatement, Toast.LENGTH_SHORT).show();
            questionView.setVisibility(View.VISIBLE);
            questionView.setText(question.getQuestion());
        }
    }
    private void getContest(String id){
        progressBar.setVisibility(View.VISIBLE);
        apiInterFace.getById(id).enqueue(
                new Callback<Contest>() {
                    @Override
                    public void onResponse(Call<Contest> call, Response<Contest> response) {
                        if(response.isSuccessful()&& response.body()!=null)
                        {
                            thisContest = response.body();
                            questionList = thisContest.getQuestions();
                            progressBar.setVisibility(View.GONE);
                            long systemCurrentTime = new Date().getTime();
                            i = (int) ((int) ((systemCurrentTime - startingTime)/1000)/duration);
                            long val = (((systemCurrentTime-startingTime)/1000))%duration;
                            Toast.makeText(DynamicQuizPlaying.this, "i is " + i, Toast.LENGTH_SHORT).show();
                            Toast.makeText(DynamicQuizPlaying.this, "val is " + val, Toast.LENGTH_SHORT).show();
                            if((val >= 0) && (val < (duration - 10))){
                                //setScreen(questionList.get(i++));
                                counter((duration-10)-val);
                            }
                            else if(val>duration-10 && val<duration-5){
                                i++;
                                displayAd(5-(val%5));
                            }
                            else{
                                i++;
                                leaderBoard(5-(val%5));
                            }

                            //addAnswer(0);
                            Log.i("response", questionList.toString());
                            // Toast.makeText(MainActivity.this, "got response"+response.body(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(DynamicQuizPlaying.this, "empty response", Toast.LENGTH_SHORT).show();
                            Log.i("response empty", questionList.toString());
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<Contest> call, Throwable t) {
                        Toast.makeText(DynamicQuizPlaying.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("error",t.getMessage());
                        progressBar.setVisibility(View.GONE);
                    }
                }
        );
    }
    public void  counter(long duration){

        findViewById(R.id.dynamic_ad_frame).setVisibility(View.GONE);
        findViewById(R.id.dynamic_leader_frame).setVisibility(View.GONE);
        findViewById(R.id.dynamic_question_frame).setVisibility(View.VISIBLE);
        if(leaderTimer!=null){
            leaderTimer.cancel();
        }
        if(i < contest.getNoOfQuestions())
            setScreen(questionList.get(i));
        else
            startActivity(new Intent(getApplicationContext(), LeaderBoard.class));
        qnTimer = new CountDownTimer((duration-10)*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                mTextField.setText( ""+(millisUntilFinished/1000));
                qnCurrentTime = millisUntilFinished;
            }

            public void onFinish() {
                if(mp.isPlaying())
                    mp.stop();

                if(videoView.isPlaying())
                    videoView.stopPlayback();

                addAnswer();
                displayAd(5000);

//                userResponse.setQuestionResponseList(questionResponseListItems);
//                userResponse.setContestId(contest.getContestId());
//                userResponse.setUserId(userId);
//                userResponse.setTimeStamp(new Date().getTime());
//
//                userApiInterface.getQuestionResponse(userResponse).enqueue(new Callback<Integer>() {
//                    @Override
//                    public void onResponse(Call<Integer> call, Response<Integer> response) {
////                        Log.i("user response", response.body().toString());
//                        Toast.makeText(DynamicQuizPlaying.this, "score = " + response.body(), Toast.LENGTH_SHORT).show();
//                         finish();
//                    }
//                    @Override
//                    public void onFailure(Call<Integer> call, Throwable t) {
//                        Log.i("user fail", t.getLocalizedMessage());
//                    }
//                });
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_frames);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mTextField=findViewById(R.id.timer_check);
        questionView=findViewById(R.id.tv_question);
        nextButton=findViewById(R.id.btn_next);
        videoView=findViewById(R.id.video_question);
        imageView=findViewById(R.id.img_question);
        option1=findViewById(R.id.rd_option1);
        option2=findViewById(R.id.rd_option2);
        option3=findViewById(R.id.rd_option3);
        option4=findViewById(R.id.rd_option4);
        chOption1=findViewById(R.id.check_option1);
        chOption2=findViewById(R.id.check_option2);
        chOption3=findViewById(R.id.check_option3);
        chOption4=findViewById(R.id.check_option4);
        difficultyLevel=findViewById(R.id.tv_diff);
        marks =findViewById(R.id.tv_mark);
        radioGroup=findViewById(R.id.radiogroup);
        layout=findViewById(R.id.check_linear);
        playPause=findViewById(R.id.img_play);
        image_linear=findViewById(R.id.image_linear);
        video_linear=findViewById(R.id.video_linear);
        audio_linear = findViewById(R.id.audio_linear);
        imageQuestion = findViewById(R.id.image_text);
        videoQuestion = findViewById(R.id.video_text);
        audioQuestion = findViewById(R.id.audio_text);
        progressBar=findViewById(R.id.progressBar_main);

        apiInterFace = ((ApplicationClass)getApplication()).retrofit.create(ApiInterFace.class);
        userApiInterface = ((ApplicationClass) getApplication()).userRetrofit.create(UserApiInterface.class);
        leaderApiInterFace= ((ApplicationClass) getApplication()).leaderBoardRetrofit.create(ApiInterFace.class);
        analyticsApiInterface = ((ApplicationClass) getApplication()).AnalyticsRetrofit.create(ApiInterFace.class);

        SharedPreferences sharedPreferences = getSharedPreferences("LoginCredentialsPref",MODE_PRIVATE);
        userId = sharedPreferences.getString("username","");
        token = sharedPreferences.getString("accessToken","");

        Intent contestIntent = getIntent();
        contest = (LiveQuizzesModel) contestIntent.getSerializableExtra("newContest");
        duration = contest.getDurationOfContest()+10;
        startingTime = contest.getStartTime();
        mp = new MediaPlayer();
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying())
                    mp.stop();

                qnTimer.cancel();

                if(videoView.isPlaying())
                    videoView.stopPlayback();

                if(radioGroup.getVisibility() == View.VISIBLE && radioGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(DynamicQuizPlaying.this, "Select an answer!", Toast.LENGTH_SHORT).show();
                }else{
                    if(i < questionList.size()-1) {
                        //Toast.makeText(DynamicQuizPlaying.this, "CALL method to display add", Toast.LENGTH_SHORT).show();
                        //callMethodToDisplayAdd
                        long time = (qnCurrentTime)+5000;
                        displayAd(time);
                    }else{
                        Toast.makeText(DynamicQuizPlaying.this, "over!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void displayAd(long time) {
        //set the image

        findViewById(R.id.dynamic_question_frame).setVisibility(View.GONE);
        findViewById(R.id.dynamic_leader_frame).setVisibility(View.GONE);
        findViewById(R.id.dynamic_ad_frame).setVisibility(View.VISIBLE);

        if(qnTimer != null)
            qnTimer.cancel();
        Toast.makeText(this, "Displaying ad", Toast.LENGTH_SHORT).show();
        nextButton.setVisibility(View.GONE);
        //qnTimer.cancel();
        adTimer = new CountDownTimer(time,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                ((TextView) findViewById(R.id.ad_timer)).setText((millisUntilFinished/1000) + "");
                adCurrentTime = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                 //callLeaderBoardApi
                leaderBoard(5);
            }
        }.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getContest(contest.getContestId());
    }

    private void leaderBoard(long time) {

        findViewById(R.id.dynamic_ad_frame).setVisibility(View.GONE);
        findViewById(R.id.dynamic_question_frame).setVisibility(View.GONE);
        findViewById(R.id.dynamic_leader_frame).setVisibility(View.VISIBLE);
        if(adTimer != null)
            adTimer.cancel();

        //call api of leaderboard
        Toast.makeText(this, "LeaderBoard", Toast.LENGTH_SHORT).show();
        leaderTimer = new CountDownTimer(time*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                ((TextView) findViewById(R.id.leaderboard_timer)).setText((millisUntilFinished/1000) + "");
                ((TextView) findViewById(R.id.leaderboard_timer)).setText((millisUntilFinished/1000) + "");
                leaderCurrentTime = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                i++;
                counter(duration);
            }
        }.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        if(countDownTimer!=null)
//            countDownTimer.cancel();
//        if(existsInLeaderboard) {
//            ContestSave contestSave = new ContestSave();
//            contestSave.setContestId(contest.getContestId());
//            contestSave.setRemainingTime(currentTime);
//            contestSave.setContestStatus("not completed");
//            contestSave.setIndex(i);
//            contestSave.setUserId(userId);
//            contestSave.setTimeLeft(new Date().getTime());
//
//            userApiInterface.putContext(contestSave, userId).enqueue(new Callback<GetUserContestState>() {
//                @Override
//                public void onResponse(Call<GetUserContestState> call, Response<GetUserContestState> response) {
//                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_LONG);
//                    Toast.makeText(MainActivity.this, "state saved!", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onFailure(Call<GetUserContestState> call, Throwable t) {
//                    Log.e("state save error", t.getLocalizedMessage());
//                }
//            });
//        }
//
//        if(sendQuestionData) {
//
//            userResponse.setQuestionResponseList(questionResponseListItems);
//            userResponse.setContestId(contest.getContestId());
//            userResponse.setUserId(userId);
//            userResponse.setContestStatus("not completed");
//            userResponse.setQuestionResponseList(questionResponseListItems);
//            userResponse.setTimeStamp(new Date().getTime());
//
//            userApiInterface.getQuestionResponse(userResponse).enqueue(new Callback<Integer>() {
//                @Override
//                public void onResponse(Call<Integer> call, Response<Integer> response) {
//                    //Log.i("user response", response.body().toString());
//                    Toast.makeText(MainActivity.this, "score = " + response.body(), Toast.LENGTH_SHORT).show();
////                Intent leader=new Intent(MainActivity.this,LeaderBoard.class);
////                leader.putExtra("contestId",contest.getContestId());
////                startActivity(leader);
//                    finish();
//                }
//
//                @Override
//                public void onFailure(Call<Integer> call, Throwable t) {
//                    Log.i("user fail", t.getLocalizedMessage());
//                }
//            });
//        }
    }

    public void callAnalyticsApi(){
        AnalyticsProfile analyticsProfile = new AnalyticsProfile();
        analyticsProfile.setQuizId(contest.getContestId());
        analyticsProfile.setUserId(getSharedPreferences("LoginCredentialsPref", MODE_PRIVATE).getString("userId", ""));
        analyticsProfile.setStartTime(0);
        analyticsProfile.setEndTime(new Date().getTime());
        analyticsApiInterface.addPlayer(analyticsProfile).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }
}