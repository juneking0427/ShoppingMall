package com.app.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


 

public class CheckUtil {


	/**
	 * 객체가 가지고 있는 값이 공백인지 아닌지 판별하여 true, false를 리턴
	 * @return boolean
	 * @param Object obj
	 * @author rocomo
	 * @throws Exception
	 */
	public static boolean isEmpty(Object obj) throws Exception {
		if (obj instanceof String)
			return obj == null || "".equals(obj.toString().trim());
		else if (obj instanceof List)
			return obj == null || ((List) obj).isEmpty();
		else if (obj instanceof Map)
			return obj == null || ((Map) obj).isEmpty();
		else if (obj instanceof Object[])
			return obj == null || Array.getLength(obj) == 0;
		else
			return obj == null;
	}
	
	/**
	 * 객체가 가지고 있는 값이 공백인지 아닌지 판별하여 true, false를 리턴
	 * @return boolean
	 * @param Object obj
	 * @author rocomo
	 * @throws Exception
	 */
	public static boolean isNotEmpty(Object s) throws Exception {
		return !isEmpty(s);
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
	 * Integer값의 Null여부를 체크한다
	 * @author rocomo
	 * @return boolean
	 * @param Integer obj
	 * @throws Exception
	 */
	public static boolean isNull(Integer integer) throws Exception {
		if (integer == null || integer.equals("")) {
			return true;
		} else {
			return false;
		}
	}
	

	/**
	 * 대상 문자열이 숫자인지 아닌지를 판단한다. (오직 숫자로만 이루어 져야함)
	 * @author rocomo
	 * @return boolean
	 * @param String obj
	 * @throws Exception
	 */
	public static boolean isNumber(String s) throws Exception {
		if (s == null || "".equals(s.trim())) {
			return false;
		}

		s = s.replaceAll(",", "");
		s = s.replaceAll("\\.", "");

		for (int i = 0, len = s.length(); i < len; i++) {
			if (!Character.isDigit(s.charAt(i)))
				return false;
		}

		return true;
	}
	

	/**
	 * 한글인지 체크 (한글이 아닌게 하나라도 있으면 false)
	 * @author rocomo
	 * @return boolean
	 * @param String obj
	 * @throws Exception
	 */
	public static boolean isKorean(String s) throws Exception {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (0xAC00 <= c && c <= 0xD7A3) {
				// korean : 가 ~ 힣 범위 체크
				continue;
			} else if (0x3131 <= c && c <= 0x318E) {
				// korean : one character (consonant or vowel)
				return false;
			} else if ((0x61 <= c && c <= 0x7A) || (0x41 <= c && c <= 0x5A)) {
				// english
				return false;
			} else if (0x30 <= c && c <= 0x39) {
				// integer/decimal
				return false;
			} else if (c <= 0x20) {
				// space
				return false;
			} else {
				// unknown
				return false;
			}
		}
		return true;
	}

	

	/**
	 * 이메일체크
	 * @author rocomo
	 * @return boolean
	 * @param String obj
	 * @throws Exception
	 */
	public static boolean validEmail(String s) throws Exception {
		int size = s.length();
		String SpecialTxt[] = { "!", "#", "$", "%", "^", "&", "*", "(", ")",
				"<", ">" };
		if (size > 50)
			return false;

		int i = s.indexOf('@');
		if (i < 0)
			return false;
		i = s.indexOf('.');
		if (i < 0)
			return false;
		for (int j = 0; j < SpecialTxt.length; j++) {
			i = s.indexOf(SpecialTxt[j]);
			if (i >= 0)
				return false;
		}
		return true;
	}
	
	/**
	 * phone format check 폰 포멧 체크 ,1)###-###-####, 2)#-###-###-####, 3)###-####, 4)##########,5)#######, 6)(###)-###-####
	 * @author rocomo
	 * @return int 1,0
	 * @param String obj
	 * @throws Exception
	 */
	public static int checkPhoneFmt(String num) {
		try {
			String expression = "^(?=.{7,32}$)(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*((\\s?x\\s?|ext\\s?|extension\\s?)\\d{1,5}){0,1}$";
			CharSequence inputStr = num;
			Pattern pattern = Pattern.compile(expression);
			Matcher matcher = pattern.matcher(inputStr);
			int x = 0, y = 0;
			char[] value = num.toCharArray();
			for (int i = 0; i < value.length; i++) {
				if (value[i] == '(')
					x++;
				if (value[i] == ')'
						&& ((value[i + 1] >= 48 && value[i + 1] <= 57) || value[i + 1] == '-'))
					y++;
			}
			if (matcher.matches() && x == y)
				return 1; // valid number
			else
				return 0; // invalid number
		} catch (Exception ex) {
			return 0;
		}
	}
	
	
	public static void checkStrObj(Object obj){
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			
			
	        for(int i=0; i<=fields.length-1;i++){
	            fields[i].setAccessible(true);
	            
	            if (fields[i].get(obj) != null && fields[i].get(obj).getClass().isArray()) {
	            	String[] innerFields = (String[]) fields[i].get(obj);
	            	for (int j = 0; j <= innerFields.length-1; j++) {
						innerFields[j] = checkStr(innerFields[j].toString());
					}
	            	
				} else if(fields[i].get(obj) != null && fields[i].get(obj).getClass().getName().indexOf("String") > 0){  
	            	// 패스워드를 제외한 나머지 검사
	            	if(!fields[i].getName().equals("passwd") || !fields[i].getName().equals("strTerms")){
	            		String str = checkStr(fields[i].get(obj).toString());
//	            		System.out.println("str : " + str + "// fields[i].get(obj).toString() : " + fields[i].get(obj).toString());
	            		fields[i].set(obj, str);
	            	}
	            }
	        }
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static void checkMap(Map<String, Object> map){
		try {
			if(map != null){
				
				Iterator<Entry<String,Object>> itr = map.entrySet().iterator();
				
				while(itr.hasNext()){
					Map.Entry<String, Object> entry = itr.next();
					if(entry.getValue() != null){
						if(entry.getValue().getClass().getName().indexOf("String") > 0){
							if("title".indexOf(entry.getKey().toLowerCase()) > -1){
								String str = checkTitle(entry.getValue().toString());
								entry.setValue(str);
							} else if("passwd|pwd|password|contents".indexOf(entry.getKey().toLowerCase()) == -1){
								String str = checkStr(entry.getValue().toString());
								entry.setValue(str);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static String checkTitle(String checkValue){
		String result = "";
		if(checkValue != null && checkValue.length() > 0) {
			checkValue = checkValue.replaceAll("\\<", "");
			checkValue = checkValue.replaceAll("\\>", "");
			checkValue = checkValue.replaceAll("\\;", "");
			
			result = checkValue.trim() ;
		} else {
			result = checkValue ;
		}
		return result;
	}
	
	public static String checkStr(String checkValue) {
		String result = "";
		if(checkValue != null && checkValue.length() > 0) {
			//XSS
			checkValue = checkValue.replaceAll("\\^", "");
			checkValue = checkValue.replaceAll("\\{", "");
			checkValue = checkValue.replaceAll("\\}", "");
			checkValue = checkValue.replaceAll("\\(", "");
			checkValue = checkValue.replaceAll("\\)", "");
			checkValue = checkValue.replaceAll("\\[", "");
			checkValue = checkValue.replaceAll("\\]", "");
			checkValue = checkValue.replaceAll("\\<", "");
			checkValue = checkValue.replaceAll("\\>", "");
			checkValue = checkValue.replaceAll("\"", "");
			checkValue = checkValue.replaceAll("\'", "");
			checkValue = checkValue.replaceAll("\\;", "");
			checkValue = checkValue.replaceAll("\\-", "");
			checkValue = checkValue.replaceAll("\\+", "");
			
			result = checkValue.trim() ;
		} else {
			result = checkValue ;
		}
		return result;
	}
	
	// 정달
	public static String checkStr2(String checkValue) {
		String result = "";
		if(checkValue != null && checkValue.length() > 0) {
			//XSS
			checkValue = checkValue.replaceAll("\\;", "&#59;");
			checkValue = checkValue.replaceAll("\\^", "&#94;");
			checkValue = checkValue.replaceAll("\\{", "&#123;");
			checkValue = checkValue.replaceAll("\\}", "&#125;");
			checkValue = checkValue.replaceAll("\\(", "&#40;");
			checkValue = checkValue.replaceAll("\\)", "&#41;");
			checkValue = checkValue.replaceAll("\\[", "&#91;");
			checkValue = checkValue.replaceAll("\\]", "&#93;");
			checkValue = checkValue.replaceAll("\\<", "&lt;");
			checkValue = checkValue.replaceAll("\\>", "&gt;");
			checkValue = checkValue.replaceAll("\"", "&quot;");
			checkValue = checkValue.replaceAll("\'", "&#39;");
			checkValue = checkValue.replaceAll("\\-", "&#45;");
			checkValue = checkValue.replaceAll("\\+", "&#43;");
			checkValue = checkValue.replaceAll("\\/", "&#47;");
			checkValue = checkValue.replaceAll("\\\\", "&#92;");
			result = checkValue.trim() ;
		} else {
			result = checkValue ;
		}
		return result;
	}
	
}

