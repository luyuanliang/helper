package org.web.domain;

import lombok.Getter;
import lombok.Setter;
import org.web.helper.StringHelper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author luyl
 * 用于封装DUBBO接口,封装BIZ层的结果.
 */
@Setter
@Getter
public class ResultDO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    // 终端用户的提示信息，不推荐使用
    private String message;

    // 结果描述，用于排查问题. @see ServiceException#description属性
    private String description;
    // code. @see ServiceException#code属性
    private String code;

    private boolean success = true; // 执行是否成功
    // 用于描述返回记录总数，如果返回单条记录，例如返回的module类型是一个对象，而非集合的场合，会自动设置1，详情见setModule方法。
    private long totalCount = 0;
    private T module; // 实际的返回结果
    // 某些情况下，特殊 格外的参数返回
    private Map<String, Object> extendsModule = null;

    public ResultDO() {
    }

    public ResultDO(boolean success) {
        this.success = success;
        if (success) {
            this.message = "操作成功";
        }
    }

    public ResultDO(boolean success, String code, String message, String description) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public void setModel(String key, Object model) {
        getExtendsModule().put(key, model);
    }

    public Map<String, Object> getExtendsModule() {
        if (extendsModule == null) {
            extendsModule = new HashMap<String, Object>();
        }
        return extendsModule;
    }

    public boolean isEmpty() {
        if (!this.success) {
            return true;
        }
        return StringHelper.isEmpty(getModule());
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public void setModule(T module) {
        this.module = module;
        if ((module instanceof List) || (module instanceof Set) || (module instanceof Map)) {
            // Do nothing.
        } else {
            setTotalCount(1L);
        }
    }

}
