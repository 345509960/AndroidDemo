package com.lyc.love.dagger2demo.dagger2.component;

import com.lyc.love.dagger2demo.MyApplication;
import com.lyc.love.dagger2demo.bean.Goods;
import com.lyc.love.dagger2demo.dagger2.module.AppModule;
import com.lyc.love.dagger2demo.dagger2.module.ObjectModule;
import com.lyc.love.dagger2demo.dagger2.module.ThirdModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2018/4/6 0006.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MyApplication myApplication);

    ThirdCompnent addSub(ThirdModule thirdModule);

    //告诉依赖的Component,我给你提供了这个类。比不可少。
    Goods getGoods();
}
