package com.lqcool.qbase.base;

import com.lqcool.qbase.base.util.DBTableInfoProcessor;

public class Test {
	public static void main(String [] args){
		DBTableInfoProcessor dbTableInfoProcessor= new DBTableInfoProcessor();
		try {
			dbTableInfoProcessor.Process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
