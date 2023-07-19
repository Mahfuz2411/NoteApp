package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(LoginActivity.User, Context.MODE_PRIVATE);

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

        }



        clickTextEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(LoginActivity.User, Context.MODE_PRIVATE);
                if(tittleText.getText().toString().equals(null) || bodyText.getText().toString().equals(null)) {
                    Toast.makeText(getApplicationContext(), "Invalid tittle or body", Toast.LENGTH_SHORT).show();
                }else if(sharedPreferences.contains(tittleText.getText().toString()) || sharedPreferences.getString(tittleText.getText().toString(), null).equals(bodyText.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "tittle or body already exists", Toast.LENGTH_SHORT).show();
//                    MainActivity.notes.remove(MainActivity.notes.size()-1);
//                    HashSet<String> set = new HashSet(MainActivity.notes);
//                    MainActivity.arrayAdapter.notifyDataSetChanged();
//
//                    Intent intent1 = new Intent(NoteEditorActivity.this, MainActivity.class);
//                    startActivity(intent1);
                } else {
                    // Creating Object of SharedPreferences to store data in the phone
//                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(LoginActivity.User, Context.MODE_PRIVATE);
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
            }
        });
    }
}