package Hanbit.co.kr.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.FaqService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Faq;
import Hanbit.co.kr.lms.vo.ManagerNotice;
import lombok.extern.slf4j.Slf4j;

@Slf4j // log객체를 사용하도록...
@Controller // servlet역활을 하도록...
public class FaqController {
	@Autowired private FaqService faqService;
	
	// 리스트
	@GetMapping ("/faq/getFaqListByPage")
	   public String getFaqListByPage(Model model) {
		List<Faq> faqList = faqService.faqList();
		log.debug( CF.KHV +"[faqService]: +디버깅 확인"+ CF.RESET);
		model.addAttribute(faqList);
		return "faq/getFaqListByPage";
	}
	
	// 상세보기
	@GetMapping ("/faq/getFaqOne")
	public String faqOne(Model model, @RequestParam(name="faqNo") int faqNo) {
		log.debug( CF.KHV +"[FaqController GetMapping faqNo]: "+ faqNo + CF.RESET);
		Faq faq = new Faq();
		faq = (Faq)faqService.faqList();
		model.addAttribute("faq", faq);
		log.debug( CF.KHV +"[FaqController GetMapping faq]: "+ faq + CF.RESET);
		return "faq/getFaqOne";
	}
	
}
