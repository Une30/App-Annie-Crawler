package com.snakehero.appannie.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StringUtil {
	private static Logger logger =  LoggerFactory.getLogger(StringUtil.class);
	public static boolean isEmpty(String str){
		return (str == null || str.length()<1)?true:false;
	} 
}
