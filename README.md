# App-Annie-Crawler

A java tool to get app list of http://www.appannie.com/ 

Usage:
```java
		GoogleAppAnnie annie = GoogleAppAnnie.build(Country.INDIA,GoogleCategory.ALL);
		
		List<AnnieApp> newAppList = annie.getTopList(AnnieTop.FREE, 150);
		
```
