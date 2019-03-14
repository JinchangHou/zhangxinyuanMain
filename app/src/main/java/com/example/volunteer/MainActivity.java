package com.example.volunteer;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.volunteer.Activity.MallActivity;
import com.example.volunteer.Home_Page.Fruit;
import com.example.volunteer.Home_Page.FruitAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;

    private Fruit[] fruits={new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item)};
    private List<Fruit> fruitList=new ArrayList<>();
    private FruitAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private void initFruits(){
        fruitList.clear();
        for(int i=0;i<50;i++){
            Random random=new Random();
            int index=random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }
    //刷新本地操作
    private void refresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(200);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();//通知adapter数据发生变化
                        swipeRefreshLayout.setRefreshing(false);//刷新结束，隐藏进度条
                    }
                });
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ToolBar代替ActionBar
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//将ToolBar实例传入

        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);//显示导航按钮
            actionBar.setHomeAsUpIndicator(R.drawable.menu);// 设置导航图标
        }

        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView= (NavigationView) findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_call);//设置默认选中的图标
        //设置点击事件setNavigationItemSelectedListener
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //mDrawerLayout.closeDrawers();
                //Log.e("ssssss","jjjjjjj");
                switch(item.getItemId()){
                    case R.id.nav_call://返回键
                        Log.e("nav_call","jjjjjjj");
                        Intent intent = new Intent(MainActivity.this, MallActivity.class);
                        startActivity(intent);
                        //Toast.makeText(this,"kaikkkk",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_friends:
                        Log.e("nav_friends","jjjjjjj");
                        break;
                    case R.id.nav_location://设置键
                        Log.e("nav_location","jjjjjjj");
                        break;
                    case R.id.nav_mail://滑动菜单显示键
                        Log.e("nav_mail","jjjjjjj");
                        break;
                    case R.id.nav_task://滑动菜单显示键
                        Log.e("nav_task","jjjjjjj");
                        break;
                    default:
                        break;

                }
                return true;

            }
        });
        FloatingActionButton floatButton= (FloatingActionButton) findViewById(R.id.fab);
        //设置点击事件
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "data deleted？", Snackbar.LENGTH_SHORT)
                        //设置undo按钮
                        .setAction("undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Data Restored", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
        initFruits();
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager =new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new FruitAdapter(this,fruitList);
        recyclerView.setAdapter(adapter);


        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        //进度条颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        //设置下拉刷新监听器
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }
    //加载toolbar文件


    //把toolbar中的item导入，，，插入到toolBar中，R.menu.toolbar中间都是menuRes资源
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    //处理menu按钮点击事件
    @Override                                  //传入item，，，确定哪个item
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.backup://返回键
                Toast.makeText(this,"backup",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete://删除键
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting://设置键
                Toast.makeText(this,"setting",Toast.LENGTH_SHORT).show();
                break;
            case android .R.id.home://滑动菜单显示键
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;

        }
        return true;
    }
}
