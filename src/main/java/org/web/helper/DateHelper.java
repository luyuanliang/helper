/**  
 * @Title: DateUtils.java
 * @Package tf56.skynet.utils
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chen.zhang
 * @date 2016年12月15日 上午10:48:38
 * @version V1.0  
 */
package org.web.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: DateUtils
 * @Description: 日期工具类
 * @author c.zhang
 * @date 2016年12月15日 上午10:48:38
 */
public class DateHelper {

	public static SimpleDateFormat df;

	public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm:00";

	public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public static final String FORMAT_YYYY_MM = "yyyy-MM";

	public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static final String FORMAT_YYYYMMDDHH = "yyyyMMddHH";
	public static final String FORMAT_YYYYMMDD = "yyyyMMdd";

	/**
	 * 根据日期字符串判断当月第几周
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static int getWeek(String str) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(str);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 第几周
		int week = calendar.get(Calendar.WEEK_OF_MONTH);
		// //第几天，从周日开始
		// int day = calendar.get(Calendar.DAY_OF_WEEK);
		return week;
	}

	/**
	 * 返回某日期 之前或之后的 日期
	 */
	public static final Date getDaysByCount(Date requestDate, int dayCount) {
		return getDaysByCount(requestDate, dayCount, FORMAT_YYYY_MM_DD_HH_MM_SS);
	}
	/**
	 * 返回某日期 之前或之后的 日期 
	 */
	public static final Date getDaysByCount(Date requestDate, int dayCount, String dataFormat) {
		df = new SimpleDateFormat(dataFormat);
		df.format(requestDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(requestDate);
		cal.add(Calendar.DATE, dayCount);
		df.format(cal.getTime());
		return cal.getTime();
	}

	public static String changeStrDateFormat(String str, String afterFormat) throws Exception {
		Date date = fromStrGetDate(str);
		return getStrByDate(date, afterFormat);
	}

	public static String changeStrDateFormat(String str, String beforFormat, String afterFormat) throws Exception {
		Date date = fromStrGetDate(str, beforFormat);
		return getStrByDate(date, afterFormat);
	}

	public static void main(String[] args) {
		try {
			System.out.println(getWeek("2017-01-23"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static final String getStrByDate(Date date) {
		return getStrByDate(date, FORMAT_YYYY_MM_DD_HH_MM_SS);
	}

	public static final String getStrByDate(Date date, String format) {
		df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static final String getStrByDate(String date, String beforeFormat, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(beforeFormat);
		return getStrByDate(sdf.parse(date), format);
	}

	public static final Date fromStrGetDate(String dateStr) throws Exception {
		return fromStrGetDate(dateStr, FORMAT_YYYY_MM_DD_HH_MM_SS);
	}

	public static final Date fromStrGetDate(String dateStr, String dateFormate) throws ParseException {
		df = new SimpleDateFormat(dateFormate);
		return df.parse(dateStr);
	}

	/**
	 * @Description 返回给定日期当月的最后一天
	 * @author chen.zhang
	 * @date 2016年12月15日 上午11:09:10
	 * @return “yyyy-MM-dd” 格式日期字符串
	 */
	public static String getMonthLastDay(String dateStr) {
		return getMonthLastDay(dateStr, FORMAT_YYYY_MM_DD);
	}

	/**
	 * @Description 返回给定日期当月的最后一天
	 * @author c.zhang
	 * @date 2016年12月15日 上午11:09:10
	 * @return dateFormat格式日期字符串
	 */
	public static String getMonthLastDay(String dateStr, String dateFormat) {
		if (StringUtils.isBlank(dateStr))
			return null;
		if (StringUtils.isBlank(dateFormat))
			return null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			Date dateDat = sdf.parse(dateStr);
			Calendar cal_1 = Calendar.getInstance();// 获取当前日期
			cal_1.setTime(dateDat);
			cal_1.add(Calendar.MONTH, 1);
			cal_1.set(Calendar.DAY_OF_MONTH, -1);
			return sdf.format(cal_1.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @Description 返回给定日期当月的第一天
	 * @author chen.zhang
	 * @date 2016年12月15日 上午11:09:10
	 * @return “yyyy-MM-dd” 格式日期字符串
	 */
	public static String getMonthFirstDay(String dateStr) {
		return getMonthFirstDay(dateStr, FORMAT_YYYY_MM_DD);
	}

	/**
	 * @Description 返回给定日期当月的第一天
	 * @author c.zhang
	 * @date 2016年12月15日 上午11:09:10
	 * @return dateFormat格式日期字符串
	 */
	public static String getMonthFirstDay(String dateStr, String dateFormat) {
		if (StringUtils.isBlank(dateStr))
			return null;
		if (StringUtils.isBlank(dateFormat))
			return null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			Date dateDat = sdf.parse(dateStr);
			Calendar cal_1 = Calendar.getInstance();// 获取当前日期
			cal_1.setTime(dateDat);
			cal_1.add(Calendar.MONTH, 0);
			cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
			return sdf.format(cal_1.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @Description: 近3个月第一天）
	 * @author chen.zhang
	 * @date 2016年12月15日 下午5:49:56
	 * @param {sign=签名,
	 *            timestamp=时间戳}
	 * @return 返回类型 String CommonJson格式
	 */
	public static String get3MonFirDay() {
		return getSomMonFirDayBySomDay(new Date(), FORMAT_YYYY_MM_DD, -2);
	}

	/**
	 * @Description: 近6个月第一天）
	 * @author chen.zhang
	 * @date 2016年12月15日 下午5:49:56
	 * @param {sign=签名,
	 *            timestamp=时间戳}
	 * @return 返回类型 String CommonJson格式
	 */
	public static String get6MonFirDay() {
		return getSomMonFirDayBySomDay(new Date(), FORMAT_YYYY_MM_DD, -5);
	}

	/**
	 * @Description: 返回给定日期之前的N个月的第一天
	 * @author chen.zhang
	 * @date 2016年12月15日 下午5:49:26
	 * @param {sign=签名,
	 *            timestamp=时间戳}
	 * @return 返回类型 String CommonJson格式
	 */
	public static String getSomMonFirDayBySomDay(Date date, String dateFormat, int num) {
		if (date == null)
			return null;
		if (StringUtils.isBlank(dateFormat))
			return null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			Calendar cal_1 = Calendar.getInstance();// 获取当前日期
			cal_1.setTime(date);
			cal_1.add(Calendar.MONTH, num);
			cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
			return sdf.format(cal_1.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	public static final String getDateStr(Date date) {
		df = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
		return df.format(date);
	}
}