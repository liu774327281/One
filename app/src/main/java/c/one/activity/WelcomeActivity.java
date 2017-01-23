package c.one.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;

import c.one.R;
import c.one.base.BaseActivity;

/**
 * Created by 123 on 2017/1/20.
 */

public class WelcomeActivity extends BaseActivity{
    private boolean flag = true;//标识是否第一次登入

    @Override
    public int setLayoutResID (){
        return R.layout.activity_welcome;
    }

    @Override
    protected int getImmerStateColor (){
        return 0;
    }

    @Override
    public void initViews (){
        new Thread(new Runnable(){
            @Override
            public void run (){
                SystemClock.sleep(3000);
                SharedPreferences preferences = getSharedPreferences("one", MODE_PRIVATE);
                boolean isFirstLogin = preferences.getBoolean("isFirstLogin", true);
                if(isFirstLogin){
                    startActivity(GuideActivity.class);
                }else{
                    startActivity(LoginActivity.class);
                }
                WelcomeActivity.this.finish();
            }
        }).start();
    }

    @Override
    public void addListeners (){
    }

    @Override
    public void initData (){
    }

    @Override
    public void addLogic (Bundle savedInstanceState){
    }
}
