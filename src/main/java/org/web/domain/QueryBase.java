package org.web.domain;

import lombok.Getter;
import lombok.Setter;


/**
 * 类QueryBase.java的实现描述：所有查询DO的父类.一般封闭通用查询属性信息.
 *
 * @author Luyl 2015年9月29日 下午5:24:41
 */
@Setter
@Getter
public class QueryBase {

    public static final Integer FIRST_PAGE = 1;
    public static final Integer ONE_PAGE_SIZE = 1;
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final Integer MAX_PAGE_SIZE = 100;

    // 分页查询，第几页
    private Integer pageNum = null;
    // 分页查询，每页size
    private Integer pageSize = null;
    // 排序查询语句
    private String orderByClause = null;

    private Integer startNum = null;
    // 查询去重列
    private String distinct;

    public Integer getStartNum() {
        if ((startNum == null) && (pageNum != null && pageSize != null)) {
            if (pageNum < 0) {
                pageNum = 0;
            }
            if (pageSize < 0) {
                pageSize = 10;
            }
            return (pageNum - 1) * pageSize;
        }
        return startNum;
    }

    public void setFirstRecord() {
        this.pageNum = FIRST_PAGE;
        this.pageSize = ONE_PAGE_SIZE;
    }

    public void setAllRecords() {
        this.pageNum = FIRST_PAGE;
        this.pageSize = Integer.MAX_VALUE;
    }
}
