package com.example.volunteer.Activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.volunteer.MainActivity;
import com.example.volunteer.R;
import com.example.volunteer.Utils.NetWorkUtils;
import com.example.volunteer.Utils.People;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_paw;
    private Button bt_login;
    public static final int LOGIN = 1;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int code;

    //private SomeView someView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //将数据存储到sharedPreferences中
        //得到SharedPreferences.Editor对象，并保存数据到该对象中
        //sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
        //editor = sharedPreferences.edit();
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
                //连接网络只能在子线程中实现
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String username = et_name.getText().toString();
                        String password = et_paw.getText().toString();
                        String result = NetWorkUtils.LoginPost(username,password);
                        Log.e("result-----","");
                        System.out.println(result);
                        Bundle bundle = new Bundle();
                        bundle.putString("result",result);
                        Message message = new Message();
                        message.setData(bundle);
                        message.what = LOGIN;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
        Log.e("","");
    }

    //在主线程里面定义一个handle，用于更新UI
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LOGIN:
                    //获取传输的结果
                    Bundle bundle = new Bundle();
                    bundle = msg.getData();
                    String result = bundle.getString("result");
                    Log.e("result///////","");
                    System.out.println("result--------"+result);
                    //解析数据
                    int resultCode = parseJSONWITHGSON(result);
                    //String user_number = user.get("user_number").getAsString();
                    try {
                        if (resultCode == 101) {
                            Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                            //界面的跳转
                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                           /* editor.putInt("DON", 11);
                            editor.putString("NUM", user_number);
                            editor.commit();
                            finish();*/
                        } else if (resultCode == 102) {
                            Toast.makeText(LoginActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                        } else if (resultCode == 103) {
                            Toast.makeText(LoginActivity.this, "账号不存在！", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
    };

    private int parseJSONWITHGSON(String jsonData){
        Gson gson = new Gson();
        List<People> peopleList = gson.fromJson(jsonData,new TypeToken<List<People>>(){}.getType());
        for (People people : peopleList){
            System.out.println("LoginActivity"+"resultcode is"+people.getResultCode());
            System.out.println("LoginActivity"+"username is"+people.getUsername());
            System.out.println("LoginActivity"+"password is"+people.getPassword());
            code = Integer.parseInt(people.getResultCode());
        }
        return code;
    }
}
