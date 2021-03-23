package org.web.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ListHelper {
    public ListHelper() {
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> List<T> transStr2List(String str, String seperate, Class<T> clazz) {
        List list = null;
        if (StringUtils.isNotBlank(str)) {
            list = new ArrayList();
            String[] array = str.split(seperate);

            for (int i = 0; i < array.length; ++i) {
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

        return list != null && list.size() != 0 ? list : null;
    }

    public static boolean isEmpty(List list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }
    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }

}
