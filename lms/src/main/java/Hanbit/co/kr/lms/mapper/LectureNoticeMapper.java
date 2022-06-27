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
		int totalCount(String category); 
		
		// 공지사항 상세보기(전체), 수정에서도 사용(운영자)
		LectureNotice getLecNoticeOne(int managerNoticeNo); 
		
		// 공지사항 작성(운영자)
		int getInsertLecNotice(LectureNotice lectureNOtice); 
		
		// 공지사항 수정(운영자)
		int getUpdateLecNotice(LectureNotice lectureNOtice); 
		
		// 공지사항 삭제(운영자)
		int getDeleteLecNotice(int lectureNOticeNo); 
		
	}
