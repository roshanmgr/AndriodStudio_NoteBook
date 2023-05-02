package com.example.notebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewsHolder> {

    ArrayList<Note> notes;
    NoteListener noteListener;

    public NotesAdapter(ArrayList<Note> notes,NoteListener noteListener) {
        this.noteListener=noteListener;
        this.notes = notes;
    }

    public void addData(Note note){
        notes.add(note);
        notifyItemInserted(notes.size());
    }

    public void deleteNote(Note note){
        Integer index = notes.indexOf(note);
      //  notes.remove(index);
        notes.remove(note);
        notifyItemRemoved(index);
        //notifyItemRangeChanged(0, notes.size());
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
        MaterialCardView colorNote;

        public NotesViewsHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.display_note_title);
            category = itemView.findViewById(R.id.display_note_category);
            description = itemView.findViewById(R.id.display_note_description);
            colorNote = itemView.findViewById(R.id.cardViewColor);

        }

        public void bindView(Note notes){
            title.setText(notes.getTitle());
            category.setText(notes.getCategory());
            description.setText(notes.getDiscription());

            colorNote.setCardBackgroundColor(notes.getColor());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    noteListener.onNoteClick(notes);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    noteListener.onNoteDelete(notes);
                    return false;
                }
            });

        }
    }
}
