package Hanbit.co.kr.lms.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
			log.debug(CF.LKL+"MemberController.addMember : " +CF.RESET +addmember);
			//레벨 체크로 학생,강사,운영자 판단 
			if(addmember.getLevel()==3) {													//level3 => manager
				
				int row=memberService.addManager(addmember);
				log.debug(CF.LKL+"MemberController.addManager : " +CF.RESET + row);
				return "redirect:/login";
				
			} else if(addmember.getLevel()==2){												//level2 => teacher
				int row=memberService.addTeacher(addmember);
				log.debug(CF.LKL+"MemberController.addTeacher : " +CF.RESET + row);
				return "redirect:/login";
				
			} else if(addmember.getLevel()==1){												//level1 => student
				int row=memberService.addStudent(addmember);						
				log.debug(CF.LKL+"MemberController.addStudent : " +CF.RESET + row);
				return "redirect:/login";
			} else {																		//level값을 못받았을때는 다시 폼으로
			
				return "redirect:/addMember/addMember";
			}
		}
		
		@GetMapping("authorizeMember")
		public String authorizeMember(Model model) {
			
			List<Map<String, Object>> waitingList = memberService.authorizeMember();
			model.addAttribute("waitingList",waitingList);
			log.debug(CF.LKL+"MemberController.authorizeMember.waitingList : "+CF.RESET+waitingList);
			
			return "/addMember/auhorizeMember";
		}
		
		@PostMapping("authorizeMember")
		public String authorizeMember(@RequestParam( name="approvalCk") List<String> waitingValue) {
			log.debug(CF.LKL+"MemberController.authorizeMember : " +CF.RESET + waitingValue);			//맴개값 
			
			for(int i=0; i<waitingValue.size(); i++) {
				memberService.approveMember(waitingValue.get(i));
			}
			
			
			return  "redirect:/authorizeMember";
		}
	}


