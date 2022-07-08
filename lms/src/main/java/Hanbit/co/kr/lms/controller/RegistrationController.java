package Hanbit.co.kr.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public String addRegistration(Registration registration) {					//강좌 액션
		
		String studentId = (String) session.getAttribute("sessionMemberId");
		
		registration.setStudentId(studentId);
		
		log.debug(CF.LKL+"RegistrationController.addRegistration.registration"+CF.RESET+registration);
		
		int row = registrationService.addRegistration(registration);
		
		log.debug(CF.LKL+"RegistrationController.addRegistration.row"+CF.RESET+row);
		
		return "redirect:/registration/getRegistration";
	}
	
	@GetMapping("/registration/getRegistration")
	public String getRegistration(Model model) {						//수강 등록 내역 리스트
		
		if (session.getAttribute("sessionMemberLv") == null) { // 세션에 로그인 상태가 아니면 로그인페이지로 이동
			return "redirect:/login";
		}
		
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
	public String pamyentMoney(Registration registration,@RequestParam(name="payMoney") int payMoney) {
				
		
		log.debug(CF.LKL +"RegistrationController.registrationService.getRegistrationOne: " + CF.RESET + registration);		//페이지에서 받은 registration 디버깅
			
		int row = registrationService.modifyPayment(registration,payMoney);					
		
		return "redirect:/registration/getRegistration";
	}
	
	@GetMapping("/registration/getRegistrationByLec")
	public String getRegistrationByLec(@RequestParam(name="lectureName", required=false) String lectureName,Model model) {
				
		
		log.debug(CF.LKL +"RegistrationController.getRegistrationByLec.registration: " + CF.RESET + lectureName);		//페이지에서 받은 registration 디버깅
							
		Map<String,Object>  map = registrationService.getRegistrationByLec(lectureName);								//학생 수강 리스트 출력
		
		log.debug(CF.LKL +"RegistrationController.getRegistrationByLec.map: " + CF.RESET + map);
		
		List<HashMap<String,Object>> paymentList = (List<HashMap<String, Object>>) map.get("paymentlist");
		
		log.debug(CF.LKL +"RegistrationController.getRegistrationByLec.paymentlist: " + CF.RESET + paymentList);
		
		List<String> beforeLectureList = (List<String>) map.get("beforeLectureList");
		
		log.debug(CF.LKL +"RegistrationController.getRegistrationByLec.beforeLectureList: " + CF.RESET + beforeLectureList);
		
		model.addAttribute("paymentList",paymentList);
		model.addAttribute("beforeLectureList",beforeLectureList);
		return "/registration/getRegistrationByLec";
	}
	
	
	//수강 환불
	@GetMapping("/registration/removeRegistRation")
	public String removeRegistRation(Registration registration) {
				
		
		log.debug(CF.LKL +"RegistrationController.deleteRegistRation.registration: " + CF.RESET + registration);		//페이지에서 받은 registration 디버깅
						
		int row = registrationService.removeRegistration(registration);
		
		log.debug(CF.LKL +"RegistrationController.removeRegistration.row: " + CF.RESET + row);
		
		return "redirect:/registration/getRegistrationByLec";
	}
}
