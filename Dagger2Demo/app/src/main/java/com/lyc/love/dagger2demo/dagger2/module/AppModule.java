package com.lyc.love.dagger2demo.dagger2.module;

import com.lyc.love.dagger2demo.bean.Goods;
import com.lyc.love.dagger2demo.bean.Person;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/4/6 0006.
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    Goods providesGoods(){
        return new Goods();
    }

    @Provides
    Person providePerson(){
        return new Person();
    }
}
