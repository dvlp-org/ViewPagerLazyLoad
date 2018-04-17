package com.example.lvzishen.viewpagerlazyload;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by liubaba on 2017/9/1.
 * ViewPager懒加载基类
 */
public abstract class LzBasePagerFragment extends Fragment {

    protected AppCompatActivity mActivity;
    protected boolean isViewInitiated; //控件是否初始化完成
    protected boolean isVisibleToUser; //页面是否可见
    protected boolean isDataInitiated; //数据是否加载


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {   //page 1等会此读数据  生命周期
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData(false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {   //page 0进入再此读数据
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData(false);
    }

    public abstract void loadData();


    /**
     * 准备加载数据
     * @param forceUpdate
     */
    protected void prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            loadData();
            isDataInitiated = false;//设置是否每次可见更新数据 false:每次都跟新，true:只有第一次更新
        }
    }


}
