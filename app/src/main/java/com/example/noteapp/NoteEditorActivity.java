package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {
    EditText tittleText, bodyText;
    TextView clickTextEdit;
    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        tittleText = findViewById(R.id.editTitle);
        bodyText = findViewById(R.id.editBody);
        clickTextEdit = findViewById(R.id.clickTextSave);


        Intent intent = getIntent();

        Bundle bundle = getIntent().getExtras();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);

        String BodyText;


        noteId = intent.getIntExtra("noteId", -1);
        if (noteId != -1) {
            if (sharedPreferences.contains(MainActivity.notes.get(noteId))) {
                BodyText = sharedPreferences.getString(MainActivity.notes.get(noteId), "");
            } else {
                BodyText = "";
            }
            tittleText.setText(MainActivity.notes.get(noteId));
            bodyText.setText(BodyText);
        }
        else {
            MainActivity.notes.add("");
            noteId = MainActivity.notes.size()-1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
        }



        clickTextEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating Object of SharedPreferences to store data in the phone
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                MainActivity.notes.set(noteId, tittleText.getText().toString());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(tittleText.getText().toString(), bodyText.getText().toString());
                HashSet<String> set = new HashSet(MainActivity.notes);
                sharedPreferences.edit().putString(MainActivity.notes.get(noteId), bodyText.getText().toString()).apply();
                sharedPreferences.edit().putStringSet("notes", set).apply();
                MainActivity.arrayAdapter.notifyDataSetChanged();

                Intent intent1 = new Intent(NoteEditorActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}