package com.example.quizapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.model.Contest;

import java.util.List;

public class ContestsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Contest> contestList;

    private RedirectToContest redirectToContest;
    public ContestsAdapter(List<Contest> contestList,RedirectToContest redirectToContest)
    {
        this.contestList=contestList;
        this.redirectToContest=redirectToContest;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.new_contest_card_item,parent,false);

        return new ContestsAdapter.CustomContestAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((ContestsAdapter.CustomContestAdapter)holder).onBind(contestList.get(position));
    }

    @Override
    public int getItemCount() {
        return contestList.size();
    }


    public class CustomContestAdapter extends RecyclerView.ViewHolder
    {


        View itemView;

        Button redirectButton;
        public CustomContestAdapter(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            redirectButton=itemView.findViewById(R.id.btn_play);

            redirectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition()>-1)
                    {
                        redirectToContest.onPlayRedirect(contestList.get(getAdapterPosition()));
                    }
                }
            });
        }
        public void onBind(Contest contest)
        {

           TextView contestName,contestCategory,contestDuration;

           contestName=itemView.findViewById(R.id.tv_contest_name);
           contestCategory=itemView.findViewById(R.id.tv_category);
           contestDuration=itemView.findViewById(R.id.tv_contest_duration);

           contestName.setText(contest.getContestName());
           contestCategory.setText(contest.getContentCategory());
           contestDuration.setText(contest.getDurationOfContest()/60000 + " minutes");
        }
    }

    public interface RedirectToContest
    {
        public void onPlayRedirect(Contest contest);

    }
}
