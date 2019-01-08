package com.lqcool.patterns.observer;

/**
 * @Auther: lqcool
 * @Description: 具体的观察者MouseObserver老鼠
 */
public class MouseObserver implements Observer{
    @Override
    public void response() {
        System.out.println("老鼠努力跑！");
    }
}
