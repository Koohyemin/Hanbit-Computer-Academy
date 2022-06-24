package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.mapper.FaqMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Faq;
import Hanbit.co.kr.lms.vo.ManagerNotice;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
public class FaqService {
	@Autowired FaqMapper faqMapper;
	// faq 리스트
	public List<Faq> faqList() {
		log.debug( CF.KHV +"[FaqService faqList Faq]: "+ faqList() + CF.RESET);
		return faqMapper.getFaqListByPage();
	}
	// faq 상세보기
	public Faq getFaqOne(int faqNo) {
		Faq faq = new Faq();
		faq = faqMapper.getFaqOne(faqNo);
		log.debug( CF.KHV +"[FaqService getFaqOne Faq]: "+ faq.getContent() + CF.RESET);
		return faq;
	}
	// faq 입력
	public int getInsertFaq(Faq faq) {
		log.debug( CF.KHV +"[FaqService getInsertFaq Faq]: "+ faq.getContent() + CF.RESET);
		return faqMapper.getInsertFaq(faq); // 등록 성공 여부
	}

	// faq 삭제
	public int getDeleteFaq(int faqNo) {
		log.debug( CF.KHV +"[FaqService getDeleteFaq Faq]: "+ faqNo + CF.RESET);
		return faqMapper.getDeleteFaq(faqNo);
	}    
}
