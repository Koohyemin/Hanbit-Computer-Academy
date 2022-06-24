package Hanbit.co.kr.lms.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Hanbit.co.kr.lms.service.CertificateService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Registration;       
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CertificateController {
	@Autowired private CertificateService certificateService;
	// 납부 고지서 출력
	@GetMapping("/certificate/paymentStudent")
	public String paymentStudent(Model model
			,HttpSession session) {
		
		//세션에 있는 아이디 값 가져옴
		String studentId = (String) session.getAttribute("sessionMemberId");
		log.debug( CF.KYJ +"[CertificateController GetMapping paymentStudent studentId]: "+ studentId + CF.RESET);
		
		// certificateService로 selectRegistrationList(납부리스트) 
		List<Registration> selectRegistrationList = certificateService.studentPaymentList(studentId);
		log.debug( CF.KYJ +"[CertificateController GetMapping paymentStudent selectRegistrationList]: "+ selectRegistrationList + CF.RESET);
		
		model.addAllAttributes(selectRegistrationList);
		return "certificate/paymentStudent";
	}
	
}

