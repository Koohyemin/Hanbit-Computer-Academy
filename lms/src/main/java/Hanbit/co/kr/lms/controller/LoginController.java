package Hanbit.co.kr.lms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.LoginService;
import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	@Autowired LoginService loginService;
	
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
		
		// 서비스에서 값 받고 view로 값 보내주기
		Map<String,Object> returnMap = loginService.selectMemberId(map);
		model.addAttribute("member",(returnMap.get("member")));
		model.addAttribute("error",(returnMap.get("error")));
		model.addAttribute("role",role);
		return "login";
	}
}
