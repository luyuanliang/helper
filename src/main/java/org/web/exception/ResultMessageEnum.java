package org.web.exception;


public enum ResultMessageEnum {

	SUCCESS("SUCCESS","成功"),
	RECORD_EXIST("RECORD_EXIST", "记录已存在"),
	COMPARE_TIME_INVALID("COMPARE_TIME_INVALID", "时间日期不合法,结束时间不能早于开始时间"),
	MORE_THAN_CURRENT("MORE_THAN_CURRENT", "时间日期不合法,设定日期早于当前时间"),
	SYSTEM_EXCEPTION("SYSTEM_EXCEPTION", "系统异常"),
	NO_PRIVILEGE("NO_PRIVILEGE", "没有访问权限"),
	RECORD_NOT_EXIST("ERROR_RECORD_NOT_EXIST", "记录不存在"),
	RECORD_FORBIDDEN_UPDATE("RECORD_FORBIDDEN_UPDATE", "记录不允许修改"),
	DATA_LOSE_EFFICACY("DATA_LOSE_EFFICACY", "数据失效"),
	SYSTEM_ERROR("SYSTEM_ERROR", "系统异常"),
	OUTER_SYSTEM_EXCEPTION("OUTER_SYSTEM_EXCEPTION", "外部系统异常"),
	ACCESS_OUTER_SYSTEM_ERROR("OUTER_SYSTEM_ERROR", "访问外部系统出错"),
	DATA_NOT_EXIST("DATA_NOT_EXIST","信息不存在"),

	// 参数相关	PARAM_INVALID("PARAM_INVALID", "参数不合法"),
	PARAM_FORMAT_INVALID("PARAM_FORMAT_INVALID", "格式不合法"),
	PARAM_EMPTY("PARAM_EMPTY", "参数为空"),
	PARAM_INVALID("PARAM_INVALID","参数无效");


	
	private String code;
	private String message;

	private ResultMessageEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public static String getMessageByCode(String code) {
		for (ResultMessageEnum c : ResultMessageEnum.values()) {
			if (c.getCode().equals(code)) {
				return c.getMessage();
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
