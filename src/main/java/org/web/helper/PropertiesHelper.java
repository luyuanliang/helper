package org.web.helper;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class PropertiesHelper {

    private static Logger logger = Logger.getLogger(PropertiesHelper.class);

    public static String getString(ResourceBundle resourceBundle, String key, String defaultValue) {
        try {
            String value = resourceBundle.getString(key);
            if (StringUtils.isNotBlank(value)) {
                return value;
            } else {
                logger.warn(key + "'s value is null ");
            }
        } catch (Exception e) {
            logger.warn(key + " is not setted.");
        }
        return defaultValue;
    }


    public static Boolean getBoolean(ResourceBundle resourceBundle, String key, boolean defaultValue) {
        try {
            String value = resourceBundle.getString(key);
            if (StringUtils.isNotBlank(value)) {
                return Boolean.valueOf(value);
            } else {
                logger.warn(key + "'s value is null ");
            }
        } catch (Exception e) {
            logger.warn(key + " is not setted.");
        }
        return defaultValue;
    }

    public static Long getLong(ResourceBundle resourceBundle, String key, Long defaultValue) {
        try {
            String value = resourceBundle.getString(key);
            if (StringUtils.isNotBlank(value)) {
                return Long.valueOf(value);
            } else {
                logger.warn(key + "'s value is null ");
            }
        } catch (Exception e) {
            logger.warn(key + " is not setted.");
        }
        return defaultValue;
    }

    public static Integer getInt(ResourceBundle resourceBundle, String key, Integer defaultValue) {
        try {
            String value = resourceBundle.getString(key);
            if (StringUtils.isNotBlank(value)) {
                return Integer.valueOf(value);
            } else {
                logger.warn(key + "'s value is null ");
            }
        } catch (Exception e) {
            logger.warn(key + " is not setted.");
        }
        return defaultValue;
    }

    /**
     * 读取指定路径下的资源文件
     *
     * @param bundleName properties文件路径
     * @param key
     * @return value
     */
    public static String getString(String bundleName, String key) throws MissingResourceException {
        return getResourceBundle(bundleName, null).getString(key);
    }


    public static ResourceBundle getResourceBundle(String bundleName, String baseName) throws MissingResourceException {
        Locale locale = null;
        if (StringUtils.isEmpty(baseName)) {
            locale = Locale.getDefault();
        } else {
            locale = new Locale(baseName);
        }
        return ResourceBundle.getBundle(bundleName, locale);
    }


}
