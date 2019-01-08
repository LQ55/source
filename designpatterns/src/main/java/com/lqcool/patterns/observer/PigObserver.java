package com.lqcool.patterns.observer;

/**
 * @Auther: lqcool
 * @Description:具体的观察者PigObserver（猪）
 */
public class PigObserver implements Observer{
    @Override
    public void response() {
        System.out.println("猪没有反应");
    }
}
