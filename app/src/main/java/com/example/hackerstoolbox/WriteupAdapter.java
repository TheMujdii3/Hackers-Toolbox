package com.example.hackerstoolbox;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WriteupAdapter extends RecyclerView.Adapter<WriteupAdapter.ViewHolder> {
    private List<Writeup> writeups;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view, Writeup writeup);
    }

    public WriteupAdapter(Context context, List<Writeup> writeups, OnItemClickListener listener) {
        this.context = context;
        this.writeups = writeups;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, tags;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.writeupTitle);
            tags = itemView.findViewById(R.id.writeupTags);
            itemView.setOnClickListener(v -> {
                listener.onItemClick(v, writeups.get(getAdapterPosition()));
            });
        }
    }

    @Override
    public WriteupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_writeup, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WriteupAdapter.ViewHolder holder, int position) {
        Writeup w = writeups.get(position);
        Log.d("ADAPTER", "Binding: " + w.title);
        holder.title.setText(w.title != null ? w.title : "No title");
        holder.tags.setText(w.tags != null ? w.tags : "No tags");
    }

    @Override
    public int getItemCount() {
        return writeups.size();
    }
}
