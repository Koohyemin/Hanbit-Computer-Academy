package Hanbit.co.kr.lms.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.CertificateService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Certification;
import Hanbit.co.kr.lms.vo.Registration;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CertificateController {
	@Autowired private CertificateService certificateService;
	
	// 자격증 등록 폼
	@GetMapping("certificate/addCertification")
	public String addCertification() {
		return "certificate/addCertification";
	}
	
	// 자격증 등록 액션
	@PostMapping("/certificate/addCertification")
	public String addCertification(HttpSession session
										,@RequestParam(name="certificationName")String certificationName
										,@RequestParam(name="certificationIssued") String certificationIssued
										,@RequestParam(name="getDate")String getDate){
		
		// 세션에 있는 멤버아이디 가져오기
		String memberId = (String) session.getAttribute("sessionMemberId");
		log.debug(CF.SWB+"[CertificateController addCertificationAction memberId]"+CF.RESET+memberId); // memberId 디버깅
		
		// vo에 값 넣어주기
		Certification certification = new Certification();
		certification.setMemberId(memberId);
		certification.setCertificationName(certificationName);
		certification.setCertificationIssued(certificationIssued);
		certification.setGetDate(getDate);
		
		// 컨트롤러에서 서비스로 값 넘겨주기
		certificateService.addCertification(certification);
		log.debug(CF.SWB+"[CertificateController addCertificationAction certification]"+CF.RESET+certification.toString()); // certification 디버깅
		return "redirect:/member/getMemberOne";
	}
	
	
	// 자격증 수정 폼
	@GetMapping("/certificate/modifyCertification")
	public String modifyCertification(Model model
									,@RequestParam(name="certificationNo")int certificationNo) {
		
		log.debug(CF.SWB+"[CertificateController modifyCertification certificationNo]"+CF.RESET+certificationNo); // certificationNo 디버깅
		
		// 컨트롤러에서 서비스로 값 넘겨주기
		Certification certification = certificateService.selectCertification(certificationNo);
		log.debug(CF.SWB+"[CertificateController modifyCertification certification]"+CF.RESET+certification.toString()); // certification 디버깅
		
		// 컨트롤러에서 jsp로 값 보내주기
		model.addAttribute("certification",certification);
		return "certificate/modifyCertification";
	}
	
	// 자격증 수정 액션
	@PostMapping("/certificate/modifyCertification")
	public String modifyCertification(Certification certification) {
		
		log.debug(CF.SWB+"[CertificateController modifyCertification certification]"+CF.RESET+certification.toString()); // certification 디버깅
		
		// 컨트롤러에서 서비스로 값 보내기
		certificateService.updateCertification(certification);
		
		return "redirect:/member/getMemberOne";
	}
	// 자격증 삭제
	@PostMapping("/certificate/deleteCertification")
	public String deleteCertification(@RequestParam(name="certificationNo")int certificationNo) {
		
		certificateService.deleteCertificate(certificationNo);
		return "redirect:/member/getMemberOne";
	}
	
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

