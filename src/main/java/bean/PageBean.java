package bean;

import com.opensymphony.xwork2.Action;

import java.util.List;

public class PageBean<T> {
    /**
     *当前页号
     */
    private long currentPage;
    /**
     * 每页数据条数
     */
    private long pageSize;
    /**
     * 总记录数
     */
    private long totalRecords;
    /**
     * 总面数
     */
    private long totalPageNo;
    /**
     * 数据
     */
    private List<T> data;
    /**
     * 得到上一页页号
     */
    public long getPreviewPageNo() {
        return currentPage <= 1 ? 1 : currentPage - 1;
    }
    /**
     * 得到下一页页号
     */
    public long getNextPageNo() {
        return currentPage >= totalPageNo ? totalPageNo : currentPage + 1;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public long getTotalPageNo() {
        return totalPageNo;
    }

    public void setTotalPageNo(long totalPageNo) {
        this.totalPageNo = totalPageNo;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
    public String execute()throws Exception{
        return Action.SUCCESS;
    }
}
