package com.lqcool.patterns.observer;

/**
 * @Auther: lqcool
 * @Description:具体的观察者DogObserver（狗）
 */
public class DogObserver implements Observer{
    @Override
    public void response() {
        System.out.println("狗跟着叫！");
    }
}
