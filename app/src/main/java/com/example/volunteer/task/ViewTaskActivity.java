package com.example.volunteer.task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.example.volunteer.Javabean.MyTask;
import com.example.volunteer.Javabean.Task;
import com.example.volunteer.Javabean.User;
import com.example.volunteer.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class ViewTaskActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyTaskAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        final User user = new User();
        user.setUsername("" + System.currentTimeMillis());
        user.setPassword("" + System.currentTimeMillis());
        user.setMobilePhoneNumber("18326188191");
        Task task=new Task();
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    Toast.makeText(ViewTaskActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ViewTaskActivity.this,"注册失败",Toast.LENGTH_LONG).show();
                }
            }
        });
        recyclerView=findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        requestData();
    }


    private void requestData(){
        final BmobQuery<MyTask> query=new BmobQuery<MyTask>();
        query.order("-createdAt");
//        query.setLimit(5);
        query.findObjects(new FindListener<MyTask>() {
            @Override
            public void done(List<MyTask> list, BmobException e) {

//                Toast.makeText(ViewTaskActivity.this,"taskContentAdapter查询成功",Toast.LENGTH_LONG).show();
                if(e==null){
                    Toast.makeText(ViewTaskActivity.this,"taskContentAdapter查询成功",Toast.LENGTH_LONG).show();
                    adapter=new MyTaskAdapter(ViewTaskActivity.this,list);
                    recyclerView.setAdapter(adapter);
                }
                else{

                }
            }
        });
    }

}
