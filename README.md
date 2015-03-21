# App-Annie-BillBoard

A java tool to get google play billBoard of http://www.appannie.com/ 

Usage:
```java
		GoogleAppAnnie annie = GoogleAppAnnie.build(Country.INDIA,GoogleCategory.ALL);
		
		List<AnnieApp> newAppList = annie.getTopList(AnnieTop.FREE, 150);
		
```
