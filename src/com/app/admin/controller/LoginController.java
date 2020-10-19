package com.app.admin.controller;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

public class LoginController {
	@Autowired
	SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * Admin-Login
	 * */
	@RequestMapping("/login_admin.do")
	public ModelAndView adminLoginForm(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
		logger.info("########## LoginController :: adminLoginForm :: Start ##########");
		
		logger.info("########## LoginController :: adminLoginForm :: End ##########");
	return new ModelAndView("admin/login/login", modelMap);
	}
	
//	@ResponseBody
//	@RequestMapping("/checkLogin.do")
//	public String pmCheckLogin(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
//	logger.info("########## LoginController :: pmCheckLogin :: Start #########");
//
//		int result = sqlSession.selectOne("LoginMapper.getMberAppMCnt", inParam);
//		
//			if( result == 1 ){
//				modelMap.put("retCd", "success");
//			}else{
//				modelMap.put("retMsg", "아이디 또는 비밀번호가 틀립니다.");
//			}
//	
//	logger.info("########## LoginController :: pmCheckLogin :: End ##########");
//	return new Gson().toJson(modelMap);
//	}
}
