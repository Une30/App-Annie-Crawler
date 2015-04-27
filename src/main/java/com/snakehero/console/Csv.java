package com.snakehero.console;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.snakehero.appannie.ddl.GoogleAnnieApp;

public class Csv {
	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = "\",\"";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "国家,榜单,应用名,包名,排名,排名变化,CP,分类";

	public static void write(String fileName,List<GoogleAnnieApp> appList) {
		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(fileName);

			// Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());

			// Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);

			// Write a new student object list to the CSV file
			for (GoogleAnnieApp app : appList) {
				fileWriter.append("\"");
				fileWriter.append(app.getCountryCode());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(app.getBillBoard().toString());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(app.getTitle());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(app.getPackageName());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(app.getRank()+"");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(app.getRankChange()+"");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(app.getCp());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(app.getCategory());
				fileWriter.append("\"");
				fileWriter.append(NEW_LINE_SEPARATOR);
			}

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {

			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}

		}
	}
}
