package c.one.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * 状态烂设置
 * Created by Mark on 2016/9/5.
 */
//沉浸式的工具类
public class LJImmerStateUtil{

    /** 设置状态栏颜色
     *  @param activity  需要设置的activity
     *  @param color
     *  状态栏颜色值 */
    public static void SetImmerState(Activity activity, int color) {
        SetImmerState(activity);//不管是否需要颜色 都设置透明
        if (0 == color) {//不设置颜色标识默认是图片之类的
            return;
        }
        setStateBarColor(activity, activity.getResources().getColor(color));
    }

    /**  使状态栏透明
     *   适用于图片作为背景的界面,此时需要图片填充到状态栏
     *   @param activity
     *  需要设置的activity */
    public static void SetImmerState(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
            // activity.getWindow().setStatusBarColor(Color.TRANSPARENT);  //直接用这个方法会有兼容性问题
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);//calculateStatusColor(Color.WHITE, (int) alphaValue)
        }
    }


    /**
     * 获得状态栏高度
     */
    private static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 设置颜色
     * @param activity
     */
    public static void setStateBarColor(Activity activity, int color) {
        // 设置状态栏颜色
        ViewGroup contentLayout = (ViewGroup) activity.findViewById(android.R.id.content);
        setupStatusBarView(activity, contentLayout, color);
        // 设置Activity layout的fitsSystemWindows
        View contentChild = contentLayout.getChildAt(0);
        contentChild.setFitsSystemWindows(true);
    }


    /**
     * 加入矩形
     * @param activity
     * @param contentLayout
     * @param color
     */
    private static void setupStatusBarView(Activity activity, ViewGroup contentLayout, int color) {
        View mStatusBarView = null;
        View statusBarView = new View(activity);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        contentLayout.addView(statusBarView, lp);
        mStatusBarView = statusBarView;
        mStatusBarView.setBackgroundColor(color);
    }

}
