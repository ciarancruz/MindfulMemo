package com.example.mymemo.diaryscreens;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymemo.DiaryEntry;
import com.example.mymemo.R;

import java.util.ArrayList;
import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.MyViewHolder> {

    // Test Data
    private List<DiaryEntry> dataList = new ArrayList<>();

//    //Constructor for data
//    public DiaryAdapter(List<DiaryEntry> dataList) {
//        this.dataList = dataList;
//    }

    public void setDataList(List<DiaryEntry> datalist) {
        this.dataList = datalist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DiaryEntry data = dataList.get(position);
        holder.textView.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public DiaryEntry getDiaryAt(int position) {
        return dataList.get(position);
    }

    public void deleteDiary(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }
    }
}
