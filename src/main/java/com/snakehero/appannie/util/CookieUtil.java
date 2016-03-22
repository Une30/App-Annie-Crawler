package com.snakehero.appannie.util;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

public class CookieUtil {
	private volatile static CookieManager cookieManager = new CookieManager();
	
	static{
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
	}
	
	public static CookieManager getCookieManager(){
		return cookieManager;
	}
}
