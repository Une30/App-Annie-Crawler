package com.snakehero.appannie.ddl;

import java.util.HashMap;
import java.util.Map;

public class AnnieCountryConfig {
	private static Map<String,String> ANNIE_COUNTRY_CODE_MAP = new HashMap<String,String>();
	static{
		ANNIE_COUNTRY_CODE_MAP.put("AU","australia");
		ANNIE_COUNTRY_CODE_MAP.put("CA","canada");
		ANNIE_COUNTRY_CODE_MAP.put("CN","china");
		ANNIE_COUNTRY_CODE_MAP.put("DE","germany");
		ANNIE_COUNTRY_CODE_MAP.put("ES","spain");
		ANNIE_COUNTRY_CODE_MAP.put("FR","france");
		ANNIE_COUNTRY_CODE_MAP.put("UK","united-kingdom");
		ANNIE_COUNTRY_CODE_MAP.put("IT","italy");
		ANNIE_COUNTRY_CODE_MAP.put("JP","japan");
		ANNIE_COUNTRY_CODE_MAP.put("US","united-states");
		ANNIE_COUNTRY_CODE_MAP.put("BE","belgium");
		ANNIE_COUNTRY_CODE_MAP.put("CH","switzerland");
		ANNIE_COUNTRY_CODE_MAP.put("CL","chile");
		ANNIE_COUNTRY_CODE_MAP.put("ZA","south-africa");
		ANNIE_COUNTRY_CODE_MAP.put("VN","vietnam");
		ANNIE_COUNTRY_CODE_MAP.put("HK","hong-kong");
		ANNIE_COUNTRY_CODE_MAP.put("AR","argentina");
		ANNIE_COUNTRY_CODE_MAP.put("BR","brazil");
		ANNIE_COUNTRY_CODE_MAP.put("IN","india");
		ANNIE_COUNTRY_CODE_MAP.put("FI","finland");
		ANNIE_COUNTRY_CODE_MAP.put("ID","indonesia");
		ANNIE_COUNTRY_CODE_MAP.put("RU","russia");
		ANNIE_COUNTRY_CODE_MAP.put("NL","netherlands");
		ANNIE_COUNTRY_CODE_MAP.put("MY","malaysia");
		ANNIE_COUNTRY_CODE_MAP.put("TR","turkey");
		ANNIE_COUNTRY_CODE_MAP.put("MX","mexico");
		ANNIE_COUNTRY_CODE_MAP.put("KR","south-korea");
		ANNIE_COUNTRY_CODE_MAP.put("PL","poland");
		ANNIE_COUNTRY_CODE_MAP.put("TH","thailand");
		ANNIE_COUNTRY_CODE_MAP.put("TW","taiwan");
		ANNIE_COUNTRY_CODE_MAP.put("PH","philippines");
		ANNIE_COUNTRY_CODE_MAP.put("SG","singapore");
		ANNIE_COUNTRY_CODE_MAP.put("EG","egypt");
		ANNIE_COUNTRY_CODE_MAP.put("SE","sweden");
		ANNIE_COUNTRY_CODE_MAP.put("AT","austria");
		ANNIE_COUNTRY_CODE_MAP.put("CZ","czech-republic");
		ANNIE_COUNTRY_CODE_MAP.put("HU","hungary");
		ANNIE_COUNTRY_CODE_MAP.put("DK","denmark");
		ANNIE_COUNTRY_CODE_MAP.put("IE","ireland");
		ANNIE_COUNTRY_CODE_MAP.put("IL","israel");
		ANNIE_COUNTRY_CODE_MAP.put("NZ","new-zealand");
		ANNIE_COUNTRY_CODE_MAP.put("NO","norway");
		ANNIE_COUNTRY_CODE_MAP.put("PT","portugal");
		ANNIE_COUNTRY_CODE_MAP.put("RO","romania");
		ANNIE_COUNTRY_CODE_MAP.put("SK","slovakia");
		ANNIE_COUNTRY_CODE_MAP.put("GR","greece");
		ANNIE_COUNTRY_CODE_MAP.put("BG","bulgaria");
		ANNIE_COUNTRY_CODE_MAP.put("UA","ukraine");
		ANNIE_COUNTRY_CODE_MAP.put("AE","united-arab-emirates");
		ANNIE_COUNTRY_CODE_MAP.put("KW","kuwait");
		ANNIE_COUNTRY_CODE_MAP.put("SA","saudi-arabia");
	}
	
	public static String getCountryNameByCode(String code){
		return ANNIE_COUNTRY_CODE_MAP.get(code);
	}
	
}
