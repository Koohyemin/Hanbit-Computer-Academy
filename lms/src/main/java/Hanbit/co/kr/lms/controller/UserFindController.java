package Hanbit.co.kr.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.UserFindService;
import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserFindController {
	@Autowired private UserFindService userFindService;
	
	//id찾기 get
	@GetMapping("/user/findId")
	public String findId(Model model
			,@RequestParam(name = "role",defaultValue = "student")String role) {
		
		log.debug(CF.SWB+"[LoginController GetMapping role]"+ role+CF.RESET);
	
		model.addAttribute("role",role);

		return "user/findId"; //포워딩
	}
	
	//id찾기 post
	@PostMapping("/user/findId")
	public String studentSearchId(Model model,
			@RequestParam(name="name") String name
			,@RequestParam(name="phone") String phone
			,@RequestParam(name = "role",defaultValue = "student")String role) {
		
		//studentName + studentPhone 값 찾기
		log.debug( CF.KHN +"[UserController PostMapping studentSearchId studentName]:  "+name+ CF.RESET);
		log.debug( CF.KHN +"[UserController PostMapping studentSearchId studentPhone]:  "+phone+ CF.RESET);
		log.debug( CF.KHN +"[UserController PostMapping studentSearchId role]:  "+role+ CF.RESET);
		//문자열타입 studentId 서비스에서 호출
		
		String Id = null;
		// 학생이라면
		if(role.equals("student")){
			Id = userFindService.studentFindId(name, phone);
			//디버깅
			log.debug( CF.KHN +"[UserController PostMapping studentSearchId Id]:  "+ Id+ CF.RESET);
		}
		
		// 강사라면
		if(role.equals("teacher")){
			Id = userFindService.teacherFindId(name, phone);
			//디버깅
			log.debug( CF.KHN +"[UserController PostMapping studentSearchId Id]:  "+ Id+ CF.RESET);
		}
		
		// 운영자라면
		if(role.equals("manager")){
			Id = userFindService.managerFindId(name, phone);
			//디버깅
			log.debug( CF.KHN +"[UserController PostMapping studentSearchId Id]:  "+ Id+ CF.RESET);
		}
		if(Id == null) { //studentId가 널값이라면
			model.addAttribute("check", 1); //모델값1로 넘김
		} else {
			model.addAttribute("check", 0); //모델값 0으로 넘김
			model.addAttribute("Id", Id); //모델값 studentId도 함께 넘김
		}
		return "/user/findId"; 
	}
	
	//학생pw찾기 get
	@GetMapping("/user/findPw")
	public String findPw() {
		return "user/findPw"; //포워딩
	}

	//학생pw찾기 post
	@PostMapping("/user/findPw")
	public String findPw(Model model
			,@RequestParam(name="studentId") String studentId
			,@RequestParam(name="studentName") String studentName
			,@RequestParam(name="studentPhone") String studentPhone) {
		
		//studentId + studentName + studentPhone 값 찾기
		log.debug( CF.KHN +"[UserController PostMapping findPw studentId]:  "+studentId+ CF.RESET);
		log.debug( CF.KHN +"[UserController PostMapping findPw studentName]:  "+studentName+ CF.RESET);
		log.debug( CF.KHN +"[UserController PostMapping findPw studentPhone]:  "+studentPhone+ CF.RESET);
		
		//문자열타입 studentPw 서비스에서 호출
		String studentPw = userFindService.studentFindPw(studentId, studentName, studentPhone);
		
		//디버깅
		log.debug( CF.KHN +"[UserController PostMapping findPw studentPw]:  "+studentPw+ CF.RESET);
		
		if(studentPw == null) { //studentPw가 널값이라면
			model.addAttribute("check", 1); //모델값 1로넘김
		} else {
			model.addAttribute("check", 0); //모델값 0으로 넘김
			model.addAttribute("studentPw", studentPw); //studentPw 넘김
		}
		return "user/findPw";
	}
}