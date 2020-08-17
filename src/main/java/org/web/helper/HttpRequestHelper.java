package org.web.helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class HttpRequestHelper {

	public static String getValueByCookie(HttpServletRequest request, String cookieKey) {
		String value = null;
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals(cookieKey)) {
					value = cookie.getValue();
					break;
				}
			}
		}
		return value;
	}

	public static String getValueByParamOrCookie(HttpServletRequest request, String key) {
		String value = getValueByCookie(request, key);
		if (StringUtils.isNotBlank(value)) {
			return value;
		} else if (StringUtils.isNotBlank(request.getParameter(key))) {
			return request.getParameter(key);
		} else {
			return null;
		}
	}

}
