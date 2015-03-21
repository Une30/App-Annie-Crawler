package com.snakehero.appannie.ddl.type;


public enum Country {
	AUSTRALIA("AU","australia"),
	CANADA("CA","canada"),
	CHINA("CN","china"),
	GERMANY("DE","germany"),
	SPAIN("ES","spain"),
	FRANCE("FR","france"),
	UNITED_KINGDOM("UK","united_kingdom"),
	ITALY("IT","italy"),
	JAPAN("JP","japan"),
	UNITED_STATES("US","united_states"),
	BELGIUM("BE","belgium"),
	SWITZERLAND("CH","switzerland"),
	CHILE("CL","chile"),
	SOUTH_AFRICA("ZA","south_africa"),
	VIETNAM("VN","vietnam"),
	HONG_KONG("HK","hong_kong"),
	ARGENTINA("AR","argentina"),
	BRAZIL("BR","brazil"),
	INDIA("IN","india"),
	FINLAND("FI","finland"),
	INDONESIA("ID","indonesia"),
	RUSSIA("RU","russia"),
	NETHERLANDS("NL","netherlands"),
	MALAYSIA("MY","malaysia"),
	TURKEY("TR","turkey"),
	MEXICO("MX","mexico"),
	SOUTH_KOREA("KR","south_korea"),
	POLAND("PL","poland"),
	THAILAND("TH","thailand"),
	TAIWAN("TW","taiwan"),
	PHILIPPINES("PH","philippines"),
	SINGAPORE("SG","singapore"),
	EGYPT("EG","egypt"),
	SWEDEN("SE","sweden"),
	AUSTRIA("AT","austria"),
	CZECH_REPUBLIC("CZ","czech_republic"),
	HUNGARY("HU","hungary"),
	DENMARK("DK","denmark"),
	IRELAND("IE","ireland"),
	ISRAEL("IL","israel"),
	NEW_ZEALAND("NZ","new_zealand"),
	NORWAY("NO","norway"),
	PORTUGAL("PT","portugal"),
	ROMANIA("RO","romania"),
	SLOVAKIA("SK","slovakia"),
	GREECE("GR","greece"),
	BULGARIA("BG","bulgaria"),
	UKRAINE("UA","ukraine"),
	UNITED_ARAB_EMIRATES("AE","united_arab_emirates"),
	KUWAIT("KW","kuwait"),
	SAUDI_ARABIA("SA","saudi_arabia");
	
	private final String countryName;
	private final String countryCode;

	Country(String countryCode,String countryName) {
		this.countryName= countryName;
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}
	
	public static Country getCountry(String countryCode){
		Country[] countrys = Country.values();
		for(Country c:countrys){
			if(c.getCountryCode().equalsIgnoreCase(countryCode)){
				return c;
			}
		}
		return null;
	}
}
