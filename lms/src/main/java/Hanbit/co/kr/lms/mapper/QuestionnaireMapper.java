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
	
	// 카테고리별 평균 평점
	List<Map<String,Object>> selectCategoryScore(int registrationNo);
	
	// 허용된 강좌별 종강일
	List<Questionnaire> endClassDate();
	
	// 설문조사 등록
	int insertQuestionnarie1(String lectureName); // 1번 설문
	int insertQuestionnarie2(String lectureName); // 2번 설문
	int insertQuestionnarie3(String lectureName); // 3번 설문
	int insertQuestionnarie4(String lectureName); // 4번 설문
	int insertQuestionnarie5(String lectureName); // 5번 설문
	int insertQuestionnarie6(String lectureName); // 6번 설문
	int insertQuestionnarie7(String lectureName); // 7번 설문
	int insertQuestionnarie8(String lectureName); // 8번 설문
	int insertQuestionnarie9(String lectureName); // 9번 설문
	int insertQuestionnarie10(String lectureName); // 10번 설문
	int insertQuestionnarie11(String lectureName); // 11번 설문
	int insertQuestionnarie12(String lectureName); // 12번 설문
	int insertQuestionnarie13(String lectureName); // 13번 설문
	int insertQuestionnarie14(String lectureName); // 14번 설문
	int insertQuestionnarie15(String lectureName); // 15번 설문
	int insertQuestionnarie16(String lectureName); // 16번 설문
	int insertQuestionnarie17(String lectureName); // 17번 설문
	int insertQuestionnarie18(String lectureName); // 18번 설문
	int insertQuestionnarie19(String lectureName); // 19번 설문
	int insertQuestionnarie20(String lectureName); // 20번 설문
}
