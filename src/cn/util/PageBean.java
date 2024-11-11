package cn.util;

import java.io.Serializable;

public class PageBean implements Serializable{
	private int pageNo=1;//当前页码
	private int pageSize=10;//每页显示
	private int totalCount;//总记录条数
	
	private int nextPage;//下一页
	private int prevPage;//上一页
	private int totalPage;//总页数
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getNextPage() {
		if(getPageNo()<getTotalPage()){
			return getPageNo()+1;
		}else{
			return getTotalPage();
		}
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPrevPage() {
		if(getPageNo()>1){
			return getPageNo()-1;
		}else{
			return 1;
		}
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getTotalPage() {
		//计算总页数
		if(getTotalCount()%getPageSize()==0){
			return getTotalCount()/getPageSize();
		}else{
			return getTotalCount()/getPageSize()+1;
		}
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
	
	
}
