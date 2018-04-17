package com.example.lvzishen.viewpagerlazyload;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lvzishen.viewpagerlazyload.view.WaveHelper;
import com.example.lvzishen.viewpagerlazyload.view.WaveView;


/**
 * Created by liubaba on 2017/9/1.
 */
public class BankFinanceFragment extends LzBasePagerFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private  String TAG="";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView imageview;
    WaveView waveView;
    private TextView tvLeft;
    private TextView tvRight1;
    private TextView tvRight2;

    //默认颜色
    private int mBorderColor = Color.parseColor("#f6efe1");
    private int mBorderWidth = 13;

    private WaveHelper mWaveHelper;



    public BankFinanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DemoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BankFinanceFragment newInstance(String param1, String param2) {
        BankFinanceFragment fragment = new BankFinanceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        Log.e("asd","DemoFragment"+param1);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate");
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        if(null!=tvLeft){
            tvLeft.setVisibility(View.INVISIBLE);
            tvRight1.setVisibility(View.INVISIBLE);
            tvRight2.setVisibility(View.INVISIBLE);
        }

        TAG=mParam1;
        Log.e(TAG,"isVisibleToUser"+isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
    }

//    @Override
//    protected void prepareFetchData(boolean forceUpdate) {
//        super.prepareFetchData(true);
//    }

    AnimatorSet set;
    private RelativeLayout floatBg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_demo, container, false);
        waveView= (WaveView) view.findViewById(R.id.waveviewid);
        mWaveHelper = new WaveHelper(waveView);



        tvLeft= (TextView) view.findViewById(R.id.tv_left);
        tvRight1= (TextView) view.findViewById(R.id.tv_right1);
        tvRight2= (TextView) view.findViewById(R.id.tv_right2);
        floatBg= (RelativeLayout) view.findViewById(R.id.floatBg);
        floatBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        animatorView();
        return view;
    }

//    @Override
//    protected void prepareFetchData(boolean forceUpdate) {
//        super.prepareFetchData(true);
//    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG,"onActivityCreated");
    }


    private void animatorView(){
        ObjectAnimator tvLeftanimatorX;
        ObjectAnimator tvLeftanimatorY;

        ObjectAnimator tvRight1animatorX;
        ObjectAnimator tvRight1animatorY;

        ObjectAnimator tvRight2animatorX;
        ObjectAnimator tvRight2animatorY;


        tvLeftanimatorX = ObjectAnimator.ofFloat(tvLeft, "translationX", 200, 0f);
        tvLeftanimatorY = ObjectAnimator.ofFloat(tvLeft, "translationY", -10, 0f);

        tvRight1animatorX = ObjectAnimator.ofFloat(tvRight1, "translationX", -200, 0f);
        tvRight1animatorY = ObjectAnimator.ofFloat(tvRight1, "translationY", 80, 0f);

        tvRight2animatorX = ObjectAnimator.ofFloat(tvRight2, "translationX", -200, 0f);
        tvRight2animatorY = ObjectAnimator.ofFloat(tvRight2, "translationY", 10, 0f);

        set = new AnimatorSet(); // X、Y轴同时移动
        set.playTogether(tvLeftanimatorX,tvLeftanimatorY,tvRight1animatorX,tvRight1animatorY,tvRight2animatorX,tvRight2animatorY);
        set.setInterpolator(new OverShoot());
        set.setDuration(2000);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                tvLeft.setVisibility(View.VISIBLE);
                tvRight1.setVisibility(View.VISIBLE);
                tvRight2.setVisibility(View.VISIBLE);
            }


        });
    }
    @Override
    public void loadData() {
        waveView.setBorder(mBorderWidth, mBorderColor);
        waveView.setWaveColor(
                Color.parseColor("#DEBB89"),
                Color.parseColor("#E4C9A1"));
        mWaveHelper.start();

        Log.e(TAG,"-----animal-----");
        set.start();
        Log.e(TAG,"loadData");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        TAG=mParam1;
        mActivity = (AppCompatActivity) context;
        Log.e(TAG,"onAttach");
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG,"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG,"onDetach");
    }

    @Override
    public void onResume() {
        super.onResume();
        waveView.setBorder(mBorderWidth, mBorderColor);
        mWaveHelper.start();

        if(isVisibleToUser){
            tvLeft.setVisibility(View.VISIBLE);
            tvRight1.setVisibility(View.VISIBLE);
            tvRight2.setVisibility(View.VISIBLE);
        }

        Log.e(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        mWaveHelper.cancel();
        set.cancel();

        Log.e(TAG,"onPause");
    }
}
