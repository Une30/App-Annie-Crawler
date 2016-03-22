package com.snakehero.appannie.ddl.type;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public enum GoogleCategory {
	ALL( "1", "overall","总榜"),
	GAME( "2", "game","游戏"),
	APP( "11", "application","应用"),
	BOOKS_AND_REFERENCE( "12","application/books-and-reference","图书与工具书"),
	BUSINESS( "13", "application/business","商业"),
	COMIC( "14", "application/comics","动漫"),
	COMMUNICATION( "15", "application/communication","通讯"),
	EDUCATION( "16", "application/education","教育"),
	ENTERTAINMENT( "17", "application/entertainment","娱乐"),
	FINANCE( "18", "application/finance","财务"),
	HEALTH_AND_FITNESS( "19","application/health-and-fitness","健康与健身"),
	LIBRARIES_AND_DEMO( "20","application/libraries-and-demo","软件与演示"),
	LIFESTYLE( "21", "application/lifestyle","生活"),
	APP_WALLPAPER( "22", "application/app-wallpaper","动态壁纸"),
	MEDIA_AND_VIDEO( "23","application/media-and-video","壁纸"),
	MEDICAL( "24", "application/medical","医疗"),
	MUSIC_AND_AUDIO( "25","application/music-and-audio","音乐与音频"),
	NEWS_AND_MAGAZINES( "26","application/news-and-magazines","新闻杂志"),
	PERSONALIZATION( "27","application/personalization","个性化"),
	PHOTOGRAPHY( "28", "application/photography","摄影"),
	PRODUCTIVITY( "29", "application/productivity","效率"),
	SHOPPING( "30", "application/shopping","购物"),
	SOCIAL( "31", "application/social","社交"),
	SPORTS( "32", "application/sports","体育"),
	TOOLS( "33", "application/tools","工具"),
	TRANSPORTATION("34", "application/transportation","交通运输"),
	TRAVEL_AND_LOCAL( "35","application/travel-and-local","旅游与本地出行"),
	WEATHER( "36", "application/weather","天气"),
	APP_WIDGETS( "37", "application/app-widgets","小部件"),
	GAME_ACTION( "38", "game/game-action","动作游戏"),
	GAME_ADVENTURE( "39", "game/game-adventure","探险游戏"),
	GAME_WORD( "40", "game/game-word","文字游戏"),
	GAME_ARCADE( "41", "game/game-arcade","街机游戏"),
	GAME_BOARD( "42", "game/game-board","桌面游戏"),
	GAME_CARD( "43", "game/game-card","扑克牌游戏"),
	GAME_CASINO( "44", "game/game-casino","娱乐场游戏"),
	GAME_CASUAL( "6", "game/casual","game-休闲"),
	GAME_EDUCATIONAL( "46", "game/game-educational","game-教育"),
	GAME_FAMILY( "47", "game/game-family","家庭游戏"),
	GAME_MUSIC( "48", "game/game-music","game-音乐"),
	GAME_PUZZLE( "49", "game/game-puzzle","智力游戏"),
	GAME_RACING( "8", "game/racing","赛车游戏"),
	GAME_ROLE_PLAYING( "51","game/game-role-playing","角色扮演游戏"),
	GAME_SIMULATION( "52", "game/game-simulation","模拟游戏"),
	GAME_SPORTS( "9", "game/sports-games","game-体育"),
	GAME_STRATEGY( "54", "game/game-strategy","策略游戏"),
	GAME_TRIVIA( "55", "game/game-trivia","小游戏");
	
	private final String tag;
	private final String order;
	private final String title;

	GoogleCategory(String order,String tag,String title) {
		this.order= order;
		this.tag= tag;
		this.title = title;
	}

	public String getTag() {
		return tag;
	}

	public String getOrder() {
		return order;
	}
	
	
	public String getTitle() {
		return title;
	}

	public static Map<String, String> getMapValues(){
		List<GoogleCategory> list = Arrays.asList(GoogleCategory.values());
		Map<String,String> map = new LinkedHashMap<String,String>();
		for(GoogleCategory cate:list){
			map.put(cate.toString(), cate.getTitle());
		}
		return map;
	}
	
	
	public static GoogleCategory getCategory(String categoryName){
		GoogleCategory[] categories = GoogleCategory.values();
		for(GoogleCategory c:categories){
			if(c.name().equalsIgnoreCase(categoryName)){
				return c;
			}
		}
		return null;
	}
}
