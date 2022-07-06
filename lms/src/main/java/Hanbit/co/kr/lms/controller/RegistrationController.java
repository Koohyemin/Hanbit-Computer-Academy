package Hanbit.co.kr.lms.controller;

import java.util.HashMap;
import java.util.List;

import javax.imageio.spi.RegisterableService;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.RegistrationService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Lec;
import Hanbit.co.kr.lms.vo.Registration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RegistrationController {
	@Autowired RegistrationService registrationService;
	@Autowired HttpSession session;
	
	@GetMapping("/registration/addregistration")
	public String addRegistration(Registration registration) {
		
		String studentId = (String) session.getAttribute("sessionMemberId");
		
		registration.setStudentId(studentId);
		
		log.debug(CF.LKL+"RegistrationController.addRegistration.registration"+CF.RESET+registration);
		
		int row = registrationService.addRegistration(registration);
		
		log.debug(CF.LKL+"RegistrationController.addRegistration.row"+CF.RESET+row);
		
		return "redirect:/registration/getRegistration";
	}
	
	@GetMapping("/registration/getRegistration")
	public String getRegistration(Model model) {
		
		String studentId =(String) session.getAttribute("sessionMemberId");	
		
		log.debug(CF.LKL+"RegistrationController.getRegistration.studentId"+CF.RESET+studentId);
		
		List<Registration> list = registrationService.getRegistration(studentId);
		
		log.debug(CF.LKL+"RegistrationController.getRegistration.list"+CF.RESET+list);
		
		model.addAttribute("list",list);
		
		return "/registration/getRegistration";
	}
	
	@GetMapping("/registration/registrationOne")
	public String getRegistrationOne(Model model, Registration registration) {
		
		String studentId = (String) session.getAttribute("sessionMemberId");
		
		registration.setStudentId(studentId);
		
		HashMap<String,Object> regimap = registrationService.getRegistrationByStudent(registration); // 강의 상세보기 정보

		log.debug(CF.LKL +"RegistrationController.registrationService.getRegistrationOne: " + CF.RESET + regimap); // 현재페이지 디버깅
		
		// model에 상세보기 값 add
		model.addAttribute("regimap", regimap);
		
		return "registration/getRegistrationOne";
	}
	
	//학생-수강 결제 액션 구현
	@PostMapping("/registration/pamyentMoney")
	public String pamyentMoney(Registration registration) {
		
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		
		
		log.debug(CF.LKL +"RegistrationController.registrationService.getRegistrationOne: " + CF.RESET + registration);		//페이지에서 받아온 금액 + 
			
		int row = registrationService.modifyPayment(registration);
		
		return "redirect:/registration/getRegistration";
	}
}
