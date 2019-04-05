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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.volunteer.Activity.MallActivity;
import com.example.volunteer.Activity.PersonActivity;
import com.example.volunteer.Home_Page.Fruit;
import com.example.volunteer.Home_Page.FruitAdapter;
import com.example.volunteer.LBS.LBSActivity;
import com.example.volunteer.Main_ui.GlideImageLoader;
import com.example.volunteer.Main_ui.SettingsActivity;
import com.example.volunteer.Main_ui.WebView1Activity;
import com.example.volunteer.personal_page.PersonPage;
import com.example.volunteer.shuoshuo.SHuoSHuoActivity;
import com.example.volunteer.stack.ScreenManager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.bmob.v3.Bmob;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity  {
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;

    private Fruit[] fruits={new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item),new Fruit("1111",R.drawable.item)};
    private List<Fruit> fruitList=new ArrayList<>();
    private FruitAdapter adapter;



    //mainUI

    private SwipeRefreshLayout swipeRefreshLayout;
    private void initFruits(){
        fruitList.clear();
        for(int i=0;i<10;i++){
            Random random=new Random();
            int index=random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }


    private CircleImageView touxiang;
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
        ScreenManager.getScreenManager().pushActivity(this);
        setContentView(R.layout.activity_main);
        List<String>images=new ArrayList<String>();
        images.add("https://cdn.dribbble.com/users/52758/screenshots/6274884/boheme_logos_jay_fletcher_dribbble.jpg");
        images.add("https://cdn.dribbble.com/users/52084/screenshots/6271548/driibb.jpg");
        images.add("https://cdn.dribbble.com/users/4664/screenshots/6276419/northstandard_waves.jpg");
        images.add("https://cdn.dribbble.com/users/66340/screenshots/6271717/tubularui_2x.jpg");
        List<String> list_title=new ArrayList<>();
        list_title.add("好好学习");
        list_title.add("天天向上");
        list_title.add("热爱劳动");
        list_title.add("不搞对象");
        Banner banner = (Banner) findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent =new Intent(MainActivity.this, WebView1Activity.class);
                startActivity(intent);
            }
        });
        //ToolBar代替ActionBar
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//将ToolBar实例传入
        Bmob.initialize(this, "706b623303ad79f7265aa54f740c3299");

        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);//显示导航按钮
            actionBar.setHomeAsUpIndicator(R.drawable.more);// 设置导航图标
        }

        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);

        final NavigationView navView= (NavigationView) findViewById(R.id.nav_view);
        final View headView=navView.inflateHeaderView(R.layout.nav_header);
        touxiang=headView.findViewById(R.id.icon_image);
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(headView.getContext(),"sdafa",Toast.LENGTH_LONG).show();
                Intent intentTopage=new Intent(MainActivity.this, PersonPage.class);
                startActivity(intentTopage);
            }
        });
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
                        Intent intent23 = new Intent(MainActivity.this, MallActivity.class);
                        startActivity(intent23);
                        //Toast.makeText(this,"kaikkkk",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_friends:
                        Log.e("nav_friends","jjjjjjj");
                        Intent intent3 =new Intent(MainActivity.this, SHuoSHuoActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.nav_location://设置键
                        Log.e("nav_location","jjjjjjj");
                        Intent intent=new Intent(MainActivity.this,LBSActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_mail://滑动菜单显示键
                        Log.e("nav_mail","jjjjjjj");
                        break;
                    case R.id.nav_task://滑动菜单显示键
                        Log.e("nav_task","jjjjjjj");

                        break;
                    case R.id.nav_boy://卡通男孩区
                        Log.e("nav_boy","jjjjjjj");
                        Intent intent2 =new Intent(MainActivity.this, PersonActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_setting:
                        Intent intentc=new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(intentc);
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
        LinearLayoutManager layoutManager3=new LinearLayoutManager(this);
        layoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        GridLayoutManager layoutManager =new GridLayoutManager(this,1);
        StaggeredGridLayoutManager layoutManager1=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager3);
        adapter=new FruitAdapter(this,fruitList);
        recyclerView.setAdapter(adapter);
        Glide.with(this).load("https://goss.veer.com/creative/vcg/veer/1600water/veer-143458687.jpg").into((ImageView) findViewById(R.id.main_ui_zhaomu));
        Glide.with(this).load("https://goss3.veer.com/creative/vcg/veer/612/veer-141760204.jpg").into((ImageView) findViewById(R.id.main_ui_canjia));

//        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
//        //进度条颜色
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
//        //设置下拉刷新监听器
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                refresh();
//            }
//        });
    }
    //加载toolbar文件


    //把toolbar中的item导入，，，插入到toolBar中，R.menu.toolbar中间都是menuRes资源
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManager.getScreenManager().popActivity(this);
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
