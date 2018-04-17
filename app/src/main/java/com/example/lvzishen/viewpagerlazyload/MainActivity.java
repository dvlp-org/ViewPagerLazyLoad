package com.example.lvzishen.viewpagerlazyload;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    List<Fragment> fragmentList = new ArrayList<>();

    private LinearLayout layoutParents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        for (int i = 0; i <10 ; i++) {
            fragmentList.add(BankFinanceFragment.newInstance(""+i,""));
        }

        layoutParents= (LinearLayout) findViewById(R.id.layout_parents);

        layoutParents.removeAllViews();
        for (int i = 0; i <10 ; i++) {
            initView(layoutParents,i);
        }

//        viewPager.setOffscreenPageLimit(0);  //fragmentList.size()-1
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), (ArrayList<Fragment>) fragmentList);
        viewPager.setAdapter(adapter);
//        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.e("todo","当前页面"+position);

            }

            @Override
            public void onPageSelected(int position) {
                selectDayNum=position;
                initViewCSS();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initViewCSS(){
        for (int i = 0; i <10 ; i++) {
            LinearLayout layout= (LinearLayout) layoutParents.getChildAt(i);
            if(selectDayNum==i){
                ((ImageView) layout.getChildAt(0)).setImageResource(R.mipmap.icon);
                ((TextView) layout.getChildAt(1)).setTextColor(Color.parseColor("#c7a975"));
            }else {
                ((TextView) layout.getChildAt(1)).setTextColor(Color.parseColor("#666666"));
                ((ImageView) layout.getChildAt(0)).setImageResource(R.mipmap.icon2);
                LinearLayout.LayoutParams btn_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                btn_params.setMargins(0,3,0,0);
                ((ImageView) layout.getChildAt(0)).setLayoutParams(btn_params);            }
        }
    }
    private int selectDayNum=0;
    private void initView(LinearLayout layout, final int index){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f);
        LinearLayout lineLayout = new LinearLayout(this);
        lineLayout.setOrientation(LinearLayout.VERTICAL);
        lineLayout.setLayoutParams(params);
        lineLayout.setWeightSum(1.0f);
        lineLayout.setGravity(Gravity.CENTER );

        ImageView img1=new ImageView(this);
        img1.setImageResource(R.mipmap.icon);
        LinearLayout.LayoutParams tv_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        img1.setLayoutParams(tv_params);

        TextView tv2=new TextView(this);
        tv2.setText("30天");
        tv2.setTop(5);
        LinearLayout.LayoutParams btn_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        btn_params.setMargins(0,10,0,0);
        tv2.setLayoutParams(btn_params);
        lineLayout.addView(img1);
        lineLayout.addView(tv2);
        layout.addView(lineLayout);

        lineLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(index);
            }
        });
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> list;

        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;

        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }


    }
}


