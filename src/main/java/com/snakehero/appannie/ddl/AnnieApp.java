package com.snakehero.appannie.ddl;


public class AnnieApp {
	private String title;
	private String packageName;
	private AnnieBillboard billBoard;
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

	public AnnieBillboard getBillBoard() {
		return billBoard;
	}

	public void setBillBoard(AnnieBillboard billBoard) {
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
		AnnieApp d = (AnnieApp) obj;
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
