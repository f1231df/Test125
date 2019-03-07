package com.example.test125.app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;

import com.example.test125.R;


public class WelcomeActivity extends Activity {

    private static  String TAG=WelcomeActivity.class.getCanonicalName(); //"WelcomeActivity"

    Handler handler=new Handler();
    @Override
    protected  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //两秒后才执行到这里
                //执行在主进程中
                startMainActivity();
                Log.e(TAG,"当前线程名称=="+Thread.currentThread().getName());
            }
        },2000);
    }

    private  boolean isStartMain=false;
    /*
     * 跳转到主界面，并把当前界面关闭掉*/
    private void startMainActivity() {
        if(!isStartMain){
            isStartMain=true;
            Intent  intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            //关闭当前界面
            finish();
        }
    }
    @Override
    public  boolean onTouchEvent(MotionEvent event){
        Log.e(TAG,"onTouchEvent==Action"+event.getAction());
        startMainActivity();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy(){
        //把所有的消息和回调移除
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();

    }

}
