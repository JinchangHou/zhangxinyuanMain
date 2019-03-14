package com.example.volunteer.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.volunteer.R;
import com.example.volunteer.Utils.UIUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by asus on 2018/11/6.
 */

public class GridMenu extends LinearLayout {
    private CircleImageView iv_menu;
    private TextView tv_menu;

    public GridMenu(Context context) {
        super(context);
        init(context);
    }



    public GridMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public GridMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }
    private void init(Context context) {
        setOrientation(VERTICAL);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
        setGravity(Gravity.CENTER);
        setPadding(UIUtils.dip2px(5),UIUtils.dip2px(5),UIUtils.dip2px(5),UIUtils.dip2px(5));
//        Log.d("alan","初始化");
        View menu = LayoutInflater.from(context).inflate(R.layout.menu1, this, true);
        iv_menu = (CircleImageView) menu.findViewById(R.id.iv_menu);
        tv_menu = (TextView) menu.findViewById(R.id.tv_menu);
    }
    public void setAttr(int imgRes, String txt){
//        Picasso.with(UIUtils.getContext()).load(imgRes).into(iv_menu);
        iv_menu.setImageResource(imgRes);
        tv_menu.setText(txt);
    }
}
