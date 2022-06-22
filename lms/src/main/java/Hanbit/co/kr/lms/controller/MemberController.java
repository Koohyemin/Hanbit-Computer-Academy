package Hanbit.co.kr.lms.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Hanbit.co.kr.lms.service.MemberService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.AddMember;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
		@GetMapping("addMember")
		public String selectAddr() {
			
			return "/addMember/addMember";
		}
		@PostMapping("addMember")
		public String addMember(AddMember addmember) {
			log.debug(CF.LKL+"MemberController.addMember : "+addmember +CF.RESET);
//			if(addmember.getLevel().equals("3")) {
			
			if(addmember.getLevel()==3) {
				
				int row=memberService.addManager(addmember);
				log.debug(CF.LKL+"MemberController.addManager : "+ row+CF.RESET);
				return "redirect:/login";
				
			} else if(addmember.getLevel()==2){
			//	int row=memberService.addTeacher(addmember);
				return "redirect:/login";
				
			} else if(addmember.getLevel()==1){
			//	int row=memberService.addStudent(addmember);
				return "redirect:/login";
			} else {
			
				return "redirect:/addMember/addMember";
			}
		}
	}

