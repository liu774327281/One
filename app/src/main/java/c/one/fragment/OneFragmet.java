package c.one.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import c.one.R;
import c.one.bean.App;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by liujie on 2017/1/24.
 */

public class OneFragmet extends Fragment{

    private final List<String> list = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();
    private BGABanner banner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.frg_one, null);
        banner = (BGABanner) view.findViewById(R.id.banner_splash_pager);
        OkHttpUtils.get().url("http://gank.io/api/random/data/%E7%A6%8F%E5%88%A9/9").build().execute(new StringCallback(){

            @Override
            public void onError(okhttp3.Call call, Exception e, int id){
            }

            @Override
            public void onResponse(String response, int id){
                Gson gson = new Gson();
                App info = gson.fromJson(response, new TypeToken<App>(){
                }.getType());
                List<App.ResultsBean> listimage = info.getResults();
                for(int i = 0; i < listimage.size(); i++){
                    list.add(listimage.get(i).getUrl());
                    strings.add(listimage.get(i).getWho());

                }
                banner.setAdapter(new BGABanner.Adapter<ImageView, String>(){
                    @Override
                    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position){
                        Glide.with(getActivity()).load(model).centerCrop().dontAnimate().into(itemView);
                    }
                });
                banner.setData(list,strings );
                banner.setAutoPlayAble(true);
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
}
