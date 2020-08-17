package org.web.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by luyl on 17-12-1.
 */
public class StringHelper {

	// 大小写均可匹配
	public static Pattern PATTERN_EVAL = Pattern.compile("eval\\((.*)\\)", Pattern.CASE_INSENSITIVE);
	// private static Pattern PATTERN_JAVA_SCRIPT =
	// Pattern.compile("([\\\"\\\'][\\s]*)javascript:(.*)([\\\"\\\'])",
	// Pattern.CASE_INSENSITIVE);
	public static Pattern PATTERN_SCRIPT = Pattern.compile("script", Pattern.CASE_INSENSITIVE);

	public static Pattern LESS_PATTERN_SCRIPT = Pattern.compile("<\\s*script", Pattern.CASE_INSENSITIVE);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> List<T> transStr2List(String str, String seperate, Class<T> clazz) {
		List list = null;
		if (StringUtils.isNotBlank(str)) {
			list = new ArrayList<>();
			String[] array = str.split(seperate);
			for (int i = 0; i < array.length; i++) {
				if (StringUtils.isNotBlank(array[i]) && StringUtils.isNotBlank(array[i].trim())) {
					if ("java.lang.Long".equals(clazz.getClass())) {
						list.add(Long.valueOf(array[i].trim()));
					} else if ("java.lang.Integer".equals(clazz.getClass())) {
						list.add(Integer.valueOf(array[i].trim()));
					} else {
						list.add(array[i].trim());
					}
				}
			}
		}
		if (list == null || list.size() == 0) {
			return null;
		}
		return list;
	}

	/**
	 * 转换xss特殊字符
	 * 
	 * @param value
	 * @return
	 */
	public static String xssEncode(String value) {
		if (value == null)
			return null;
		value = replaceAll(value, PATTERN_EVAL, "ｅval($1)");
		value = replaceAll(value, PATTERN_SCRIPT, "ｓcript");
		// value = replaceAll(value, PATTERN_JAVA_SCRIPT, "$1ｊavascript:$2$3");

		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		// value = value.replaceAll("'", "&#39;");
		// value = value.replaceAll("\"", "&#22;");

		return value;
	}

	/**
	 * 转换xss特殊字符
	 * 
	 * @param value
	 * @return
	 */
	public static String lessXssEncode(String value) {
		if (value == null)
			return null;
		value = replaceAll(value, PATTERN_EVAL, "ｅval($1)");
		value = replaceAll(value, LESS_PATTERN_SCRIPT, "<ｓcript");
		// value = replaceAll(value, PATTERN_JAVA_SCRIPT, "$1ｊavascript:$2$3");
		//
		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		// value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		// value = value.replaceAll("'", "&#39;");
		// value = value.replaceAll("\"", "&#22;");

		return value;
	}

	private static String replaceAll(String input, Pattern p, String replacement) {
		Matcher m = p.matcher(input);
		String result = m.replaceAll(replacement);
		return result;
	}

	public static boolean isEmpty(Object obj) {
		if (obj != null) {
			if (obj instanceof List) {
				List list = (List) obj;
				if (list.size() == 0) {
					return true;
				}
			} else if (obj instanceof Set) {
				Set set = (Set) obj;
				if (set.size() == 0) {
					return true;
				}
			} else if (obj instanceof Map) {
				Map map = (Map) obj;
				if (map.size() == 0) {
					return true;
				}
			}
		}
		return false;
	}

	public static String getStringCheckNull(String value, String defaulValue) {
		return StringUtils.isNotBlank(value) ? value : defaulValue;
	}
}
