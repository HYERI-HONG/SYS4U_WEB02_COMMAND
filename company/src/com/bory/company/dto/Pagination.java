package com.bory.company.dto;

import java.io.Serializable;

public class Pagination implements Serializable{

	private static final long serialVersionUID = 2740001415930612399L;
	
	private int count, pageRow, blockSize,lastPage,beginPage, 
	endPage, beginRow,endRow,prevBlock,nextBlock;
	private boolean existPrev,existNext;

	public void calcPage(int pageNum, int count) {	
		this.count = count;
		this.pageRow = 3;
		this.blockSize = 5;

		this.lastPage = count%pageRow>0? count/pageRow+1:count/pageRow;
		this.beginPage = pageNum-((pageNum-1)%blockSize);
		this.endPage = ((beginPage+blockSize-1)>lastPage)? lastPage:beginPage+blockSize-1;
		
		this.beginRow = (pageRow*pageNum)-(pageRow-1);
		this.endRow = pageNum ==lastPage? pageRow*pageNum-pageRow+(count%pageRow):pageRow*pageNum;
		
	    this.prevBlock = beginPage - blockSize;
        this.nextBlock = beginPage + blockSize;
        this.existPrev = (prevBlock>=0);
        this.existNext = (nextBlock<=lastPage);
        
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageRow() {
		return pageRow;
	}

	public void setPageRow(int pageRow) {
		this.pageRow = pageRow;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getBeginRow() {
		return beginRow;
	}

	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}

	public boolean isExistPrev() {
		return existPrev;
	}

	public void setExistPrev(boolean existPrev) {
		this.existPrev = existPrev;
	}

	public boolean isExistNext() {
		return existNext;
	}

	public void setExistNext(boolean existNext) {
		this.existNext = existNext;
	}
	
}
