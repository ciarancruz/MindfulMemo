package com.example.mymemo.diaryscreens;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymemo.R;

import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.MyViewHolder> {

    // Test Data
    private List<String> dataList;

    //Constructor for data
    public DiaryAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String data = dataList.get(position);
        holder.textView.setText(data);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }
    }
}
