package com.example.notebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewsHolder> {

    ArrayList<Note> notes;

    public NotesAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesViewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_view,parent,false);
        return new NotesViewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewsHolder holder, int position) {
        holder.bindView(notes.get(position));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewsHolder extends RecyclerView.ViewHolder{

        TextView category, title, description;

        public NotesViewsHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.display_note_title);
            category = itemView.findViewById(R.id.display_note_category);
            description = itemView.findViewById(R.id.display_note_description);

        }

        public void bindView(Note notes){
            title.setText(notes.getTitle());
            category.setText(notes.getCategory());
            description.setText(notes.getDiscription());

        }
    }
}
