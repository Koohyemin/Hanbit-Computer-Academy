package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Faq;


@Mapper
public interface FaqMapper {
	// update action
		int updateNotice(int faq);
		
		// update form
		// 상세보기
		// delete form
		Faq selectNoticeOne(int noticeNo);
		
		// delete action
		int deleteNotice(int faqNo);
		
		// insert
		int insertNotice(Faq faq);
		
		// 전체 row
		int selectTotalCount();
		
		// select
		List<Faq> getFaqByPage(Map<String, Object> map);
	}
