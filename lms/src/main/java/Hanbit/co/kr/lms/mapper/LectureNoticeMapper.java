package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.LecPlan;
import Hanbit.co.kr.lms.vo.LectureNotice;


@Mapper
public interface LectureNoticeMapper {
	// 강좌 공지사항(학생/강사)
		// 강좌 공지사항 목록(페이징 포함)
		List<LectureNotice> getLecNoticeListByPage(Map<String, Object> map); 
		// 공지 개수
		int totalCount(); 
		
		//  상세보기(전체) 
		LectureNotice getLecNoticeOne(int lecNoticeNo); 
		
		//  작성(강사)
		int getInsertLectureNotice(LectureNotice lectureNotice); 
		
		//  수정(강사)
		int updateLecNotice(LectureNotice lectureNotice); 
		
		//  삭제(강사)
		int getDeleteLectureNotice(int lecNoticeNo); 
		
		// 강사 강좌정보 불러오기
		List<LecPlan> lectureNameList(String teacherId);
		
		// 학생 강좌장버 블러오기
		List<LecPlan> studentIdList(String studuntId); 
	}
