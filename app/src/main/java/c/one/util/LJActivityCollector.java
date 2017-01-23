package c.one.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 2017/1/9.
 */
//添加或者移除activity的类 有获取栈顶的方法
public class LJActivityCollector{

    private static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static Activity getTopActivity() {
        if (activityList.isEmpty()) {
            return null;
        } else {
            return activityList.get(activityList.size() - 1);
        }
    }

}
