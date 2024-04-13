package com.example.mymemo.diaryscreens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymemo.R;

import java.util.ArrayList;

public class MD_RecyclerviewAdapter extends RecyclerView.Adapter<MD_RecyclerviewAdapter.MyViewHolder> {
    Context context;
    ArrayList<MyDiaryModel> myDiaryModels;

    public MD_RecyclerviewAdapter(Context context, ArrayList<MyDiaryModel> myDiaryModels){
        this.context = context;
        this.myDiaryModels = myDiaryModels;

    }
    @NonNull
    @Override
    public MD_RecyclerviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout and give look to each of the rows
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.diary_recycler_row, parent, false);
        return new MD_RecyclerviewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MD_RecyclerviewAdapter.MyViewHolder holder, int position) {
        //assigning values to the views we created for the rows as they come back on the screen
        holder.tvTitle.setText(myDiaryModels.get(position).getDiaryentryTitle());
        holder.tvDate.setText(myDiaryModels.get(position).getDiaryentryDate());
        holder.imageView.setImageResource(myDiaryModels.get(position).getBlackLineImage());
        holder.moreImgButton.setImageResource(myDiaryModels.get(position).getMoreOptionImage());
        holder.likeImgButton.setImageResource(myDiaryModels.get(position).getHeartimage());

    }

    @Override
    public int getItemCount() {
        //for recycler view to know number of items you want displayed
        return myDiaryModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //taking the views from the diary_recycler_row layout

        ImageView imageView;
        ImageButton moreImgButton, likeImgButton;
        TextView tvTitle, tvDate;

         public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.line_divider);
            moreImgButton = itemView.findViewById(R.id.diary_option);
            likeImgButton = itemView.findViewById(R.id.heart_btn);
            tvTitle = itemView.findViewById(R.id.diary_title);
            tvDate = itemView.findViewById(R.id.date_entry);
        }
    }


}
