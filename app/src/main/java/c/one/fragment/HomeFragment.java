package c.one.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import c.one.R;
import c.one.adapter.TablayoutFragmentAdapter;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by liujie on 2017/1/23.
 */

public class HomeFragment extends Fragment{

    private TabLayout frg_home_tablayout;
    private ViewPager frg_home_viewpager;
    private List<String> list;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.frg_home, null);
        frg_home_tablayout = (TabLayout) view.findViewById(R.id.frg_home_tablayout);
        frg_home_viewpager = (ViewPager) view.findViewById(R.id.frg_home_viewpager);
        initData();
        return view;
    }

    private void initData(){
        list = new ArrayList<>();
        list.add("首页");
        list.add("地图");
        List<Fragment> frgList=new ArrayList<>();
       frgList.add(new OneFragmet());
        frgList.add(new MapFragment());
        TablayoutFragmentAdapter tablayoutFragmentAdapter=new TablayoutFragmentAdapter(getChildFragmentManager(),getContext(),frgList,list);
        frg_home_viewpager.setAdapter(tablayoutFragmentAdapter);
        frg_home_tablayout.setupWithViewPager(frg_home_viewpager);

    }
}
