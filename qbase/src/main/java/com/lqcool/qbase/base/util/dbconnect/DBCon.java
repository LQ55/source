package com.lqcool.qbase.base.util.dbconnect;

import java.sql.Connection;

/**
 * 含有状态的数据库连接
 * @author Q 2017-05-18 00:27:59
 *
 */
public class DBCon {
	/**
	 * 连接的几种状态
	 */
	public static final int FREE = 100;//当前连接空闲
	public static final int BUZY = 101;//当前连接繁忙
	public static final int CLOSED = 102;//当前连接关闭
	
	private Connection con;//持有的数据库连接
	
	private int state = FREE;//数据库连接当前状态
	
	public DBCon(){
	}
	
	public DBCon(Connection con){
		this.con = con;
	}
	
	public Connection getCon(){
		return con;
	}
	
	public int getState(){
		return state;
	}
	
	public void setState(int state){
		this.state = state;
	}
}
