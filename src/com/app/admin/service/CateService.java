package com.app.admin.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.app.util.MybatisClient;

@Service
public class CateService extends MybatisClient{

	private static final Logger logger = LoggerFactory.getLogger(CateService.class);
	
	@Autowired
	private SqlSession sqlSession;
	public void selSqlSession(SqlSession sqlSession){ this.sqlSession = sqlSession;	}
	
	public ModelMap cateList(ModelMap modelMap, Map<String, Object> inParam) {
	logger.info("########## CateService :: cateList :: Start ##########");
	
	List<Map<String, Object>> cateList = sqlSession.selectList("CateMapper.cateList", inParam);
	
	modelMap.put("cateList", cateList);
	
	logger.info("########## CateService :: cateList :: Start ##########");
	return modelMap;
	}
	
	public ModelMap depth1CateReg(ModelMap modelMap, Map<String, Object> inParam) {
	logger.info("########## CateService :: depth1CateReg :: Start ##########");
	
	sqlSession.insert("CateMapper.depth1Reg", inParam);
	
	logger.info("########## CateService :: depth1CateReg :: Start ##########");
	return modelMap;
	}

	public ModelMap depthCateList(ModelMap modelMap, Map<String, Object> inParam) {
	logger.info("########## CateService :: depthCateList :: Start ##########");
	
		List<Map<String, Object>> depth1List = sqlSession.selectList("CateMapper.depth1List", inParam);
		
		modelMap.put("depth1List", depth1List);
	
	logger.info("########## CateService :: depthCateList :: Start ##########");
	return modelMap;
	}

	public ModelMap depth2List(ModelMap modelMap, Map<String, Object> inParam) {
	logger.info("########## CateService :: depth2List :: Start ##########");
		
		List<Map<String, Object>> depth2List = sqlSession.selectList("CateMapper.depth2List", inParam);
		modelMap.put("depth2List", depth2List);

	logger.info("########## CateService :: depth2List :: Start ##########");
	return modelMap;
	}
	
	public ModelMap depth3List(ModelMap modelMap, Map<String, Object> inParam) {
		logger.info("########## CateService :: depth3List :: Start ##########");
		
		List<Map<String, Object>> depth3List = sqlSession.selectList("CateMapper.depth3List", inParam);
		modelMap.put("depth3List", depth3List);

	logger.info("########## CateService :: depth3List :: Start ##########");
	return modelMap;
	}

	public void cateDelete(ModelMap modelMap, Map<String, Object> inParam) {
	logger.info("########## CateService :: depth2List :: Start ##########");
	
		sqlSession.update("CateMapper.depthUse", inParam);
	
	logger.info("########## CateService :: depth2List :: Start ##########");
	}


//	List<Map<String, Object>> listBoard = sqlSession.selectList("BoardMapper.boardList", inParam);


}
