package com.example.volunteer.personal_page;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.volunteer.Javabean.Student;
import com.example.volunteer.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ChangeMessageActivity extends AppCompatActivity {
    //intent传入的值，决定修改哪一个值
    public static final int CHANGE_TYPE_STU_NAME = 1;
    public static final int CHANGE_TYPE_SCHOOL = 2;
    public static final int CHANGE_TYPE_MOBILE = 3;
    public static final int CHANGE_TYPE_EMAIL = 4;


    public static final int RESULT_USER_NAME = 1;// 修改用户名之后
    public static final int RESULT_SCHOOL = 2;// 修改学校名之后
    public static final int RESULT_MOBILE = 3;// 修改手机号之后
    public static final int RESULT_EMAIL = 4;// 修改邮箱之后
    private EditText ed_new_message;
    private EditText ed_old_message;
    private Button btn_submit;
    private TextView tv_change_title;
    private TextView tv_old_message;
    private TextView tv_new_message;
    private int type;
    private int intentResult;
    private ImageView bImageView;
    private LinearLayout ll_verify;
    private Button btn_verify;
    private MyCountDownTimer myCountDownTimer;
    private Student newStudent;
    private EditText ed_verify;
    private String strNewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_message);
        initView();

//        Bmob.initialize(this,"706b623303ad79f7265aa54f740c3299");

        findViewById(R.id.backup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        //左上角返回
//        bImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        //点击发送验证码
        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCountDownTimer = new MyCountDownTimer(60000, 1000);
                myCountDownTimer.start();
            }
        });
//
//        Intent intent = getIntent();
//        type = intent.getIntExtra("changeType", 0);
//        switch (type) {
//            case CHANGE_TYPE_STU_NAME:
//                //更改用户名
//                ed_old_message.setText(BmobUser.getCurrentUser(Student.class).getStuName());
//                ed_new_message.setHint("新用户名");
//                tv_change_title.setText("更改用户名");
//                tv_old_message.setText("我现在的名字是");
//                tv_new_message.setText("修改后的名字是");
//                break;
//            case CHANGE_TYPE_SCHOOL:
//                //更改学校
//                ed_old_message.setText(BmobUser.getCurrentUser(Student.class).getSchool());
//                ed_new_message.setHint("新学校名");
//                tv_change_title.setText("更改学校");
//                tv_old_message.setText("我现在的学校是");
//                tv_new_message.setText("修改后的学校是");
//                break;
//            case CHANGE_TYPE_MOBILE:
//                //更改手机号
//                ed_old_message.setText(BmobUser.getCurrentUser(Student.class).getMobilePhoneNumber());
//                ed_new_message.setHint("新手机号");
//                tv_change_title.setText("更改手机号");
//                tv_old_message.setText("我现在的手机号是");
//                tv_new_message.setText("修改后的手机号是");
//                ll_verify.setVisibility(View.VISIBLE);
//                break;
//            case CHANGE_TYPE_EMAIL:
//                //更改邮箱
//                ed_old_message.setText(BmobUser.getCurrentUser(Student.class).getEmail());
//                ed_new_message.setHint("新邮箱");
//                tv_change_title.setText("更改邮箱");
//                tv_old_message.setText("我现在的邮箱是");
//                tv_new_message.setText("修改后的邮箱是");
//                break;
//
//            default:
//                break;
//        }

        //提交
//        btn_submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                strNewMessage = ed_new_message.getText().toString();
//                newStudent = new Student();
//                switch (type) {
//                    case CHANGE_TYPE_STU_NAME:
//                        if (strNewMessage.isEmpty() || strNewMessage.length() > 10) {
//                            ed_new_message.setError("请输入小于10位用户名！！！");
//                            return;
//                        }
//                        newStudent.setStuName(strNewMessage);
//                        intentResult = RESULT_USER_NAME;
//                        break;
//                    case CHANGE_TYPE_SCHOOL:
//                        if (strNewMessage.isEmpty()) {
//                            ed_new_message.setError("学校名不能为空！！！");
//                            return;
//                        }
//                        newStudent.setSchool(strNewMessage);
//                        intentResult = RESULT_SCHOOL;
//                        break;
//                    case CHANGE_TYPE_MOBILE:
//                        String num = "[1][34578]\\d{9}";
//                        if (strNewMessage.isEmpty() || !strNewMessage.matches(num)) {
//                            ed_new_message.setError("请输入一个有效的手机号！！！");
//                            return;
//                        }
//                        //验证码不能为空
//                        if (ll_verify.getVisibility() != View.GONE  &&  ed_verify.getText().toString().isEmpty()) {
//                            ed_verify.setError("请输入验证码");
//                            return;
//                        }
//                        newStudent.setMobilePhoneNumber(strNewMessage);
//                        newStudent.setUsername(strNewMessage);
//                        intentResult = RESULT_MOBILE;
//                        break;
//                    case CHANGE_TYPE_EMAIL:
//                        if (strNewMessage.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(strNewMessage).matches()) {
//                            ed_new_message.setError("请输入一个有效的邮箱！！！");
//                            return;
//                        }
//                        newStudent.setEmail(strNewMessage);
//                        intentResult = RESULT_EMAIL;
//                        break;
//                    default:
//                        break;
//                }
//
//                if (type != CHANGE_TYPE_MOBILE) {
//                    stuUpdate();
//                } else {
//                    //验证码验证
//                    BmobSMS.verifySmsCode(ed_new_message.getText().toString(), ed_verify.getText().toString(), new UpdateListener() {
//                        @Override
//                        public void done(BmobException e) {
//                            if (e == null) {
//                                //短信验证码已验证成功
//                                stuUpdate();
//                            } else {
//                                Log.i("htht", "验证失败：code =" + e.getErrorCode() + ",msg = " + e.getLocalizedMessage() + "线程名      " + Thread.currentThread().getName());
//                                String errorMessage;
//                                switch (e.getErrorCode()) {
//                                    case 207:
//                                        errorMessage = "验证码错误，请重新输入 :)";
//                                        break;
//                                    default:
//                                        errorMessage = "出错了！！！";
//                                        break;
//
//                                }
//                                Toast.makeText(getBaseContext(), errorMessage, Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }
//
//            }
//        });
    }
//
    private void stuUpdate(){
        newStudent.update(BmobUser.getCurrentUser(Student.class).getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i("htht", "intentResult: " + intentResult);
                    setResult(intentResult);
                    finish();
                    Toast.makeText(getApplicationContext(), "更新用户信息成功！！！", Toast.LENGTH_SHORT).show();
                    Log.i("htht", "更新用户信息成功！！ " + BmobUser.getCurrentUser(Student.class).getStuName());
                } else {
                    String errorMessage;
                    switch (e.getErrorCode()) {
                        case 202:
                        case 209:
                            errorMessage = "该手机号码已经存在！！！";
                            break;
                        case 9016:
                            errorMessage = "没有网，上传服务器失败！！！";
                            break;
                        default:
                            errorMessage = "发生了一个不为人知的错误！！！";
                            break;

                    }
                    Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    Log.i("htht", "更新用户信息失败！！ " + e.getErrorCode());
                }
            }
        });

    }

    private void initView() {
        ed_old_message = findViewById(R.id.ed_old_message);
        ed_new_message = findViewById(R.id.ed_new_message);
        btn_submit = findViewById(R.id.btn_submit);
        tv_change_title = findViewById(R.id.tv_change_title);
        tv_old_message = findViewById(R.id.tv_old_message);
        tv_new_message = findViewById(R.id.tv_new_message);

        //发送验证码
        ll_verify = findViewById(R.id.ll_verify);
        ed_verify = findViewById(R.id.ed_verify);
        btn_verify = findViewById(R.id.btn_verify);

        //左上角返回图标

    }

//
//    //验证码倒计时类
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }


        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            btn_verify.setClickable(false);
            btn_verify.setAlpha(0.8f);
            btn_verify.setText(l / 1000 + "s");

        }


        //计时完毕的方法
        @Override
        public void onFinish() {
            btn_verify.setAlpha(1.0f);
            //重新给Button设置文字
            btn_verify.setText("重新获取验证码");
            //设置可点击
            btn_verify.setClickable(true);
        }
    }

}
