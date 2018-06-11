package org.web.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.web.exception.ServiceException;

public class HttpHelper {

	private static Logger logger = Logger.getLogger(HttpHelper.class);

	private static final String DEFAULT_CHARSET = "utf-8";

	public static String requestByGet(String uri) throws ServiceException {
		// 创建默认的httpClient实例
		logger.info("URI is " + uri);
		HttpGet get = new HttpGet(uri);
		String result = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try {
			httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(get);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity);
			}
			logger.info("result is " + result);
		} catch (Exception e) {
			logger.error(ServiceExceptionHelper.getExceptionInfo(e));
			throw new ServiceException("GET_REQUEST_FAIL", "Get 请求访问失败。", uri, result);
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					logger.error(ServiceExceptionHelper.getExceptionInfo(e));
				}
			}
			if (httpResponse != null) {
				try {
					httpResponse.close();
				} catch (IOException e) {
					logger.error(ServiceExceptionHelper.getExceptionInfo(e));
				}
			}
		}
		return result;
	}

	
	public static String requestByPost(String uri, Map<String, String> params, String charset) throws ServiceException {
		logger.info("URI is " + uri);
		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse httpResponse = null;
		try {
			HttpPost post = new HttpPost(uri);
			// 创建参数列表
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			if (params != null) {
				for (String key : params.keySet()) {
					list.add(new BasicNameValuePair(key, params.get(key)));
				}
			}
			// url格式编码
			if (StringUtils.isNotBlank(charset)) {
				charset = DEFAULT_CHARSET;
			}
			post.setEntity( new UrlEncodedFormEntity(list, charset));
			// 执行请求
			httpResponse = httpClient.execute(post);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity);
			}
			return result;
		} catch (Exception e) {
			logger.error(ServiceExceptionHelper.getExceptionInfo(e));
			throw new ServiceException("POST_REQUEST_FAIL", "Get 请求访问失败。", uri, result);
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					logger.error(ServiceExceptionHelper.getExceptionInfo(e));
				}
			}
			if (httpResponse != null) {
				try {
					httpResponse.close();
				} catch (IOException e) {
					logger.error(ServiceExceptionHelper.getExceptionInfo(e));
				}
			}
		}
	}


}
