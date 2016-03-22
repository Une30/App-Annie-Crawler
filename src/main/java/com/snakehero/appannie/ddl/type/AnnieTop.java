package com.snakehero.appannie.ddl.type;

public enum AnnieTop {
	TOP_FREE(1),PAID(2),GROSSING(3),NEW_FREE(4),NEW_PAID(5);
	private final int type;

	AnnieTop(int type) {
		this.type = type;
	}

	public int getDBText() {
		return type;
	}
	
	public static AnnieTop getBoard(String name){
		AnnieTop[] tops = AnnieTop.values();
		for(AnnieTop c:tops){
			if(c.toString().equalsIgnoreCase(name)){
				return c;
			}
		}
		return null;
	}
	public static void main(String[] args) {
		AnnieTop c = getBoard("TOP_FREE");
		System.out.println(c.getDBText());
	}
}
