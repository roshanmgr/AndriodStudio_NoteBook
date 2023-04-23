package com.example.notebook;

import static android.widget.Toast.makeText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnaddnote;
    ArrayList<Note> notes;


    NotesAdapter adapter;

    NotebookDbHelper dbHelper;

    ActivityResultLauncher<Intent> resultIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                Intent intent = result.getData();
                String title = intent.getExtras().getString("note_title");
                String description = intent.getExtras().getString("note_description");
                String category = intent.getExtras().getString("note_category");
                makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                makeText(MainActivity.this, "Title :" + title + " Description :" + description, Toast.LENGTH_SHORT).show();

                Note note = new Note(title, description, category);
                adapter.addData(note);

                dbHelper.addNote(note);

            }
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new NotebookDbHelper(getApplicationContext());

        notes = new ArrayList<>();
        notes.addAll(dbHelper.getAllNotes());


        btnaddnote = findViewById(R.id.btnaddnote);
        RecyclerView rv = findViewById(R.id.recycle_notes);
        adapter = new NotesAdapter(notes);
        rv.setAdapter(adapter);

        btnaddnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addNote.class);
                resultIntent.launch(intent);
            }
        });
    }
}