package com.snakehero.Crawler;


import com.snakehero.appannie.service.LoginService;
import com.snakehero.appannie.util.HttpsRequest;
import com.snakehero.appannie.util.StringUtil;

import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.text.Element;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Crawler_V1 {
	
	public static void main(String[] args) throws IOException, ParseException,
	ClassNotFoundException, SQLException, InvalidKeyException,
	NoSuchAlgorithmException, InterruptedException, KeyManagementException {
		
		if(!LoginService.isLogin()){
			LoginService.login();
		}
		
		
  		String html = null;
  		Document doc = null;
  		String url = "https://www.appannie.com/apps/google-play/app/org.telegram.messenger/details/";
  		html = HttpsRequest.get(url, null, false,null);
  		if (!StringUtil.isEmpty(html)) {
  			doc = Jsoup.parse(html);
  		}
     	Elements els = doc.select("div.desc");
        System.out.println(els.get(0).text());
	}
	
}
