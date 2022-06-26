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
		
		// jsp로 값 보내기
		model.addAttribute("role",role);
		return "login";
	}
	
	// login form
	@GetMapping("/login")
	public String login(Model model
						,@RequestParam(name = "role",defaultValue = "student")String role) {
		
		// jsp로 값 보내기
		model.addAttribute("role",role);
		log.debug(CF.SWB+"[LoginController GetMapping role]"+ role+CF.RESET); // role 디버깅
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
		
		// session + vo.member 등록
		HttpSession session = request.getSession();
		Member member = new Member();
		
		// 서비스에서 값 받고 view로 값 보내주기
		Map<String, Object> returnMap= loginService.selectMemberId(map);
		if((Member)returnMap.get("member") != null) {  
			member = (Member)returnMap.get("member");
		}
		
		// 변수등록
		String returnMemberId = null;
		int returnMemberLv = 0;
		
		// member.getMemberId() + member.getMemberId() 값이 잘 들어와 있다면 변수에 값 넣어주기
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
		String error =(String) returnMap.get("error");
		
		// 레벨을 가지고 있다면 index로 이동
		if(returnMemberLv == 1 || returnMemberLv == 2 || returnMemberLv == 3) {
			return "redirect:/home/index";
		}
		
		// 레벨값이 없으면 로그인으로
		return "redirect:/login?error="+error;
	}
}
