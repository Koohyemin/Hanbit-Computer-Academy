package Hanbit.co.kr.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.CertificateService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Registration;       
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CertificateController {
	@Autowired private CertificateService certificateService;
	@GetMapping("/certificate/paymentStudent")
	public String paymentStudent(Model model
			,@RequestParam (name="studentId" , defaultValue = "student1") String studentId) {
		
		// 학생 아이디 값 받아옴
		log.debug( CF.KYJ +"[CertificateController GetMapping paymentStudent studentId]: "+ studentId + CF.RESET);
		List<Registration> selectRegistrationList = certificateService.studentPaymentList(studentId);
		log.debug( CF.KYJ +"[CertificateController GetMapping paymentStudent selectRegistrationList]: "+ selectRegistrationList + CF.RESET);
		model.addAllAttributes(selectRegistrationList);
		return "certificate/paymentStudent";
	}
}
