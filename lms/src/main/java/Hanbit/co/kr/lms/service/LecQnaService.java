package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.LecQnaMapper;
import Hanbit.co.kr.lms.vo.LecQuestion;

@Service
@Transactional
public class LecQnaService {
	
	@Autowired LecQnaMapper lecQnaMapper;
	@Autowired HttpSession session;
	
	// 강의실 질문 상세보기 Get
	public LecQuestion lecQuestionOne(int lecQuestionNo) {
		return lecQnaMapper.lecQuestionOne(lecQuestionNo);
	}
	
	// 강의실별 질문 목록 Get
	public Map<String, Object> lecQuestionListByPage(String lectureName, int currentPage, int rowPerPage) {
		// 현재페이지를 이욯하여 시작페이지 계산
		int beginRow = (currentPage-1) * rowPerPage;
		
		List<String> lectureList = lecQnaMapper.lectureList((String)session.getAttribute("sessionMemberId")); // 수강 강의 목록

		// 수강강의가 1개이상 있고 선택한 lectureName이 없다면 수강과목 중 첫번째 값을 lectureName기본값으로 설정
		if(lectureList.size() > 0 && (lectureName == null || "".equals(lectureName))) { 
			lectureName = lectureList.get(0);
		} else if(lectureList.size() == 0) {
			lectureName = ""; // 수강중인 강의가 없다면 공백으로 기본값 설정
		}

		// map으로 하나의 값으로 묶어주기
		Map<String, Object> map = new HashMap<>();
		map.put("lectureName", lectureName); // 해당 강의 명
		map.put("beginRow", beginRow); // 시작 페이지
		map.put("rowPerPage", rowPerPage); // 한 페이지당 표시할 강의 개수
		
		// SQL 매개값 대입
		List<LecQuestion> list = lecQnaMapper.lecQuestionList(map);
		int totalCount = lecQnaMapper.totalCount(lectureName);
		
		
		System.out.println("강의명 : " + lectureName);

		
		// 마지막 페이지
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		
		// return값 하나의 값으로 묶어주기
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		
		// returnMap 반환
		return returnMap;
	}
	
	// 강의실 질문 등록 Post
	public int insertQuestion(LecQuestion lecQuestion) {
		// 비공개 여부 선택이 없다면 공백이나 null로 들어온다.
		if(lecQuestion.getRevelation() == null || "".equals(lecQuestion.getRevelation())) {
			lecQuestion.setRevelation("T"); // 공개여부를 표시하는 T로 값 셋팅
		}
		
		return lecQnaMapper.insertQuestion(lecQuestion); // 입력 성공 행
	}

	// 강의실 질문 등록 Get
	public List<String> lectureList(String studentId) {
		return lecQnaMapper.lectureList(studentId); // 수강 중인 강의 목록
	}
	
}
