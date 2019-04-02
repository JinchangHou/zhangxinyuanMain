package com.example.volunteer.stack;

import android.app.Activity;
import android.util.Log;

import java.util.Stack;
  /*具体使用：
Java代码  收藏代码
@Override
   protected void onCreate(Bundle savedInstanceState)
   {

       super.onCreate(savedInstanceState);

       ActivityManager am =  (ActivityManager)getSystemService("");
       am.
       ScreenManager.getScreenManager().pushActivity(this);
   }

   @Override
   protected void onDestroy()
   {
       super.onDestroy();
       ScreenManager.getScreenManager().popActivity(this);
   }


   /**
    * <退出系统>
    * <绑定退出按钮>
    * @param v
    * @see [类、类#方法、类#成员]
    */
  /*
public void exitSystem(View v)
        {
        ScreenManager.getScreenManager().popAllActivityExceptMain(getClass());

        finish();
        }  */
public class ScreenManager  
{  
    private static final String TAG = "ScreenManager";  
    private static Stack<Activity> activityStack;
      
    private static ScreenManager instance;  
      
    /** 
     * <单例方法> 
     * <功能详细描述> 
     * @return 该对象的实例 
     * @see [类、类#方法、类#成员] 
     */  
    public static ScreenManager getScreenManager()
    {  
        if (instance == null)  
        {  
            instance = new ScreenManager();  
        }  
        return instance;  
    }  
      
    /** 
     * <获取当前栈顶Activity> 
     * <功能详细描述> 
     * @return 
     * @see [类、类#方法、类#成员] 
     */  
    public Activity currentActivity()  
    {  
        if (activityStack == null || activityStack.size() == 0)  
        {  
            return null;  
        }  
        Activity activity = activityStack.lastElement();  
          
        Log.e(TAG, "get current activity:" + activity.getClass().getSimpleName());
        return activity;  
    }  
      
    /** 
     * <将Activity入栈> 
     * <功能详细描述> 
     * @param activity 
     * @see [类、类#方法、类#成员] 
     */  
    public void pushActivity(Activity activity)  
    {  
        if (activityStack == null)  
        {  
            activityStack = new Stack<Activity>();  
        }  
        Log.e(TAG, "push stack activity:" + activity.getClass().getSimpleName());  
        activityStack.add(activity);  
    }  
      
    /** 
     * <退出栈顶Activity> 
     * <功能详细描述> 
     * @param activity 
     * @see [类、类#方法、类#成员] 
     */  
    public void popActivity(Activity activity)  
    {  
        if (activity != null)  
        {  
            activity.finish();  
            Log.e(TAG, "remove current activity:" + activity.getClass().getSimpleName());  
            activityStack.remove(activity);  
            activity = null;  
        }  
    }  
      
    /** 
     * <退出栈中所有Activity,当前的activity除外> 
     * <功能详细描述> 
     * @param cls 
     * @see [类、类#方法、类#成员] 
     */  
    public void popAllActivityExceptMain(Class cls)  
    {  
        while(true)  
        {  
            Activity activity = currentActivity();  
            if (activity == null)  
            {  
                break;  
            }  
            if (activity.getClass().equals(cls))  
            {  
                break;  
            }  
              
            popActivity(activity);  
        }  
    }  
}  