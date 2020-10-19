package com.app.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.tomcat.util.http.fileupload.IOUtils;

// 공통 코드 읽어서 처리 하는 것으로 ComCode 자체가 전역 변수로 사용 하면 됨.
// ComCode.get("sdfsdf");
// java jsp 동일하게 사용함.
public class ServerCode {
	
	public static Properties prop =null;

	public  ServerCode ()
	{
		try{
			prop = new Properties();
			InputStream testPropertiesInput = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf/server.xml");
			try {
				prop.loadFromXML( testPropertiesInput );
			}
	        
	        catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
	               IOUtils.closeQuietly(testPropertiesInput);
	         }
		    
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		 static{
             //Just to initialize the properties
             new ServerCode();
        }        
	
	
	public static String to_utf8(String str) {
		String val = "";
		try {
			val = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
		}
		return val;
	}
		
	public static String get(String Key){
		return ( prop.getProperty(Key) ) ;
	}
	
	public static int getInt(String Key){
		return Integer.parseInt(prop.getProperty(Key));
	}
}