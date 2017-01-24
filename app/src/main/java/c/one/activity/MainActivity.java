package c.one.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import c.one.R;
import c.one.adapter.FragmentAdapter;
import c.one.base.BaseActivity;
import c.one.fragment.HomeFragment;
import c.one.fragment.TwoFragment;
import c.one.util.LJLogUtils;
import cn.sharesdk.tencent.qq.QQ;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private QQ qq;
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private NavigationView navigationView;
    private CircleImageView userIcon;
    private TextView userName;
    private DrawerLayout drawerLayout;

    @Override
    public int setLayoutResID(){
        return R.layout.activity_main;
    }

    @Override
    protected int getImmerStateColor(){
        return R.color.bule;
    }

    @Override
    public void initViews(){
        qq = new QQ(this);
        List<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new TwoFragment());
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), list);
        viewPager = (ViewPager) findViewById(R.id.act_main_viewpager);
        viewPager.setAdapter(adapter);
        radioGroup = (RadioGroup) findViewById(R.id.act_main_radiogroup);
        drawerLayout = (DrawerLayout) findViewById(R.id.act_main_drwaerLayout);
        navigationView = (NavigationView) findViewById(R.id.act_main_navigationView);
        navigationView.setItemIconTintList(null);//设置menu图片为原本的颜色
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
            }

            @Override
            public void onPageSelected(int position){
                radioGroup.getChildAt(position).setClickable(true);
            }

            @Override
            public void onPageScrollStateChanged(int state){
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId){
                switch(checkId){
                    case R.id.one:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.two:
                        viewPager.setCurrentItem(1);
                        break;
                }
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                switch(item.getItemId()){
                    case R.id.favorite:
                        Toast.makeText(MainActivity.this, "点击了收藏", Toast.LENGTH_SHORT).show();
                        break;
                }
                //在这里处理item的点击事件
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        userIcon = (CircleImageView) header.findViewById(R.id.act_main_header_iv_userIcon);
        userName = (TextView) header.findViewById(R.id.act_main_header_tv_userName);
    }

    @Override
    public void addListeners(){
    }

    @Override
    public void initData(){
        if(qq.isAuthValid()){
            Intent intent = getIntent();
            String qq_userName = intent.getStringExtra("qq_userName");
            String qq_userIconUrl = intent.getStringExtra("qq_userIconUrl");
            LJLogUtils.d("mainactivity", qq_userName);
            LJLogUtils.d("mainactivity", qq_userIconUrl);
            Glide.with(MainActivity.this).load(qq_userIconUrl).into(userIcon);
            userName.setText(qq_userName);
        }
    }

    @Override
    public void addLogic(Bundle savedInstanceState){
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        //判读drawLayout是否是展开状态，如果是，则关闭，不是返回建把应用挂起
        if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
            drawerLayout.closeDrawer(Gravity.LEFT);
        }else{
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
