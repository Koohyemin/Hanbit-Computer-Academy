package Hanbit.co.kr.lms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Hanbit.co.kr.lms.service.ImformationService;
import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ImformationMemberController {
	@Autowired ImformationService imformation;
	
	// 학생상세보기
	@GetMapping("/student/getStudentOne")
	public String studentOne(Model model
							,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String studentId = null;
		if(session.getAttribute("sessionMemberId") != null) {
			studentId = (String)session.getAttribute("sessionMemberId");
		}
		log.debug(CF.SWB+"[StudentController studentOne studentId]"+ studentId+CF.RESET);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap = imformation.studentOne(studentId);
		// returnMap.size 디버깅
		log.debug(CF.SWB+"[StudentController studentOne returnMap.size()]"+ returnMap.size()+CF.RESET);
		
		model.addAttribute("student",returnMap.get("student"));
		model.addAttribute("certificationList",returnMap.get("certificationList"));
		model.addAttribute("lecList",returnMap.get("lecList"));
		model.addAttribute("photoFile",returnMap.get("photoFile"));
		return "member/imformationMember";
	}
	
	// 강사상세보기
	@GetMapping("/getTeacherOne")
	public String teacherOne(Model model
							,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String teacherId = null;
		if(session.getAttribute("sessionMemberId") != null) {
			teacherId = (String)session.getAttribute("sessionMemberId");
		}
		log.debug(CF.SWB+"[StudentController teacherOne teacher]"+ teacherId+CF.RESET);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap = imformation.teacherOne(teacherId);
		// returnMap.size 디버깅
		log.debug(CF.SWB+"[StudentController studentOne returnMap.size()]"+ returnMap.size()+CF.RESET);
		
		model.addAttribute("teacher",returnMap.get("teacher"));
		model.addAttribute("certificationList",returnMap.get("certificationList"));
		model.addAttribute("lecList",returnMap.get("lecList"));
		model.addAttribute("photoFile",returnMap.get("photoFile"));
		model.addAttribute("registrationList",returnMap.get("registrationList"));
		
		return "member/imformationMember";
	}
	
	// 운영진상세보기
	@GetMapping("/getManagerOne")
	public String managerOne(Model model
							,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String managerId = null;
		if(session.getAttribute("sessionMemberId") != null) {
			managerId = (String)session.getAttribute("sessionMemberId");
		}
		Map<String, Object> returnMap = new HashMap<>();
		returnMap = imformation.managerOne(managerId);
		// returnMap.size 디버깅
		log.debug(CF.SWB+"[StudentController studentOne returnMap.size()]"+ returnMap.size()+CF.RESET);
		
		model.addAttribute("manager",returnMap.get("manager"));
		model.addAttribute("photoFile",returnMap.get("photoFile"));
		
		return "member/imformationMember";		
	}
}
