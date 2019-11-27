package com.lyc.love.dagger2demo.dagger2.component;

import com.lyc.love.dagger2demo.ThirdActivity;
import com.lyc.love.dagger2demo.dagger2.module.ThirdModule;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2018/4/7 0007.
 */
@Subcomponent(modules = ThirdModule.class)
public interface ThirdCompnent {
    void inject(ThirdActivity thirdActivity);
}
