package org.web.exception;

import lombok.Getter;
import lombok.Setter;


/**
 * 类ServiceException.java的实现描述：封装业务异常
 * 
 * @author Luyl 2016年10月24日 上午10:23:00
 */
@Setter
@Getter
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	// 错误码
	private String errorCode;
	// 结果描述，用于排查问题
	private String description;
	// 参数
	private String params;
	// 终端用户的提示信息，不推荐使用
	private String message;

	public ServiceException(String errorCode, String description) {
		this.errorCode = errorCode;
		this.description = description;
	}

	public ServiceException(String errorCode, String description, String message, String params) {
		this.errorCode = errorCode;
		this.message = message;
		this.params = params;
		this.description = description;
	}

}
