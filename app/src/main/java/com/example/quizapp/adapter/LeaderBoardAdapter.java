package com.example.quizapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.model.LeaderBoardModel;

import java.util.List;

public class LeaderBoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<LeaderBoardModel> leaderBoardList;

    public LeaderBoardAdapter(List<LeaderBoardModel> leaderBoardList)
    {
        this.leaderBoardList=leaderBoardList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.new_leaderboard_card,parent,false);
        return new LeaderBoardAdapter.customLeaderBoard(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        ((LeaderBoardAdapter.customLeaderBoard)holder).onBind(leaderBoardList.get(position),position);

    }

    public class customLeaderBoard extends RecyclerView.ViewHolder
    {

       View itemView;

        public customLeaderBoard(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
        }
        public void onBind(LeaderBoardModel leaderBoardModel,int position)
        {

            TextView playerName,playerScore,playerRank;
            playerName=itemView.findViewById(R.id.tv_player_name);
            playerScore=itemView.findViewById(R.id.tv_score);
            playerRank=itemView.findViewById(R.id.tv_rank);

            playerName.setText(leaderBoardModel.getUserName());
            playerScore.setText(leaderBoardModel.getScore()+"");
            playerRank.setText(position+1+"");

        }
    }


    @Override
    public int getItemCount() {
        return leaderBoardList.size();
    }


}
