package com.app.hancom.service;

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
public class MainService extends MybatisClient{

	private static final Logger logger = LoggerFactory.getLogger(MainService.class);
	
	@Autowired
	private SqlSession sqlSession;
	public void selSqlSession(SqlSession sqlSession){ this.sqlSession = sqlSession;	}
	
	public ModelMap mainView(ModelMap modelMap, Map<String, Object> inParam) {
	logger.info("########## MainService :: mainView :: Start ##########");

		List<Map<String, Object>> depth1List = sqlSession.selectList("MainMapper.depth1List", inParam);
		List<Map<String, Object>> depth2List = sqlSession.selectList("MainMapper.depth2List", inParam);
		List<Map<String, Object>> depth3List = sqlSession.selectList("MainMapper.depth3List", inParam);
		
		modelMap.put("depth1List", depth1List);
		modelMap.put("depth2List", depth2List);
		modelMap.put("depth3List", depth3List);
	
	logger.info("########## MainService :: mainView :: End ##########");
	return modelMap;
	}

	public ModelMap selectList(ModelMap modelMap, Map<String, Object> inParam) {
	logger.info("########## MainService :: selectList :: End ##########");
		
		List<Map<String, Object>> depth1List = sqlSession.selectList("MainMapper.depth1List", inParam);
		List<Map<String, Object>> depth2List = sqlSession.selectList("MainMapper.depth2List", inParam);
		List<Map<String, Object>> depth3List = sqlSession.selectList("MainMapper.depth3List", inParam);
		
		modelMap.put("depth1List", depth1List);
		modelMap.put("depth2List", depth2List);
		modelMap.put("depth3List", depth3List);
	
		List<Map<String, Object>> selectList = sqlSession.selectList("MainMapper.selectList", inParam);
		modelMap.put("selectList", selectList);
		
	logger.info("########## MainService :: selectList :: End ##########");
	return modelMap;
	}

	
	public ModelMap detailMain(ModelMap modelMap, Map<String, Object> inParam) {
	logger.info("########## MainService :: detailMain :: End ##########");
		
		List<Map<String, Object>> depth1List = sqlSession.selectList("MainMapper.depth1List", inParam);
		List<Map<String, Object>> depth2List = sqlSession.selectList("MainMapper.depth2List", inParam);
		List<Map<String, Object>> depth3List = sqlSession.selectList("MainMapper.depth3List", inParam);
		
		modelMap.put("depth1List", depth1List);
		modelMap.put("depth2List", depth2List);
		modelMap.put("depth3List", depth3List);
	
		Map<String, Object> boardDetail = sqlSession.selectOne("MainMapper.boardDetail", inParam);
		modelMap.put("boardDetail", boardDetail);
		
	logger.info("########## MainService :: detailMain :: End ##########");
	return modelMap;
	}

	public ModelMap loginReg(ModelMap modelMap, Map<String, Object> inParam) {
	logger.info("########## MainService :: loginReg :: Start ##########");
		
		sqlSession.insert("MainMapper.loginReg", inParam);
		
	logger.info("########## MainService :: loginReg :: End ##########");
	return modelMap;
	}

	public ModelMap loginCheck(ModelMap modelMap, Map<String, Object> inParam) {
	logger.info("########## MainService :: loginCheck :: Start ##########");
	
	int result = sqlSession.selectOne("MainMapper.MberCnt", inParam);
	
		if( result == 1 ){
			modelMap.put("retCd", "success");
		}else{
			modelMap.put("retMsg", "아이디 또는 비밀번호가 틀립니다.");
		}
	
	logger.info("########## MainService :: loginCheck :: End ##########");
	return modelMap;
	}
	
}
