package com.snakehero.appannie;

import java.util.ArrayList;
import java.util.List;

import jodd.util.StringUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.snakehero.appannie.ddl.AnnieApp;
import com.snakehero.appannie.ddl.AnnieBillboard;
import com.snakehero.appannie.ddl.AnnieCategory;
import com.snakehero.appannie.ddl.AnnieCategoryConfig;
import com.snakehero.appannie.ddl.AnnieCountryConfig;
import com.snakehero.appannie.service.AnnieService;

/**
 * Help you to get AppAnnie Google BillBoard
 * 
 * @usage
 *   <p>
 *   GoogleAppAnnie annie = new GoogleAppAnnie("IN","game");
 *   <p>
 *   List&lt;AnnieApp&gt; list = annie.getTopFree(100);
 * 
 * @author dujx
 * @date 2015-3-19
 */
public class GoogleAppAnnie {
	private static final Logger logger = LoggerFactory.getLogger(GoogleAppAnnie.class);
	private final static String firstQuery = "#storestats-top-table tr";
	public final static String moreQuery = "tr";
	
	private String topUrl;
	private String moreUrl;

	private Document firstDoc;
	private Document moreDoc;

	private String countryCode;
	private AnnieCategory annieCategory;

	
	public static GoogleAppAnnie build(String countryCode){
		return build(countryCode,"ALL");
	}
	
	public static GoogleAppAnnie build(String countryCode,String categoryName){
		GoogleAppAnnie instance = new GoogleAppAnnie();
		instance.countryCode = countryCode.toUpperCase();
		String countryName = AnnieCountryConfig.getCountryNameByCode(countryCode);
		instance.annieCategory = AnnieCategoryConfig.getCategory(categoryName);
		instance.topUrl = AnnieService.getGoogleTopFirstUrl(countryName,instance.annieCategory);
		return instance;
	}

	private List<AnnieApp> subBillBoard( List<AnnieApp> appList,int size,int maxSize){
		if(size < 0 || size > maxSize){
			size = maxSize;
		}
		if(appList!=null && appList.size()>0){
			logger.info("annie app list size="+appList.size());
			try{
				appList = appList.subList(0, size);
			}catch(Exception e){
				logger.error("subBillBoard error, subSize={},listSize=",new Object[]{size,appList.size()});
			}
		}
		return appList;
	}
	
	private List<AnnieApp> getNewFreeFirst(int size) {
		List<AnnieApp> appList = firstGet(topUrl, AnnieBillboard.NEW_FREE);
		return subBillBoard(appList,size,100);
	}
	
	private List<AnnieApp> getNewFreeMore(int size) {
		List<AnnieApp> appList =  moreGet(moreUrl, AnnieBillboard.NEW_FREE);
		return subBillBoard(appList,size,400);
	}
	
	private List<AnnieApp> getTopFreeFirst(int size) {
		List<AnnieApp> appList = firstGet(topUrl, AnnieBillboard.TOP_FREE);
		return subBillBoard(appList,size,100);
	}
	
	private List<AnnieApp> getTopFreeMore(int size) {
		List<AnnieApp> appList =  moreGet(moreUrl, AnnieBillboard.TOP_FREE);
		return subBillBoard(appList,size,400);
	}
	
	/**
	 * Get first page data of given billBoard from appAnnie
	 * 
	 * @param url
	 * @param billBoard
	 * @return list of app
	 */
	private List<AnnieApp> firstGet(String url, AnnieBillboard billBoard) {
		List<AnnieApp> annieApps = new ArrayList<AnnieApp>();
		Document doc = null;
		try {
			if (this.firstDoc == null) {
				String html = AnnieService.httpGet(url,false);
				if (!StringUtil.isEmpty(html)) {
					doc = Jsoup.parse(html);
					this.firstDoc = doc;
				}
			} else {
				doc = this.firstDoc;
			}

			if (doc != null) {
				if(StringUtil.isEmpty(this.moreUrl)){
					this.moreUrl = AnnieService.getMoreUrl(doc);
				}
				Elements els = doc.select(firstQuery);
				annieApps = AnnieService.extractAnnieApp(els, billBoard, this.countryCode);
			}
		} catch (Exception e) {
			logger.error("extractAnnieApp error", e);
		}
		return annieApps;
	}

	/**
	 * Get more data of the given billBoard
	 * 
	 * @param url
	 * @param billBoard Type of AnnieBillboard
	 * @return list of app
	 */
	private List<AnnieApp> moreGet(String url, AnnieBillboard billBoard) {
		List<AnnieApp> annieApps = new ArrayList<AnnieApp>();
		if (!StringUtil.isEmpty(url)) {
			try {
				Document doc = null;
				if (this.moreDoc == null) {
					String html = AnnieService.httpGet(url,true);
					if (!StringUtil.isEmpty(html)) {
						String wrapHtml = "<html><body><table>%s</</body></html>";
						String _html = String.format(wrapHtml, html);
						doc = Jsoup.parse(_html);
						this.moreDoc = doc;
					}
				} else {
					doc = this.moreDoc;
				}

				if (doc != null) {
					Elements els = doc.select(moreQuery);
					annieApps = AnnieService.extractAnnieApp(els, billBoard, this.countryCode);
				}
			} catch (Exception e) {
				logger.error("extractAnnieApp ajax error", e);
			}
		}

		return annieApps;
	}
	
	
	public List<AnnieApp> getNewFree(int size) {
		if(size < 0 || size > 500){
			size = 500;
		}
		
		if(size <= 100){
			return getNewFreeFirst(size);
		}else{
			List<AnnieApp> appList = new ArrayList<AnnieApp>();
			appList.addAll(getNewFreeFirst(100));
			appList.addAll(getNewFreeMore(size-100));
			return appList;
		}
	}
	
	public List<AnnieApp> getTopFree(int size) {
		if(size < 0 || size > 500){
			size = 500;
		}
		
		if(size <= 100){
			return getTopFreeFirst(size);
		}else{
			List<AnnieApp> appList = new ArrayList<AnnieApp>();
			appList.addAll(getTopFreeFirst(100));
			appList.addAll(getTopFreeMore(size-100));
			return appList;
		}
	}
	
	public static void main(String[] args) {
		GoogleAppAnnie annie = GoogleAppAnnie.build("IN","app");
		List<AnnieApp> appList = annie.getNewFree(150);
		System.out.println(appList.size());
		for (AnnieApp app : appList) {
			System.out.println(app.getPackageName());
		}
	}
}
