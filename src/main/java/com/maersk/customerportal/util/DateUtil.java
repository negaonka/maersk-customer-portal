package com.maersk.customerportal.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

	/* to check if the timstamp is in valid format */
	public static boolean validateTimestamp(String timestampFormat, String timestamp) {
		SimpleDateFormat format = new java.text.SimpleDateFormat(timestampFormat);
		try {
			format.parse(timestamp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
