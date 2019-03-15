package com.bory.company.dto;

import java.io.Serializable;

public class Pagination implements Serializable{

	private static final long serialVersionUID = 2740001415930612399L;
	
	private static final int PAGE_ROW = 3;
	private static final int BLOCK_SIZE = 5;
	
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

	public void calcPage(int pageNum, int count) {	
		
		this.count = count;
		
		this.lastPage = count%PAGE_ROW>0? count/PAGE_ROW+1:count/PAGE_ROW;
		this.beginPage = pageNum-((pageNum-1)%BLOCK_SIZE);
		this.endPage = ((beginPage+BLOCK_SIZE-1)>lastPage)? lastPage:beginPage+BLOCK_SIZE-1;
		
		this.beginRow = (PAGE_ROW*pageNum)-(PAGE_ROW-1);
		this.endRow = pageNum ==lastPage? PAGE_ROW*pageNum-PAGE_ROW+(count%PAGE_ROW):PAGE_ROW*pageNum;
		
	    this.prevBlock = beginPage - BLOCK_SIZE;
        this.nextBlock = beginPage + BLOCK_SIZE;
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
	
	
}
