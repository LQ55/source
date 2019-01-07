package com.lqcool.patterns.prototype;

import java.io.*;

/**
 * @Auther: lqcool
 * @Description: 原型对象，自带一个clone方法，从自身克隆副本
 */
public abstract class Shape implements Cloneable,Serializable{
    private String id;

    protected String type;

    abstract void draw();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 浅克隆方法，该方法返回的对象所有普通成员都与原对象有相同的值，而所有其它对象的引用任然指向原来的对象，而不是复制他所有的成员引用
     * @return
     */
    public Object clone(){
        Object clone = null;
        try{
            clone = super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return clone;
    }

    /**
     * 深度克隆，该方法会深度克隆对象
     * @return
     */
    public Object deepClone() {
        try{
            //将对象写入流中
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bao);
            oos.writeObject(this);
            //将对象从流中取出
            ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (ois.readObject());
        }catch (IOException e1){
            e1.printStackTrace();
        }catch (ClassNotFoundException e2){
            e2.printStackTrace();
        }
        return null;
    }
}
