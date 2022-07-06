package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.LecAnswer;
import Hanbit.co.kr.lms.vo.LecQuestion;

@Mapper
public interface LecQnaMapper {
	// 질문
	LecQuestion lecQuestionOne(int questionNo); // 질문 상세보기
	List<LecQuestion> lecQuestionList(Map<String,Object> map); // 강의별 질문게시판 목록 (페이징) lectureName, beginPage, rowPerPage
	int totalCount(String lectureName); // 강의별 질문 개수
	int insertQuestion(LecQuestion lecQuestion); // 질문 등록
	int updateQuestion(LecQuestion lecQuestion); // 질문 수정
	int deleteQuestion(int lecQuestionNo); // 답변과 함께 삭제
	int deleteAnswer(int lecQuestionNo); // 질문 삭제시 관련된 모든 답변 삭제
	List<String> lectureList(String studentId); // 해당 학생 수강강의 불러오기(개강 한 강의)

	// 답변
	List<LecAnswer> lecAnswerList(Map<String,Object> map); // 답변 상세보기 목록 (페이징) lecQuestionNo, beginPage, rowPerPage
	int insertAnswer(LecAnswer lecAnswer); // 답변(댓글) 등록
	int updateAnswer(LecAnswer lecAnswer); // 답변(댓글) 수정
	int deleteOneAnswer(int lecAnswerNo); // 답변(댓글) 삭제
}
