package com.lqcool.patterns.observer;

/**
 * @Auther: lqcool
 * @Description:主要测试类
 */
public class Client {
    public static void main(String [] args){
        Subject subject = new CatSubject();

        Observer mouseObs,dogObs,pigObs;
        mouseObs = new MouseObserver();
        dogObs = new DogObserver();
        pigObs = new PigObserver();

        subject.attach(mouseObs);
        subject.attach(dogObs);
        subject.attach(pigObs);

        subject.cry();
    }
}
