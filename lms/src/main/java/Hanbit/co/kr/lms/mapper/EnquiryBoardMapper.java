package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.EnquiryAnswer;
import Hanbit.co.kr.lms.vo.EnquiryBoard;

@Mapper
public interface EnquiryBoardMapper {
	
	
	// 문의사항 전체 행의 수
	int selectEnquiryBoardTotalCount(String category);
	
	// 문의사항문의사항 목록
	List<EnquiryBoard> selectEnquiryBoardListByPage(Map<String, Object> map);
	
	// 문의사항상세보기
	EnquiryBoard selectEnquiryBoardOne(int enquiryBoardNo);
	
	// 문의사항 입력
	int insertEnquiryBoard(EnquiryBoard enquiryBoard);
	
	// 문의사항 수정
	int updateEnquiryBoard(EnquiryBoard enquiryBoard);
	
	// 문의사항삭제
	int deleteEnquiryBoard(int enquiryBoardNo);
	
	// 문의사항 댓글 리스트
	List<EnquiryAnswer> selectEnquiryAnswerList(int enquiryBoardNo);
	
	// 문의사항 댓글 한개 삭제
	int deleteAnswer(int enquiryBoardAnswerNo);
	
	// 문의사항 댓글 입력
	int insertAnswer(Map<String, Object> map);
	
}
