package c.one.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by liujie on 2017/1/24.
 */

public class TablayoutFragmentAdapter extends FragmentPagerAdapter{
    private Context context;
    private List<Fragment> list;
    private List<String> titles;

    public TablayoutFragmentAdapter(FragmentManager fm, Context context, List<Fragment> list, List<String> titles){
        super(fm);
        this.context = context;
        this.list = list;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position){
        return list.get(position);
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return titles.get(position);
    }
}
