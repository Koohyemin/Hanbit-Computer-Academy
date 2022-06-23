package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.LecQuestion;

@Mapper
public interface LecQuestionMapper {

	// 리스트+페이징
	List<LecQuestion> getLecQuestionListByPage(Map<String, Object> map); 
	// 상세보기
	
	// 입력
	
	// 삭제
}
