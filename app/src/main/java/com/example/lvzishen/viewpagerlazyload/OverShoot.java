package com.example.lvzishen.viewpagerlazyload;

import android.animation.TimeInterpolator;

/**
 * Created by liubaba on 2017/9/6.
 */

public class OverShoot implements TimeInterpolator {

    private float tension = 1.0f;

    @Override
    public float getInterpolation(float input) {

        input -= 1.0;
      return   input * input * ((tension + 1) * input + tension) + 1.0f;

    }
}
