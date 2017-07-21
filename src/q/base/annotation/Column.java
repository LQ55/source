package q.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)//作用于字段
@Retention(RetentionPolicy.RUNTIME)//注解作到于运行时
public @interface Column {

	/**
	 * 字段映射到数据库字段名
	 * @return
	 */
	public String DBColumnName() default "";
	
	/**
	 * 字段中文名称
	 * @return
	 */
	public String label() default "";
	
	/**
	 * 字段注释
	 * @return
	 */
	public String conment() default "";
	
	/**
	 * 字段是否为空，默认允许为空
	 * @return
	 */
	public boolean nullable() default true;
	
	/**
	 * 字段的长度
	 * @return
	 */
	public int length() default -1;
	
	/**
	 * 外键名称
	 * @return
	 */
	public String FKName() default "";
	
	/**
	 * 字段是否自增
	 * @return
	 */
	public boolean isidentity() default false;
	
	/**
	 * 字段是否为主键
	 * @return
	 */
	public boolean isprimarykey() default false;
	
	/**
	 * 字段是否唯一
	 * @return
	 */
	public boolean isunique() default false;
	
}
