package com.example.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class addNote extends AppCompatActivity{

    EditText editTitle, editDescription;

    Spinner category;
    Button addNotes;
    String noteCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTitle = findViewById(R.id.editTitle);
        editDescription = findViewById(R.id.editDescription);
        category = findViewById(R.id.spinnerCategory);
        addNotes = findViewById(R.id.add_note);

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

                    Toast.makeText(addNote.this, "Title:"+title+",Description:"+description+" Note Category:"+noteCategory, Toast.LENGTH_SHORT).show();
                }
            });
    }
}