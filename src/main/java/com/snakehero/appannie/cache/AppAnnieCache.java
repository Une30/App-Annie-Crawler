package com.snakehero.appannie.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.snakehero.appannie.GoogleAppAnnie;
import com.snakehero.appannie.ddl.GoogleAnnieApp;
import com.snakehero.appannie.ddl.type.AnnieTop;
import com.snakehero.appannie.ddl.type.Country;
import com.snakehero.appannie.ddl.type.GoogleCategory;

public class AppAnnieCache {
	private static Cache<String, List<GoogleAnnieApp>> appCache ;
	/**
	 * @dujx
	 * app cache 首页应用<p>
	 * 有效时间 10分钟
	 * @return
	 */
	public static Cache<String, List<GoogleAnnieApp>> getFeaturedAppCache(){
		if(appCache == null){// 单重检查
			appCache = CacheBuilder.newBuilder().maximumSize(5000).expireAfterWrite(1, TimeUnit.HOURS).build(new CacheLoader<String, List<GoogleAnnieApp>>() {
				@Override
				public List<GoogleAnnieApp> load(String key) throws Exception {
					String [] keys = key.split("`");
					
					String countryCode = keys[0];
					String categoryName = keys[1];
					String board = keys[2];
					String date = keys[3];
					
					Country country = Country.getCountry(countryCode);
					GoogleCategory category = GoogleCategory.getCategory(categoryName);
					AnnieTop topBoard = AnnieTop.getBoard(board);
					
					GoogleAppAnnie annie = GoogleAppAnnie.build(country, category,date);
					List<GoogleAnnieApp> newAppList = annie.getTopList(topBoard, 500);
					return newAppList;
				}
			});
		}
		return appCache;
	}
}
