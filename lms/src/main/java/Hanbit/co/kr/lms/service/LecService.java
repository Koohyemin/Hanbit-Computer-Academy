package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.LecMapper;
import Hanbit.co.kr.lms.vo.Lec;
import Hanbit.co.kr.lms.vo.LecPlan;
import Hanbit.co.kr.lms.vo.LectureRoom;
import Hanbit.co.kr.lms.vo.Subject;

@Service
@Transactional
public class LecService {
	
	@Autowired LecMapper lecMapper;
	
	// 강의 삭제
	public int getDeleteLec(String lectureName) {
		return lecMapper.getDeleteLec(lectureName);
	}
	
	// 강의 수정
	public Map<String,Object> getUpdateLec(String lectureName) {
		
		// 카테고리 불러오기
		List<Subject> subjectList = lecMapper.subjectList(); // 과목 목록
		List<LecPlan> lecPlanList = lecMapper.lecPlanList(); // 강의계획 목록
		List<LectureRoom> lectureRoomList = lecMapper.lectureRoomList(); // 강의실 목록
		
		// return값 하나의 값으로 묶어주기
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("subjectList", subjectList);
		returnMap.put("lecPlanList", lecPlanList);
		returnMap.put("lectureRoomList", lectureRoomList);
		
		return returnMap;
	}
	
	// 강의 상세보기
	public Lec getLecOne(String lectureName) {
		return lecMapper.getLecOne(lectureName);
	}
	
	// 강의 등록 POST
	public int insertLec(Lec lec) {
		return lecMapper.insertLec(lec);
	}
	
	// 강의 등록 GET
	public Map<String,Object> insertLec() {
		
		// 카테고리 불러오기
		List<Subject> subjectList = lecMapper.subjectList(); // 과목 목록
		List<LecPlan> lecPlanList = lecMapper.lecPlanList(); // 강의계획 목록
		List<LectureRoom> lectureRoomList = lecMapper.lectureRoomList(); // 강의실 목록
		
		// return값 하나의 값으로 묶어주기
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("subjectList", subjectList);
		returnMap.put("lecPlanList", lecPlanList);
		returnMap.put("lectureRoomList", lectureRoomList);
		
		return returnMap;
	}
	
	// 개강전 강의 리스트
	public Map<String, Object> getLecListByPage(int currentPage, int rowPerPage) {
		
		// 현재페이지를 이욯하여 시작페이지 계산
		int beginRow = (currentPage-1) * rowPerPage;
		
		// map으로 하나의 값으로 묶어주기
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow); // 시작 페이지
		map.put("rowPerPage", rowPerPage); // 한 페이지당 표시할 강의 개수	
		
		// SQL 매개값 대입
		List<Lec> list = lecMapper.getLecListByPage(map);
		int totalCount = lecMapper.totalCount();
		
		// 마지막 페이지
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		
		// return값 하나의 값으로 묶어주기
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		
		// returnMap 반환
		return returnMap;
	}
}
