package com.app.util;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.app.util.ServerCode;
import com.app.commonVo.FileVo;
import com.app.common.dao.FileDao;
import com.app.util.CheckUtil;
import com.app.util.DateUtil;
import com.app.util.UploadUtil;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class MybatisClient {
	
	private static final Logger logger = LoggerFactory.getLogger(MybatisClient.class);
	private static SqlSessionFactory factory = null;
	private FileDao filedao;

	//Mapper Setting
	public static synchronized SqlSession getSqlMapperStatic(){
		return factory.openSession(true);
	}
	
	protected synchronized SqlSession getSqlMapper(){
		return factory.openSession(true);
	}
	
	//Paging
	protected ModelMap PageNaviModeMap(ModelMap modelMap, int totalRecord, int page_no, int maxrow) {

		PageNavi pn = new PageNavi(totalRecord, page_no, maxrow, maxrow);

		modelMap.put("prevLink", pn.getPrevLink());
		modelMap.put("endPage", pn.getEndpage());
		modelMap.put("endRecord", pn.getEndRecord());
		modelMap.put("nextLink", pn.getNextLink());
		modelMap.put("firstPage", pn.getFirstPage());
		modelMap.put("lastPage", pn.getLastPage());
		modelMap.put("pageLinks", pn.getPageLinks());
		modelMap.put("page_no", page_no);
		modelMap.put("totalRecord", totalRecord);
		modelMap.put("maxrow", maxrow);

		return modelMap;
	}
	
	//FileUpload
	protected String insertFileInfo(SqlSession mapper, ModelMap modelMap, MultipartFile file, String prefix)throws Exception{
		logger.info("########## Service insertFileInfo Start! ##########");
		String idx = "";
		FileVo mp = new FileVo();
		
		mp.setHost(ServerCode.get("upload_host"));
		
		// uploadfile 여부 확인
		if(file != null && file.getSize() > 0){
			FileOutputStream output = null;
			try{
				
				MultipartFile uploadFile = file;
				String fileName =  uploadFile.getOriginalFilename();
				
				if (CheckUtil.isNotEmpty(fileName)) {
					
					String s = "";
					String uploadFileName = UploadUtil.rename(fileName,prefix);
					String localPath = ServerCode.get("upload_file_path");
					
					String folderPath = DateUtil.toDateString("yyyyMM");
					
					if(localPath.indexOf("/") > -1){
						s = "/";
					}else{
						s = "\\";
					}
					localPath += prefix + s;
					localPath += folderPath;
					   
					String host_nm = ServerCode.get("upload_host");
					String url = ServerCode.get("upload_url");
					
					url += prefix+"/";
					url += folderPath+"/";
					url += uploadFileName;
					
					mp.setFile_name(fileName);
					mp.setHost(host_nm);
					mp.setUrl(url);
					mp.setFile_path(localPath+ s + uploadFileName);
					
					logger.debug("param : " + mp.toString());
					
					filedao.insertFile(mapper,mp);
					idx = mp.getFile_idx();

					logger.debug(">>>>> idx : "+idx);
					
					// directory 생성
					File dir = new File(localPath);
					if(!dir.exists()){
						dir.mkdirs();
					}
					
					localPath += s + uploadFileName; 
					byte[] fileData = uploadFile.getBytes();
					output = new FileOutputStream(localPath);
					output.write(fileData);
					output.close();
					
				}
		
			}catch(Exception e){
				//logger.error(">>>>> insertFileInfo file_id : " + e);
				 e.printStackTrace();
				idx = "";
				throw new Exception("FILE UPLOAD FAIL :: "+e.getMessage());
			
				
			}finally{
				if(output != null){
					try{output.close();}catch(Exception e){logger.error("file_id"+e);}
				}
			}
		}
		logger.info("########## Service insertFileInfo End! ##########");
		return idx;
	}
	
	protected int updateFileInfo(SqlSession mapper, ModelMap modelMap, MultipartFile file, String idx,String prefix)throws Exception{
		logger.info("########## Service updateFileInfo Start! ##########");
		
		int re = -1;
		FileVo mp = new FileVo();
		
		mp.setHost(ServerCode.get("upload_host"));
		
		// uploadfile 여부 확인
		if(file != null && file.getSize() > 0){
			
			FileOutputStream output = null;
			
			try{
				
				MultipartFile uploadFile = file;
				String fileName = uploadFile.getOriginalFilename();
				
				if (CheckUtil.isNotEmpty(fileName)) {
					
					String s = "";
					String uploadFileName = UploadUtil.rename(fileName,prefix);
					String localPath = ServerCode.get("upload_file_path");
					String folerPath = DateUtil.toDateString("yyyyMM");
					
					if(localPath.indexOf("/") > -1){
						s = "/";
					}else{
						s = "\\";
					}
					localPath += prefix + s;
					localPath += folerPath;
					   
					String host = ServerCode.get("upload_host");
					String url = ServerCode.get("upload_url");
					url += prefix+"/";
					url += folerPath+"/";
					url += uploadFileName;
					
					mp.setFile_name(fileName);
					mp.setHost(host);
					mp.setUrl(url);
					mp.setFile_path(localPath+ s + uploadFileName);
					
					logger.debug("localPath: "+localPath + s + uploadFileName);
					
					//mp.setIdx(idx);
					re = filedao.updateFile(mapper,mp);
					
					// directory 생성
					File dir = new File(localPath);
					
					if(!dir.exists()){
						dir.mkdirs();
					}
					
					localPath += s + uploadFileName; 
					byte[] fileData = uploadFile.getBytes();
					output = new FileOutputStream(localPath);
					output.write(fileData);
					output.close();
					
					// convert to thumbnail image
//					ImageUtil.createThumbnail(localPath, localPath, 300);
				}
			}catch(Exception e){
				logger.error(">>>>> updateFileInfo:"+e);
//				mapper.rollback();
				throw new Exception("FILE UPLOAD FAIL :: "+e.getMessage());
			}finally{
				if(output != null){
					try{output.close();}catch(Exception e){logger.error("updateFileInfo output.close():"+e);}
				}
			}
		}
		
		logger.info("########## Service updateFileInfo End! ##########");
		return re;
	}
	
	public static String insertFileInfo(HttpServletRequest request, String prefix)throws Exception{
		logger.info("########## Service insertFileInfo Start! ##########");
		String host = "";
		String url = "";
		
		try {
			String path = ServerCode.get("upload_file_path"); // 이미지가 저장될 주소
			
			String filename = "";
			String s = "";

			if(path.indexOf("/") > -1){
				s = "/";
			}else{
				s = "\\";
			}
			path += prefix;
			path += s+DateUtil.toDateString("yyyyMMdd");
			path += s+ DateUtil.toDateString("HH");
			
			// directory 생성
			File dir = new File(path);
			
			if(!dir.exists()){
				dir.mkdirs();
			}
			
			MultipartRequest multi=new MultipartRequest(request, path, 15*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			String upfile = multi.getFilesystemName("Filedata");
			
			
			if (upfile != null && !"".equals(upfile)) {
				
				host = ServerCode.get("upload_host");
				url = ServerCode.get("upload_url");
				
				String uploadFileName = UploadUtil.rename(upfile,prefix);
				
				// relative path
				url += prefix+"/";
				url += DateUtil.toDateString("yyyyMMdd")+"/";
				url += DateUtil.toDateString("HH")+"/";
		 		url += uploadFileName;


//	 			String dateString = formatter2.format(new java.util.Date());
				String moveFileName = uploadFileName;
				String fileExt = upfile.substring(upfile.lastIndexOf(".") + 1);
				File sourceFile = new File(path + File.separator + upfile);
				File targetFile = new File(path + File.separator + moveFileName);
				sourceFile.renameTo(targetFile);
				filename = moveFileName;
				
				logger.debug(">>>>> upfile : " + upfile);
				logger.debug(">>>>> targetFile : " + targetFile);
				logger.debug(">>>>> moveFileName : " + moveFileName);
				logger.debug(">>>>> filename : " + filename);
				logger.debug(">>>>> moveFileName : " + moveFileName);
				logger.debug(">>>>> host : "+host);
				logger.debug(">>>>> url : "+url);
				
				sourceFile.delete();
				
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("########## Service insertFileInfo End! ##########");
		return host+url;
	}
	
}
