package Hanbit.co.kr.lms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Hanbit.co.kr.lms.service.LectureRoomService;

@Controller
public class LectureRoomController {
	
	@Autowired LectureRoomService lectureRoomService;
	@Autowired HttpSession session;
	
	@GetMapping("/lectureRoom/index")
	public String lectureRoomIndex(Model model) {
		List<String> lectureList = lectureRoomService.lectureList((String)session.getAttribute("sessionMemberId"));
		List<String> teacherLectureList = lectureRoomService.teacherLectureList((String)session.getAttribute("sessionMemberId"));
		
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("teacherLectureList", teacherLectureList);
		return "lectureRoom/lectureRoomIndex";
	}
}
