package q.base.persistence;

import q.base.persistence.baseEntity.Entity;

public class Session {
	
	/** 
	 * @author Q 2017-05-23 00:29:24
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public <T extends Entity> int save(T entity) throws Exception{
		return 1;
	}
	
	/**
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public <T extends Entity> int saveEntity(T entity) throws Exception{
		if(entity==null)
			return 0;
		return 1;
	}
}
