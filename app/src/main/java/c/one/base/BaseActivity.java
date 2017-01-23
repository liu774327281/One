package c.one.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import c.one.ljinterface.LJPermissionListener;
import c.one.util.LJImmerStateUtil;
import c.one.util.LJActivityCollector;

/**
 * Created by 123 on 2017/1/19.
 */

public abstract class BaseActivity extends AppCompatActivity{

    private static LJPermissionListener mListener;

    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";

    @Override
    public View onCreateView (String name, Context context, AttributeSet attrs){
        View view = null;
        if(name.equals(LAYOUT_FRAMELAYOUT)){
            view = new AutoFrameLayout(context, attrs);
        }
        if(name.equals(LAYOUT_LINEARLAYOUT)){
            view = new AutoLinearLayout(context, attrs);
        }
        if(name.equals(LAYOUT_RELATIVELAYOUT)){
            view = new AutoRelativeLayout(context, attrs);
        }
        if(view != null)
            return view;
        return super.onCreateView(name, context, attrs);
    }

    /**
     * 设置布局文件
     *
     * @return 布局文件ID
     */
    public abstract int setLayoutResID();


    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        LJActivityCollector.addActivity(this);
        setContentView(setLayoutResID());//设置布局文件
        LJImmerStateUtil.SetImmerState(this, setImmerStateColor());//设置沉浸
        initData();
        addLogic(savedInstanceState);
    }

    @Override
    public void setContentView (@LayoutRes int layoutResID){
        super.setContentView(layoutResID);
        initViews();
        addListeners();
    }

    /**
     * 设置状态栏颜色 默认返回0则表示不设置颜色
     * @return
     */
    private int setImmerStateColor() {
        return getImmerStateColor();
    }

    /**
     * 设定状态栏颜色
     * @return
     */
    protected abstract int getImmerStateColor();

    /**
     * 初始化View
     */
    public abstract void initViews ();

    /**
     * 初始化监听
     */
    public abstract void addListeners ();

    /**
     * 初始化数据
     */
    public abstract void initData ();

    /**
     * 初始化逻辑
     */
    public abstract void addLogic (Bundle savedInstanceState);

    /**
     * 页面跳转
     *
     * @param clz
     */
    public void startActivity (Class<?> clz){
        startActivity(new Intent(BaseActivity.this, clz));
    }


    /**
     * 动态申请权限的方法
     */
    public static void requestRuntimePermission (String[] permissions, LJPermissionListener listener){
        Activity topActivity = LJActivityCollector.getTopActivity();
        if(topActivity == null){
            return;
        }
        mListener = listener;
        List<String> permissionList = new ArrayList<>();
        for(String permission : permissions){
            if(ContextCompat.checkSelfPermission(topActivity, permission) != PackageManager.PERMISSION_GRANTED){
                permissionList.add(permission);
            }
        }
        if(!permissionList.isEmpty()){
            ActivityCompat.requestPermissions(topActivity, permissionList.toArray(new String[permissionList.size()]), 1);
        }else{
            mListener.onGranted();
        }
    }
    /**
     * 动态申请权限的回调方法
     */
    @Override
    public void onRequestPermissionsResult (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case 1:
                if(grantResults.length > 0){
                    List<String> deniedPermissions = new ArrayList<>();
                    for(int i = 0; i < grantResults.length; i++){
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if(grantResult != PackageManager.PERMISSION_GRANTED){
                            deniedPermissions.add(permission);
                        }
                    }
                    if(deniedPermissions.isEmpty()){
                        mListener.onGranted();
                    }else{
                        mListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy (){
        super.onDestroy();
        LJActivityCollector.removeActivity(this);
    }
}
