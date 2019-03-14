package com.example.volunteer.Activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.volunteer.R;
import com.example.volunteer.ui.SatelliteMenu;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;



public class PersonActivity extends AppCompatActivity {

    private RelativeLayout zhiliLayout;
    private RelativeLayout tiliLayout;
    private RelativeLayout artLayout;
    private TextView countZhili;
    private TextView countTili;
    private TextView countArt;
    private TextView loveLeft;
    private ImageView imageView;
    private AnimationDrawable animationDrawable;
    SatelliteMenu menu ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_person);


        imageView = (ImageView) findViewById(R.id.max);//实例化控件对象
        Log.d("boyboy", "sdfsa");
        animationDrawable = (AnimationDrawable) imageView.getDrawable();



        zhiliLayout= (RelativeLayout) findViewById(R.id.zhili);
        zhiliLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShow(zhiliLayout);
            }
        });
        tiliLayout= (RelativeLayout) findViewById(R.id.tili);
        tiliLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShow(tiliLayout);
            }
        });
        artLayout= (RelativeLayout) findViewById(R.id.art);
        artLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShow(artLayout);
            }
        });
        countZhili= (TextView) findViewById(R.id.count_zhili);
        loveLeft= (TextView) findViewById(R.id.love_left);
        countTili= (TextView) findViewById(R.id.count_tiyu);
        countArt= (TextView) findViewById(R.id.count_art);
        countArt.setText("100");
        countTili.setText("100");
        countZhili.setText("23");
        loveLeft.setText("34");
        menu= (SatelliteMenu) findViewById(R.id.satelliteMenu);

        menu.setSatelliteMenuOnClickListener(new SatelliteMenu.OnSatelliteMenuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                loveLeft.setText(Integer.valueOf(loveLeft.getText().toString()).intValue()-1+"");
                if(pos==1){

                    countZhili.setText(Integer.valueOf(countZhili.getText().toString()).intValue()+1+"");
                    Toast.makeText(PersonActivity.this, "智力+1", Toast.LENGTH_SHORT).show();
                }
                else if(pos==2){
                    countTili.setText(Integer.valueOf(countTili.getText().toString()).intValue()+1+"");
                    Toast.makeText(PersonActivity.this, "体力+1", Toast.LENGTH_SHORT).show();
                }
                else if(pos==3){
                    countArt.setText(Integer.valueOf(countArt.getText().toString()).intValue()+1+"");
                    Toast.makeText(PersonActivity.this,"见识+1",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        animationDrawable.start();//开启帧动画
    }

    @Override
    protected void onPause() {
        super.onPause();
        animationDrawable.stop();//关闭帧动画
    }


    public void dialogShow(View v) {

        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);
        String title="";
        String text="";
        switch (v.getId()) {
            //根据每个窗口的类型展现不同的dialog 效果
            case R.id.zhili:
                title="智力";
                text="孩子的大脑至关重要，智力每积累到100，我们会随机向山区儿童捐助一本读物"+"\n"+"\n"+"\n"+"爱心币与智力一对一兑换";
                break;
            case R.id.tili:
                title="体力";
                text="身体是孩子走出大山的本钱，体力每积累到100，我们会随机向山区儿童捐助体育用品"+"\n"+"\n"+"\n"+"爱心币与体力一对一兑换";
                break;
            case R.id.art:
                title="见识";
                text="山区孩子也有学习琴棋书画的可能呦，见识每积累到一百，我们会随机向山区儿童情怀" +"\n"+"\n"+"\n"+"爱心币与见识一对一兑换";
                break;
        }
        /**
         * 设置dialog样式
         */
        dialogBuilder
                .withTitle(title)                                  //窗口标题
                .withTitleColor("#FFFFFF")                                  //窗口字体颜色
                .withDividerColor("#11000000")                              //线条颜色
                .withMessage(text)                     //内容
                .withMessageColor("#FFFFFFFF")                              //里面内容的字体颜色
                .withDialogColor("#A5CD4E")                               //窗口颜色
                .withIcon(getResources().getDrawable(R.drawable.ic_dialog))   //窗口的icon(直接用withIcon(R.drawable.ic_launcher会出现异常))
                .isCancelableOnTouchOutside(true)                           //是否可点击窗口外边取消窗口
                .withDuration(500)                                          //动画速度
                .withEffect(Effectstype.Shake)                                         //窗口类型
                .withButton1Text("了解")                                      //两个button按钮
                        //.withButton2Text("取消")
                .setCustomView(R.layout.dialog_view, v.getContext())         //窗口内容下面的layout
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "已了解", Toast.LENGTH_SHORT).show();
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
