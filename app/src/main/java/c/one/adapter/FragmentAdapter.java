package c.one.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by liujie on 2017/1/23.
 */

public class FragmentAdapter extends FragmentPagerAdapter{
private List<Fragment> list;

    public FragmentAdapter(FragmentManager fm, List<Fragment> list){
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position){
        return list.get(position);
    }

    @Override
    public int getCount(){
        return list.size();
    }
}
