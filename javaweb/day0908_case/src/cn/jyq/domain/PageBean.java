package cn.jyq.domain;

import java.util.List;

//分页对象
public class PageBean<T> {

    private int totalCount; //总记录数
    private int totalPage; //总页数
    private int rows; //每页要显示的数据数
    private int currentPage; //当前页码
    private List<T> list; //当前页的数据

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(512);
        sb.append("PageBean [");
        sb.append(super.toString());
        sb.append("\n    totalCount=").append(this.totalCount);
        sb.append("\n    totalPage=").append(this.totalPage);
        sb.append("\n    rows=").append(this.rows);
        sb.append("\n    currentPage=").append(this.currentPage);
        sb.append("\n    list=").append(this.list);
        sb.append("\n]");
        return sb.toString();
    }
}
