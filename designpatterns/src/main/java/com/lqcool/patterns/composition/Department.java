package com.lqcool.patterns.composition;

/**
 * @Auther: lqcool
 * @Description: 部门抽象类
 */
public abstract class Department {
    private String name;

    public Department(){

    }

    public Department(String name){
        this.name = name;
    }
    /**
     * 新增部门
     * @param department
     */
    protected abstract void add(Department department);
    /**
     * 打印
     * @param depth
     */
    protected abstract void show(int depth);

    /**
     * 获取名称
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * 设置名称
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
