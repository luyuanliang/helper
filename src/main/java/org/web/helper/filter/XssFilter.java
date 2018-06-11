package org.web.helper.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.web.helper.StringHelper;

public class XssFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String contentType = request.getContentType();
		ServletRequest wrapperRequest = request;
		if (contentType == null || !contentType.toLowerCase().startsWith("multipart/form-data")) {
			wrapperRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
		}
		chain.doFilter(wrapperRequest, response);
	}

	@Override
	public void destroy() {
	}
}

class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	public XssHttpServletRequestWrapper(HttpServletRequest httpServletRequest) {
		super(httpServletRequest);
		this.setRequest(httpServletRequest);
	}

	/**
	 * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
	 * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
	 */
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(StringHelper.xssEncode(name));
		if (value != null) {
			value = StringHelper.xssEncode(value);
		}
		return value;
	}

	/**
	 * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
	 * getHeaderNames 也可能需要覆盖
	 */
	// @Override
	// public String getHeader(String name) {
	//
	// String value = super.getHeader(name);
	// if (value != null) {
	// value = StringUtil.xssEncode(value);
	// }
	// return value;
	// }
}