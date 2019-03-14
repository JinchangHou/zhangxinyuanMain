package com.example.volunteer.ui;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.WindowManager;


public class IdeaViewPager extends ViewPager {

    private Point point;
    //private ImageView mDetailimage;

    public IdeaViewPager(Context context) {
        this(context,null);
        //init(context);
    }

    public IdeaViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(point.x,point.x);
    }
   /* private void init(Context context) {
        View bottomBtnView = LayoutInflater.from(context).inflate(R.layout.activity_main, this, true);
        mDetailimage = (ImageView)bottomBtnView.findViewById(R.id.detail_image);
    }

    public void setIvAndTv(int imgRes){
        mDetailimage.setImageResource(imgRes);
    }*/
}
