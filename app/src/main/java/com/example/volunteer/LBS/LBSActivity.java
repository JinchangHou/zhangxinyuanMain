package com.example.volunteer.LBS;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.volunteer.R;
import com.example.volunteer.stack.ScreenManager;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class LBSActivity extends AppCompatActivity {

    public LocationClient mLocationClient;
    private TextView positionText;
    private MapView mapView;
    private BaiduMap baiduMap;
    private boolean isFirstLocate=true;//防止多次调用animateMapStatus方法

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenManager.getScreenManager().pushActivity(this);
        mLocationClient=new LocationClient(getApplicationContext());//构建位置服务器对象
        mLocationClient.registerLocationListener(new MyLocationListener());//当获取到位置信息时就会调用该监听器
        Log.d("getApplication",getApplicationContext().toString());
        SDKInitializer.initialize(getApplicationContext());//为了让地图显示出来，，自然要初始化，而且必须在setContentView之前
        setContentView(R.layout.activity_lbs);
        mapView= (MapView) findViewById(R.id.bmapView);
        positionText= (TextView) findViewById(R.id.positon_text_view);
        List<String> permissionList=new ArrayList<>();//权限容器
        //如果需要申请就添加到容器当中
        if(ContextCompat.checkSelfPermission(LBSActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(LBSActivity.this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(LBSActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            //容器非空，，就转化成数组，，，，传递非requestPermissions
            String[] permissions=permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(LBSActivity.this, permissions, 1);
        }else{
            //容器为空，则不需要在申请权限，，，直接调用requestLocation方法
            requestLocation();
        }
        //在onCreate方法最后，，还要给baiduMap初始化，，，，设置我的位置
        baiduMap=mapView.getMap();//给baiduMap 初始化
        baiduMap.setMyLocationEnabled(true);
        findViewById(R.id.backup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.arriver_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShow();

            }
        });
    }

    //把地图移动到当前位置
    private void navigateTo(BDLocation location){
        if(isFirstLocate){
            //LatLng类存储经纬度
            LatLng ll=new LatLng(location.getLatitude(),location.getLongitude());
            //update 是MapStatusUpdate 类的对象，用来更新地图
            MapStatusUpdate update= MapStatusUpdateFactory.newLatLng(ll);//更新经纬度
            baiduMap.animateMapStatus(update);
            //设置缩放倍数,
            update=MapStatusUpdateFactory.zoomTo(16f);//更新缩放倍数
            baiduMap.animateMapStatus(update);//把更新对象传入baiduMap.animateMapStatus()方法
            isFirstLocate=false;

        }
        //先创建一个locationData,,再将这个值传递给BaiduMap对象，，，，采取builder方法
        MyLocationData.Builder locationBuilder=new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData=locationBuilder.build();
        baiduMap.setMyLocationData(locationData);//给baiduMap对象设置locationData
    }
    private void requestLocation(){
        initLocation();//mLocationClient初始化
        mLocationClient.start();//
    }
    private void initLocation(){//初始化mLocationClient
        LocationClientOption option=new LocationClientOption();
        option.setScanSpan(5000);
        //设置为GPS定位模式
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        mLocationClient.setLocOption(option);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManager.getScreenManager().popActivity(this);
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);//程序退出是关闭我的位置
    }

    @Override//申请权限的回调方法onRequestPermissionResult
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch(requestCode){
            case 1:
                if (grantResults.length > 0){
                    //遍历所有权限
                    for(int result:grantResults){
                        //如果说权限申请失败
                        if(result!=PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(LBSActivity.this, "你必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();//不要忘
                            return;
                        }
                    }
                    requestLocation();//权限申请成功，，，，
                } else{
                    Toast.makeText(LBSActivity.this,"发生未知错误",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:

        }
    }

    public class MyLocationListener implements BDLocationListener {//自定义一个监听器。。。。监听位置的变化

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {//如果定位方式为百度的gps定位或者网络定位
            if(bdLocation.getLocType()==BDLocation.TypeGpsLocation||bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                navigateTo(bdLocation);//调用navigateTo方法，，，并且传入location对象
            }
//            StringBuilder currentPosition=new StringBuilder();
//            currentPosition.append("纬度：").append(bdLocation.getLatitude()).append("\n");
//            currentPosition.append("经度：").append(bdLocation.getLongitude()).append("\n");
//            currentPosition.append("国家：").append(bdLocation.getCountry()).append("\n");
//            currentPosition.append("省：").append(bdLocation.getProvince()).append("\n");
//            currentPosition.append("城市：").append(bdLocation.getCity()).append("\n");
//            currentPosition.append("区：").append(bdLocation.getDistrict()).append("\n");
//            currentPosition.append("街道：").append(bdLocation.getStreet()).append("\n");
//            currentPosition.append("定位方式：");
//            if(bdLocation.getLocType()==BDLocation.TypeGpsLocation){
//                currentPosition.append("GPS");
//
//            }else if(bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
//                currentPosition.append("网络");
//            }
//            positionText.setText(currentPosition);

        }
    }
    public void dialogShow() {

        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);
        String title="";
        String text="";

        /**
         * 设置dialog样式
         */
        dialogBuilder
                .withTitle("请输入六位验证码")                                  //窗口标题
                .withTitleColor("#FFFFFF")                                  //窗口字体颜色
                .withDividerColor("#11000000")                              //线条颜色

                .withMessageColor("#FFFFFFFF")                              //里面内容的字体颜色
                .withDialogColor("#81a663")                               //窗口颜色
                .withIcon(getResources().getDrawable(R.drawable.dituudaka))   //窗口的icon(直接用withIcon(R.drawable.ic_launcher会出现异常))
                .isCancelableOnTouchOutside(true)                           //是否可点击窗口外边取消窗口
                .withDuration(500)                                          //动画速度
                .withEffect(Effectstype.Flipv)                                         //窗口类型
                .withButton1Text("签到")                                      //两个button按钮
                //.withButton2Text("取消")
                .setCustomView(R.layout.dialog_lbs, getApplicationContext())         //窗口内容下面的layout
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//
                        findViewById(R.id.arriver_bt).setBackground(getResources().getDrawable(R.mipmap.restaurant_btbg_yellow));
                        TextView textView=findViewById(R.id.arriver_timetv);
                        textView.setText("已签到");
                        Toast.makeText(LBSActivity.this,"签到成功",Toast.LENGTH_SHORT).show();
                        dialogBuilder.dismiss();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "取消", Toast.LENGTH_SHORT).show();
                        dialogBuilder.dismiss();
                    }
                })
                .show();

    }

}
