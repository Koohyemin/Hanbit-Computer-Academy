package Hanbit.co.kr.lms.controller;

import java.util.List;

import javax.imageio.spi.RegisterableService;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Hanbit.co.kr.lms.service.RegistrationService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Registration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RegistrationController {
	@Autowired RegistrationService registrationService;
	@Autowired HttpSession session;
	
	@PostMapping("/registration/addregistration")
	public String addRegistration(Registration registration) {
		
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
}
