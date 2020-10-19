package com.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageNavi {
	
	private static final Logger logger = LoggerFactory.getLogger(PageNavi.class);
	/** 첫번째 페이지 번호 **/
	private int firstPage; 
	/** 마지막 페이지 번호 **/
	private int lastPage; 
	/** [이전] 링크 **/
	private int prevLink;
	/** [다음] 링크 **/
	private int nextLink;
	/** 목록을 구할때 쓰이는 ROWNUM 시작 **/
	private int startRecord;
	/** 목록을 구할때 쓰이는 ROWNUM 끝 **/
	private int endRecord; // 
	/** 목록에서 위에서 순서대로 붙여지는 번호 **/
	private int listNo;
	/** 첫번쩨 페이지 번호부터 시작하여 1씩 증가하여 마지막 페이지번호까지 int[] 배열 **/
	private int[] pageLinks;
	private int page_no;
	private int Endpage;
	private int totalCnt = 0;
	
	public PageNavi(int totalRecord, int page_no, int numPerPage, int pagePerBlock) {
		
		int totalPage = ((totalRecord % numPerPage) == 0) ? 
			totalRecord / numPerPage : totalRecord / numPerPage + 1;
		int totalBlock = ((totalPage % pagePerBlock) == 0) ? 
			totalPage / pagePerBlock : totalPage / pagePerBlock + 1;
		int block = ((page_no % pagePerBlock) == 0) ? 
				page_no / pagePerBlock : page_no / pagePerBlock + 1;
		this.firstPage = (block - 1) * pagePerBlock + 1;
		this.lastPage =(block * pagePerBlock);
		this.Endpage = (int) Math.ceil(((float) totalRecord/ (float) numPerPage) );
		logger.debug("=============>Endpage [" + Endpage + "] totalRecord [" + totalRecord+ "]");
		if (block >= totalBlock) {
			this.lastPage = totalPage;
		}
		pageLinks = makeArray(firstPage, lastPage);
		this.page_no = page_no;
		if (block > 1) {
			this.prevLink = firstPage - 1;
		}
		if (block < totalBlock) {
			this.nextLink = lastPage + 1;
		}
		this.listNo = totalRecord - (page_no - 1) * numPerPage;
		this.startRecord = (page_no - 1) * numPerPage + 1;
		this.endRecord = startRecord + numPerPage - 1;
		
		this.totalCnt = totalRecord;
	}
	
	public PageNavi() {
		
	}
	
	
	private int[] makeArray(int first, int last) {
		
		logger.info("=============>first [" + first + "] last [" + last+ "]");
		
		int size = last - first + 1;
		logger.info("=============>size [" + size + "] last [" + last+ "]");

		if(size < 0) size = 0;
		
		int[] ret = new int[size]; 
		
		
		for (int i = 0; i < size; i++) {
			ret[i] = first++;
		}
		
		return ret;
	}

	
	
	public int getFirstPage() {
		return firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public int getPrevLink() {
		return prevLink;
	}

	public int getNextLink() {
		return nextLink;
	}

	public int getStartRecord() {
		return startRecord;
	}

	public int getEndRecord() {
		return endRecord;
	}

	public int getListNo() {
		return listNo;
	}

	public int[] getPageLinks() {
		return pageLinks;
	}

	/**
	 * @return the page_no
	 */
	public int getPage_no() {
		return page_no;
	}

	public int getEndpage() {
		return Endpage;
	}

	/**
	 * @return the totalCnt
	 */
	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
}

