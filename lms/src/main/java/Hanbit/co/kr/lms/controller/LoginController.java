package Hanbit.co.kr.lms.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.LoginService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	@Autowired LoginService loginService;
	
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(Model model
						,HttpSession session
						,@RequestParam(name = "role",defaultValue = "student")String role) {
		session.invalidate();
		model.addAttribute("role",role);
		return "login";
	}
	
	// login form
	@GetMapping("/login")
	public String login(Model model
						,@RequestParam(name = "role",defaultValue = "student")String role) {
		model.addAttribute("role",role);
		log.debug(CF.SWB+"[LoginController GetMapping role]"+ role+CF.RESET);
		return "login";
	}
	
	// login action
	@PostMapping("/login")
	public String login(Model model
						,HttpServletRequest request
						,@RequestParam(name="memberId")String memberId
						,@RequestParam(name="memberPw")String memberPw
						,@RequestParam(name="role")String role) {
		
		// memberId + memberPw + role 디버깅
		log.debug(CF.SWB+"[LoginController login memberId]"+ memberId+CF.RESET);
		log.debug(CF.SWB+"[LoginCOntroller login memberPw]"+ memberPw+CF.RESET);
		log.debug(CF.SWB+"[LoginCOntroller login role]"+ role+CF.RESET);
		
		// map으로 묶어서 service로 보내기
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("memberPw", memberPw);
		map.put("role", role);
		
		
		HttpSession session = request.getSession();
		Member member = new Member();
		// 서비스에서 값 받고 view로 값 보내주기
		Map<String, Object> returnMap= loginService.selectMemberId(map);
		if((Member)returnMap.get("member") != null) {
			member = (Member)returnMap.get("member");
		}
		String returnMemberId = null;
		int returnMemberLv = 0;
		
		if(member.getMemberId() != null && !"".equals(member.getMemberId())) {
			returnMemberId = member.getMemberId();
			returnMemberLv = member.getMemberLevel();
		}
		// returnMemberId + returnMemberLv 디버깅
		log.debug(CF.SWB+"[LoginController login returnMemberId]"+ returnMemberId+CF.RESET);
		log.debug(CF.SWB+"[LoginCOntroller login returnMemberLv]"+ returnMemberLv+CF.RESET);
		
		// session에 등록
		session.setAttribute("sessionMemberId",returnMemberId);
		session.setAttribute("sessionMemberLv",returnMemberLv);
		model.addAttribute("error",(returnMap.get("error")));
		log.debug(CF.SWB+"[LoginCOntroller login error]"+ returnMap.get("error")+CF.RESET);
		model.addAttribute("role",role);
		
		// 레벨이 1이라면 학생 인덱스로
		if(returnMemberLv == 1) {
			return "IndexStudent";
		}
		
		// 레벨이 2이라면 강사 인덱스로
		//if(returnMemberLv == 2) {
		//	return "IndexTeacher";
		//}
		// 레벨이 3이라면 운영자 인덱스로
		//if(returnMemberLv == 3) {
		//	return "IndexManaber";
		//} 
		return "login";
	}
}
