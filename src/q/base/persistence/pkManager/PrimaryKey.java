package q.base.persistence.pkManager;

import q.base.annotation.Column;
import q.base.persistence.baseEntity.Entity;

/**
 * 主键
 * @author Q 2017年6月15日01:16:08
 *
 */
public class PrimaryKey extends Entity{
	
	//web服務器的實例ID,即唯一標識一箇Web實例
	@Column(DBColumnName="serverId",nullable=false,label="服务标识")
    private String serverId;
    
    //序列名称,如果key是與實例相關，則此是數據庫錶的名稱，如果不相關，則 PrimaryKey 的唯一標識
	@Column(DBColumnName="name",nullable=false,isunique=false,label="序列名称",conment="序列名称,如果序列与实体相关，则此是实体对应的数据库表名，如果不相关，则是序列的唯一表识")
    private String name;
 
    //用這箇Key相關的字段名稱
	@Column(DBColumnName="tableName",nullable=false,label="表名",conment="使用序列所在的表名")
    private String tableName ="";
	
    //用這箇Key相關的字段名稱
	@Column(DBColumnName="columnName",nullable=false,label="字段名",conment="")
    private String columnName ="";
    
    //噹前的key
	@Column(DBColumnName="currentKey",nullable=false,label="当前序列号")
    private Long currentKey;
    
    //噹前已分配範圍的起始key
	@Column(DBColumnName="startKey",nullable=false,label="缓存开始号",conment="当前缓存开始号")
    private Long startKey;
    
    //噹前已分配範圍的結束key，如果 currentKey 等 于 endKey，則需要重新分配大小爲 incrementSize的最新範圍，
    //如果 endKey大于maxKey,則需要爲此server(即App)重新分配 maxKey 和 minkey
	@Column(DBColumnName="endKey",nullable=false,label="缓存结束号",conment="当前缓存结束号")
    private Long endKey;
    
	@Column(DBColumnName="maxKey",nullable=false,label="分配的最大号",conment="给当前服务器分配的最大号")
    private Long maxKey;
    
	@Column(DBColumnName="minKey",nullable=false,label="分配的最小号",conment="给当前服务器分配的最小号")
    private Long minKey;
    
	@Column(DBColumnName="banKey",nullable=true,label="禁止的序列号",conment="禁止分配的序列号，即在分配时要路过这些号")
    private String banKey=" ";
    
	@Column(DBColumnName="keyType",nullable=false,label="序列类型",conment="序列分类：table 用于表的EId，code 用于编码，other 用于其他用途")
    private String keyType;
    
    //如果分段不足，增加的大小
	@Column(DBColumnName="cachedSize",nullable=false,label="缓存大小",conment="即每次缓存的序列号的大小，通常有：缓存大小=缓存结束号 - 缓存开始号")
    private Long cachedSize;
    
	@Column(DBColumnName="billCodeTypeName",nullable=true,label="使用序列的编码类型名称")
	private String billCodeTypeName = " ";
	
    private boolean needUpdate = false;
}
