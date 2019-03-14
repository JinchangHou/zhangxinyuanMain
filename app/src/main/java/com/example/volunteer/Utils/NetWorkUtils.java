package com.example.volunteer.Utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by asus on 2018/10/27.
 */

public class NetWorkUtils {
    public static String LoginPost(String use_name, String use_paw){
        String path = "http://192.168.123.102:8080/login?username="+use_name+"&password="+use_paw;
        String result = "";
        try {
            //创建URL对象
            URL url = new URL(path);
            //获取HttpURLConnection连接
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            httpURLConnection.setRequestMethod("GET");
            //设置超时时间
            httpURLConnection.setConnectTimeout(5000);
            //设置服务器返回的状态码
            int code = httpURLConnection.getResponseCode();
            if (code == 200){
                //获取数据，以流的形式形式返回
                InputStream inputStream = httpURLConnection.getInputStream();
                //缓存数据
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int len = -1;
                byte[] buffer = new byte[1024];
                while ((len = inputStream.read(buffer))!=-1){
                    bos.write(buffer,0,len);
                }
                //关闭流
                bos.close();
                inputStream.close();
                result = new String(bos.toByteArray());
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
