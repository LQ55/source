package com.lqcool.qbase.base.util.dbconnect;

import com.lqcool.qbase.base.util.Loader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Jdbc {
	public static Map<String, String> jdbc = new HashMap<String, String>();
	
	static{
		Loader loader =Loader.instance();
		try {
			if(jdbc.size()==0){
				jdbc.putAll(loader.load("jdbc.properties"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String DRIVERCLASSNAME = jdbc.get("jdbc.driverClassName");
	
	public static String USERNAME = jdbc.get("jdbc.username");
	
	public static String PASSWORD = jdbc.get("jdbc.password");
	
	public static String DBURL = jdbc.get("jdbc.url");
	
}
