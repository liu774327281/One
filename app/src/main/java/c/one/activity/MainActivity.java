package c.one.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import c.one.R;
import c.one.base.BaseActivity;
import c.one.ljinterface.LJPermissionListener;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Button phone;

    @Override
    public int setLayoutResID (){
        return R.layout.activity_main;
    }

    @Override
    protected int getImmerStateColor (){
        return 0;
    }

    @Override
    public void initViews (){
        phone = (Button) findViewById(R.id.phone);
    }

    @Override
    public void addListeners (){
        phone.setOnClickListener(this);
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
            case R.id.phone:
                requestRuntimePermission(new String[]{Manifest.permission.CALL_PHONE}, new LJPermissionListener(){
                    @Override
                    public void onGranted (){
                        phone();
                    }

                    @Override
                    public void onDenied (List<String> deniedPermission){
                    }
                });
                break;
        }
    }

    private void phone (){
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        startActivity(intent);
    }
}
