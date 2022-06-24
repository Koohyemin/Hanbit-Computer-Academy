package Hanbit.co.kr.lms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.QuestionnaireService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Questionnaire;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class QuestionnaireController {
	@Autowired QuestionnaireService questionnaireService;
	
	//설문지 목록 불러오기 get
	@GetMapping("/questionnaire/getQuestionnaireList")
	public String getQuestionnaireList(Questionnaire questionnaire, Model model
			,@RequestParam(name="lectureName", defaultValue="자바 웹 스피드 취업반") String lectureName) {
		
		//list에 서비스호출
		List<Questionnaire> list = questionnaireService.selectQuestionnaireList(lectureName);
		
		//list + lectureName 디버깅
		log.debug( CF.KHN +"[QuestionnaireController GetMapping list]: "+list+ CF.RESET);
		log.debug( CF.KHN +"[QuestionnaireController GetMapping lectureName]: "+lectureName+ CF.RESET);
		
		//model값 list > jsp로 전달
		model.addAttribute("list", list);
		
		//뷰 포워딩
		return "questionnaire/getQuestionnaireList";
	}
}
