package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.EnquiryBoard;

@Mapper
public interface EnquiryBoardMapper {
	
	
	//전체 행의 수
	int selectEnquiryBoardTotalCount(String category);
	
	//문의사항 목록
	List<EnquiryBoard> selectEnquiryBoardListByPage(Map<String, Object> map);
	
	//상세보기
	EnquiryBoard selectEnquiryBoardOne(int enquiryBoardNo);
	
	//입력
	int insertEnquiryBoard(EnquiryBoard enquiryBoard);
	
	//수정
	int updateEnquiryBoard(EnquiryBoard enquiryBoard);
}
