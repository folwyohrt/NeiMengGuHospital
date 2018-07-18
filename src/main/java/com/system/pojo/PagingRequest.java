package com.system.pojo;

/**
 * @Auther: 李景然
 * @Date: 2018/7/11 19:59
 * @Description:
 */
public class PagingRequest {
    private Integer pageNum;
    private Integer pageSize;
    private String sort;
    private String sortOrder;

    public PagingRequest(){}

    public PagingRequest(int pageNum,int pageSize,String sort,String sortOrder){
        this.pageNum=pageNum;
        this.pageSize=pageSize;
        this.sort=sort;
        this.sortOrder=sortOrder;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
