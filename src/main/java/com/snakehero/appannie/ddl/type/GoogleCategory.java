package com.snakehero.appannie.ddl.type;

public enum GoogleCategory {
	ALL( "1", "overall"),
	GAME( "2", "game"),
	APP( "11", "application"),
	BOOKS_AND_REFERENCE( "12","application/books-and-reference"),
	BUSINESS( "13", "application/business"),
	COMIC( "14", "application/comics"),
	COMMUNICATION( "15", "application/communication"),
	EDUCATION( "16", "application/education"),
	ENTERTAINMENT( "17", "application/entertainment"),
	FINANCE( "18", "application/finance"),
	HEALTH_AND_FITNESS( "19","application/health-and-fitness"),
	LIBRARIES_AND_DEMO( "20","application/libraries-and-demo"),
	LIFESTYLE( "21", "application/lifestyle"),
	APP_WALLPAPER( "22", "application/app-wallpaper"),
	MEDIA_AND_VIDEO( "23","application/media-and-video"),
	MEDICAL( "24", "application/medical"),
	MUSIC_AND_AUDIO( "25","application/music-and-audio"),
	NEWS_AND_MAGAZINES( "26","application/news-and-magazines"),
	PERSONALIZATION( "27","application/personalization"),
	PHOTOGRAPHY( "28", "application/photography"),
	PRODUCTIVITY( "29", "application/productivity"),
	SHOPPING( "30", "application/shopping"),
	SOCIAL( "31", "application/social"),
	SPORTS( "32", "application/sports"),
	TOOLS( "33", "application/tools"),
	TRANSPORTATION("34", "application/transportation"),
	TRAVEL_AND_LOCAL( "35","application/travel-and-local"),
	WEATHER( "36", "application/weather"),
	APP_WIDGETS( "37", "application/app-widgets"),
	GAME_ACTION( "38", "game/game-action"),
	GAME_ADVENTURE( "39", "game/game-adventure"),
	GAME_WORD( "40", "game/game-word"),
	GAME_ARCADE( "41", "game/game-arcade"),
	GAME_BOARD( "42", "game/game-board"),
	GAME_CARD( "43", "game/game-card"),
	GAME_CASINO( "44", "game/game-casino"),
	GAME_CASUAL( "6", "game/casual"),
	GAME_EDUCATIONAL( "46", "game/game-educational"),
	GAME_FAMILY( "47", "game/game-family"),
	GAME_MUSIC( "48", "game/game-music"),
	GAME_PUZZLE( "49", "game/game-puzzle"),
	GAME_RACING( "8", "game/racing"),
	GAME_ROLE_PLAYING( "51","game/game-role-playing"),
	GAME_SIMULATION( "52", "game/game-simulation"),
	GAME_SPORTS( "9", "game/sports-games"),
	GAME_STRATEGY( "54", "game/game-strategy"),
	GAME_TRIVIA( "55", "game/game-trivia");
	
	private final String tag;
	private final String order;

	GoogleCategory(String order,String tag) {
		this.order= order;
		this.tag= tag;
	}

	public String getTag() {
		return tag;
	}

	public String getOrder() {
		return order;
	}
	
	
}
