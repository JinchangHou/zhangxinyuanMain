package com.example.volunteer.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.volunteer.MainActivity;
import com.example.volunteer.R;
import com.example.volunteer.stack.ScreenManager;

public class LoginActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_paw;
    private Button bt_login;
    public static final int LOGIN = 1;



    //private SomeView someView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenManager.getScreenManager().pushActivity(this);
        setContentView(R.layout.activity_login);

        /**
        * 找到相关的控件
        */
        et_name = (EditText)findViewById(R.id.et_name);
        et_paw = (EditText)findViewById(R.id.et_paw);
        bt_login = (Button)findViewById(R.id.bt_login);
        /**
         * 实现登录按钮的功能
         */
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
