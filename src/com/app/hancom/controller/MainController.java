package com.app.hancom.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.hancom.service.MainService;
import com.google.gson.Gson;

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	MainService service;
	
	@RequestMapping(value="/mainForm.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView mainForm(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
	logger.info("########## MainController :: MainView :: Start ##########");
		
		modelMap = service.mainView(modelMap, inParam);
	
	logger.info("########## MainController :: MainView :: End ##########");
	return new ModelAndView("hancom/main/main", modelMap);
	}

	@RequestMapping(value="/mainList.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView mainList(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
	logger.info("########## MainController :: mainList :: Start ##########");
		
		modelMap = service.selectList(modelMap, inParam);
		
	logger.info("########## MainController :: mainList :: End ##########");
	return new ModelAndView("hancom/main/list", modelMap);
	}

	@RequestMapping(value="/mainDetail.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView mainDetail(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
		logger.info("########## MainController :: mainDetail :: Start ##########");
		
		modelMap = service.detailMain(modelMap, inParam);
		
		logger.info("########## MainController :: mainDetail :: End ##########");
		return new ModelAndView("hancom/main/detail", modelMap);
	}
	
	/**
	 * Login_User
	 * */
	
	@RequestMapping("/login_user.do")
	public ModelAndView userLoginForm(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
	logger.info("########## LoginController :: userLoginForm :: Start ##########");
	
	logger.info("########## LoginController :: userLoginForm :: End ##########");
	return new ModelAndView("hancom/login/login", modelMap);
	}
	
	@RequestMapping("/loginRegF.do")
	public ModelAndView login_RegForm(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
	logger.info("########## LoginController :: login_RegForm :: Start ##########");
	logger.info("########## LoginController :: login_RegForm :: End ##########");
	return new ModelAndView("hancom/login/loginForm", modelMap);
	}

	@RequestMapping("/loginReg.do")
	public ModelAndView login_Reg(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
	logger.info("########## LoginController :: login_RegForm :: Start ##########");
	
		service.loginReg(modelMap, inParam);
	
	logger.info("########## LoginController :: login_RegForm :: End ##########");
	return new ModelAndView("redirect:/mainForm.do", modelMap);
	}
	
	@ResponseBody
	@RequestMapping("/checkLogin.do")
	public String pmCheckLogin(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
	logger.info("########## LoginController :: pmCheckLogin :: Start #########");

		logger.debug(">>> >>> : " + inParam.toString());
	
		service.loginCheck(modelMap, inParam);
	
	logger.info("########## LoginController :: pmCheckLogin :: End ##########");
	return new Gson().toJson(modelMap);
	}
	
}
