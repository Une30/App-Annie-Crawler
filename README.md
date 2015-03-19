# App-Annie-BillBoard

A simple Java web page extract tool to get billBoard of http://www.appannie.com/ 

Usage:
```java
		GoogleAppAnnie annie = GoogleAppAnnie.build("IN","GAME");
		
		List<AnnieApp> newAppList = annie.getNewFree(100);
		List<AnnieApp> topAppList = annie.getTopFree(100);
		
```
