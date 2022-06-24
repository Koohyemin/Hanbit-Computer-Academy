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
	
	// 학생정보 업데이트 폼
	@GetMapping("/student/modifyStudent")
	public String modifyStudent(Model model
								,HttpServletRequest request) {
		
		// 세션에 있는 값 아이디값 담기
		HttpSession session = request.getSession();
		String	studentId = (String)session.getAttribute("sessionMemberId");
		log.debug(CF.SWB+"[ImformationMemberController studentOne studentId]"+ studentId+CF.RESET); // studentId 디버깅
	
		// service에서 controller로 값 가져오기
		Map<String, Object> returnMap = imformation.studentOne(studentId);
		model.addAttribute("student",returnMap.get("student"));
		return "member/modifyMember";
	}
	
	// 학생정보 업데이트 액션
	
	// 멤버 상세보기
	@GetMapping("/member/getMemberOne")
	public String memberOne(Model model
							,HttpServletRequest request) {
		
		// 세션에 있는 값 멤버레벨에 담기
		HttpSession session = request.getSession();
		int memberLv = (int)session.getAttribute("sessionMemberLv");
		log.debug(CF.SWB+"[ImformationMemberController memberOne memberLv]"+ memberLv+CF.RESET); // memberLv
		
		// 변수 선언
		String studentId = null;
		String teacherId = null;
		String managerId = null;
		
		// service에서 controller로 값 가져오기
		Map<String, Object> returnMap = new HashMap<>();
		if(memberLv == 1) { // 학생일때
			studentId = (String)session.getAttribute("sessionMemberId");
			returnMap = imformation.studentOne(studentId);
			model.addAttribute("student",returnMap.get("student"));
		} else if(memberLv == 2) { // 강사일때
			teacherId = (String)session.getAttribute("sessionMemberId");
			returnMap = imformation.teacherOne(teacherId);
			model.addAttribute("teacher",returnMap.get("teacher"));
			model.addAttribute("registrationList",returnMap.get("registrationList"));
		} else if(memberLv == 3) { // 운영자
			managerId = (String)session.getAttribute("sessionMemberId");
			returnMap = imformation.managerOne(managerId);
			model.addAttribute("manager",returnMap.get("manager"));
		}
		
		// returnMap.size 디버깅
		log.debug(CF.SWB+"[ImformationMemberController studentOne returnMap.size()]"+ returnMap.size()+CF.RESET);
		
		// jsp로 값보내기
		model.addAttribute("certificationList",returnMap.get("certificationList"));
		model.addAttribute("lecList",returnMap.get("lecList"));
		model.addAttribute("photoFile",returnMap.get("photoFile"));
		return "member/imformationMember";
	}
}
