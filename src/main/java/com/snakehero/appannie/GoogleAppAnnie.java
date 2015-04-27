package com.snakehero.appannie;

import java.util.ArrayList;
import java.util.List;

import jodd.util.StringUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.snakehero.appannie.ddl.GoogleAnnieApp;
import com.snakehero.appannie.ddl.type.AnnieTop;
import com.snakehero.appannie.ddl.type.Country;
import com.snakehero.appannie.ddl.type.GoogleCategory;
import com.snakehero.appannie.service.AnnieService;
import com.snakehero.appannie.util.HttpsRequest;

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
	private static Logger testLogger = LoggerFactory.getLogger("test.log");
	private static final Logger logger = LoggerFactory.getLogger(GoogleAppAnnie.class);
	private final static String firstQuery = "#storestats-top-table tr";
	public final static String moreQuery = "tr";
	
	private String topUrl;
	private String moreUrl;

	private Document firstDoc;
	private Document moreDoc;

	private Country country;
	private GoogleCategory category;

	public static GoogleAppAnnie build(String countryCode){
		Country country = Country.getCountry(countryCode);
		
		return build(country,GoogleCategory.ALL);
	}
	
	public static GoogleAppAnnie build(String countryCode,String categoryName){
		Country country = Country.getCountry(countryCode);
		GoogleCategory category = GoogleCategory.getCategory(categoryName);
		
 		return build(country,category);
 	}
	
	public static GoogleAppAnnie build(Country annieCountry){
		return build(annieCountry,GoogleCategory.ALL);
	}
	
	public static GoogleAppAnnie build(Country annieCountry,GoogleCategory category){
		GoogleAppAnnie instance = new GoogleAppAnnie();
		instance.country = annieCountry;
		instance.category = category;
		instance.topUrl = AnnieService.getGoogleTopFirstUrl(instance.country,instance.category);
		return instance;
	}

	private List<GoogleAnnieApp> subBillBoard( List<GoogleAnnieApp> appList,int size,int maxSize){
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
	
	private List<GoogleAnnieApp> getFirst(AnnieTop topName,int size) {
		List<GoogleAnnieApp> appList = firstGet(topUrl, topName);
		return subBillBoard(appList,size,100);
	}
	
	private List<GoogleAnnieApp> getMore(AnnieTop topName,int size) {
		List<GoogleAnnieApp> appList =  moreGet(moreUrl, topName);
		return subBillBoard(appList,size,400);
	}
	
	/**
	 * Get first page data of given billBoard from appAnnie
	 * 
	 * @param url
	 * @param billBoard
	 * @return list of app
	 */
	private List<GoogleAnnieApp> firstGet(String url, AnnieTop billBoard) {
		List<GoogleAnnieApp> annieApps = new ArrayList<GoogleAnnieApp>();
		Document doc = null;
		try {
			if (this.firstDoc == null) {
				String html = AnnieService.httpGet(url,false);
				if (!StringUtil.isEmpty(html)) {
					//testLogger.info(html+"\r\n-----------------------------\r\n");
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
				annieApps = AnnieService.extractAnnieApp(els, billBoard, this.country.getCountryCode(),this.category.getTag(),1);
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
	private List<GoogleAnnieApp> moreGet(String url, AnnieTop billBoard) {
		List<GoogleAnnieApp> annieApps = new ArrayList<GoogleAnnieApp>();
		if (!StringUtil.isEmpty(url)) {
			try {
				Document doc = null;
				if (this.moreDoc == null) {
					String html = HttpsRequest.get(url,null, true);
					if (!StringUtil.isEmpty(html)) {
						//testLogger.info(html);
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
					annieApps = AnnieService.extractAnnieApp(els, billBoard, this.country.getCountryCode(),this.category.getTag(),101);
				}
			} catch (Exception e) {
				logger.error("extractAnnieApp ajax error", e);
			}
		}

		return annieApps;
	}
	
	public List<GoogleAnnieApp> getTopList(AnnieTop billboard, int size) {
		if(size < 0 || size > 500){
			size = 500;
		}
		
		if(size <= 100){
			return getFirst(billboard,size);
		}else{
			List<GoogleAnnieApp> appList = new ArrayList<GoogleAnnieApp>();
			appList.addAll(getFirst(billboard,100));
			appList.addAll(getMore(billboard,size-100));
			return appList;
		}
	}
	
	
	public static void main(String[] args) {
		GoogleAppAnnie annie = GoogleAppAnnie.build(Country.SAUDI_ARABIA,GoogleCategory.ALL);
		List<GoogleAnnieApp> appList = annie.getTopList(AnnieTop.FREE, 150);
		System.out.println(appList.size());
		for (GoogleAnnieApp app : appList) {
			System.out.println(app);
		}
	}
}
