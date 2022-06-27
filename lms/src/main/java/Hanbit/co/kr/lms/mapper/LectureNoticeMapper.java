package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.LectureNotice;


@Mapper
public interface LectureNoticeMapper {
	// 강좌 공지사항(학생/강사)
		// 강좌 공지사항 목록(페이징 포함)
		List<LectureNotice> getLecNoticeListByPage(Map<String, Object> map); 
		// 공지 개수
		int totalCount(); 
		
		// 공지사항 상세보기(전체) 
		LectureNotice getLecNoticeOne(int lecNoticeNo); 
		
		// 공지사항 작성(강사)
		int getInsertLectureNotice(LectureNotice lectureNotice); 
		
		// 공지사항 수정(강사)
		int getUpdateLectureNotice(LectureNotice lectureNotice); 
		
		// 공지사항 삭제(강사)
		int getDeleteLectureNotice(int lecNoticeNo); 
		
	}
