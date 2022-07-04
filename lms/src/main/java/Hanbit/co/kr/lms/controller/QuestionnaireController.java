package Hanbit.co.kr.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	//설문지 목록 불러오기 get
		@PostMapping("/questionnaire/getQuestionnaireList")
		public String getQuestionnaireList(
				@RequestParam(value="quelist[]") List<Integer> quelistmap
				,@RequestParam(value="checklist[]") List<Integer> checklistmap
				,HttpSession session) {
			
			// 문제 번호 가져오기 
			log.debug( CF.KYJ +"[QuestionnaireController PostMapping quelistmap]: "+quelistmap+ CF.RESET);
			
			// 응답한 번호 가져오기
			log.debug( CF.KYJ +"[QuestionnaireController PostMapping checklistmap]: "+checklistmap+ CF.RESET);
			
			//세션에 있는 아이디 값 가져옴
			String studentId = (String) session.getAttribute("sessionMemberId");
			log.debug( CF.KYJ +"[QuestionnaireController PostMapping studentId]: "+ studentId + CF.RESET);
			
			// 맵으로 묶기 
			Map <String,Object> map = new HashMap<>();
			map.put("studentId", studentId);
			map.put("quelistmap", quelistmap);
			map.put("checklistmap", checklistmap);
			
			questionnaireService.selectQuestionnairepoint(map);
			

			return "redirect:/home/index";
		}

}
