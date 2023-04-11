package com.example.notebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnaddnote;
    ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes = new ArrayList<>();
        notes.add(new Note("sd","fgs","fds"));
        notes.add(new Note("sd","fgs","fds"));
        notes.add(new Note("sd","fgs","fds"));

        btnaddnote = findViewById(R.id.btnaddnote);
        RecyclerView rv= findViewById(R.id.recycle_notes);
        rv.setAdapter(new NotesAdapter(notes));

        btnaddnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addNote.class);
                startActivity(intent);
            }
        });


    }
}