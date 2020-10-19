package com.app.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.admin.service.BoardService;

@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService service;
	
	@RequestMapping(value="/adminBoardList.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView boardList(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
	logger.info("########## BoardController :: boardList :: Start ##########");
		
		modelMap = service.boardList(modelMap, inParam);
	
	logger.info("########## BoardController :: boardList :: End ##########");
	return new ModelAndView("admin/board/boardList", modelMap);
	}

	@RequestMapping(value="/boardRegForm.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView boardRegForm(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
	logger.info("########## BoardController :: boardRegForm :: Start ##########");
	
		modelMap = service.depthCateList(modelMap, inParam);
	
	logger.info("########## BoardController :: boardRegForm :: End ##########");
	return new ModelAndView("admin/board/boardInsert", modelMap);
	}

	@RequestMapping(value="/boardReg.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView boardReg(ModelMap modelMap, @RequestParam Map<String, Object> inParam, MultipartHttpServletRequest multipartRequest){
	logger.info("########## BoardController :: boardReg :: Start ##########");
	
	
		MultipartFile file = multipartRequest.getFile("uploadfile");
		inParam.put("uploadFile", file);
		
		modelMap = service.boardReg(modelMap, inParam);
	
	logger.info("########## BoardController :: boardReg :: End ##########");
	return new ModelAndView("redirect:/adminBoardList.do", modelMap);
	}

	@RequestMapping(value="/boardDetail.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView boardDetail(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
	logger.info("########## BoardController :: boardDetail :: Start ##########");
	
	modelMap = service.boardDetail(modelMap, inParam);
	
	logger.info("########## BoardController :: boardDetail :: End ##########");
	return new ModelAndView("admin/board/boardDetail", modelMap);
	}

	@RequestMapping(value="/boardUpdate.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView boardUpdate(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
	logger.info("########## BoardController :: boardUpdate :: Start ##########");
	
	modelMap = service.boardUpdate(modelMap, inParam);
	
	logger.info("########## BoardController :: boardUpdate :: End ##########");
	return new ModelAndView("redirect:/adminBoardList.do", modelMap);
	}

	@RequestMapping(value="/boardDel.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView boardDel(ModelMap modelMap, @RequestParam Map<String, Object> inParam){
	logger.info("########## BoardController :: boardDel :: Start ##########");
	
	
	logger.info("########## BoardController :: boardDel :: End ##########");
	return new ModelAndView("redirect:/adminBoardList.do", modelMap);
	}

	/**
	 * File Upload PopUP
	 * */
//	@RequestMapping(value="/admin/main/popupFileUplaod.do", method = {RequestMethod.GET, RequestMethod.POST} )
	@RequestMapping(value="/popFileUpload.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView popupMainPopup(HttpServletRequest request, ModelMap modelMap, @RequestParam Map<String, Object> inParam, HttpServletRequest response , RedirectAttributes redirectAttributes){
	logger.info("########## BoardController :: popupFileUpload :: Start ##########");
	
	logger.info("########## BoardController :: popupFileUpload :: End ##########");
	return new ModelAndView("common/popupFileUpload", modelMap);
	}
	
	
}
