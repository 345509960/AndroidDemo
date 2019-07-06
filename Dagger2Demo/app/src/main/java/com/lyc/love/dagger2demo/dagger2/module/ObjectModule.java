package com.lyc.love.dagger2demo.dagger2.module;

import com.google.gson.Gson;
import com.lyc.love.dagger2demo.bean.Goods;
import com.lyc.love.dagger2demo.bean.Order;
import com.lyc.love.dagger2demo.dagger2.scope.Name;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
    @作者 lyc
    create date: $time
    des:
**/

@Module
public class ObjectModule {

//    @Singleton
//    @Provides
//    public Goods provideGoods(){
//        return new Goods();
//    }
//
    @Provides
    Gson provideGson(){
      return new Gson();
  }


    @Provides
    Order provideOrder(Goods goods){
        return new Order(goods);
    }

    @Name("value")
    @Provides
    Order provideOrder2(Goods goods){
        Order order=new Order(goods);
        order.setName("我的另外的Order");
        return order;
    }

}
