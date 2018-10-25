package com.lqcool.qbase.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//作用于类，接口，枚举上
@Retention(RetentionPolicy.RUNTIME)//注解作到于运行时
public @interface Entity {
	/**
	 * 映射到数据库的表名，默认为实体名称
	 * @return
	 */
	public String DBTName() default "";
	
	/**
	 * 实体中文名称
	 * @return
	 */
	public String label() default "";
	
	
	/**
	 * 实体注释
	 * @return
	 */
	public String conment() default "";
}
