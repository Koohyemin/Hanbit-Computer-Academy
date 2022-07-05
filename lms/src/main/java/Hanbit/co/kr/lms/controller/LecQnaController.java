package Hanbit.co.kr.lms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
