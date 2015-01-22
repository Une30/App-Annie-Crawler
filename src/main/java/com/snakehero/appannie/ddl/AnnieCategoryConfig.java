package com.snakehero.appannie.ddl;

import java.util.HashMap;
import java.util.Map;

public class AnnieCategoryConfig {
	private static Map<String, AnnieCategory> ANNIE_CATEGORY_MAP = new HashMap<String, AnnieCategory>();
	static {
		ANNIE_CATEGORY_MAP.put("ALL", new AnnieCategory("ALL", "1", "overall"));
		ANNIE_CATEGORY_MAP.put("GAME", new AnnieCategory("GAME", "2", "game"));
		ANNIE_CATEGORY_MAP.put("APP", new AnnieCategory("APP", "11", "application"));
		ANNIE_CATEGORY_MAP.put("BOOKS_AND_REFERENCE", new AnnieCategory("BOOKS_AND_REFERENCE", "12",
				"application/books-and-reference"));
		ANNIE_CATEGORY_MAP.put("BUSINESS", new AnnieCategory("BUSINESS", "13", "application/business"));
		ANNIE_CATEGORY_MAP.put("COMIC", new AnnieCategory("COMIC", "14", "application/comics"));
		ANNIE_CATEGORY_MAP.put("COMMUNICATION", new AnnieCategory("COMMUNICATION", "15", "application/communication"));
		ANNIE_CATEGORY_MAP.put("EDUCATION", new AnnieCategory("EDUCATION", "16", "application/education"));
		ANNIE_CATEGORY_MAP.put("ENTERTAINMENT", new AnnieCategory("ENTERTAINMENT", "17", "application/entertainment"));
		ANNIE_CATEGORY_MAP.put("FINANCE", new AnnieCategory("FINANCE", "18", "application/finance"));
		ANNIE_CATEGORY_MAP.put("HEALTH_AND_FITNESS", new AnnieCategory("HEALTH_AND_FITNESS", "19",
				"application/health-and-fitness"));
		ANNIE_CATEGORY_MAP.put("LIBRARIES_AND_DEMO", new AnnieCategory("LIBRARIES_AND_DEMO", "20",
				"application/libraries-and-demo"));
		ANNIE_CATEGORY_MAP.put("LIFESTYLE", new AnnieCategory("LIFESTYLE", "21", "application/lifestyle"));
		ANNIE_CATEGORY_MAP.put("APP_WALLPAPER", new AnnieCategory("APP_WALLPAPER", "22", "application/app-wallpaper"));
		ANNIE_CATEGORY_MAP.put("MEDIA_AND_VIDEO", new AnnieCategory("MEDIA_AND_VIDEO", "23",
				"application/media-and-video"));
		ANNIE_CATEGORY_MAP.put("MEDICAL", new AnnieCategory("MEDICAL", "24", "application/medical"));
		ANNIE_CATEGORY_MAP.put("MUSIC_AND_AUDIO", new AnnieCategory("MUSIC_AND_AUDIO", "25",
				"application/music-and-audio"));
		ANNIE_CATEGORY_MAP.put("NEWS_AND_MAGAZINES", new AnnieCategory("NEWS_AND_MAGAZINES", "26",
				"application/news-and-magazines"));
		ANNIE_CATEGORY_MAP.put("PERSONALIZATION", new AnnieCategory("PERSONALIZATION", "27",
				"application/personalization"));
		ANNIE_CATEGORY_MAP.put("PHOTOGRAPHY", new AnnieCategory("PHOTOGRAPHY", "28", "application/photography"));
		ANNIE_CATEGORY_MAP.put("PRODUCTIVITY", new AnnieCategory("PRODUCTIVITY", "29", "application/productivity"));
		ANNIE_CATEGORY_MAP.put("SHOPPING", new AnnieCategory("SHOPPING", "30", "application/shopping"));
		ANNIE_CATEGORY_MAP.put("SOCIAL", new AnnieCategory("SOCIAL", "31", "application/social"));
		ANNIE_CATEGORY_MAP.put("SPORTS", new AnnieCategory("SPORTS", "32", "application/sports"));
		ANNIE_CATEGORY_MAP.put("TOOLS", new AnnieCategory("TOOLS", "33", "application/tools"));
		ANNIE_CATEGORY_MAP.put("TRANSPORTATION",
				new AnnieCategory("TRANSPORTATION", "34", "application/transportation"));
		ANNIE_CATEGORY_MAP.put("TRAVEL_AND_LOCAL", new AnnieCategory("TRAVEL_AND_LOCAL", "35",
				"application/travel-and-local"));
		ANNIE_CATEGORY_MAP.put("WEATHER", new AnnieCategory("WEATHER", "36", "application/weather"));
		ANNIE_CATEGORY_MAP.put("APP_WIDGETS", new AnnieCategory("APP_WIDGETS", "37", "application/app-widgets"));
		ANNIE_CATEGORY_MAP.put("GAME_ACTION", new AnnieCategory("GAME_ACTION", "38", "game/game-action"));
		ANNIE_CATEGORY_MAP.put("GAME_ADVENTURE", new AnnieCategory("GAME_ADVENTURE", "39", "game/game-adventure"));
		ANNIE_CATEGORY_MAP.put("GAME_WORD", new AnnieCategory("GAME_WORD", "40", "game/game-word"));
		ANNIE_CATEGORY_MAP.put("GAME_ARCADE", new AnnieCategory("GAME_ARCADE", "41", "game/game-arcade"));
		ANNIE_CATEGORY_MAP.put("GAME_BOARD", new AnnieCategory("GAME_BOARD", "42", "game/game-board"));
		ANNIE_CATEGORY_MAP.put("GAME_CARD", new AnnieCategory("GAME_CARD", "43", "game/game-card"));
		ANNIE_CATEGORY_MAP.put("GAME_CASINO", new AnnieCategory("GAME_CASINO", "44", "game/game-casino"));
		ANNIE_CATEGORY_MAP.put("GAME_CASUAL", new AnnieCategory("GAME_CASUAL", "6", "game/casual"));
		ANNIE_CATEGORY_MAP
				.put("GAME_EDUCATIONAL", new AnnieCategory("GAME_EDUCATIONAL", "46", "game/game-educational"));
		ANNIE_CATEGORY_MAP.put("GAME_FAMILY", new AnnieCategory("GAME_FAMILY", "47", "game/game-family"));
		ANNIE_CATEGORY_MAP.put("GAME_MUSIC", new AnnieCategory("GAME_MUSIC", "48", "game/game-music"));
		ANNIE_CATEGORY_MAP.put("GAME_PUZZLE", new AnnieCategory("GAME_PUZZLE", "49", "game/game-puzzle"));
		ANNIE_CATEGORY_MAP.put("GAME_RACING", new AnnieCategory("GAME_RACING", "8", "game/racing"));
		ANNIE_CATEGORY_MAP.put("GAME_ROLE_PLAYING", new AnnieCategory("GAME_ROLE_PLAYING", "51",
				"game/game-role-playing"));
		ANNIE_CATEGORY_MAP.put("GAME_SIMULATION", new AnnieCategory("GAME_SIMULATION", "52", "game/game-simulation"));
		ANNIE_CATEGORY_MAP.put("GAME_SPORTS", new AnnieCategory("GAME_SPORTS", "9", "game/sports-games"));
		ANNIE_CATEGORY_MAP.put("GAME_STRATEGY", new AnnieCategory("GAME_STRATEGY", "54", "game/game-strategy"));
		ANNIE_CATEGORY_MAP.put("GAME_TRIVIA", new AnnieCategory("GAME_TRIVIA", "55", "game/game-trivia"));

	}
	
	public static AnnieCategory getCategory(String code){
		return ANNIE_CATEGORY_MAP.get(code.toUpperCase());
	}
	
}
