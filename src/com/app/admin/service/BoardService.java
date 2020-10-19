package com.app.admin.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.app.commonVo.FileVo;
import com.app.util.DateUtil;
import com.app.util.MybatisClient;
import com.app.util.ServerCode;
import com.app.util.UploadUtil;

@Service
public class BoardService extends MybatisClient{

	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	
	@Autowired
	private SqlSession sqlSession;
	public void selSqlSession(SqlSession sqlSession){ this.sqlSession = sqlSession;	}
	
	
	public ModelMap boardList(ModelMap modelMap, Map<String, Object> inParam) {
	logger.info("########## BoardService :: boardList :: Start ##########");

	int page_no = 1;
	int maxrow = 10;
	int rownum = 0;
	
		if(inParam.get("page_no") == null || "".equals(inParam.get("page_no").toString())){
			inParam.put("page_no", page_no);
		}else{
			page_no = Integer.parseInt((String) inParam.get("page_no"));
		}
	
		maxrow = 10;
		inParam.put("maxrow", maxrow);
		
		modelMap.put("searchName", inParam.get("searchName"));
		
		List<Map<String, Object>> listBoard = sqlSession.selectList("BoardMapper.boardList", inParam);
		int totalRecord = sqlSession.selectOne("BoardMapper.boardCnt", inParam);
		
		logger.debug(" TotalCnt ::: ::: " + totalRecord);
		
		rownum = totalRecord - (page_no -1) * maxrow;
		modelMap =	PageNaviModeMap ( modelMap,  totalRecord , page_no,  maxrow);
		modelMap.put("totCnt", rownum);
		modelMap.put("endPage", modelMap.get("endPage"));

		modelMap.put("listBoard", listBoard);

	logger.info("########## BoardService :: boardList :: End ##########");
	return modelMap;
	}

	public ModelMap depthCateList(ModelMap modelMap, Map<String, Object> inParam) {
	logger.info("########## BoardService :: depthCateList :: Start ##########");

		List<Map<String, Object>> depth1List = sqlSession.selectList("CateMapper.depth1List", inParam);
		modelMap.put("depth1List", depth1List);
	
	logger.info("########## BoardService :: depthCateList :: End ##########");
	return modelMap;
	}

	public ModelMap boardReg(ModelMap modelMap, Map<String, Object> inParam) {
	logger.info("########## BoardService :: depthCateList :: Start ##########");
	
		try {
			if(inParam.get("uploadFile") != null){
				
				String idx = "";
				FileVo mp = new FileVo();
				
				mp.setHost(ServerCode.get("upload_host"));
				
				FileOutputStream output = null;
				
					try{
						MultipartFile uploadFile = (MultipartFile)inParam.get("uploadFile");
						String fileName =  uploadFile.getOriginalFilename();
						
						String s = "";
						String uploadFileName = UploadUtil.rename(fileName , "contents");
						String localPath = ServerCode.get("upload_file_path");
						
						String folderPath = DateUtil.toDateString("yyyyMM");
						
							if(localPath.indexOf("/") > -1){
								s = "/";
							}else{
								s = "\\";
							}
							
						localPath += "contents" + s;
						localPath += folderPath;
						
						String host_nm = ServerCode.get("upload_host");
						String url = ServerCode.get("upload_url");
						
						url += "contents" + "/";
						url += folderPath+"/";
						url += uploadFileName;
						
						mp.setFile_name(fileName);
						mp.setHost(host_nm);
						mp.setUrl(url);
						mp.setFile_path(localPath + s + uploadFileName);
						
						inParam.put("file_name", fileName);
						inParam.put("host_nm", host_nm);
						inParam.put("file_path", localPath + s + uploadFileName);
						inParam.put("url", url);
						
						logger.debug("param : " + mp.toString());
						logger.debug("param : " + inParam.toString());
						
						sqlSession.insert("BoardMapper.fileReg", inParam);
						idx = (String) inParam.get("file_idx");

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
						
					}catch(Exception e){
						e.printStackTrace();
					}
				
				inParam.put("FILE_IDX", idx);
			}
			
			sqlSession.insert("BoardMapper.boardReg", inParam);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	logger.info("########## BoardService :: depthCateList :: End ##########");
	return modelMap;
	}

	public ModelMap boardDetail(ModelMap modelMap, Map<String, Object> inParam) {
	logger.info("########## BoardService :: boardDetail :: Start ##########");
	
		Map<String, Object> boardDetail = sqlSession.selectOne("BoardMapper.boardDetail", inParam);
		modelMap.put("boardDetail", boardDetail);
	
	logger.info("########## BoardService :: boardDetail :: Start ##########");
	return modelMap;
	}

	public ModelMap boardUpdate(ModelMap modelMap, Map<String, Object> inParam) {
	logger.info("########## BoardService :: boardUpdate :: Start ##########");
	
		try {
			sqlSession.update("BoardMapper.boardUpdate", inParam);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	logger.info("########## BoardService :: boardUpdate :: End ##########");
	return modelMap;
	}
	
}
