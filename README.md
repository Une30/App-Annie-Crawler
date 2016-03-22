# App-Annie-Crawler

A java tool to get app list of http://www.appannie.com/ 

Only support Google Play only so far , Apple Store list comming soon!

Usage:
```java
		GoogleAppAnnie annie = GoogleAppAnnie.build(Country.INDIA,GoogleCategory.ALL,"2016-03-15");
		
		List<AnnieApp> newAppList = annie.getTopList(AnnieTop.FREE, 150);
		
```
