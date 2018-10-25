package com.lqcool.qbase.base.util;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 该工具类用于加载指定文件夹下面的.class文件：注：当在文件夹中新建立了实体之后，需要从新启动tomcat才能扫描文件夹下的文件
 * @author Q 2017-05-17 16:00:24
 *
 */
public class ClassScanner {

	public static ArrayList<Class<?>> loadClasses() throws Exception{

		String classFilePath = Config.config.get("classFilePath");//.class文件路径
		String entityPackage = Config.config.get("entityPackge");//实体存放包名

		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();

		File directory = new File(classFilePath);

		// 记录加载.class文件的数量  
		int clazzCount = 0;
		if (directory.exists() && directory.isDirectory()) {  
			// 获取从src文件夹开始之前路径长度  
			int directoryLen = directory.getAbsolutePath().length() - entityPackage.length();  
			//采用栈设计
			Stack<File> stack = new Stack<>();  
			stack.push(directory);  
			// 遍历类路径  
			System.out.println("[扫描实体开始]------------------------------------------------------------->>>>>>>>>>>>>>>");
			while (!stack.isEmpty()) { 
				File path = stack.pop();  
				File[] classFiles = path.listFiles(new FileFilter() {  
					public boolean accept(File pathname) {  //\entity\
						return pathname.isDirectory() || pathname.getName().endsWith(".class");  
					}  
				});  
				for (File subFile : classFiles) {  
					if (subFile.isDirectory()) {  
						stack.push(subFile);  
					} else {  
						if (clazzCount++ == 0) {  
							Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);  
							boolean accessible = method.isAccessible();  
							try {  
								if (accessible == false) {  
									method.setAccessible(true);  
								}  
								// 设置类加载器  
								URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();  
								// 将当前类路径加入到类加载器中  
								method.invoke(classLoader, directory.toURI().toURL());  
							} finally {  
								method.setAccessible(accessible);  
							}  
						}  
						// 文件名称  
						String className = subFile.getAbsolutePath(); 
						//输出扫描到的实体名
						className = className.substring(directoryLen, className.length() - 6);  
						System.out.println(className.replaceAll("[\\\\]", "."));
						className = className.replace(File.separatorChar, '.');  
						// 加载Class类  
						classes.add(Class.forName(className));
						//LOG.debug("读取应用程序类文件[class={}]", className);  
					}  
				}  
			}  
			System.out.println("[扫描实体结束]-------------------------------------------------------------<<<<<<<<<<<<<<<");
			System.out.println();
			System.out.println();
		}  
		return classes;
	}

	public static void main(String [] args){
		try {
			loadClasses();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
