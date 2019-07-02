package com.lyc.indonesia.animationdemo;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

public class PointFEvaluator implements TypeEvaluator<PointF> {

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        PointF pointF=new PointF();
        pointF.x=200 * fraction * 3;
        pointF.y=0.5f * 200 * (fraction * 3) * (fraction * 3);
        return pointF;
    }
}
