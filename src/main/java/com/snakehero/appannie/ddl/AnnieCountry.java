package com.snakehero.appannie.ddl;

public class AnnieCountry {
	private String code;
	private String countryName;
	
	public AnnieCountry(String code,String countryName){
		this.code = code;
		this.countryName = countryName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}
