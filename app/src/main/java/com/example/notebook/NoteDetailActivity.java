package com.example.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class NoteDetailActivity extends AppCompatActivity {

    TextView tvTitle, tvDescription, tvCat;
    String title,description,category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        tvTitle = findViewById(R.id.detailTitle);
        tvDescription = findViewById(R.id.ediDescription);
        tvCat = findViewById(R.id.editCategory);


        Intent intent = getIntent();
        title = intent.getExtras().getString("title");
        description = intent.getExtras().getString("description");
        category = intent.getExtras().getString("category");
        tvTitle.setText(title);
        tvDescription.setText(description);
        tvCat.setText(category);

    }


}