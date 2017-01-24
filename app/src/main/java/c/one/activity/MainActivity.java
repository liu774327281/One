package c.one.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import c.one.R;
import c.one.adapter.FragmentAdapter;
import c.one.base.BaseActivity;
import c.one.fragment.HomeFragment;
import c.one.fragment.TwoFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private ViewPager viewPager;
    private RadioGroup radioGroup;

    @Override
    public int setLayoutResID(){
        return R.layout.activity_main;
    }

    @Override
    protected int getImmerStateColor(){
        return R.color.white;
    }

    @Override
    public void initViews(){
        List<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new TwoFragment());
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), list);
        viewPager = (ViewPager) findViewById(R.id.act_main_viewpager);
        viewPager.setAdapter(adapter);
        radioGroup = (RadioGroup) findViewById(R.id.act_main_radiogroup);
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
    }

    @Override
    public void addListeners(){
    }

    @Override
    public void initData(){
    }

    @Override
    public void addLogic(Bundle savedInstanceState){
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
        }
    }
}
