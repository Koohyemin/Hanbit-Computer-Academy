package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Questionnaire;
import Hanbit.co.kr.lms.vo.Registration;

@Mapper
public interface QuestionnaireMapper {
	
	// 설문 여부 체크
	int selectQuestioncheck(String studentId);
	
	// 강의듣는 과목 리스트
	List<Registration> selectlecList(String studentId);
	
	//설문지 목록 불러오기
	List<Questionnaire> selectQuestionnaireList(String lectureName);
	
	// 설문지 응답 입력하기
	int selectQuestionnairecheck(Map<String,Object> map);
	
	// 설문지 응답 카테고리 별 반올림 평균치 내기
	List<Map<String,Object>> selectLecCategoryQueScore(Map<String,Object> map);
	
	// 강좌별 평점 
	Map<String,Object> selectLecScore();
}
