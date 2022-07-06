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

import Hanbit.co.kr.lms.service.LecQnaService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.LecQuestion;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LecQnaController {
	
	// !아직 filter에 추가 전!
	
	@Autowired LecQnaService lecQnaService;
	@Autowired HttpSession session;
	
	@GetMapping("lecQna/lecQnaList")
	public String lecQuestionListByPage(Model model,
			@RequestParam(name = "lectureName", defaultValue = "") String lectureName, // 한페이지당, 10개 게시글 출력
			@RequestParam(name = "currentPage", defaultValue = "1") int currentPage, // 현재페이지, 1페이지부터 시작
			@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) { 
			
			// Service에 처리한 코드를 이용하여 매개값 대입
			Map<String, Object> map = lecQnaService.lecQuestionListByPage(lectureName, currentPage, rowPerPage);
			List<String> lectureList = lecQnaService.lectureList((String)session.getAttribute("sessionMemberId"));
			
			log.debug(CF.KHM +"[LecQnaController GetMapping currentPage]: " + CF.RESET + currentPage); // 현재페이지 디버깅
			log.debug(CF.KHM +"[LecQnaController GetMapping lectureName]: " + CF.RESET + lectureName); // 선택 강의 디버깅
			log.debug(CF.KHM +"[LecQnaController GetMapping list.size]: " + CF.RESET + map.size()); // 글 개수 디버깅
			
			// model에 값 add
			model.addAttribute("lectureName", lectureName);
			model.addAttribute("list", map.get("list"));
			model.addAttribute("lastPage", map.get("lastPage"));
			model.addAttribute("currentPage", map.get("currentPage"));
			model.addAttribute("totalCount", map.size());
			model.addAttribute("lectureList", lectureList);
			
			return "/lecQna/lecQnaList";
	}
	
	
	@PostMapping("lecQna/addLecQna")
	public String insertQustion(LecQuestion lecQuestion) {
		// 등록 성공 행 반환(1 성공, 0 실패, 그 외 DB이상)
		int row = lecQnaService.insertQuestion(lecQuestion);
		
		if(row == 1) {
			log.debug(CF.KHM + "[LecQnaController PostMapping insert] :" + CF.RESET + "강의실 질문 등록 성공"); // 성공
		} else {
			log.debug(CF.KHM + "[LecQnaController PostMapping insert] :" + CF.RESET + "강의실 질문 등록 실패"); // 실패
		}
		
		return "redirect:/lecQna/lecQnaList";
	}
	
	@GetMapping("lecQna/addLecQna")
	public String insertQuestion(Model model) {
		String studentId = (String)session.getAttribute("sessionMemberId");
		// 해당 학생의 수강 중인 강의 목록
		List<String> lectureList = lecQnaService.lectureList(studentId);
		
		log.debug(CF.KHM + "[LecQnaController GetMapping lectureList.size] :" + CF.RESET + lectureList.size());
		
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("listSize", lectureList.size());
		return "lecQna/addLecQuestion";
	}
}
