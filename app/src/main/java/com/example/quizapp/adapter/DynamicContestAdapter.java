package com.example.quizapp.adapter;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.model.Contest;
import com.example.quizapp.model.DynamicContest;

import java.util.Date;
import java.util.List;

public class DynamicContestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DynamicContest> contestList;

    private RedirectToContest redirectToContest;
    public DynamicContestAdapter(List<DynamicContest> contestList,RedirectToContest redirectToContest)
    {
        this.contestList=contestList;
        this.redirectToContest=redirectToContest;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_contest_item,parent,false);

        return new CustomContestAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((DynamicContestAdapter.CustomContestAdapter)holder).onBind(contestList.get(position));
    }

    @Override
    public int getItemCount() {
        return contestList.size();
    }


    public class CustomContestAdapter extends RecyclerView.ViewHolder
    {


        View itemView;

        Button redirectButton;
        TextView contestName,contestCategory,contestDuration,contestTime;
        CountDownTimer countDownTimer;
        public CustomContestAdapter(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            redirectButton=itemView.findViewById(R.id.btn_play);

            redirectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition()>-1)
                    {
                        redirectToContest.onPlayRedirectDynamic(contestList.get(getAdapterPosition()));
                    }
                }
            });
        }
        public void onBind(DynamicContest contest)
        {


            contestName=itemView.findViewById(R.id.dy_tv_contest_name);
            contestCategory=itemView.findViewById(R.id.dy_tv_category);
            contestDuration=itemView.findViewById(R.id.dy_tv_contest_duration);
            contestTime = itemView.findViewById(R.id.dy_tv_time_to_play);

            contestName.setText(contest.getContestName());
            contestCategory.setText(contest.getContentCategory());
            contestDuration.setText(contest.getNoOfQuestions()+"");

            long currentTime = new Date().getTime();
            long duration = contest.getStartTime() - currentTime;
            countDownTimer = new CountDownTimer(duration,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    contestTime.setText(formatMilliSecondsToTime(millisUntilFinished));
                }

                @Override
                public void onFinish() {

                }
            }.start();

        }
        private String formatMilliSecondsToTime(long milliseconds) {

            int seconds = (int) (milliseconds / 1000);
            int minutes = (int) ((milliseconds / (1000 * 60)));
            int hours = (int) ((milliseconds / (1000 * 60 * 60)));
            long days = hours/24;
            return twoDigitString(days)+" : "+twoDigitString(hours%24) + " : " + twoDigitString(minutes%60) + " : "
                    + twoDigitString(seconds%60);
        }

        private String twoDigitString(long number) {

            if (number == 0) {
                return "00";
            }

            if (number / 10 == 0) {
                return "0" + number;
            }

            return String.valueOf(number);
        }
    }

    public interface RedirectToContest
    {
        public void onPlayRedirectDynamic(DynamicContest contest);

    }
}