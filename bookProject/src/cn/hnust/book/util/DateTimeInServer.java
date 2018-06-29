package cn.hnust.book.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeInServer {
	private SimpleDateFormat sdf = null;
	
	public String getDateTime() {
		this.sdf = new SimpleDateFormat("yyyy-MM-dd");
		return this.sdf.format(new Date());
	}
	
}