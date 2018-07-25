package com.system.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 李景然
 * @Date: 2018/7/12 09:23
 * @Description:
 */
public class PagingResult {
    private int total;
    private List<?> rows=new ArrayList();

    public PagingResult(){}

    public PagingResult(int totalCount,List<?> list){
        this.total =totalCount;
        this.rows=list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
