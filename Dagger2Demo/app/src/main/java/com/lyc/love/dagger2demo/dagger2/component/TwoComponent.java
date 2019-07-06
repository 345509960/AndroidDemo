package com.lyc.love.dagger2demo.dagger2.component;

import com.lyc.love.dagger2demo.TwoActivity;
import com.lyc.love.dagger2demo.dagger2.module.AppModule;
import com.lyc.love.dagger2demo.dagger2.module.ObjectModule;
import com.lyc.love.dagger2demo.dagger2.scope.ActivityScope;

import dagger.Component;

/**
    @作者 lyc
    create date: $time
    des:
**/
@ActivityScope
@Component(modules = ObjectModule.class,dependencies = AppComponent.class)
public interface TwoComponent {
    void inject(TwoActivity twoActivity);
}
