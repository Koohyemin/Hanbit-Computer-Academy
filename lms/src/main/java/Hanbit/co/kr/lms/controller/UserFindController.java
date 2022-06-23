package Hanbit.co.kr.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.UserFindService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Student;
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

		//조건에 따른 id값을 서비스에서 호출
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
		if(Id == null) { //Id가 널값이라면
			model.addAttribute("check", 1); //모델값1로 넘김
		} else {
			model.addAttribute("check", 0); //모델값 0으로 넘김
			model.addAttribute("Id", Id); //모델값 Id도 함께 넘김
		}
		return "/user/findId"; 
	}
	
  	//Pw찾기 get
   @GetMapping("/user/findPw")
   public String findPw(Model model
         ,@RequestParam(name = "role",defaultValue = "student")String role) {
      
      log.debug(CF.SWB+"[LoginController GetMapping findPw role]"+ role+CF.RESET);
   
      model.addAttribute("role",role);

      return "user/findPw"; //포워딩
   }

	//학생pw찾기 post
	@PostMapping("/user/findPw")
	public String findPw(Model model
			,@RequestParam(name="id") String id
			,@RequestParam(name="name") String name
			,@RequestParam(name="phone") String phone
			,@RequestParam(name="role", defaultValue="student") String role) {
		
		//studentId + studentName + studentPhone 값 찾기
		log.debug( CF.KHN +"[UserController PostMapping findPw studentId]:  "+id+ CF.RESET);
		log.debug( CF.KHN +"[UserController PostMapping findPw studentName]:  "+name+ CF.RESET);
		log.debug( CF.KHN +"[UserController PostMapping findPw studentPhone]:  "+phone+ CF.RESET);
		log.debug( CF.KHN +"[UserController PostMapping findPw role]:  "+role+ CF.RESET);
		
		//조건에 따른 pw값을 서비스에서 호출
		String Pw = null;
		
		//학생이라면
		if(role.equals("student")) {
			Pw = userFindService.studentFindPw(id, name, phone);
		}
		//디버깅
		log.debug( CF.KHN +"[UserController PostMapping findPw studentPw]:  "+Pw+ CF.RESET);
		
		//강사라면
		if(role.equals("teacher")) {
			Pw = userFindService.teacherFindPw(id, name, phone);
		}
		//디버깅
		log.debug( CF.KHN +"[UserController PostMapping findPw teacherPw]:  "+Pw+ CF.RESET);	
		
		//운영자라면
		if(role.equals("manager")) {
			Pw = userFindService.managerFindPw(id, name, phone);
		}
		//디버깅
		log.debug( CF.KHN +"[UserController PostMapping findPw teacherPw]:  "+Pw+ CF.RESET);	
		
		if(Pw == null) { //Pw가 널값이라면
			model.addAttribute("check", 1); //모델값 1로넘김
		} else {
			model.addAttribute("check", 0); //모델값 0으로 넘김
			model.addAttribute("Pw", Pw); //Pw 넘김
		}
		return "user/findPw";
	}
}