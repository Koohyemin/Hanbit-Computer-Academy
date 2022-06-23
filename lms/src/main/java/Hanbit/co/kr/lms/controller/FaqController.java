package Hanbit.co.kr.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.mapper.FaqMapper;
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
	public String getFaqOne(Model model, @RequestParam(name="faqNo") int faqNo) {
		log.debug( CF.KHV +"[FaqController GetMapping faqNo]: "+ faqNo + CF.RESET);
		Faq faq = new Faq();
		faq = (Faq)faqService.getFaqOne(faqNo);
		model.addAttribute("faq", faq);
		log.debug( CF.KHV +"[FaqController GetMapping faq]: "+ faq + CF.RESET);
		return "faq/getFaqOne";
	}
	// FAQ 등록
	@PostMapping("/faq/addFaq")
	public String getInsertFaq(Faq faq) {
		int row = faqService.getInsertFaq(faq);
		if(row == 1) {
			log.debug(CF.KHV + "[FaqController postMapping addFaq] : 입력 성공" + CF.RESET); // 성공 디버깅
		} else {
			log.debug(CF.KHV + "[FaqController postMapping addFaq] : 입력 실패" + CF.RESET); // 실패 디버깅
		}
	
		return "redirect:/faq/getFaqListByPage"; // 공지 입력 후, 리스트로 돌아가기
	}
		
		@GetMapping("/faq/addFaq")
		public String getInsertFaq() {
			return "faq/addFaq";
		}
	
	
		
	// 삭제 엑션	
		@PostMapping("/faq/deleteFaq")
		public String getDeleteFaq(int faqNo, Model model) {
			int row = faqService.getDeleteFaq(faqNo);
			if(row == 1) {
				log.debug(CF.KHM + "[FaqController postMapping addFaq] : 공지 삭제 성공" + CF.RESET); // 성공 디버깅
			} else {
				log.debug(CF.KHM + "[FaqController postMapping addFaq] : 공지 삭제 실패" + CF.RESET); // 실패 디버깅
			}
		
			return "redirect:/faq/getFaqListByPage"; // 공지 수정 후, 리스트로 돌아가기
		}
	
		
}
