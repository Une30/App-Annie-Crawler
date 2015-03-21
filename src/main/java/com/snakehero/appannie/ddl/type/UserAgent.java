package com.snakehero.appannie.ddl.type;

public enum UserAgent {
	Chrome("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.52 Safari/537.17"),
	Firefox("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0"),
	Google("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)"),
	Safari("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_6; en-US) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
	private String ua;

	private UserAgent(String ua) {
		this.ua = ua;
	}

	public String getUa() {
		return ua;
	}

	public static String getDefault() {
		return Chrome.getUa();
	}

	public static String getRandomUserAgent() {
		return values()[(int) (Math.random() * values().length)].getUa();
	}
	
	public static String getOtherUserAgent() {
		int i =(int) (Math.random() * values().length);
		if(i == 0) i =1;
		return values()[i].getUa();
	}

	public static void main(String[] args) {
		System.out.println(getRandomUserAgent());
	}
}
