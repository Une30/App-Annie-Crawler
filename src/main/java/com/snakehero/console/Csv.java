package com.snakehero.console;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.snakehero.appannie.ddl.GoogleAnnieApp;

public class Csv {
	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = "\",\"";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "国家,榜单,应用名,包名,排名,排名变化,CP,榜单所属分类";
	private static Logger logger = LoggerFactory.getLogger(Csv.class);
	
	public static File write(List<GoogleAnnieApp> appList,File fe){
		try {
			FileOutputStream fop = new FileOutputStream(fe);
			IOUtils.write(toCsvString(appList), fop, "UTF-8");
		} catch (IOException e) {
			logger.error("Csv write error:",e);
		}
		return fe;
	}
	
	public static String toCsvString(List<GoogleAnnieApp> appList) {
		try {
			StringBuilder sb = new StringBuilder();

			// Write the CSV file header
			sb.append(FILE_HEADER.toString());

			// Add a new line separator after the header
			sb.append(NEW_LINE_SEPARATOR);

			// Write a new student object list to the CSV file
			for (GoogleAnnieApp app : appList) {
				sb.append("\"");
				sb.append(app.getCountryCode());
				sb.append(COMMA_DELIMITER);
				sb.append(app.getBillBoard().toString());
				sb.append(COMMA_DELIMITER);
				sb.append(app.getTitle());
				sb.append(COMMA_DELIMITER);
				sb.append(app.getPackageName());
				sb.append(COMMA_DELIMITER);
				sb.append(app.getRank()+"");
				sb.append(COMMA_DELIMITER);
				sb.append(app.getRankChange()+"");
				sb.append(COMMA_DELIMITER);
				sb.append(app.getCp());
				sb.append(COMMA_DELIMITER);
				sb.append(app.getCategory());
				sb.append("\"");
				sb.append(NEW_LINE_SEPARATOR);
			}

			logger.info("CSV file was created successfully !!!");
			return sb.toString();
		} catch (Exception e) {
			logger.error("toCsvString error:",e);
		}
		return null; 
	}
}
