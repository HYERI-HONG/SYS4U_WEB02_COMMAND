package com.bory.company.dto;

import java.io.Serializable;

public class Pagination implements Serializable{

	private static final long serialVersionUID = 2740001415930612399L;
	
	private final int RowsPerPage;
	private final int blockSize;
	
	private int count;
	private int beginRow;
	private int endRow;
	private int beginPage;
	private int endPage;
	private int lastPage;
	private int prevBlock;
	private int nextBlock;
	
	private boolean existPrev;
	private boolean existNext;
	
	public Pagination(int RowsPerPage, int blockSize) {
		this.RowsPerPage = RowsPerPage;
		this.blockSize = blockSize;
	}

	public void calcPage(int pageNum, int count) {	
		
		this.count = count;
		
		this.lastPage = count%RowsPerPage>0? count/RowsPerPage+1:count/RowsPerPage;
		this.beginPage = pageNum-((pageNum-1)%blockSize);
		this.endPage = ((beginPage+blockSize-1)>lastPage)? lastPage:beginPage+blockSize-1;
		
		this.beginRow = (RowsPerPage*pageNum)-(RowsPerPage-1);
		this.endRow = pageNum ==lastPage? count:RowsPerPage*pageNum;
		
	    this.prevBlock = beginPage - blockSize;
        this.nextBlock = beginPage + blockSize;
        this.existPrev = (prevBlock>=0);
        this.existNext = (nextBlock<=lastPage);
        
	}
	
	public int getCount() {
		return count;
	}

	public int getLastPage() {
		return lastPage;
	}
	
	public int getBeginPage() {
		return beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getBeginRow() {
		return beginRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public boolean isExistPrev() {
		return existPrev;
	}

	public boolean isExistNext() {
		return existNext;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
