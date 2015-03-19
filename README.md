# App-Annie-BillBoard

A java tool to get google play billBoard of http://www.appannie.com/ 

Usage:
```java
		GoogleAppAnnie annie = GoogleAppAnnie.build("IN","GAME");
		
		List<AnnieApp> newAppList = annie.getNewFree(100);
		List<AnnieApp> topAppList = annie.getTopFree(100);
		
```
