package Hanbit.co.kr.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.LecQuestion;

@Mapper
public interface LecQnaMapper {
	// 질문
	int insertQuestion(LecQuestion lecQuestion); // 질문 등록
	List<String> lectureList(String studentId); // 해당 학생 수강강의 불러오기(개강 한 강의)
	
	// 답변
}
