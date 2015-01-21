package com.snakehero.appannie.ddl;

public enum AnnieBillboard {
	TOP_FREE(1),TOP_PAID(2),TOP_ALL(3),NEW_FREE(4),NEW_PAID(5);
	private final int type;

	AnnieBillboard(int type) {
		this.type = type;
	}

	public int getDBText() {
		return type;
	}
}
