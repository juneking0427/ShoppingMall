package com.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadUtil {

public static String rename(String realFileNm,String prefix){
        
        long currentTime = System.currentTimeMillis();   
        SimpleDateFormat simDf = new SimpleDateFormat("yyyyMMddHHmmss");   
        int randomNumber = (int)(Math.random()*100000);
        
        String uniqueFileName = prefix + simDf.format(new Date(currentTime)) + randomNumber;   
        
        String body = null;
        String ext = null;
        
        String realfileName = realFileNm;
        
        int dot = realfileName.lastIndexOf(".");
        
        if(dot != -1) {
            body = realfileName.substring(0, dot);   
            ext = realfileName.substring(dot);  // includes "."
        }else{
            body = realfileName;
            ext = "";
        } 
        
        realfileName = uniqueFileName + ext;
        
        return realfileName;
        
    }
}
