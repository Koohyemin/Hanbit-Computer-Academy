package Hanbit.co.kr.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Questionnaire;

@Mapper
public interface QuestionnaireMapper {
	//질문목록 불러오기
	List<Questionnaire> selectQuestionnaireList(String lectureName);
}
