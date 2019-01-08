package com.lqcool.patterns.observer;

/**
 * @Auther: lqcool
 * @Description: 具体的目标类Cat
 */
public class CatSubject extends Subject{
    @Override
    public void cry() {
        System.out.println("猫叫！");
        System.out.println("------------------------------------------------------------");
        //通知该目标对象的所有的观察者
        for(Object obs:observers){
            ((Observer)obs).response();
        }
    }
}
