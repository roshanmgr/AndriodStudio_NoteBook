package com.example.notebook;

public interface NoteListener {
    void onNoteClick(Note note);
    void onNoteEdit(Note note);
    void onNoteDelete(Note note);
}
