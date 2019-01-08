package com.lqcool.patterns.observer;

import java.util.ArrayList;

/**
 * @Auther: lqcool
 * @Description: 抽象观察类
 */
public abstract class Subject {
    /**
     * 记录当前目标的观察者
     */
    protected ArrayList observers = new ArrayList();
    /**
     * 注册观察者
     * @param obs
     */
    public void attach(Observer obs){
        this.observers.add(obs);
    }
    /**
     * 注销观察者
     * @param obs
     */
    public void detach(Observer obs){
        this.observers.remove(obs);
    }
    /**
     * 抽象的通知方法
     */
    public abstract void cry();
}
