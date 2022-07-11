package Hanbit.co.kr.lms.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Faq;
import Hanbit.co.kr.lms.vo.ManagerNotice;


@Mapper
public interface FaqMapper {

	// 운영자 Faq 목록(페이징 포함)
	List<Faq> getFaqListByPage(); 
	
	// Faq 상세보기
	Faq getFaqOne(int faqNo); 
	
	//삭제
	int getDeleteFaq(int faqNo);
	
	// FAQ 작성
	int getInsertFaq(Faq faq); 
	
	// FAQ 수정 (운영자)
	
	int getUpdateFaq(Faq faq); 
}
