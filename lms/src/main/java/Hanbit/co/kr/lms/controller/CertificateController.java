package Hanbit.co.kr.lms.controller;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.CertificateService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Certification;
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
	 
      // 현재 날짜 값 출력
      SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
      Calendar time = Calendar.getInstance();
	  String nowDate = format1.format(time.getTime());
	  log.debug(CF.SWB+"[CertificateController modifyCertification nowDate]"+CF.RESET+nowDate); // certification 디버깅
	  
      // 컨트롤러에서 jsp로 값 보내주기
      model.addAttribute("certification",certification);
      model.addAttribute("nowDate", nowDate);
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
   
	// 납부 고지서  보여주기
	@GetMapping("/certificate/paymentStudent")
	public String paymentStudent(Model model
			,HttpSession session) {
		
		//세션에 있는 아이디 값 가져옴
		String studentId = (String) session.getAttribute("sessionMemberId");
		log.debug( CF.KYJ +"[CertificateController GetMapping paymentStudent studentId]: "+ studentId + CF.RESET);
		
		// certificateService로 selectRegistrationList(납부리스트) 
		List<Map<String,Object>> selectRegistrationList = certificateService.studentPaymentList(studentId);
		log.debug( CF.KYJ +"[CertificateController GetMapping paymentStudent selectRegistrationList]: "+ selectRegistrationList + CF.RESET);
		
		model.addAttribute("selectRegistrationList",selectRegistrationList);
		return "certificate/paymentStudent";
	}
	
	//  수료증 보여주기
	@GetMapping("/certificate/completion")
	public String completion(Model model
			,HttpSession session) {
		
		//세션에 있는 아이디 값 가져옴
		String studentId = (String) session.getAttribute("sessionMemberId");
		log.debug( CF.KYJ +"[CertificateController GetMapping completion studentId]: "+ studentId + CF.RESET);
		
		// certificateService로 selectRegistrationList(납부리스트) 
		List<Map<String,Object>> selectCompletionList = certificateService.completionList(studentId);
		log.debug( CF.KYJ +"[CertificateController GetMapping completion selectCompletionList]: "+ selectCompletionList + CF.RESET);
		
		model.addAttribute("selectCompletionList",selectCompletionList);
		return "certificate/completion";
	}
}
