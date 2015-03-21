package com.snakehero.appannie;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.snakehero.appannie.ddl.GoogleAnnieApp;
import com.snakehero.appannie.ddl.type.AnnieTop;
import com.snakehero.appannie.ddl.type.Country;
import com.snakehero.appannie.ddl.type.GoogleCategory;

public class GoogleAppAnnieTest {
	@Test
	public void testGoogleAppAnnie(){
		List<GoogleAnnieApp> appList = GoogleAppAnnie.build(Country.INDIA,GoogleCategory.ALL).getTopList(AnnieTop.FREE, 150);
		System.out.println(appList.size());
		for (GoogleAnnieApp app : appList) {
			System.out.println(app.getPackageName());
		}
		Assert.assertNotNull(appList);
	}
}
