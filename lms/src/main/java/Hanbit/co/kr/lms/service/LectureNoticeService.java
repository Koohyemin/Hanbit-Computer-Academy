package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.LectureNoticeMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.LecPlan;
import Hanbit.co.kr.lms.vo.LectureNotice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LectureNoticeService {
	@Autowired LectureNoticeMapper lectureNoticeMapper;
	@Autowired HttpSession session;
	
	
		// 공지사항 리스트
		public Map<String, Object> getLecNoticeListByPage(int currentPage, int rowPerPage, String lectureName) {
			
			// 컨트롤러에서 가져온 값 
			log.debug(CF.KHV +"[LectureNoticeService getLecNoticeListByPage currentPage ]: " + CF.RESET + currentPage);
			log.debug(CF.KHV +"[LectureNoticeService getLecNoticeListByPage rowPerPage ]: " + CF.RESET + rowPerPage);
			log.debug(CF.KHV +"[LectureNoticeService getLecNoticeListByPage lectureName ]: " + CF.RESET + lectureName);
			
			// 현재페이지를 이욯하여 시작페이지 계산
			int beginRow = (currentPage-1) * rowPerPage;
			
			// map으로 하나의 값으로 묶어주기
			Map<String, Object> map = new HashMap<>();
			map.put("beginRow", beginRow); // 시작 페이지
			map.put("rowPerPage", rowPerPage); // 한 페이지당 표시할 공지 개수	
			map.put("lectureName", lectureName);
			
			// SQL 매개값 대입
			List<LectureNotice> list = lectureNoticeMapper.getLecNoticeListByPage(map);	
			int totalCount = lectureNoticeMapper.totalCount(); // 전체 공지 개수
			
			// 마지막 페이지
			int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
			
			// return값 하나의 값으로 묶어주기
			Map<String, Object> returnMap = new HashMap<>();
			returnMap.put("list", list);
			returnMap.put("lastPage", lastPage);
			
			// returnMap 반환
			return returnMap;
		}
		
		// 강좌공지사항 상세보기
		public LectureNotice getLecNoticeOne(int lecNoticeNo) {
			return lectureNoticeMapper.getLecNoticeOne(lecNoticeNo);
		}
		
		// 강좌공지사항 select(강사의 강좌)
		public int addLectureNotice(LectureNotice lectureNotice) {
			return lectureNoticeMapper.getInsertLectureNotice(lectureNotice);
		}
		
		
		// 강좌공지사항 삭제
		public int getDeleteLectureNotice(int lecNoticeNo) {
			return lectureNoticeMapper.getDeleteLectureNotice(lecNoticeNo);
		}
		
		// 강좌공지사항 수정 서비스
		public int updateLecNotice(LectureNotice lectureNotice) {
			return lectureNoticeMapper.updateLecNotice(lectureNotice);
		}
		
		
	// 공지사항 강사 수강별 강좌
		public List<LecPlan> lectureNameList(String teacherId) {
			List<LecPlan> lecPlan = lectureNoticeMapper.lectureNameList(teacherId);
			log.debug( CF.KHV +"[lectureNoticeService lectureNameList]: "+ CF.RESET + lecPlan.size());
			return lecPlan;
		}
		
		// 공지사항 학생 수강별 강좌
		public List<LecPlan> studentIdList(String studentId) {
			List<LecPlan> lecPlan = lectureNoticeMapper.studentIdList(studentId);
			log.debug( CF.KHV +"[lectureNoticeService lectureNameList]: "+ CF.RESET + lecPlan.size());
			return lecPlan;
		}
}