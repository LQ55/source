package q.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

import q.base.util.Config;

/**
 * 
 * 过滤器，请求需要经过此处
 * @author Q
 *
 */
public class DispatcherFilter implements Filter{
	
	public DispatcherFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		//记录用户的访问操作
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		//获取请求方法
		String currentRequestMethod = httpServletRequest.getMethod();
		//获取请求路径
		String currentRequestPath = getRequestPath(httpServletRequest);
		
		// 设置编码
		httpServletRequest.setCharacterEncoding(Config.CHARSET_UTF8);
		
		//输出请求信息
		System.out.println("-----------------请求信息----------------");
		System.out.println("请求信息:" + currentRequestPath);
		System.out.println(""
				+ new String(JSON.toJSONString(request.getParameterMap(), true).getBytes(Config.CHARSET_ISO), Config.CHARSET_UTF8));
		System.out.println("--------------------------------------");

		// 取得session. 如果没有session则自动会创建一个, 参数false表示没有取得到session则设置为session为空.
		HttpSession session = httpServletRequest.getSession(false);
		
		
		//对url进行过滤，如果是js/css/image则不进行处理
		if(judgeFile(currentRequestPath.toString())){
			//userHttpServletRequest.getQueryString();地址参数
		}
		//将控制权交给下一个过滤器
		chain.doFilter(request, response);
	}
	
	// 得到请求路径
	public static String getRequestPath(HttpServletRequest request)
	{
		String servletPath = request.getServletPath();
		String pathInfo = StringUtils.defaultIfEmpty(request.getPathInfo(), "");
		return servletPath + pathInfo;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	//判断请求的是不是css文件，js文件，以及图片文件
	public boolean judgeFile(String url){
		if (url.endsWith(".gif") || url.endsWith(".jpg") || url.endsWith(".png")
				|| url.endsWith(".bmp") || url.endsWith(".css") || url.endsWith(".js")
				|| url.endsWith(".jsx")){
			return false;
		} else {
			return true;
		}
	}

}
