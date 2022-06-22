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
	//학생id찾기 get
	@GetMapping("/user/findId")
	public String findId() {
		return "user/findId"; //포워딩
	}
	//학생id찾기 post
	@PostMapping("/user/findId")
	public String findId(Model model,
			@RequestParam(name="studentName") String studentName
			,@RequestParam(name="studentPhone") String studentPhone) {
		
		//studentName + studentPhone 값 찾기
		log.debug( CF.KHN +"[UserController PostMapping studentSearchId studentName]:  "+studentName+ CF.RESET);
		log.debug( CF.KHN +"[UserController PostMapping studentSearchId studentPhone]:  "+studentPhone+ CF.RESET);
		//문자열타입 studentId 서비스에서 호출
		
		String studentId = userFindService.studentFindId(studentName, studentPhone);
		//디버깅
		log.debug( CF.KHN +"[UserController PostMapping studentSearchId studendId]:  "+studentId+ CF.RESET);
		
		if(studentId == null) { //studentId가 널값이라면
			model.addAttribute("check", 1); //모델값1로 넘김
		} else {
			model.addAttribute("check", 0); //모델값 0으로 넘김
			model.addAttribute("studentId", studentId); //모델값 studentId도 함께 넘김
		}
		return "user/findId";
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
	
	//강사ID찾기 get
	@GetMapping("/user/teacherSearchId")
	public String teacherSearchId() {
		return "user/findId"; //포워딩
	}
	
	//강사ID찾기 post
	@PostMapping("/user/teacherSearchId")
	public String teacherSearchId(Model model
			,@RequestParam(name="teacherName") String teacherName
			,@RequestParam(name="teacherPhone") String teacherPhone) {
		
		// teacherName + teacherPhone 값 찾기
		log.debug( CF.KHN +"[UserController PostMapping teacherSearchId teacherName]: "+teacherName+ CF.RESET);
		log.debug( CF.KHN +"[UserController PostMapping teacherSearchId teacherPhone]: "+teacherPhone+ CF.RESET);
		
		// teacherId 값 받기/ 서비스 호출
		String teacherId = userFindService.teacherFindId(teacherName, teacherPhone);
		log.debug( CF.KHN +"[UserController PostMapping teacherSearchId teacherId]:  "+teacherId+ CF.RESET);		
		
		if(teacherId == null) { //teacherId가 널값이라면
			model.addAttribute("check", 1); //모델값 1로넘김
		} else {
			model.addAttribute("check", 0); //모델값 0으로 넘김
			model.addAttribute("teacherId", teacherId); //studentPw 넘김
		}
		return "user/findId";
	}
	
	//운영자ID찾기 get
	@GetMapping("/user/managerSearchId")
	public String managerSearchId() {
		return "user/findId"; //포워딩
	}
	//운영자ID찾기 post
	@PostMapping("/user/managerSearchId")
	public String managerSearchId(Model model
			,@RequestParam(name="managerName") String managerName
			,@RequestParam(name="managerPhone") String managerPhone) {
		
		//managerName + managerPhone 값 찾기
		log.debug( CF.KHN +"[UserController PostMapping managerSearchId managerName]: "+managerName+ CF.RESET);		
		log.debug( CF.KHN +"[UserController PostMapping managerSearchId managerPhone]: "+managerPhone+ CF.RESET);		
		
		//managerId 값 받아옴
		String managerId = userFindService.managerFindId(managerName, managerPhone);
		log.debug( CF.KHN +"[UserController PostMapping managerSearchId managerId]: "+managerId+ CF.RESET);		
		
		if(managerId == null) { //managerId가 널값이라면
			model.addAttribute("check", 1);
		} else {
			model.addAttribute("check", 0);
			model.addAttribute("managerId", managerId);
		}
		
		return "user/findId";
	}
}