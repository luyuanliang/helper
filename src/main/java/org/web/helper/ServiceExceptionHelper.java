package org.web.helper;

import org.apache.commons.lang3.StringUtils;
import org.web.domain.ResultDO;
import org.web.domain.ViewResult;
import org.web.exception.ResultMessageEnum;
import org.web.exception.ServiceException;

/**
 * 类ServiceExceptionHelper.java的实现描述：
 * 
 * @author Administrator 2016年10月24日 上午11:19:35
 */
@SuppressWarnings("rawtypes")
public class ServiceExceptionHelper {

	public static ServiceException buildServiceException(ResultMessageEnum resultMessageEnum, String message, String params) {
		ServiceException serviceException = new ServiceException(resultMessageEnum.getCode(), resultMessageEnum.getMessage(), message, params);
		return serviceException;
	}

	public static ServiceException buildServiceException(ResultMessageEnum resultMessageEnum, String message) {
		return buildServiceException(resultMessageEnum, message, null);
	}

	public static ServiceException buildServiceException(ResultMessageEnum resultMessageEnum) {
		return buildServiceException(resultMessageEnum, null, null);
	}

	public static ViewResult buildViewResultByServiceException(Exception e) {
		if (e instanceof ServiceException) {
			return buildViewResultByServiceException((ServiceException) e, null);
		}
		return buildViewResultByServiceException(buildServiceException(ResultMessageEnum.SYSTEM_ERROR), null);
	}

	public static ViewResult buildViewResultByServiceException(ServiceException e) {
		return buildViewResultByServiceException(e, null);
	}

	public static ViewResult buildViewResultByServiceException(ServiceException e, String msg) {
		return buildViewResultByServiceException(e, msg, StringUtils.isEmpty(msg) ? msg : e.getMessage());
	}

	public static ViewResult buildViewResultByServiceException(ServiceException e, String msg, String title) {
		ViewResult view = new ViewResult();
		view.setType(ViewResult.ViewType.error.name());
		view.setResult(false);
		if (StringUtils.isNotBlank(title)) {
			view.setTitle(title);
		} else {
			view.setTitle("操作失败");
		}

		if (StringUtils.isNotBlank(msg)) {
			view.setMsg(msg);
		} else if (StringUtils.isNotBlank(e.getMessage())) {
			view.setMsg(e.getMessage());
		} else if (StringUtils.isNotBlank(e.getDescription())) {
			view.setMsg(e.getDescription());
		}
		return view;
	}

	public static ResultDO buildResultDOByServiceException(ServiceException e) {
		return buildResultDOByServiceException(e, null);
	}

	public static ResultDO buildResultDOByServiceException(ServiceException e, String message) {
		ResultDO resultDO = new ResultDO(false);
		resultDO.setCode(e.getErrorCode());
		resultDO.setDescription(e.getDescription());
		if (StringUtils.isNotEmpty(e.getDescription())) {
			e.setDescription(e.getDescription() + " and parameter is " + e.getParams());
		}
		if (StringUtils.isNotBlank(message)) {
			resultDO.setMessage(message);
		} else if (StringUtils.isNotEmpty(e.getMessage())) {
			resultDO.setMessage(e.getMessage());
		} else {
			resultDO.setMessage(e.getDescription());
		}
		return resultDO;
	}

	public static String getExceptionInfo(Exception e) {
		StringBuffer buffer = new StringBuffer();
		if (e != null) {
			buffer.append(e.toString()).append("\n\t");
			StackTraceElement[] messages = e.getStackTrace();
			int length = messages.length;
			for (int i = 0; i < length; i++) {
				buffer.append("at ").append(messages[i].toString()).append("\n\t");
			}
		}
		return buffer.toString();
	}
}
