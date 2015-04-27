package com.snakehero.appannie.ddl;

import com.snakehero.appannie.ddl.type.AnnieTop;


public class GoogleAnnieApp {
	private String title;
	private String packageName;
	private AnnieTop billBoard;
	private String countryCode;
	
	private int rank;
	private String cp;
	private String category;
	private String rankChange;


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
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRankChange() {
		return rankChange;
	}

	public void setRankChange(String rankChange) {
		this.rankChange = rankChange;
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

	@Override
	public String toString() {
		return "GoogleAnnieApp [title=" + title + ", packageName=" + packageName + ", billBoard=" + billBoard
				+ ", countryCode=" + countryCode + ", rank=" + rank + ", cp=" + cp + ", category=" + category
				+ ", rankChange=" + rankChange + "]";
	}

}
