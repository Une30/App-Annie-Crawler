package com.snakehero.appannie;

import java.util.ArrayList;
import java.util.List;

import jodd.util.StringUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.snakehero.appannie.ddl.AnnieApp;
import com.snakehero.appannie.ddl.AnnieBillboard;
import com.snakehero.appannie.ddl.AnnieCountryConfig;
import com.snakehero.appannie.service.AnnieService;


/**
 * Help you to get AppAnnie Google BillBoard
 * 
 * @usage
 *   <p>
 *   GoogleAppAnnie annie = new GoogleAppAnnie("IN");
 *   <p>
 *   List&lt;AnnieApp&gt; list = annie.getAllTopFree();
 * 
 * @author dujx
 * @date 2015-1-19
 */
public class GoogleAppAnnie {
	private final static String firstQuery = "#storestats-top-table tr";
	public final static String moreQuery = "tr";
	
	private String topUrl;
	private String moreUrl;

	private Document firstDoc;
	private Document moreDoc;

	private String countryCode;

	public GoogleAppAnnie(String countryCode) {
		this.countryCode = countryCode;
		String countryName = AnnieCountryConfig.getCountryNameByCode(countryCode);
		this.topUrl = AnnieService.getGoogleTopFirstUrl(countryName);
	}

	public List<AnnieApp> getTopFree100() {
		return firstGet(topUrl, AnnieBillboard.TOP_FREE);
	}

	public List<AnnieApp> getTopFreeMore() {
		return moreGet(moreUrl, AnnieBillboard.TOP_FREE);
	}

	public List<AnnieApp> getAllTopFree() {
		List<AnnieApp> appList = new ArrayList<AnnieApp>();
		appList.addAll(getTopFree100());
		appList.addAll(getTopFreeMore());
		return appList;
	}

	public List<AnnieApp> getAllNewFree() {
		List<AnnieApp> appList = new ArrayList<AnnieApp>();
		appList.addAll(getNewFree100());
		appList.addAll(getNewFreeMore());
		return appList;
	}

	public List<AnnieApp> getNewFree100() {
		return firstGet(topUrl, AnnieBillboard.NEW_FREE);
	}

	public List<AnnieApp> getNewFreeMore() {
		return moreGet(moreUrl, AnnieBillboard.NEW_FREE);
	}

	/**
	 * Get first page data of given billBoard from appAnnie
	 * 
	 * @param url
	 * @param billBoard
	 * @return list of app
	 */
	public List<AnnieApp> firstGet(String url, AnnieBillboard billBoard) {
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
			// TODO
			e.printStackTrace();
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
				// TODO
				e.printStackTrace();
			}
		}

		return annieApps;
	}


	public static void main(String[] args) {
		List<AnnieApp> appList = new GoogleAppAnnie("IN").getAllNewFree();
		System.out.println(appList.size());
		for (AnnieApp app : appList) {
			System.out.println(app.getPackageName());
		}
	}
}
