package Hanbit.co.kr.lms.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.QuestionnaireService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Questionnaire;
import Hanbit.co.kr.lms.vo.Registration;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j

public class QuestionnaireController {
	@Autowired QuestionnaireService questionnaireService;
	
	// 강의 평가 강좌 리스트 
	@GetMapping("/questionnaire/getLecQuestionnaireList")
	public String getLecQuestionnaireList(Model model
			, HttpSession session) {
		
		//세션에 있는 아이디 값 가져옴
		String studentId = (String) session.getAttribute("sessionMemberId");
		log.debug( CF.KYJ +"[QuestionnaireController PostMapping studentId]: "+ studentId + CF.RESET);

		// 아이디에 해당하는 강의 평가 강좌 리스트 
		List<Registration> selectLecList = questionnaireService.lecList(studentId);
		model.addAttribute("selectLecList",selectLecList);
		
		return "questionnaire/getLecQuestionnaireList";
	}
	
	//설문지 목록 불러오기 get
	@GetMapping("/questionnaire/getQuestionnaireList")
	public String getQuestionnaireList(Questionnaire questionnaire
			, Model model
			, HttpSession session
			,@RequestParam(name="lectureName") String lectureName) {
		
		//세션에 있는 아이디 값 가져옴
		String studentId = (String) session.getAttribute("sessionMemberId");
		log.debug( CF.KYJ +"[QuestionnaireController GetMapping studentId]: "+ studentId + CF.RESET);
		
		// 설문 여부 체크
		int row = questionnaireService.selectcheck(studentId);
		
		if(row != 0) {
			log.debug( CF.KYJ +"[QuestionnaireController GetMapping studentId]: "+ "설문을 이미 함" + CF.RESET);
			return "redirect:/questionnaire/getLecQuestionnaireList";
		}else {
		
			log.debug( CF.KYJ +"[QuestionnaireController GetMapping studentId]: "+ lectureName + CF.RESET);
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
	
	
		// 설문 응답 완료	
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
	
			log.debug( CF.KYJ +"[QuestionnaireController PostMapping]: "+ "설문 성공" + CF.RESET);
			
			return "redirect/:home/index";

	}

		// 설문 평점 확인
		@GetMapping("/questionnaire/getStatsQuestionnaireList")
		public String getStatsQuestionnaireList(Model model) {
			
			// 수강이 열리고 강좌 평가가 된 강의들의 리스트와 반올림된 평점
			Map<String,Object> selectLecScore = questionnaireService.selectScorelectureName();
			log.debug( CF.KYJ +"[QuestionnaireController GetMapping selectLecScore]: "+ selectLecScore + CF.RESET);
			
			model.addAttribute("selectLecScore", selectLecScore);
			return "questionnaire/getStatsQuestionnaireList";
		}
		
		// 강의 설문 카테고리별 평점
		@GetMapping("/questionnaire/getStatsQuestionnaireListOne")
		public String getStatsQuestionnaireListOne(
				Model model		
				,@RequestParam (name="registrationNo") int registrationNo) {
			
			log.debug( CF.KYJ +"[QuestionnaireController GetMapping getStatsQuestionnaireListOne registrationNo]: "+ registrationNo + CF.RESET);
			
			// 첫번쨰 질문 보여주기
			int checknum = 1;
			log.debug( CF.KYJ +"[QuestionnaireController GetMapping getStatsQuestionnaireListOne checknum]: "+ checknum + CF.RESET);

			// 카테고리별 점수 
			List<Map<String,Object>> returnlist = questionnaireService.CategoryScore(registrationNo);
			log.debug( CF.KYJ +"[QuestionnaireController GetMapping getStatsQuestionnaireListOne returnlist]: "+ returnlist + CF.RESET);
			
			// 카테고리 리스트 
			List<String> categoryList = new ArrayList<>();
			// 점수 리스트 
			List<BigDecimal> categoryScoreList = new ArrayList<>();
			
			// returnlist의 횟수만큼 반복하여 카테고리와 점수를 출력
			for (Map<String, Object> map : returnlist) {
				String category = "'"+(String)map.get("category")+"'";
				categoryList.add(category); // 카테고리 리스트 넣기
				BigDecimal var = (BigDecimal) map.get("selectedEvaluationNo"); // 점수 추출
				categoryScoreList.add(var);// 카테고리 점수  넣기
			}
			
			log.debug( CF.KYJ +"[QuestionnaireController GetMapping getStatsQuestionnaireListOne categoryList: "+categoryList + CF.RESET);
			log.debug( CF.KYJ +"[QuestionnaireController GetMapping getStatsQuestionnaireListOne categoryScoreList: "+categoryScoreList + CF.RESET);
//			
//			log.debug( CF.KYJ +"[QuestionnaireController GetMapping getStatsQuestionnaireListOne categoryList]: "+ categoryList + CF.RESET);
//			log.debug( CF.KYJ +"[QuestionnaireController GetMapping getStatsQuestionnaireListOne categoryScoreList]: "+ categoryScoreList + CF.RESET);
			
			model.addAttribute("registrationNo", registrationNo);
			model.addAttribute("checknum", checknum);
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("categoryScoreList", categoryScoreList);
			return "questionnaire/getStatsQuestionnaireListOne";
		}
}
