package com.example.volunteer.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.example.volunteer.R;




public class SatelliteMenu extends ViewGroup {
    private int pm_width;       //定义屏幕的宽度
    private int pm_height;      //定义屏幕的高度

    //定义菜单半径
    private int mRadius;
    //默认为关闭状态
    private Status mCurrentStatus = Status.CLOSE;
    //触发菜单的按钮
    private View mButton;
    //子菜单单击事件
    private OnSatelliteMenuItemClickListener mMenuItemClickListener;
    //枚举类 菜单状态
    public enum Status
    {
        OPEN, CLOSE
    }
    public void setSatelliteMenuOnClickListener(OnSatelliteMenuItemClickListener listener){
        mMenuItemClickListener=listener;
    }
    /**
     * 单击子菜单的回调接口
     */
    public interface OnSatelliteMenuItemClickListener
    {
        void onClick(View view, int pos);
    }

    //构造方法
    public SatelliteMenu(Context context) {
        this(context,null);
    }

    public SatelliteMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SatelliteMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //        获取屏幕宽高
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        pm_width = wm.getDefaultDisplay().getWidth();
        pm_height = wm.getDefaultDisplay().getHeight();

        // 获取自定义属性的值
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.ArcMenu, defStyleAttr, 0);
        //获取按钮半径属性值
        mRadius = (int) a.getDimension(R.styleable.ArcMenu_radius, TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
                        getResources().getDisplayMetrics()));
        Log.i("tag",mRadius+"");

        a.recycle();        //回收资源
    }
    /**
     *测量自定义控件中所有子控件的尺寸
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int count = getChildCount();        //获取所有的按钮
        for (int i = 0; i < count; i++)    //便利所有的按钮
        {
            // 测量所有按钮，宽度与高度
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     *设置按钮的位置
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {                                    //如果按钮布局发生改变
            buttonLayout();                               //调用主按钮布局位置的方法
            childLayout();

            }
        }

    private void childLayout() {
        int count = getChildCount();                  //获取所有的子按钮
        for (int i = 0; i < count - 1; i++) {         //便利所有按钮
            View child = getChildAt(i + 1);            //获取子控件下标
            child.setVisibility(View.GONE);//隐藏子按钮


            //获取子按钮的宽高尺寸
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            //==================中=================
            //           中心坐标（pm_width/2-childWidth/2，getMeasuredHeight() - height）
            int c_x = pm_width / 2 - childWidth / 2;
            int c_y = getMeasuredHeight() - childHeight/2;

            //子按钮与父容器左边距
            int childLeft = (int) (mRadius * Math.sin(Math.PI / (count - 2)
                    * i));
            int childTop = (int) (mRadius * Math.sin(Math.PI / (count - 2)
                    * i));
            childTop = getMeasuredHeight() - childHeight - childTop;
            if (i == 0) {
                childLeft = c_x - mRadius;
                childTop = getMeasuredHeight() - childHeight;
            }
//            else if (i == 1) {
//                childLeft = c_x  - childLeft;
//            }
            else if (i == 1) {
                childLeft = c_x;
                childTop=getMeasuredHeight() - childHeight-mRadius;
            }
//            else if (i == 3) {
//                childLeft = c_x  + childLeft;
//
//            }
            else if (i == 2) {
                childLeft = c_x + mRadius;
                childTop = getMeasuredHeight() - childHeight;
            }
            //设置子按钮的位置
            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
        }
    }


    /**
     *收缩子按钮动画
     */
    public void shrinkMenu(int time) {
        int count = getChildCount();                    //获取所有的子按钮
        for (int i = 0; i < count - 1; i++)             //便利所有按钮
        {
            final View childView = getChildAt(i + 1);   //获取子按钮下标
            childView.setVisibility(View.VISIBLE);      //显示子按钮
            //==================中=================
                //动画开始与结束的位置x,y
             int clx = (int) (mRadius * Math.sin(Math.PI / (count - 2) * i));
             int cty = (int) (mRadius * Math.cos(Math.PI  / (count - 2) * i));
                if (i == 0) {       //第一个子按钮
                    clx = mRadius;
                    cty = 0;
                }
//                else if (i == 1) {    //第二个子按钮
//
//                }
                else if (i == 1) {    //第三个子按钮
                    clx = 0;
                    cty = mRadius;
                }
//                else if (i == 3) {    //第四个子按钮
//                    clx=(int) (mRadius * Math.cos(Math.PI  / (count - 2) * i));
//                    cty=-cty;
//                }
                else if (i == 2) {    //第五个子按钮
                    clx = - mRadius;
                    cty = 0;
                }

            //设置动画集合
            AnimationSet animset = new AnimationSet(true);
            Animation tranAnim = null;

            //如果当前按钮菜单为关闭状态
            if (mCurrentStatus == Status.CLOSE)
            {   //设置打开按钮菜单动画
                tranAnim = new TranslateAnimation(clx,0 , cty, 0);
                childView.setClickable(true);       //子按钮可以单击
                childView.setFocusable(true);       //子按钮可调焦
            } else//如果当前按钮菜单为开启状态
            {   //设置关闭按钮菜单动画
                tranAnim = new TranslateAnimation(0, clx, 0, cty);
                childView.setClickable(false);      //子按钮不可点击
                childView.setFocusable(false);      //子按钮不可调焦
            }
            tranAnim.setDuration(time);             //  动画显示的时间
            tranAnim.setFillAfter(true);            //停止最后一帧的位置
            tranAnim.setStartOffset((i * 100) / count);
            //设置动画监听器
            tranAnim.setAnimationListener(new Animation.AnimationListener()
            {

                @Override
                public void onAnimationStart(Animation animation)
                {

                }

                @Override
                public void onAnimationRepeat(Animation animation)
                {

                }

                /**
                 *动画结束后隐藏子按钮
                 */
                @Override
                public void onAnimationEnd(Animation animation)
                {
                    if (mCurrentStatus == Status.CLOSE)
                    {
                        childView.setVisibility(View.GONE);
                    }
                }
            });
            // 旋转动画
            RotateAnimation rotateAnim = new RotateAnimation(0, 720,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnim.setDuration(time);       //设置旋转的时间
            rotateAnim.setFillAfter(true);      //停止最后一帧的位置
            animset.addAnimation(rotateAnim);   //添加旋转动画
            animset.addAnimation(tranAnim);     //添加平移动画
            childView.startAnimation(animset);  //启动动画
            final int pos = i + 1;
            //子按钮单击事件
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMenuItemClickListener != null)
                        mMenuItemClickListener.onClick(childView, pos);
                    childButtonClickAnim(pos - 1);      //调用字按钮单击动画方法
                    menuStatus();                       //调用菜单状态方法
                }
            });
        }
        // 切换菜单状态
        menuStatus();

    }

    /**
     * 子按钮单击动画的方法
     */
    private void childButtonClickAnim(int pos) {
        for (int i = 0; i < getChildCount() - 1; i++)
        {

            View childView = getChildAt(i + 1);
            if (i == pos)       //当前单击的子按钮
            {   //启动子按钮变大并消失动画
                childView.startAnimation(scaleBigAnim(200));
            } else
            {
                //启动其它没有被单击的按钮变小并消失动画
                childView.startAnimation(scaleSmallAnim(200));
            }

            childView.setClickable(true);  //子按钮不可点击
            childView.setFocusable(false);  //子按钮不可调焦

        }
    }

    /**
     *没有被单击的子按钮，变小并消失的动画方法
     */
    private Animation scaleSmallAnim(int time)
    {   //设置动画集合
        AnimationSet animationSet = new AnimationSet(true);
        //缩放动画
        ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        //透明度动画
        AlphaAnimation alphaAnim = new AlphaAnimation(1f, 0.0f);
        animationSet.addAnimation(scaleAnim);   //添加缩放动画
        animationSet.addAnimation(alphaAnim);   //添加透明度动画
        animationSet.setDuration(time);         //设置动画时间
        animationSet.setFillAfter(true);        //停止最后一帧的位置
        return animationSet;

    }

    /**
     * 单击子按钮，当前子按钮变大并消失动画方法
     */
    private Animation scaleBigAnim(int time)
    {   //设置动画集合
        AnimationSet animationSet = new AnimationSet(true);
        //缩放动画
        ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 4.0f, 1.0f, 4.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        //透明度动画
        AlphaAnimation alphaAnim = new AlphaAnimation(1f, 0.0f);
        animationSet.addAnimation(scaleAnim);   //添加缩放动画
        animationSet.addAnimation(alphaAnim);   //添加透明度动画
        animationSet.setDuration(time);         //设置动画时间
        animationSet.setFillAfter(true);        //停止最后一帧的位置
        return animationSet;

    }

    /**
     * 切换菜单状态
     */
    private void menuStatus()
    {
        mCurrentStatus = (mCurrentStatus == Status.CLOSE ? Status.OPEN
                : Status.CLOSE);
    }
    /**
     *设置主按钮旋转动画
     */
    private void rotatemButton(View v, float start, float end, int time) {
        //中心旋转动画
        RotateAnimation anim = new RotateAnimation(start, end,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        anim.setDuration(time);         //设置旋转的时间
        anim.setFillAfter(true);        //停止最后一帧的位置
        v.startAnimation(anim);         //开始动画
    }

    /**
     * 设置主按钮在布局中的位置
     */
    private void buttonLayout() {
        mButton = getChildAt(0);       //获取菜单主按钮
        //设置按钮的单击事件
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"zhili",Toast.LENGTH_SHORT).show();
                rotatemButton(v, 0f, 360f, 500);    //调用主按钮旋转的方法
                shrinkMenu(500);                    //调用收缩子按钮动画方法
            }
        });
        //初始化按钮左边距与上边距
        int l = 0;
        int t = 0;
        //获取主按钮的宽度与高度
        int width = mButton.getMeasuredWidth();
        int height = mButton.getMeasuredHeight();
        //判断主按钮位置
            //==================中=================
                l = pm_width/2-width/2;
                t = getMeasuredHeight() - height;
        mButton.layout(l, t, l + width, t + width);  //设置主按钮在父容器的位置
    }
}
