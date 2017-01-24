package c.one.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

import c.one.R;
import c.one.base.BaseActivity;
import c.one.util.LJLogUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by 123 on 2017/1/20.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private static final int CODE_ING = 1;   //已发送，倒计时
    private static final int CODE_REPEAT = 2;  //重新发送
    private static final int SMSDDK_HANDLER = 3;  //短信回调
    private int TIME = 60;//倒计时60s
    private EditText phone;
    private EditText code;
    private Button getcode;
    private Button login;
    private String s;//电话号码
    private ImageView login_qq;
   private QQ qq;
    private String qq_userName;//qq用户名
    private String qq_userIconUrl;//qq头像的url
    private ImageView login_winxin;

    @Override
    public int setLayoutResID (){
        return R.layout.activity_login;
    }

    @Override
    protected int getImmerStateColor (){
        return 0;
    }

    @Override
    public void initViews (){
        ShareSDK.initSDK(this, "1afe7db509d58");
         qq=new QQ(this);
        phone = (EditText) findViewById(R.id.act_login_edt_phone);
        code = (EditText) findViewById(R.id.act_login_edt_identifyingCode);
        getcode = (Button) findViewById(R.id.act_login_btn_get);
        login = (Button) findViewById(R.id.act_login_btn_login);
        login_qq = (ImageView) findViewById(R.id.act_login_qq);
        login_winxin = (ImageView) findViewById(R.id.act_login_weixin);
        SMSSDK.initSDK(this, "1af4d0af4db99", "cb7bdc4337901efe72d681663f00de3f");
        EventHandler eh = new EventHandler(){
            @Override
            public void afterEvent (int event, int result, Object data){
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                msg.what = SMSDDK_HANDLER;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh);
    }

    @Override
    public void addListeners (){
        getcode.setOnClickListener(this);
        login.setOnClickListener(this);
        login_qq.setOnClickListener(this);
        login_winxin.setOnClickListener(this);
    }

    @Override
    public void initData (){
    }

    @Override
    public void addLogic (Bundle savedInstanceState){
    }

    @Override
    public void onClick (View view){
        switch(view.getId()){
            case R.id.act_login_btn_get:
                getcode();
                break;
            case R.id.act_login_btn_login:
                login();
                break;
            case R.id.act_login_qq:
                loginQQ();
                break;
            case R.id.act_login_weixin:
                startActivity(MainActivity.class);
                break;
        }
    }

    private void loginQQ (){
        //判断是否登录，没有登录则登录
        if(!qq.isAuthValid()){
            qq.authorize();
        }
        qq.setPlatformActionListener(new PlatformActionListener(){
            @Override
            public void onComplete (Platform platform, int i, HashMap<String, Object> hashMap){
                qq_userName = qq.getDb().getUserName();
                qq_userIconUrl = qq.getDb().getUserIcon();
                LJLogUtils.d(qq_userName);
                LJLogUtils.d(qq_userIconUrl);

            }

            @Override
            public void onError (Platform platform, int i, Throwable throwable){
            }

            @Override
            public void onCancel (Platform platform, int i){
            }
        });
    }

    //获取验证码的方法
    public void getcode (){
        s = phone.getText().toString();
        if(!TextUtils.isEmpty(s)){
            SMSSDK.getVerificationCode("86", s);
            getcode.setClickable(false);
            new Thread(new Runnable(){
                @Override
                public void run (){
                    for(int i = 60; i > 0; i--){
                        handler.sendEmptyMessage(CODE_ING);
                        if(i <= 0){
                            break;
                        }
                        try{
                            Thread.sleep(1000);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    handler.sendEmptyMessage(CODE_REPEAT);
                }
            }).start();
        }else{
            Toast.makeText(this, "电话号码有误", Toast.LENGTH_SHORT).show();
        }
    }

    public void login (){
        String ss = code.getText().toString();
        if(!TextUtils.isEmpty(ss)){
            SMSSDK.submitVerificationCode("86", s, ss);
        }else{
            Toast.makeText(this, "验证码不正确，请重新输入", Toast.LENGTH_SHORT).show();
        }
    }

    //验证码的回调判断
    Handler handler = new Handler(){
        @Override
        public void handleMessage (Message msg){
            switch(msg.what){
                case CODE_ING://已发送,倒计时
                    getcode.setBackgroundResource(R.drawable.btn_color2);
                    getcode.setText("重新发送(" + --TIME + "s)");
                    break;
                case CODE_REPEAT://重新发送
                    getcode.setBackgroundResource(R.drawable.btn_radius_gray);
                    getcode.setText("重新获取验证码");
                    getcode.setClickable(true);
                    TIME = 60;
                    break;
                case SMSDDK_HANDLER:
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    if(result == SMSSDK.RESULT_COMPLETE){
                        // 短信注册成功后，返回MainActivity,然后提示新好友
                        if(event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){// 提交验证码成功
                            Toast.makeText(LoginActivity.this, "成功登录", Toast.LENGTH_SHORT).show();
                            startActivity(MainActivity.class);
                        }else if(event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                            Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                        }else if(event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){// 返回支持发送验证码的国家列表
                            Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                        }
                        if(event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                            if(result == SMSSDK.RESULT_COMPLETE){
                                boolean smart = (Boolean) data;
                                if(!smart){
                                    //通过智能验证
                                }else{
                                    //依然走短信验证
                                }
                            }
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "验证码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
            }
        }
    };
}
