package com.example.volunteer.Main_ui;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 29208 on 2019/4/3.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<View> viewList;
    private List<String> titleList;

    public ViewPagerAdapter(List<View>viewList,List<String> titleList){
        this.viewList=viewList;
        this.titleList=titleList;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titleList.get(position);
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
