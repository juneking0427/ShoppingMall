package com.app.util;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


 
public class DateUtil {
  
  
  public static final  int YEAR       = 1;
  public static final  int MONTH      = 2;
  public static final  int DATE       = 3;
  public static final  int MONTHFIRST = 4;
  public static final  int MONTHEND   = 5;
	
	
  public static void main(){
	  try {
		
		  System.out.println(DateUtil.toDateString(Calendar.DATE,-15,"yyyyMMdd"));
		  System.out.println(DateUtil.toDateString(Calendar.DATE,0,"yyyyMMdd"));

		  System.out.println(DateUtil.toDateString(DateUtil.toDateString(Calendar.DATE,0,"yyyyMMdd"), "yyyyMMdd", "yyyy-MM-dd"));
		  
	  } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

	/**
	 *  날짜를 문자열로 (현재)
	 * @return String
	 * @param String format
	 * @author rocomo
	 * @throws Exception
	 */
	public static String toDateString(String format) throws Exception {
		if (isNull(format)) {
			format = "yyyyMMddHHmmss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	
	/**
	 *  현재 날짜를 long 형식으로
	 * @return String
	 * @param String format
	 * @author rocomo
	 * @throws Exception
	 */
	public static String toLongString() throws Exception{
		return String.valueOf(new Date().getTime());
	}
	
	/**
	 *  날짜를 문자열로 (현재기준 날짜 이전,이후 지정 일 단위)
	 * @return String
	 * @param int amount, String format
	 * @author rocomo
	 * @throws Exception
	 */
	public static String toDateString(int amount, String format)throws Exception {
		return toDateString(Calendar.DATE, amount, format);
	}
	
	
	/**
	 * 특정일자의 특정기간 이전/이후의 날짜 반환
	 * @author rocomo
	 * @param current
	 * @param field
	 * @param gap
	 * @return String
	 * @throws Exception
	 */
	public static String getDateWithGap (String current, String field, int gap) throws Exception{
	  
	  String rsltStr = "";
	  
	  if (field == null || field.equals("")) return "";
	  
	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	  Date currentDate = null;
	  
	  try {
	    currentDate = dateFormat.parse(current);
	  } catch (Exception e) {
	    return "";
	  }
	  
	  Calendar calendar = Calendar.getInstance();
	  calendar.setTime(currentDate);   // Current Time
	  
	  if (field.toUpperCase().equals("YAER")) calendar.add(Calendar.YEAR, gap);
	  else if (field.toUpperCase().equals("MONTH")) calendar.add(Calendar.MONTH, gap);
	  else if (field.toUpperCase().equals("DATE")) calendar.add(Calendar.DATE, gap);
	  else if (field.toUpperCase().equals("HOUR")) calendar.add(Calendar.HOUR, gap);
	  else if (field.toUpperCase().equals("MINUTE")) calendar.add(Calendar.MINUTE, gap);
	  else if (field.toUpperCase().equals("SECOND")) calendar.add(Calendar.SECOND, gap);
	  
	  rsltStr = dateFormat.format(new Date(calendar.getTimeInMillis()));
	  
	  return rsltStr;
	}
	

	/**
	 *  날짜를 문자열로 (현재기준 날짜 이전,이후 지정 일 단위)
	 * @return String
	 * @param int field, int amount, String format
	 * @author rocomo
	 * @throws Exception
	 */
	public static String toDateString(int field, int amount, String format)
			throws Exception {
		if (isNull(format)) {
			format = "yyyyMMddHHmmss";
		}
		if (amount == 0) {
			return toDateString(format); // 현재일자
		}

		Calendar c = Calendar.getInstance();
		c.add(field, amount); // 날짜데이터(플러스,마이너스에 따라 이전/이후일자)

		return toDateString(c.getTimeInMillis(), format);
	}
	

	/**
	 *  long타입 형태의  날짜를 문자열로 
	 * @return String
	 * @param long dt, String format
	 * @author rocomo
	 * @throws Exception
	 */
	public static String toDateString(long dt, String format)throws Exception {
		if (isNull(format)) {
			format = "yyyyMMddHHmmss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(dt));
	}

	/**
	 *  String 타입 형태의  날짜를 Date타입으로 
	 * @return Date
	 * @param String dt, String format
	 * @author rocomo
	 * @throws Exception
	 */
	public static Date toDateString(String dt, String format) throws Exception{
		if (isNull(format)) {
			format = "yyyyMMddHHmmss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date sDate = null;
		try {
			sDate = sdf.parse(dt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sDate;
	}

	/**
	 * String 타입 날짜를 포멧을 형태를 변경
	 * @return Date
	 * @param String dt, String format
	 * @author rocomo
	 * @throws Exception
	 */
	public static String toDateString(String dt, String fromformat, String toformat) throws Exception{

	  if (isNull(dt)) {
	    return "";
	  }
	  
		if (fromformat == null)
			fromformat = "yyyyMMdd";
		if (toformat == null)
			toformat = "yyyy-MM-dd";
		
		if (fromformat.equals("yyyyMMdd")) {
		  dt = dt.replaceAll("-", "").replaceAll("/", "").replace(".", "");
		}

		SimpleDateFormat fromsdf = new SimpleDateFormat(fromformat);
		SimpleDateFormat tosdf = new SimpleDateFormat(toformat);
		Date sDate = null;

		try {
			sDate = fromsdf.parse(dt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tosdf.format(sDate);
	}

	/** 
	 * Date 타입의 date 를 기본포맷인 yyyy/MM/dd로 
	 * 포맷팅하여 리턴한다.
	 * @param date
	 * @return String
	 * @author rocomo
	 * @throws Exception
	 * */
	public static String getStringFromDate(Date date) {
		return getStringFromDate(date, "yyyy/MM/dd");
	}
	
	/** 
	 * 8자리의 날짜값을 token 값에 따라 날짜포맷으로 리턴한다.
	 * data: 20080101   token : "-" 
	 * result: 2008-01-01
	 * @param data
	 * @return String
	 * @author rocomo
	 * @throws Exception
	 * */
	public static String getDateFormat(String date, String token)  throws Exception{
		StringBuffer ret = new StringBuffer();

			date = date.trim();
			
			if (date != null && date.length() == 8) { 
				ret.append(date.substring(0, 4)).append(token).append(
						date.substring(4, 6)).append(token).append(
						date.substring(6));
			} else {
				ret.append("-");
			}
		
		return ret.toString();
	}
	
	/** 
	 * 6자리의 시간값(150801)을 시간 포맷(15:08:01)으로 리턴한다.
	 * 시간이 6자리보다 짧으면 입력한 시간값(strTime)을 그대로 리턴한다.
	 * @param strTime
	 * @return String
	 * @author rocomo
	 * @throws Exception
	 * */
	public static String getTimeFormat(String strTime, String token)  throws Exception{
		StringBuilder strResult = new StringBuilder();

			if (strTime != null && strTime.trim().length() >= 6) {
				strTime = strTime.trim();
				strResult.append(strTime.substring(0, 2));
				strResult.append(token);
				strResult.append(strTime.substring(2, 4));
				strResult.append(token);
				strResult.append(strTime.substring(4, 6));
			} else {
				strResult.append(strTime);
			}
		return strResult.toString();
	}
	
	/** 
	 * 4자리의 시간값(1508)을 시간 포맷(15:08)으로 리턴한다.
	 * 시간이 4자리보다 짧으면 입력한 시간값(strTime)을 그대로 리턴한다.
	 * @param strTime
	 * @return String
	 * @author rocomo
	 * @throws Exception
	 * */
	public static String getTimeFormat2(String strTime, String token)  throws Exception{
		StringBuilder strResult = new StringBuilder();

			if (strTime != null && strTime.trim().length() >= 4) {
				strTime = strTime.trim();
				strResult.append(strTime.substring(0, 2));
				strResult.append(token);
				strResult.append(strTime.substring(2, 4));
			} else {
				strResult.append(strTime);
			}
		return strResult.toString();
	}

	/** 
	 * 입력한 날짜에 달수을 더한 날짜를 리턴한다.
	 * @param date
	 * @param month
	 * @return Date
	 * @author rocomo
	 * @throws Exception
	 * */
	public static Date addDateByMonth(Date date, int month)  throws Exception{
		Calendar cal = Calendar.getInstance();

			cal.setTime(date);
			cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}

	/** 
	 * 입력한 날짜에 일수를 더한 날짜를 리턴한다.
	 * @param date
	 * @param day
	 * @return Date
	 * @author rocomo
	 * @throws Exception
	 * */
	public static Date addDateByDay(Date date, int day)  throws Exception{
		Calendar cal = Calendar.getInstance();

			cal.setTime(date);
			cal.add(Calendar.DAY_OF_YEAR, day);

		return cal.getTime();
	}
	
	/** <PRE>
	 * Date  타입의 date 를 포맷문자열(format) 형식의 문자열로 리턴한다.
	 * @param date
	 * @param format
	 * @return String
	 * @author rocomo
	 * @throws Exception
	 * </PRE> */
	public static String getStringFromDate(Date date, String format) {
		StringBuffer dateResult = new StringBuffer();
		SimpleDateFormat simpleDate = new SimpleDateFormat(format);
		simpleDate.format(date, dateResult, new FieldPosition(1));
		return dateResult.toString();
	}
	


	/**
	 * 두 날짜 사이의 차이
	 * @param String startDate, String endDate, String format
	 * @return long
	 * @author rocomo
	 * @throws Exception
	 */
	public static long day2Day(String startDate, String endDate, String format)
			throws Exception {
		if (format == null) {
			format = "yyyy/MM/dd HH:mm:ss.SSS";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date sDate;
		Date eDate;
		long day2day = 0;
		try {
			sDate = sdf.parse(startDate);
			eDate = sdf.parse(endDate);
			day2day = (eDate.getTime() - sDate.getTime())
					/ (1000 * 60 * 60 * 24);
		} catch (Exception e) {
			throw new Exception("wrong format string");
		}
		return day2day;
	}

	/**
	 * 두 날짜 사이의 차이
	 * @param long startDate, long endDate
	 * @return long
	 * @author rocomo
	 * @throws Exception
	 */
	public static long day2Day(long startDate, long endDate) throws Exception {
		Date sDate;
		Date eDate;
		long day2day = 0;
		try {
			sDate = new Date(startDate);
			eDate = new Date(endDate);
			day2day = (eDate.getTime() - sDate.getTime())
					/ (1000 * 60 * 60 * 24);
		} catch (Exception e) {
			throw new Exception("wrong format string");
		}
		return day2day;
	}

	
	 /**
	 * GregorianCalendar 객체를 반환함.
	 * 
	 * @param yyyymmdd 날짜 인수
	 * @return GregorianCalendar
     * @see java.util.Calendar 
     * @see java.util.GregorianCalendar
     * 
     *  - 사용 예
     * Calendar cal = DateUtil.getGregorianCalendar(DateUtil.getCurrentYyyymmdd())
     * 
     */
	public static GregorianCalendar getGregorianCalendar(String yyyymmdd) throws Exception{
	
	     int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
	     int mm = Integer.parseInt(yyyymmdd.substring(4, 6));
	     int dd = Integer.parseInt(yyyymmdd.substring(6));
	
	     GregorianCalendar calendar = new GregorianCalendar(yyyy, mm - 1, dd, 0, 0, 0);
	
	     return calendar;
	
	}
	
	/**
     * 주로 일자를 구하는 메소드.
     *
     * @param yyyymm 년월
     * @param week 몇번째 주
     * @param pattern 리턴되는 날짜패턴 (ex:yyyyMMdd)
     * @return 연산된 날짜
     * @see java.util.Calendar
     * 
     *  - 사용 예
     * String date = DateUtil.getWeekToDay('200801' , 1, 'yyyyMMdd')
     *
     */
	public static String getWeekToDay(String yyyymm, int week, String pattern)
			throws Exception {

		Calendar cal = Calendar.getInstance(Locale.KOREA);

		int new_yy = Integer.parseInt(yyyymm.substring(0, 4));
		int new_mm = Integer.parseInt(yyyymm.substring(4, 6));
		int new_dd = 1;

		cal.set(new_yy, new_mm - 1, new_dd);

		// 임시 코드
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			week = week - 1;
		}

		cal.add(Calendar.DATE,
				(week - 1)
						* 7
						+ (cal.getFirstDayOfWeek() - cal
								.get(Calendar.DAY_OF_WEEK)));

		SimpleDateFormat formatter = new SimpleDateFormat(pattern,
				Locale.FRANCE);

		return formatter.format(cal.getTime());

	}

    /**
     *  입력된 일자를 더한 주를 구하여 return한다
     *  
     * @param yyyymmdd 년도별 
     * @param addDay 추가일 
     * @return 연산된 주
     * @see java.util.Calendar
     * 
     *  - 사용 예
     * int date = DateUtil.getWeek(DateUtil.getCurrentYyyymmdd() , 0)
     *
     */
	public static int getWeek(String yyyymmdd, int addDay)throws Exception{
	  Calendar cal = Calendar.getInstance(Locale.KOREA);
	  int new_yy = Integer.parseInt(yyyymmdd.substring(0,4));
	  int new_mm = Integer.parseInt(yyyymmdd.substring(4,6));
	  int new_dd = Integer.parseInt(yyyymmdd.substring(6,8));
	
	  cal.set(new_yy,new_mm-1,new_dd);
	        cal.add(Calendar.DATE, addDay);
	
	  int week = cal.get(Calendar.DAY_OF_WEEK);
	  return week;
	}
	
	 /** 
     * 입력된 년월의 마지막 일수를 return 한다.
     * 
     * @param year
     * @param month
     * @return 마지막 일수 
     * @see java.util.Calendar
     * 
     *  - 사용 예
     * int date = DateUtil.getLastDayOfMon(2008 , 1)
     * 
     */
    public static int getLastDayOfMon(int year, int month) throws Exception{ 

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
     
    }
    
    /** 
     * 입력된 년월의 마지막 일수를 return한다
     * 
     * @param year
     * @param month
     * @return 마지막 일수  
     * 
     *  - 사용 예
     * int date = DateUtil.getLastDayOfMon('2008')
     * 
     */ 
    public static int getLastDayOfMon(String yyyymm) throws Exception{

        Calendar cal = Calendar.getInstance();
        int yyyy = Integer.parseInt(yyyymm.substring(0, 4));
        int mm = Integer.parseInt(yyyymm.substring(4)) - 1;

        cal.set(yyyy, mm, 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    /** 
     * 현재의 요일을 구한다.
     * 
     * @param
     * @return 요일
     * @see java.util.Calendar 
     *
     *  - 사용 예
     * int day = DateUtil.getDayOfWeek()
     *  SUNDAY    = 1
     *  MONDAY    = 2
     *  TUESDAY   = 3
     *  WEDNESDAY = 4
     *  THURSDAY  = 5
     *  FRIDAY    = 6
     * 
     */
    public static int getDayOfWeek()throws Exception{
        Calendar rightNow = Calendar.getInstance();
        int day_of_week = rightNow.get(Calendar.DAY_OF_WEEK);
        return day_of_week;
    }
    
    public static int getDayOfWeek(String yymmdd) throws Exception {
    	Calendar yyyymmdd = Calendar.getInstance();
		int year = Integer.parseInt(yymmdd.substring(0,4));
		int month = Integer.parseInt(yymmdd.substring(4,6));
		int date = Integer.parseInt(yymmdd.substring(6,8));
    	yyyymmdd.set(year, month-1, date);
    	int day_of_week = yyyymmdd.get(Calendar.DAY_OF_WEEK);
    	return day_of_week;
    }
    
    /** 
     * 현재주가 올해 전체의 몇째주에 해당되는지 계산한다. 
     * 
	 * @param
	 * @return 요일
     * @see java.util.Calendar 
     * 
     *  - 사용 예
     * int day = DateUtil.getWeekOfYear()
     * 
     */    
    public static int getWeekOfYear()throws Exception{
     Locale LOCALE_COUNTRY = Locale.KOREA;
        Calendar rightNow = Calendar.getInstance(LOCALE_COUNTRY);
        int week_of_year = rightNow.get(Calendar.WEEK_OF_YEAR);
        return week_of_year;
    }
    
    /** 
     *현재주가 현재월에 몇째주에 해당되는지 계산한다. 
     * 
     * @param
     * @return 요일
     * @see java.util.Calendar 
     * 
     *  - 사용 예
     * int day = DateUtil.getWeekOfMonth()
     * 
     */     
    public static int getWeekOfMonth()throws Exception{
     Locale LOCALE_COUNTRY = Locale.KOREA;
        Calendar rightNow = Calendar.getInstance(LOCALE_COUNTRY);
        int week_of_month = rightNow.get(Calendar.WEEK_OF_MONTH);
        return week_of_month;
    }
    
    /**
	 * yyyy.MM.dd HH:mm:ss 포맷을 받아 날짜만 분리시키고 구분자를 변환시켜준다.
	 * @param date
	 * @param gubun
	 * @return
	 */
	public static String formatOnlyDate(String date, char gubun){
		String[] tempDate = date.split(" ");
		date = tempDate[0];
		date = date.replace('.', gubun);
		return date;
	}
	
	/**
	 * yyyy.MM.dd HH:mm:ss 포맷을 받아 날짜만 분리시키고 반환한다.
	 * @param date
	 * @return
	 */
	public static String formatDateTime(String date){
		String[] dateTime = date.split(" ");
		
		return dateTime[0] + " (" + dateTime[1] + ")";
	}
	
	/**
	 * 현재 날짜 시간을 yyyy/MM/dd HH:mm:ss 포맷으로 반환한다.
	 * @return
	 */
	public static String getNowDateTime(){
		Calendar calendar = Calendar.getInstance();
		return (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(calendar.getTime()));
	}
	
	
	/**
	 * 사용자가 지정한 형식의 현재 날짜 시간을 반환한다.
	 * @param format
	 * @return
	 */
	public static String getNowDateTime(String format){
		Calendar calendar = Calendar.getInstance();
		return (new SimpleDateFormat(format).format(calendar.getTime()));
	}
	
	
	/**
	 * yyyy/MM/dd HH:mm:ss 형식에서 날짜만을 분리시켜 반환한다.
	 * @param date
	 * @return
	 */
	public static String parseDate(String date){
		return date.split(" ")[0];
	}
	
	/**
	 * 입력받은 날짜를 yyyy년 MM월 형식으로 리턴한다.
	 * @param date
	 * @return
	 */
	public static String formatYearMonth(String date){
		if(date == null) return "Wrong Date";
		return date.substring(0, 4) + "년 " + date.substring(4, 6) + "월";
	}
	
	
	/**
	 * 시작일 종료일이 있는지 상시인지를 구분하여 해당 값을 리턴한다.
	 * @param sangsiYN
	 * @param s_date
	 * @param e_date
	 * @return
	 */
	public static String formatSDate2Edate(String sangsiYN, Object s_date, Object e_date){
		if(sangsiYN.equals("Y")) return "상시";
		return s_date + " ~ " + e_date.toString().substring(5);
	}
	
	
	/**
	 * 날짜 기간을 표현해주는 메서드..
	 * @param s
	 * @param e
	 * @return
	 */
	public static String formatSEDate(Object s, Object e){
		return s.toString().replaceAll("-", ".") + "~" + e.toString().replaceAll("-", ".");
	}

	
	/**
	 *   두 날짜 세션 타임 아웃 비교
	 * @author rocomo
	 * @return boolean
	 * @param Date sessTime, Date curTime, int timeOut
	 * @throws Exception
	 */
	public static boolean timeOutCheck(Date sessTime, Date curTime, int timeOut)
			throws Exception { 
		timeOut = timeOut * 1000 * 60; //1분 단위

		long checkTime = curTime.getTime() - sessTime.getTime();
		if(checkTime > timeOut){
			return false;
		}
		return true;
	}
	
	/**
	 *   두 날짜 세션 타임 아웃 비교 DATE 형식일때
	 * @author rocomo
	 * @return boolean
	 * @param Date sessTime, Date curTime, int timeOut
	 * @throws Exception
	 */
	public static boolean timeOutCheck(Date sessTime, Date curTime, Date timeOut)
			throws Exception { 

	
		if(curTime.getTime() > timeOut.getTime()){
			return false;
		}
		return true;
	}
  
	/**
	 *  String값의 Null여부를 체크한다.
	 * @author rocomo
	 * @return boolean
	 * @param String obj
	 * @throws Exception
	 */
	public static boolean isNull(String str) throws Exception {
		if (str == null || str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * 현재 날짜에 일수을 더한값이나 뺀값을 구한다. 
	 * @return String
	 * @param String format ( ex: 20140403 )
	 * @author rocomo
	 */
	public static String getDate(int iDay) {
		
		Calendar temp = Calendar.getInstance();
		StringBuffer sbDate = new StringBuffer();
	
		temp.add(Calendar.DAY_OF_MONTH, iDay);
	
		int nYear = temp.get(Calendar.YEAR);
		int nMonth = temp.get(Calendar.MONTH) + 1;
		int nDay = temp.get(Calendar.DAY_OF_MONTH);
	
		sbDate.append(nYear);
		
		if ( nMonth < 10 ) {
			sbDate.append("0");
			sbDate.append(nMonth);
		} else {
			sbDate.append(nMonth);
		}

		if ( nDay < 10 ) {
			sbDate.append("0");
			sbDate.append(nDay);
		} else {
			sbDate.append(nDay);
		}
		
		return sbDate.toString();
	}
	
	public static String getDate(String yyyymmdd, int day){
		Calendar temp = Calendar.getInstance();
		
		int year = Integer.parseInt(yyyymmdd.substring(0,4));
		int month = Integer.parseInt(yyyymmdd.substring(4,6))-1;
		int date = Integer.parseInt(yyyymmdd.substring(6,8));
		
		temp.set(year, month, date);
		temp.add(Calendar.DAY_OF_MONTH, day);
		
		String yy = "" + temp.get(Calendar.YEAR);
		String mm = (temp.get(Calendar.MONTH)+1) < 10 ? "0" + (temp.get(Calendar.MONTH)+1) : "" +(temp.get(Calendar.MONTH)+1);
		String dd = temp.get(Calendar.DATE) < 10 ? "0" + temp.get(Calendar.DATE) : "" + temp.get(Calendar.DATE);
		return yy+mm+dd;
	}
	
	
	public static String getStartWeekDate(String yyyymm, int order){
		String startWeekDate = "";
		try {
			int startDayOfWeek = getDayOfWeek(yyyymm+"01");
			String sDate = getDate(yyyymm+"01", -(startDayOfWeek - 2));
			startWeekDate = getDate(sDate, 7*(order-1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return startWeekDate;
	}
	
	public static String getEndWeekDate(String yyyymm, int order){
		String startWeekDate = "";
		try {
			int startDayOfWeek = getDayOfWeek(yyyymm+"01");
			String sDate = getDate(yyyymm+"01", -(startDayOfWeek - 2));
			startWeekDate = getDate(sDate, 6*order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return startWeekDate;
	}
	
	
	
	
}
