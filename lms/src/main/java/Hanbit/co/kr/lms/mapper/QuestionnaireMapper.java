package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Questionnaire;

@Mapper
public interface QuestionnaireMapper {
	//설문지 목록 불러오기
	List<Questionnaire> selectQuestionnaireList(String lectureName);
	
	// 설문지 응답 입력하기
	int selectQuestionnairecheck(Map<String,Object> map);
}
