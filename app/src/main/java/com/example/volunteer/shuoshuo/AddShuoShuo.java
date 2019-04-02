package com.example.volunteer.shuoshuo;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.volunteer.R;
import com.example.volunteer.stack.ScreenManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

public class AddShuoShuo extends AppCompatActivity {
    private Button takephoto;
    private ImageView picture;
    //两个常量用于onActivityResult回调方法
    public static  final int TAKE_PHOTO=1;
    public static final int CHOOSE_PHOTO=2;
    //创建图片的uri对象
    private Uri ImageUri;
//    private Uri destinationUri;
    private String ImagePath;
    File outputImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ScreenManager.getScreenManager().pushActivity(this);
        setContentView(R.layout.activity_add_shuo_shuo);
        picture= (ImageView) findViewById(R.id.picture);
        takephoto= (Button) findViewById(R.id.take_photo);
        Log.d("AddShuoShuo",""+(takephoto==null));
        Log.d("AddShuoShuo",""+(picture==null));
        takephoto.setOnClickListener(new View.OnClickListener() {
        //点击调用相机
            @Override
            public void onClick(View v) {
                Log.d("AddShuoShuo", "execuate");
                //创建file对象,用于存储拍照后的照片
                outputImage=new File(getExternalCacheDir(), "output_image.jpg");
                ImagePath="output_image.jpg";
                try {
                //                      关联缓存目录
                    if (outputImage.exists()) {
                    //看看文件是否已经存在，，，如果存在就删除
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                    //把旧的删除了，，创建一个新的
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //下面是根据文件对象生成一个uri对象
                if (Build.VERSION.SDK_INT >= 24) {
                //判断安卓版本、sdk24以上安全性提高   封装uri  使用内容提供器（必须在AndroidManifest中注册）
                    ImageUri = FileProvider.getUriForFile(AddShuoShuo.this,
                            "com.example.volunteer.shuoshuo.AddShuoShuo.fileprovider", outputImage);
                }
                //      获取文件uri的方法，，，File.getUriForFile();，，传入context，authority，file对象
                else {
                    //sdk24以下uri尚未封装，，，直接用Uri.fromFile(file对象)获取 uri
                    ImageUri = Uri.fromFile(outputImage);
                }

                Log.d("lujing","ImageUri"+ImageUri.toString());
                Log.d("lujing","组合的"+getExternalCacheDir());
                //启动相机                     启动相机的action，android.media.action.IMAGE_CAPTURE
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                //EXTRA_OUTPUT是键，ImageUri是值，传给回调方法
                intent.putExtra(MediaStore.EXTRA_OUTPUT, ImageUri);
                //startActivityResult与startActivity的区别就是有回调方法
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });

        //这个调用相册图片是个重头戏
        findViewById(R.id.chose_from_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //由于WRITE_EXTERNAL_STORAGE是危险权限 要获得需要用户同意//先判断用户是否已经给了权限，如果没有，，，就请求一下
                if (ContextCompat.checkSelfPermission(AddShuoShuo.this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager
                        /*用于检索与应用程序相关的各种信息的类*/.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions/*调用回调方法*/(AddShuoShuo.this,
                            new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                } else {                  //这个String里面放的都是各种权限      后面的1是id，辨识符
                    openAlbum();
                }
            }
        });

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Log.d("last",outputImage.getPath()+"    name=="+outputImage.getName());
                    outputImage.getName();
                    final BmobFile bmobFile=new BmobFile(outputImage);
                    Log.d("file","bmobFile==null?"+(bmobFile==null));
                    bmobFile.upload(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                        Log.d("AddShuoShuogetURL", "e==null?" + (e == null));
                            if (e == null) {
                                Log.d("AddShuoShuogetURL", "" + bmobFile.getUrl());
                                TextView text= (TextView) findViewById(R.id.item_content);
                                String s=text.getText().toString();
                                upload(bmobFile.getUrl(),s);
                            }
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    private void openAlbum(){
    //选择图片，解析函数接力intent
        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");//means:选择图片
        startActivityForResult(intent, CHOOSE_PHOTO);//此处会把intent传递给onActivityResult中的data
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch(requestCode){//requestCode 就是Activity.compat()中的最后一个参数
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();//如果用户同意了，就打开album
                }
                else{
                    Toast.makeText(AddShuoShuo.this,"you denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }


    @Override
    //对于takephoto就是设置图片
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
        //startActivityResult的第二个参数
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK){
                    try{
                    //解析uri生成BitMap对象，，，，，BItMap对象可以直接设置图片
                        Log.d("AddShuoShuogetURL",ImageUri.toString());
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(ImageUri));
                        picture.setImageBitmap(bitmap);

                    }catch(FileNotFoundException e){
                        e.printStackTrace();
                    }


                }
                break;


            case CHOOSE_PHOTO:
                if(resultCode==RESULT_OK){
                    //判断手机型号
                    if(Build.VERSION.SDK_INT>=19){
                        //4.4以及以上系统使用这个方法处理图片，需要解析
                        handleImageOnKitKat(data);
                        //把这个intent传入handleImageOnKitKat中进行解析得到图片的uri对象
                    }
                    else{
                        //4.4以下系统使用这个方法
                        handleImageBeforeKitKat(data);
                    }
                }
            default:break;

        }
    }
    //如何解析封装后的Uri
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath=null;
        Uri uri=data.getData();

        if(DocumentsContract.isDocumentUri(this,uri)){
        //document类型就是封装之后的uri
            //如果是document类型的uri,,,则通过document id进行处理
            String docuId=DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                //解析出数字格式的id
                String id=docuId.split(":")[1];
                String selection =MediaStore.Images.Media._ID+"="+id;
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }
            else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri= ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docuId));
                imagePath=getImagePath(contentUri,null);
            }
        }
        else if("content".equalsIgnoreCase(uri.getScheme())){
            //如果是content类型的Uri，，，，使用普通方式处理
            imagePath=getImagePath(uri,null);
        }
        else if("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是文件类型的uri，，，直接就能够获得path，调用Uri.getPath()
            imagePath=uri.getPath();

        }
        ImagePath=imagePath;
        String filename=ImagePath.substring(ImagePath.lastIndexOf("/")+1);
        String cache=ImagePath.substring(0,ImagePath.lastIndexOf("/"));
       Log.d("substring",cache);
        Log.d("substring",filename);
        outputImage=new File(cache,filename);
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data){
        //前提是安卓4.4以下，，，才能获得真正的uri 否则获得的就是封装后的uri 需要进一步转化之后才能得到绝对路径
        //Intent.getData(),返回一个uri对象
        Uri uri=data.getData();
        String imagePath=getImagePath(uri, null);
        displayImage(imagePath);
    }
    private String getImagePath(Uri uri,String selection){
        String path=null;
        //通过uri和selection来获得真实的路径
        Cursor cursor=getContentResolver().query(uri, null, selection, null, null);
        if(cursor!=null){
            if(cursor.moveToNext()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        ImagePath=path;
        return path;
    }
    private void displayImage(String imagePath){
    //根据路径生成Bitmap对象并且设置为图片显示
        if(imagePath != null) {
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
            //有了图片真是路径调用BitmaoFactory.decodeFile(String)即可获得Bitmap对象
//            Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(ImageUri));
// 如果是uri就要调用getContentResolver.openInputStream(Uri)先获得一个stream对象，，再使用decodeStream解析来获得Bitmap对象
            picture.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManager.getScreenManager().popActivity(this);
    }

    private void upload(String url, String text){
        Content content=new Content();
        content.setImageUrl(url.toString());
        content.setTitle(text);
        content.save();
        Intent intent=new Intent(AddShuoShuo.this,SHuoSHuoActivity.class);
        startActivity(intent);
    }
}
