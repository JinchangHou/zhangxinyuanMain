package com.example.volunteer.task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.volunteer.R;

public class ViewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        TextView textView = new TextView(this);
        textView.setText("This is the ThirdActivity!");
        setContentView(textView);
        textView.setTextSize(25);
        textView.setPadding(150,150,150,150);

}

}
