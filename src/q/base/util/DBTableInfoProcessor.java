package q.base.util;

import java.util.ArrayList;

public class DBTableInfoProcessor implements IProcessor{

	@Override
	public String Process() throws Exception {
		ArrayList<Class<?>> classes = ClassScanner.loadClasses();//加载类型信息
		for(int i = 0; i < classes.size(); i ++){
			DBTableInfo tempDbTableInfo = new DBTableInfo().parse(classes.get(i));
			if(tempDbTableInfo!=null){
				System.out.println((tempDbTableInfo.toString()));
			}
		}
		
		return null;
	}
}
