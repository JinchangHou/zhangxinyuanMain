package com.example.volunteer.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.volunteer.Javabean.User;
import com.example.volunteer.MainActivity;
import com.example.volunteer.R;
import com.example.volunteer.stack.ScreenManager;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_paw;
    private Button bt_login;
    public static final int LOGIN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenManager.getScreenManager().pushActivity(this);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, "706b623303ad79f7265aa54f740c3299");
        /**
        * 找到相关的控件
        */
        findViewById(R.id.tv_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new User();
               user.setPassword("1231231");
               user.setMobilePhoneNumber("18326181819");

                user.signUp(new SaveListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if (e == null) {
                            Toast.makeText(LoginActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
//        et_name = (EditText)findViewById(R.id.et_name);
//        et_paw = (EditText)findViewById(R.id.et_paw);
        bt_login = (Button)findViewById(R.id.login);
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
