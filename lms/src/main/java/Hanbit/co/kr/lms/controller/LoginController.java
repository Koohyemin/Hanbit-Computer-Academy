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
	public String login() {
		return "login";
	}
	
	// login action
	@PostMapping("/login")
	public String login(Model model
						,@RequestParam(name="memberId")String memberId
						,@RequestParam(name="memberPw")String memberPw
						,@RequestParam(name="role")String role) {
		log.debug(CF.SWB+"[LoginController login memberId]"+ memberId+CF.RESET);
		log.debug(CF.SWB+"[LoginCOntroller login memberPw]"+ memberPw+CF.RESET);
		log.debug(CF.SWB+"[LoginCOntroller login role]"+ role+CF.RESET);
		Map<String, Object> map = new HashMap<>();
		// map.put
		map.put("memberId", memberId);
		map.put("memberPw", memberPw);
		map.put("role", role);
		Map<String,Object> returnMap = loginService.selectMemberId(map);
		model.addAttribute("member",(returnMap.get("member")));
		return "login";
	}
}
