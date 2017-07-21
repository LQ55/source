package q.base.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Config {
	public static Map<String, String> config = new HashMap<String, String>();

	static{
		Loader loader =Loader.instance();
		try {
			if(config.size()==0){
				config.putAll(loader.load("config.properties"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static final String CHARSET_UTF8 = config.get("charset_UTF8");

	public static final String CHARSET_ISO = config.get("charset_ISO");
}
