package Hanbit.co.kr.lms.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Hanbit.co.kr.lms.service.LoginService;
import Hanbit.co.kr.lms.service.MemberService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.AddMember;
import Hanbit.co.kr.lms.vo.PasswordUpdateDate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@Autowired
	LoginService loginService;
	
	@GetMapping("addMember")
	public String getAddr() {		//회원가입 뷰 호출 컨트롤러
		
		return "/addMember/addMember";
	}
	@PostMapping("addMember")
	public String addMember(AddMember addmember) {		//회원가입 액션 컨트롤러
		
		// addmember 디버깅
		log.debug(CF.LKL+"MemberController.addMember : " +CF.RESET +addmember);
		
		//레벨 체크로 학생,강사,운영자 판단 
		//level3 => manager
		if(addmember.getLevel()==3) {												
			
			int row=memberService.addManager(addmember);
			
			//실행된 쿼리문 갯수 디버깅
			log.debug(CF.LKL+"MemberController.addManager : " +CF.RESET + row);
			
			return "redirect:/login";
			
		//level2 => teacher
		} else if(addmember.getLevel()==2){												
			
			int row=memberService.addTeacher(addmember);
			
			//실행된 쿼리문 갯수 디버깅
			log.debug(CF.LKL+"MemberController.addTeacher : " +CF.RESET + row);
			
			return "redirect:/login";
			
			//level1 => student
		} else if(addmember.getLevel()==1){												
			
			int row=memberService.addStudent(addmember);
			
			//실행된 쿼리문 갯수 디버깅
			log.debug(CF.LKL+"MemberController.addStudent : " +CF.RESET + row);
			
			return "redirect:/login";
		} else {																		//level값을 못받았을때는 다시 폼으로
		
			return "redirect:/addMember/addMember";
		}
	}
	
	@GetMapping("authorizeMember")
	public String authorizeMember(Model model) {	//운영자 회원 상태 승인 페이지 구현
		
		
		//회원 상태가 1인 member List를 받는다.
		List<Map<String, Object>> waitingList = memberService.authorizeMember();
		
		// 승인대기중인 member들 디버깅 
		log.debug(CF.LKL+"MemberController.authorizeMember.waitingList : "+ CF.RESET + waitingList); 
		
		//모델에 저장
		model.addAttribute("waitingList",waitingList);
		
		return "/addMember/auhorizeMember";
	}
	
	@PostMapping("authorizeMember")				//운영자가 체크한 member들을 list형태로 받는다.
	public String authorizeMember(@RequestParam( name="approvalCk") List<String> waitingValue) {	
		
		// 운영자가 체크한 waitingValue 디버깅
		log.debug(CF.LKL+"MemberController.authorizeMember : " + CF.RESET + waitingValue);		
		
		//받은 체크값 개수만큼 for문 
		for(int i=0; i<waitingValue.size(); i++) {							
		// 승인메서드 실행
			memberService.approveMember(waitingValue.get(i));				
		}
		
		return "redirect:/authorizeMember";
	}
	
	@GetMapping("activeMember")													//휴면 계정 해제 뷰 호출
	public String activeMember(Model model
								,HttpSession session) {				
		
		String memberId = (String) session.getAttribute("sessionMemberId");
		
		log.debug(CF.LKL+"MemberController.activeMember.memberId" + CF.RESET + memberId );
		
		model.addAttribute("memberId",memberId);
		return "/addMember/activeMember";
	}
	@PostMapping("activeMember")
	public String activeMember(HttpSession session
								,PasswordUpdateDate passwordUpdateDate) {				//휴면계정 해제 액션 호출
		
		
		log.debug(CF.LKL+"MemberController.activeMember.passwordUpdateDate" + CF.RESET + passwordUpdateDate );
		
		String memberId = (String) session.getAttribute("sessionMemberId");
		int memberLevel = (Integer) session.getAttribute("sessionMemberLv");
		
		passwordUpdateDate.setMemberId(memberId);
		
		int row=0;
		//세션 레벨에 따라 매니저,강사,학생을 나눈다.
		if(memberLevel == 3) {										
			
			row = memberService.awakeManager(passwordUpdateDate);
			
		} else if(memberLevel == 2) {
			
			row = memberService.awakeTeacher(passwordUpdateDate);
			
		} else if(memberLevel == 1){
			
			row = memberService.awakeStudent(passwordUpdateDate);
			
		} 
		if(row ==1) {
			return "redirect:/home/index";
	} else {
		return "redirect:/login";
		}
	}

}
