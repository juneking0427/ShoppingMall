package com.app.admin.controller;

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

import com.app.admin.service.CateService;
import com.google.gson.Gson;

@Controller
public class CateController {

	private static final Logger logger = LoggerFactory.getLogger(CateController.class);

	@Autowired
	CateService service;
	
	@RequestMapping(value="/adminCateList.do", method ={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView cateList(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
	logger.info("########## CateController :: cateList :: Start ##########");
	
	modelMap = service.cateList(modelMap, inParam);
	
	logger.info("########## CateController :: cateList :: End ##########");	
	return new ModelAndView("admin/cate/cateList", modelMap); 
	}

	@RequestMapping(value="/adminCateRegForm.do", method ={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView cateRegForm(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
	logger.info("########## CateController :: cateRegForm :: Start ##########");
	
	modelMap = service.depthCateList(modelMap, inParam);
	
	logger.info("########## CateController :: cateRegForm :: End ##########");	
	return new ModelAndView("admin/cate/cateInsert", modelMap); 
	}
	
	@RequestMapping(value = "/depth1Cate.jp")
	public ModelAndView depth1Cate(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
	logger.info("########## CateController :: depth1Cate :: Start ##########");
	
	modelMap = service.depth1CateReg(modelMap, inParam);
	
	logger.info("########## CateController :: depth1Cate :: End ##########");
	return new ModelAndView("redirect:/adminCateRegForm.do");
	}
	
	/********
	 * aJax *
	 ****** */
	@ResponseBody
	@RequestMapping(value = "/depth2List.jp")
	public String depth2List(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
	logger.info("########## CateController :: depth2List :: Start ##########");
		
		modelMap = service.depth2List(modelMap, inParam);
		
	logger.info("########## CateController :: depth2List :: End ##########");
	return new Gson().toJson(modelMap);
	}

	@ResponseBody
	@RequestMapping(value = "/depth3List.jp")
	public String depth3List(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
		logger.info("########## CateController :: depth2List :: Start ##########");
		
		modelMap = service.depth3List(modelMap, inParam);
		
		logger.info("########## CateController :: depth2List :: End ##########");
		return new Gson().toJson(modelMap);
	}
	

	@RequestMapping(value="/adminCateDel.do", method ={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView cateDel(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
	logger.info("########## CateController :: cateDel :: Start ##########");
	
		service.cateDelete(modelMap, inParam);
	
	logger.info("########## CateController :: cateDel :: End #c#########");	
	return new ModelAndView("redirect:/adminCateList.do", modelMap); 
	}

	
}
