package com.snakehero.appannie.service;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.snakehero.appannie.GoogleAppAnnie;
import com.snakehero.appannie.ddl.GoogleAnnieApp;
import com.snakehero.appannie.ddl.type.AnnieTop;
import com.snakehero.appannie.ddl.type.Country;
import com.snakehero.appannie.ddl.type.GoogleCategory;
import com.snakehero.appannie.util.CookieUtil;
import com.snakehero.appannie.util.HttpsRequest;

public class LoginService {
	private static Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	public static void login(){
		String preLoginUrl = "https://www.appannie.com/account/login/?_ref=header";
		String csrfmiddlewaretoken = "";
		try {
			String loginHtml = HttpsRequest.get(preLoginUrl, null, false,null);
			System.out.println(loginHtml);
			 Document doc = Jsoup.parse(loginHtml);
			 Element csrfEl = doc.select("input[name=csrfmiddlewaretoken]").first();
			 System.out.println(csrfEl);
			 csrfmiddlewaretoken = csrfEl.val();
		} catch (Exception e) {
			e.printStackTrace();
		}				
		
		String url  = "https://www.appannie.com/account/login/";
		Map<String,String> params = new HashMap<String,String>();
		params.put("username","snakehero1@126.com");
		params.put("password","thisatestaccount1");
		params.put("remember_user","on");
		params.put("csrfmiddlewaretoken",csrfmiddlewaretoken);
		
		try {
			System.out.println("islogin"+isLogin());
			HttpsRequest.post(url, params, false,null);
			System.out.println("islogin"+isLogin());
		} catch (Exception e) {
			logger.error("login error:",e);
		} 
	}
	
	public static boolean isLogin(){
		boolean isLogin = false;
		CookieManager cookieManager = CookieUtil.getCookieManager();
		CookieStore cookieStore = cookieManager.getCookieStore();
		try {
			List<HttpCookie> cookies = cookieStore.get(new URI("https://www.appannie.com/"));
			for(HttpCookie cookie:cookies){
				if(cookie.getName().equalsIgnoreCase("aa_user_token")){
					isLogin =  true;
				}
			}
		} catch (URISyntaxException e) {
			logger.error("isLogin error:",e);
		}
		return isLogin;
	}
	
	public static void main(String[] args) {
		login();
	}
}
