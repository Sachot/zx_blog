package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//ʹ�ù�������Ҫ�ȶ�xml��������
public class CharacterEncodingFilter implements Filter {

	//����һ����������������
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
		
		//֪ͨweb���������������ش��������
		chain.doFilter(request, response);
	}

	@Override
	//FilterConfig����������������
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
