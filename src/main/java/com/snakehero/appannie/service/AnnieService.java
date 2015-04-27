package com.snakehero.appannie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.snakehero.appannie.ddl.GoogleAnnieApp;
import com.snakehero.appannie.ddl.type.AnnieTop;
import com.snakehero.appannie.ddl.type.Country;
import com.snakehero.appannie.ddl.type.GoogleCategory;
import com.snakehero.appannie.util.StringUtil;

public class AnnieService {
	private static final Logger logger = LoggerFactory.getLogger(AnnieService.class);
	
	private final static String GP_NAME = "google-play";
	private final static String GP_NAME_NEW = "gp";

	// if GP_NAME not work ,use GP_NAME_NEW instead by setting useGpNewName=true
	private static boolean useGpNewName = false;
	private final static String GP = useGpNewName ? GP_NAME_NEW : GP_NAME;
	private final static String GOOGLE_TOP_TPL = "https://www.appannie.com/apps/" + GP + "/top/%s/%s/";
	public final static String GOOGLE_TOP_MORE_TPL = "https://www.appannie.com%s?p=2-&h=23&iap=undefined";
	private final static String tdTemplate = "td:nth-child(%d)";
	private final static String infoQuery = ".item-info";
	private final static Pattern packageRegex = Pattern.compile("/apps/(gp|google-play){1}/app/(.*)/");
	
	private final static Pattern nextPageRegex = Pattern.compile("pageVal.data_url\\s?=\\s?'(.*)'", Pattern.MULTILINE);

	/**
	 * extract a list of app from tr elements
	 * 
	 * @param trEls The core tr elements of html which include app data
	 * @param billBoard Type of AnnieBillboard
	 * @param countryCode Code of country (e.g. IN)
	 * @return list of AnnieApp
	 * 
	 * @see AnnieTop 
	 */
	public static List<GoogleAnnieApp> extractAnnieApp(Elements trEls, AnnieTop billBoard, String countryCode,String categoryTag,int rankStart) {
		List<GoogleAnnieApp> annieApps = new ArrayList<GoogleAnnieApp>();
		if (trEls != null) {
			try {
				String tdQuery = String.format(tdTemplate, billBoard.getDBText());
				int rank = rankStart;
				for (Element tr : trEls) {
					Elements tds = tr.select(tdQuery);
					if (tds != null) {
						Element td = tds.first();
						if(td!=null){
							Elements info = td.select(infoQuery);
							if(info!=null){
								// first node:packageName + title
//								Element titleInfo= info.first();
								// rank change
								String rankChange = info.select(".pull-right .var").text();
								// 
								Element titleInfo= info.select(".main-info .title-info a").first();
								// packageName
								String packageName = extractPackageName(titleInfo.attr("href"));
//								// title
								String title = titleInfo.text().trim();
								
								Element cpInfo= info.select(".main-info .add-info").first();
								String cp = cpInfo.text();
								
								GoogleAnnieApp app = new GoogleAnnieApp();
								app.setRankChange(rankChange);
								app.setPackageName(packageName);
								app.setTitle(title);
								app.setCp(cp);
								app.setCategory(categoryTag);
								app.setRank(rank++);
								app.setBillBoard(billBoard);
								app.setCountryCode(countryCode);
								
								annieApps.add(app);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return annieApps;
	}

	/**
	 * extract packageName from a url
	 * 
	 * @param href the string of a link
	 * @return the packageName string
	 */
	public static String extractPackageName(String href) {
		Matcher m = packageRegex.matcher(href);
		if (m.matches()) {
			return m.group(2);
		}
		return null;
	}

	/**
	 * extract next page url from html
	 * @param html the html page string
	 * @return  next page url string
	 */
	public static String extractNextPageUrl(String html) {
		Matcher m = nextPageRegex.matcher(html);
		if (m.find()) {
			return m.group(1);
		}
		return null;
	}

	/**
	 * get moreUrl (the second page url) from document of annie html page
	 * @param doc the document parse by Jsoup
	 * @return the second page url string
	 */
	public static String getMoreUrl(Document doc) {
		String moreUrl = "";
		String scripts = doc.getElementsByTag("script").toString();
		String nextUrlPath = extractNextPageUrl(scripts);
		if (!StringUtil.isEmpty(nextUrlPath)) {
			moreUrl = String.format(GOOGLE_TOP_MORE_TPL, nextUrlPath);
		}
		
		return moreUrl;
	}

	/**
	 * Constructs first page url of a country's Google-Top-BillBoard
	 * 
	 * @param countryName
	 * @param annieCategory 
	 * @return the first page url string
	 * 
	 * @see ddl.AnnieCountryConfig
	 */
	public static String getGoogleTopFirstUrl(Country country, GoogleCategory category) {
		return String.format(GOOGLE_TOP_TPL, country.getCountryName(),category.getTag());
	}
}
