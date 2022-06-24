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
		
		//UserFindController get role값 디버깅
		log.debug(CF.SWB+"[UserFindController GetMapping findId role]"+ CF.RESET +role);
		
		//모델값 넘겨줌
		model.addAttribute("role",role);

		return "user/findId"; //포워딩
	}
	
	//id찾기 post
	@PostMapping("/user/findId")
	public String studentSearchId(Model model,
			@RequestParam(name="name") String name
			,@RequestParam(name="phone") String phone
			,@RequestParam(name = "role",defaultValue = "student")String role) {
		
		//studentName + studentPhone + role 값 디버깅
		log.debug( CF.KHN +"[UserFindController PostMapping studentSearchId studentName]:  "+ CF.RESET +name);
		log.debug( CF.KHN +"[UserFindController PostMapping studentSearchId studentPhone]:  "+ CF.RESET +phone);
		log.debug( CF.KHN +"[UserFindController PostMapping studentSearchId role]:  "+ CF.RESET +role);

		//조건에 따른 id값을 서비스에서 호출
		String Id = null;
		
		// 학생이라면
		if(role.equals("student")){
			Id = userFindService.studentFindId(name, phone);
			
			//UserFindController post id값 디버깅
			log.debug( CF.KHN +"[UserFindController PostMapping studentSearchId Id]:  "+ CF.RESET +Id);
		}
		
		// 강사라면
		if(role.equals("teacher")){
			Id = userFindService.teacherFindId(name, phone);
			
			//UserFindController post id값 디버깅
			log.debug( CF.KHN +"[UserFindController PostMapping teacherSearchId Id]:  "+ CF.RESET +Id);
		}
		
		// 운영자라면
		if(role.equals("manager")){
			Id = userFindService.managerFindId(name, phone);
			
			//UserFindController post id값 디버깅
			log.debug( CF.KHN +"[UserFindController PostMapping managerSearchId Id]:  "+ CF.RESET +Id);
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
      //UserFindController get 
      log.debug(CF.SWB+"[UserFindController GetMapping findPw role]"+CF.RESET + role);
      
      //모델값 넘겨줌
      model.addAttribute("role",role);

      return "user/findPw"; //포워딩
   }

	//pw찾기 post
	@PostMapping("/user/findPw")
	public String findPw(Model model
			,@RequestParam(name="id") String id
			,@RequestParam(name="name") String name
			,@RequestParam(name="phone") String phone
			,@RequestParam(name="role", defaultValue="student") String role) {
		
		//studentId + studentName + studentPhone + role 값 찾기
		log.debug( CF.KHN +"[UserFindController PostMapping findPw studentId]:  "+ CF.RESET +id);
		log.debug( CF.KHN +"[UserFindController PostMapping findPw studentName]:  "+ CF.RESET +name);
		log.debug( CF.KHN +"[UserFindController PostMapping findPw studentPhone]:  "+ CF.RESET +phone);
		log.debug( CF.KHN +"[UserFindController PostMapping findPw role]:  "+ CF.RESET +role);
		
		//조건에 따른 pw값을 서비스에서 호출
		String Pw = null; //변수선언
		
		//학생이라면
		if(role.equals("student")) {
			Pw = userFindService.studentFindPw(id, name, phone);
			
			//UserFindController post pw값 디버깅
			log.debug( CF.KHN +"[UserFindController PostMapping findPw studentPw]:  "+ CF.RESET +Pw);
		}
		
		//강사라면
		else if(role.equals("teacher")) {
			Pw = userFindService.teacherFindPw(id, name, phone);
			
			//UserFindController post pw값 디버깅
			log.debug( CF.KHN +"[UserFindController PostMapping findPw teacherPw]:  "+ CF.RESET +Pw);	
		}
	
		//운영자라면
		else if(role.equals("manager")) {
			Pw = userFindService.managerFindPw(id, name, phone);
			
			//UserFindController post pw값 디버깅
			log.debug( CF.KHN +"[UserFindController PostMapping findPw teacherPw]:  "+ CF.RESET +Pw);
		}
			
		
		if(Pw == null) { //Pw가 널값이라면
			model.addAttribute("check", 1); //모델값 1로넘김
		} else {
			model.addAttribute("check", 0); //모델값 0으로 넘김
			model.addAttribute("checkId", id); //계정이 있을때 id값을 넘김
			model.addAttribute("checkRole", role); //계정이 있을때 role값을 넘김
		}

		return "user/findPw";
	}

	//pw변경 post
	@PostMapping("/user/updatePw")
	public String modifyPw(Model model
			,@RequestParam(name="Id") String Id
			,@RequestParam(name="pw") String pw	
			,@RequestParam(name="role") String role) {
		
		//id + pw + role 값 디버깅
		log.debug( CF.KHN +"[UserFindController PostMapping modifyPw Id ]:  "+ CF.RESET +Id);
		log.debug( CF.KHN +"[UserFindController PostMapping modifyPw pw ]:  "+ CF.RESET +pw);
		log.debug( CF.KHN +"[UserFindController PostMapping modifyPw role ]:  "+ CF.RESET +role);
		
		//학생
		if(role.equals("student")) {
			int row = userFindService.studentUpdatePw(Id, pw);
			
			//수정확인
			if(row==1) {
				
				//수정성공 확인 디버깅
				log.debug( CF.KHN +"[UserFindController PostMapping modifyPw 수정성공 ]:  "+ CF.RESET);
				
				//수정성공 > 서비스호출
				userFindService.passwordUpdate(Id, pw);
			} else {
				
				//수정실패 확인 디버깅
				log.debug( CF.KHN +"[UserFindController PostMapping modifyPw 수정실패 ]:  "+ CF.RESET);
			}

		}
		
		//강사
		if(role.equals("teacher")) {
			int row = 	userFindService.teacherUpdatePw(Id, pw);
			
			//수정확인
			if(row==1) {
				
				//수정성공 확인 디버깅
				log.debug( CF.KHN +"[UserFindController PostMapping modifyPw 수정성공 ]:  "+ CF.RESET);
				
				//수정성공 > 서비스호출
				userFindService.passwordUpdate(Id, pw);		
			} else {
				
				//수정실패 확인 디버깅
				log.debug( CF.KHN +"[UserFindController PostMapping modifyPw 수정실패 ]:  "+ CF.RESET);
			}
		}
		
		//운영자
		if(role.equals("manager")) {
			int row = userFindService.managerUpdatePw(Id, pw);
			
			//수정확인
			if(row==1) {
				
				//수정성공 확인 디버깅				
				log.debug( CF.KHN +"[UserFindController PostMapping modifyPw 수정성공 ]:  "+ CF.RESET);
				
				//수정성공 > 서비스호출				
				userFindService.passwordUpdate(Id, pw);	
			} else {
				
				//수정실패 확인 디버깅				
				log.debug( CF.KHN +"[UserFindController PostMapping modifyPw 수정실패 ]:  "+ CF.RESET);
			}
		}	
		return "redirect:/login";
	}
}