package com.example.volunteer.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.volunteer.R;
import com.example.volunteer.Utils.UIUtils;


/**
 * Created by asus on 2018/11/5.
 */

public class ButtomBtn extends LinearLayout {
    private ImageView mall_iv;
    private TextView mall_tv;

    public ButtomBtn(Context context) {
        super(context);
        init(context);
    }
    public ButtomBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ButtomBtn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context) {
        setOrientation(VERTICAL);
        LayoutParams layoutParams =
                new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
        setGravity(Gravity.CENTER);
        setPadding(UIUtils.dip2px(5), UIUtils.dip2px(5),UIUtils.dip2px(5),UIUtils.dip2px(5));

        View bottomBtnView = LayoutInflater.from(context).inflate(R.layout.bottom_btn_view, this, true);
        mall_iv = (ImageView) bottomBtnView.findViewById(R.id.mall_iv);
        mall_tv = (TextView) bottomBtnView.findViewById(R.id.mall_tv);
    }
    public void setIvAndTv(int imgRes,String tvString){
        mall_iv.setImageResource(imgRes);
        mall_tv.setText(tvString);
    }
    public void setTvColor(int color){
        mall_tv.setTextColor(color);
    }
}
