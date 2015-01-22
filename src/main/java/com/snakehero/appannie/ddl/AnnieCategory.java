package com.snakehero.appannie.ddl;

public class AnnieCategory {
	private String title;
	private String order;
	private String tag;
	
	public AnnieCategory(String title,String order,String tag){
		this.title = title;
		this.order = order;
		this.tag = tag;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
