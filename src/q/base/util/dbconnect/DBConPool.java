package q.base.util.dbconnect;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * 该类为数据库连接池实例
 * 该池为单利池
 * 用户可以从此池中获取含有状态的数据库连接
 * @author Q 2017-05-18 00:36:34
 *
 */
public class DBConPool{
	/**
	 * 
	 */
	private List<DBCon> freeCons = new ArrayList<DBCon>();//空闲连接列表
	private List<DBCon> buzyCons = new ArrayList<DBCon>();//繁忙连接列表
	private int maxDBConNum = 10;//最大连接数
	private int minDBConNum = 2;//最小连接数
	private int currentDBCNum = 0;//当前连接数
	
	private static DBConPool instance;//单实例
	
	/**
	 * 获取池实例
	 * @return
	 */
	private static DBConPool getInstance() {
		if(instance==null)
			instance = new DBConPool();
		return instance;
	}
	
	
	/**
	 * 获取空闲数据库连接
	 * 先从空闲列表中找出一个连接
	 * 如果空闲列表中没有连接，则试图创建一个连接
	 * @return
	 */
	public DBCon getCon(){
		DBCon dbCon = this.getFreeCon();
		if(dbCon != null){
			return dbCon;
		}else {
			return this.getNewCon();
		}
	}
	
	/**
	 * 
	 * 获取一个空闲连接
	 */
	private DBCon getFreeCon(){
		if(freeCons.size() > 0){
			DBCon con = freeCons.remove(0);//获取空闲连接
			con.setState(DBCon.BUZY);//设置繁忙
			this.buzyCons.add(con);
			return con;
		}else {
			return null;
		}
		
	}
	
	/**
	 * 试图获取一个新的连接
	 * @return
	 */
	private DBCon getNewCon(){
		if(this.currentDBCNum < this.maxDBConNum){//如果当前连接数仍然小于最大连接数
			DBCon con = this.createCon();
			con.setState(DBCon.BUZY);//设置繁忙
			this.buzyCons.add(con);
			return con;
		}else {
			return null;//这里有问题，我觉得当达到了最大连接数，就扩大最大连接数
		}
	}
	
	/**
	 * 创建新的连接，并更新当前连接总数
	 * @return
	 */
	private DBCon createCon(){
		try {
			Connection con = DBDao.getConnction();
			DBCon dbCon = new DBCon(con);
			this.currentDBCNum ++;
			return dbCon;
		} 
		catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return null;
	}
	
	/**
	 * 将连接设置为空闲状态
	 * @param con
	 */
	public void setFree(DBCon con){
		this.buzyCons.remove(0);
		con.setState(DBCon.FREE);
		this.freeCons.add(con);
	}
	
	@Override
	public String toString(){
		return "[当前连接数]----" + 
				this.currentDBCNum + 
				"[空闲连接数]----" + 
				this.freeCons.size() + 
				"[繁忙连接数]----" + 
				this.buzyCons.size();
	}
	
}
