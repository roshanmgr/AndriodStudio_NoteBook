package com.example.notebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewsHolder> {
    @NonNull
    @Override
    public NotesViewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_view,parent,false);
        return new NotesViewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class NotesViewsHolder extends RecyclerView.ViewHolder{

        public NotesViewsHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
