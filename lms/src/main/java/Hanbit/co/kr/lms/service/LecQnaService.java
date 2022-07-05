package Hanbit.co.kr.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.LecQnaMapper;
import Hanbit.co.kr.lms.vo.LecQuestion;

@Service
@Transactional
public class LecQnaService {
	
	@Autowired LecQnaMapper lecQnaMapper;
	
	// insertQuestion Post
	public int insertQuestion(LecQuestion lecQuestion) {
		// 비공개 여부 선택이 없다면 공백이나 null로 들어온다.
		if(lecQuestion.getRevelation() == null || "".equals(lecQuestion.getRevelation())) {
			lecQuestion.setRevelation("T"); // 공개여부를 표시하는 T로 값 셋팅
		}
		
		return lecQnaMapper.insertQuestion(lecQuestion); // 입력 성공 행
	}

	// insertQuestion Get
	public List<String> lectureList(String studentId) {
		return lecQnaMapper.lectureList(studentId); // 수강 중인 강의 목록
	}
	
}
