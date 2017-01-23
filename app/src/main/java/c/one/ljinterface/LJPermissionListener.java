package c.one.ljinterface;

import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */
//权限接口
public interface LJPermissionListener{

    void onGranted ();

    void onDenied (List<String> deniedPermission);
}
