package clawer.data;

import java.util.ArrayList;
import java.util.List;

public class PageModel<T> {

	private long total;
	private int pageIndex;
	private int pageSize;
	private List<T> data=new ArrayList<>();
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	public int getSkip() {
		return (this.pageIndex-1)*this.pageSize;
	}
	
	@Override
	public String toString() {
		return "PageModel [total=" + total + ", pageIndex=" + pageIndex + ", pageSize=" + pageSize + ", data size=" + this.data.size()
				+ "]";
	}

	
	
}
