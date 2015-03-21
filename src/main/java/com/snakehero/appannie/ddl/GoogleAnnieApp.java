package com.snakehero.appannie.ddl;

import com.snakehero.appannie.ddl.type.AnnieTop;


public class GoogleAnnieApp {
	private String title;
	private String packageName;
	private AnnieTop billBoard;
	private String countryCode;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public AnnieTop getBillBoard() {
		return billBoard;
	}

	public void setBillBoard(AnnieTop billBoard) {
		this.billBoard = billBoard;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		GoogleAnnieApp d = (GoogleAnnieApp) obj;
		if (packageName.equals(d.packageName)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (this.packageName).hashCode();
	}
}
