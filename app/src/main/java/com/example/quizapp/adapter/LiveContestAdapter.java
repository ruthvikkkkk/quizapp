package com.example.quizapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.model.LiveQuizzesModel;

import java.util.List;

public class LiveContestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<LiveQuizzesModel> liveQuizzesList;

    private LiveQuizRedirect liveQuizRedirect;

    public LiveContestAdapter(List<LiveQuizzesModel> liveQuizzesList, LiveQuizRedirect liveQuizRedirect)
    {
        this.liveQuizzesList = liveQuizzesList;
        this.liveQuizRedirect=liveQuizRedirect;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_contest_item,parent,false);
        return new LiveContestAdapter.CustomLiveQuizes(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((LiveContestAdapter.CustomLiveQuizes)holder).onBind(liveQuizzesList.get(position));
    }

    @Override
    public int getItemCount() {
        return liveQuizzesList.size();
    }
    public class CustomLiveQuizes extends RecyclerView.ViewHolder{

        View itemView;

        TextView contestName,contestCategory,contestDuration,contestTime;


        public CustomLiveQuizes(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            itemView.findViewById(R.id.btn_play).setActivated(true);
            itemView.findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    liveQuizRedirect.onLivePlayRedirectDynamic(liveQuizzesList.get(getAdapterPosition()));
                }
            });
        }

        public void onBind(LiveQuizzesModel liveQuiz)
        {

            contestName=itemView.findViewById(R.id.dy_tv_contest_name);
            contestCategory=itemView.findViewById(R.id.dy_tv_category);
            contestDuration=itemView.findViewById(R.id.dy_tv_contest_duration);
            contestTime = itemView.findViewById(R.id.dy_tv_time_to_play);

            contestName.setText(liveQuiz.getContestName());
            contestCategory.setText(liveQuiz.getContentCategory());
            contestDuration.setText(liveQuiz.getNoOfQuestions()+"");

        }


    }

    public interface LiveQuizRedirect
    {
        public void onLivePlayRedirectDynamic( LiveQuizzesModel liveQuiz);

    }

}
