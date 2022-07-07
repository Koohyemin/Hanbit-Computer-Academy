package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.LecReferenceMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.LecReference;
import Hanbit.co.kr.lms.vo.Registration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LecReferenceService {
@Autowired LecReferenceMapper lecReferenceMapper;
@Autowired HttpSession session;

		// 강좌자료실 리스트
		public Map<String, Object> getLecReferenceListByPage(int currentPage, int rowPerPage, String lectureName) {
		
		// 컨트롤러에서 가져온 값 
		log.debug(CF.KHV +"[LectureNoticeService getLecReferenceListByPage currentPage ]: " + CF.RESET + currentPage);
		log.debug(CF.KHV +"[LectureNoticeService getLecReferenceListByPage rowPerPage ]: " + CF.RESET + rowPerPage);
		log.debug(CF.KHV +"[LectureNoticeService getLecReferenceListByPage lectureName ]: " + CF.RESET + lectureName);
		
		// 현재페이지를 이욯하여 시작페이지 계산
		int beginRow = (currentPage-1) * rowPerPage;
		
		// map으로 하나의 값으로 묶어주기
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow); // 시작 페이지
		map.put("rowPerPage", rowPerPage); // 한 페이지당 표시할 공지 개수	
		map.put("lectureName", lectureName);
		
		// SQL 매개값 대입
		List<LecReference> list = lecReferenceMapper.getLecReferenceListByPage(map);	
		int totalCount = lecReferenceMapper.totalCount(); // 전체 공지 개수
		
		// 마지막 페이지
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		
		// return값 하나의 값으로 묶어주기
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		
		// returnMap 반환
		return returnMap;
	}
		//공지사항 강사 수강별 강좌
		public String teacherList(String teacherId) {
			String teacherList  = lecReferenceMapper.teacherList(teacherId);
			log.debug( CF.KHV +"[lecReferenceService teacherList teacherId]: "+ CF.RESET + teacherId);
			return teacherList;
			}
		
		// 공지사항 학생 수강별 강좌
		public List<Registration> studentLectureNameList(String studentId) {
			List<Registration> registrations = lecReferenceMapper.studentLectureNameList(studentId);
			log.debug( CF.KHV +"[lecReferenceService studentLectureNameList studentId]: "+ CF.RESET + studentId);
			return registrations;
		}
}