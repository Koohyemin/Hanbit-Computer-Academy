package Hanbit.co.kr.lms.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Faq;
import Hanbit.co.kr.lms.vo.ManagerNotice;


@Mapper
public interface FaqMapper {

		// Faq (운영자)
		// 운영자 Faq 목록(페이징 포함)
		List<Faq> getFaqListByPage(); 
		
		// 카테고리별(전체/학생/강사) 공지 개수
		int totalCount(); 
		
		// Faq 상세보기
		Faq getFaqOne(int faqNo); 
		
		
}
