package com.example.volunteer.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.volunteer.R;


/**
 * Created by asus on 2018/11/6.
 */

public class GoodsTrendSection extends LinearLayout {
    private String title;
    private String desc;
    private Drawable drawable;
    private TextView tv_title;
    private TextView tv_desc;
    private ImageView ivGoodsTrend;

    public GoodsTrendSection(Context context) {
        this(context, null);

    }

    public GoodsTrendSection(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoodsTrendSection(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GoodsTrendSection);

//        mPointsIsVisible = a.getBoolean(R.styleable.FlyBanner_points_visibility, true);
//        mPointPosition = a.getInt(R.styleable.FlyBanner_points_position, CENTER);
//        mPointContainerBackgroundDrawable
//                = a.getDrawable(R.styleable.FlyBanner_points_container_background);
        title = a.getString(R.styleable.GoodsTrendSection_Title);

        desc = a.getString(R.styleable.GoodsTrendSection_desc);
        drawable = a.getDrawable(R.styleable.GoodsTrendSection_imgRes);

        a.recycle();
        View goodsSectionView = LayoutInflater.from(context).inflate(R.layout.goods_section,this);
        tv_title = (TextView) goodsSectionView.findViewById(R.id.tv_title);
        tv_desc = (TextView) goodsSectionView.findViewById(R.id.tv_desc);
        ivGoodsTrend = (ImageView) goodsSectionView.findViewById(R.id.ivGoodsTrend);
        setAttrs();
    }

    private void setAttrs() {
//        Log.d("alan","设置属性");
        tv_title.setText(title);
        tv_desc.setText(desc);
        ivGoodsTrend.setImageDrawable(drawable);
    }
}
