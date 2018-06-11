package org.web.helper;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.web.exception.ResultMessageEnum;
import org.web.exception.ServiceException;


@SuppressWarnings({"rawtypes","unchecked"})
public class HttpServletRequestHelper {

	private static final Logger logger = Logger.getLogger(HttpServletRequestHelper.class);

	public static final String DATA_FORMAT_ = "DATA_FORMAT_";
	public static final String TIME_FORMAT_ = "TIME_FORMAT_";

	public static <T> T buildBeanByRequest(HttpServletRequest request, Class<T> clazz) throws ServiceException {
		logger.info("URI is " + request.getRequestURI());
		return buildBeanByMap(buildMapByRequest(request), clazz);
	}

	public static <T> T buildBeanByMap(Map map, Class<T> clazz) throws ServiceException {
		try {
			Object obj = clazz.newInstance();
			BeanUtils.populate(obj, map);
			return (T) obj;
		} catch (Exception e) {
			logger.error(ServiceExceptionHelper.getExceptionInfo(e));
			throw ServiceExceptionHelper.buildServiceException(ResultMessageEnum.PARAM_FORMAT_INVALID);
		}
	}

	public static Map<String, Object> buildMapByRequest(HttpServletRequest request) throws ServiceException {
		logger.info("URI is " + request.getRequestURI());
		try {
			Enumeration<String> e = request.getParameterNames();
			Map<String, Object> map = new HashMap<String, Object>();
			while (e.hasMoreElements()) {
				String name = e.nextElement().toString();
				String value = request.getParameter(name);
				logger.info(name + "=" + request.getParameter(name));
				if (name.startsWith(DATA_FORMAT_)) {
					map.put(name.replaceFirst(DATA_FORMAT_, ""), DateHelper.fromStrGetDate(request.getParameter(name),DateHelper.FORMAT_YYYY_MM_DD));
				} else if (name.startsWith(TIME_FORMAT_)) {
					map.put(name.replaceFirst(TIME_FORMAT_, ""), DateHelper.fromStrGetDate(request.getParameter(name),DateHelper.FORMAT_YYYY_MM_DD_HH_MM_SS));
				} else if ("全部".equals(value)){
					map.put(name, null);
				} else if (!StringUtils.isEmpty(value)){
					map.put(name, request.getParameter(name));
				}

			}
			return map;
		} catch (Exception e) {
			logger.error(ServiceExceptionHelper.getExceptionInfo(e));
			throw ServiceExceptionHelper.buildServiceException(ResultMessageEnum.PARAM_FORMAT_INVALID);
		}
	}

}
