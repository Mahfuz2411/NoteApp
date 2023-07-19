package com.example.noteapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    ArrayList<String> Notes_tittle;
    Context context;
    private LayoutInflater inflater;
    CustomAdapter(Context context, ArrayList<String> Notes_tittle){
        this.context = context;
        this.Notes_tittle = Notes_tittle;
    }

    @Override
    public int getCount() {
        return Notes_tittle.size();
    }

    @Override
    public Object getItem(int i) {
        return Notes_tittle.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sample_view, parent, false);
        }

        TextView textView1 = convertView.findViewById(R.id.NameText);
        TextView textView2 = convertView.findViewById(R.id.BodyText);

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
        String bodyText = "";
        if(sharedPreferences.contains(Notes_tittle.get(position))) {
            bodyText = sharedPreferences.getString(Notes_tittle.get(position), "");
        }

        textView1.setText(Notes_tittle.get(position));
        textView2.setText(bodyText);
        return convertView;
    }
}

