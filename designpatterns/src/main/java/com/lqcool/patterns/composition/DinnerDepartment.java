package com.lqcool.patterns.composition;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lqcool
 * @Description:
 */
public class DinnerDepartment extends Department{
    private List<Department> childDepartmentList = new ArrayList<Department>();

    public DinnerDepartment(){
        super();
    }

    public DinnerDepartment(String name){
        super(name);
    }

    @Override
    protected void add(Department department) {
        childDepartmentList.add(department);
    }

    @Override
    protected void show(int depth) {
        StringBuilder info = new StringBuilder("");
        for(int i = 0; i < depth; i ++){
            info.append("-");
        }
        System.out.println(new String(info) + this.getName());
        for(Department department:childDepartmentList){
            department.show(depth+2);
        }
    }
}
