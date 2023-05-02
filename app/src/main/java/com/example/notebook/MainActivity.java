package com.example.notebook;

import static android.widget.Toast.makeText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnaddnote;
    ArrayList<Note> notes;


    NotesAdapter adapter;

    NotebookDbHelper dbHelper;

    LinearLayout ll_no_note;

    ActivityResultLauncher<Intent> resultIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                Intent intent = result.getData();
                String title = intent.getExtras().getString("note_title");
                String description = intent.getExtras().getString("note_description");
                String category = intent.getExtras().getString("note_category");
                Integer color = intent.getExtras().getInt("note_color");
                makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                makeText(MainActivity.this, "Title :" + title + " Description :" + description + "color :" + color , Toast.LENGTH_SHORT).show();

                Note note = new Note(title, description, category, color);
                adapter.addData(note);

                dbHelper.addNote(note);

                if(ll_no_note.getVisibility() == View.VISIBLE){
                    ll_no_note.setVisibility(View.GONE);
                }

            }
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new NotebookDbHelper(getApplicationContext());
        ll_no_note= findViewById(R.id.no_notes);

        notes = new ArrayList<>();
        notes.addAll(dbHelper.getAllNotes());


        btnaddnote = findViewById(R.id.btnaddnote);
        if (notes.size()==0){
            ll_no_note.setVisibility(View.VISIBLE);
        }else {
            ll_no_note.setVisibility(View.GONE);
        }


        RecyclerView rv = findViewById(R.id.recycle_notes);
        adapter = new NotesAdapter(notes, new NoteListener() {
            @Override
            public void onNoteClick(Note note) {
                Intent intent= new Intent(MainActivity.this, NoteDetailActivity.class);
                intent.putExtra("title",note.getTitle());
                intent.putExtra("description",note.getDiscription());
                intent.putExtra("category",note.getCategory());
                intent.putExtra("color", note.getColor());
                startActivity(intent);

            }

            @Override
            public void onNoteEdit(Note note) {

            }

            @Override
            public void onNoteDelete(Note note) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do u want to Delete ? ");
                builder.setTitle("Alert !");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes",(DialogInterface.OnClickListener) (dialog,which)->{
                    dbHelper.deleteNote(note.getId());
                    adapter.deleteNote(note);
                });
                builder.setNegativeButton("No",(DialogInterface.OnClickListener) (dialog,which)->{

                });
               AlertDialog alterDialog= builder.create();
                alterDialog.show();

            }
        });
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