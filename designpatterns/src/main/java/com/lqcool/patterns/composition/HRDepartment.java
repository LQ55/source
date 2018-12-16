package com.lqcool.patterns.composition;

/**
 * @Auther: lqcool
 * @Description:
 */
public class HRDepartment extends Department{
    public HRDepartment(){

    }

    public HRDepartment(String name){
        super(name);
    }

    @Override
    protected void add(Department department) {

    }

    @Override
    protected void show(int depth) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        System.out.println(new String(sb) + this.getName());
    }
}
