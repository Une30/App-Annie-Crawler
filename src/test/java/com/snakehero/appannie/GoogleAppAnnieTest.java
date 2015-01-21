package com.snakehero.appannie;

import java.util.List;

import org.junit.Test;

import com.snakehero.appannie.ddl.AnnieApp;

public class GoogleAppAnnieTest {
	@Test
	public void testGoogleAppAnnie(){
		List<AnnieApp> appList = new GoogleAppAnnie("IN").getAllNewFree();
		System.out.println(appList.size());
		for (AnnieApp app : appList) {
			System.out.println(app.getPackageName());
		}
	}
}
