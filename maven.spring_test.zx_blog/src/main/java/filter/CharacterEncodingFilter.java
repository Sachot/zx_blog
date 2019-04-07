package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//使用过滤器需要先对xml进行配置
public class CharacterEncodingFilter implements Filter {

	//创建一个过滤器参数对象
	private FilterConfig config;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(config.getInitParameter("charset"));
		
		//通知web服务器，请求拦截处理已完成
		chain.doFilter(request, response);
	}

	@Override
	//FilterConfig——过滤器配置类
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

	public FilterConfig getConfig() {
		return config;
	}

	public void setConfig(FilterConfig config) {
		this.config = config;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
