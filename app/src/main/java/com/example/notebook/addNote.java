package com.example.notebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Random;

public class addNote extends AppCompatActivity{

    EditText editTitle, editDescription;

    Spinner category;
    Button addNotes;
    String noteCategory;
    RecyclerView rvColor;
    MaterialToolbar toolbar;
    Integer selectedColor= Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTitle = findViewById(R.id.editTitle);
        editDescription = findViewById(R.id.editDescription);
        category = findViewById(R.id.spinnerCategory);
        addNotes = findViewById(R.id.add_note);
        rvColor = findViewById(R.id.color_list);
        toolbar = findViewById(R.id.add_note_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int i =0; i<=50; i++){
            Random random= new Random();
            int color = Color.argb(255,random.nextInt(256),random.nextInt(256),random.nextInt(256));
            colors.add(color);
        }

        NoteColorAdapter adapter= new NoteColorAdapter(colors, new NoteColorListener() {
            @Override
            public void onNoteColorClicked(int color) {
                selectedColor = color;

            }
        });
        rvColor.setAdapter(adapter);

        String[] items={"normal","urgent","important"};
        ArrayAdapter spinnerAdapter= new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,items);
            category.setAdapter(spinnerAdapter);

            category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    noteCategory = category.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            addNotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title = editTitle.getText().toString();
                    String description = editDescription.getText().toString();

                    if (validate(title, description)) {
                        Intent intent = new Intent();
                        intent.putExtra("note_title", title);
                        intent.putExtra("note_description", description);
                        intent.putExtra("note_category", noteCategory);
                        intent.putExtra("note_color", selectedColor);

                        setResult(RESULT_OK,intent);
                        finish();

                    }else {
                        Toast.makeText(addNote.this, "Validate data", Toast.LENGTH_SHORT).show();

                    }



                }
            });
    }

    public boolean validate(String title,String description){
        if (!title.isEmpty() && !description.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

}