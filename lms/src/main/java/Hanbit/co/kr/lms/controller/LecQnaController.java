package Hanbit.co.kr.lms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Hanbit.co.kr.lms.service.LecQnaService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LecQnaController {
	
	// !아직 filter에 추가 전!
	
	@Autowired LecQnaService lecQnaService;
	@Autowired HttpSession session;
	
	@GetMapping("lecQna/addLecQna")
	public String insertQuestion(Model model) {
		String studentId = (String)session.getAttribute("sessionMemberId");
		// 해당 학생의 수강 중인 강의 목록
		List<String> lectureList = lecQnaService.lectureList(studentId);
		
		model.addAttribute("lectureList", lectureList);
		return "lecQna/addLecQuestion";
	}
}
