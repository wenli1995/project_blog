package com.java.model;

public class PagingInfo {
	private int whichPage;   //第几页
	private int size;        //页面大小
	
	public PagingInfo(){
		
	}
	
	public PagingInfo(int whichPage, int size) {
		this.whichPage = whichPage;
		this.size = size;
	}
	
	/**
	 * @return the whichPage
	 */
	
	public int getWhichPage() {
		return whichPage;
	}

	/**
	 * @param whichPage the whichPage to set
	 */
	public void setWhichPage(int whichPage) {
		this.whichPage = whichPage;
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	
}
