package Hanbit.co.kr.lms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.QuestionnaireMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Questionnaire;
import Hanbit.co.kr.lms.vo.Registration;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class QuestionnaireService {
	@Autowired QuestionnaireMapper questionnaireMapper;
	
	// 종강일 설문 등록
	public int insertQuestionnaire() {
		
		// 강의명 + 종강일 -> 해당하는 일만 뜬다
		List<Questionnaire> questionnaireList = questionnaireMapper.endClassDate();		
		
		String lectureName = ""; // 강의 명
		int totalRow = 0; // 등록 성공 전체 행 수
		
		// 종강일이 오늘날짜와 같다면 insert 실행
		if(questionnaireList.size() > 0) {
			for(int i=0; i<questionnaireList.size(); i++) {
				
				// 해당 강의명 받아오기(여러개일 수 있으므로 사이즈만큼 반복)
				lectureName = questionnaireList.get(i).getLectureName();
				
				// 설문 1~20번 실행
				int row1 = questionnaireMapper.insertQuestionnarie1(lectureName);
				int row2 = questionnaireMapper.insertQuestionnarie2(lectureName);
				int row3 = questionnaireMapper.insertQuestionnarie3(lectureName);
				int row4 = questionnaireMapper.insertQuestionnarie4(lectureName);
				int row5 = questionnaireMapper.insertQuestionnarie5(lectureName);
				int row6 = questionnaireMapper.insertQuestionnarie6(lectureName);
				int row7 = questionnaireMapper.insertQuestionnarie7(lectureName);
				int row8 = questionnaireMapper.insertQuestionnarie8(lectureName);
				int row9 = questionnaireMapper.insertQuestionnarie9(lectureName);
				int row10 = questionnaireMapper.insertQuestionnarie10(lectureName);
				int row11 = questionnaireMapper.insertQuestionnarie11(lectureName);
				int row12 = questionnaireMapper.insertQuestionnarie12(lectureName);
				int row13 = questionnaireMapper.insertQuestionnarie13(lectureName);
				int row14 = questionnaireMapper.insertQuestionnarie14(lectureName);
				int row15 = questionnaireMapper.insertQuestionnarie15(lectureName);
				int row16 = questionnaireMapper.insertQuestionnarie16(lectureName);
				int row17 = questionnaireMapper.insertQuestionnarie17(lectureName);
				int row18 = questionnaireMapper.insertQuestionnarie18(lectureName);
				int row19 = questionnaireMapper.insertQuestionnarie19(lectureName);
				int row20 = questionnaireMapper.insertQuestionnarie20(lectureName);
				
				// 성공 행 수
				totalRow = row1 + row2 + row3 + row4 + row5 + row6 + row7 + row8 + row9 + row10 + row11 + row12 + row13 + row14 + row15 + row16 + row17 + row18 + row19 + row20;
			}
		}
		
		return totalRow;
	}
	
	// 설문 여부 체크
	public int selectcheck(String studentId) {
		return questionnaireMapper.selectQuestioncheck(studentId);
	}
	
	// 강의 목록 출력
	public List<Registration> lecList(String studentId){
		return questionnaireMapper.selectlecList(studentId);
	}
	
	//설문지 목록 불러오기
	public List<Questionnaire> selectQuestionnaireList(String lectureName) {
		return questionnaireMapper.selectQuestionnaireList(lectureName);
	}

	// 설문지 응답 
	public void selectQuestionnairepoint(Map<String,Object> map) {
		
		// 문제번호 가져오기 
		List<Integer> quelist = (List<Integer>)(map.get("quelistmap"));
		log.debug( CF.KYJ +"[QuestionnaireService selectQuestionnairepoint quelist]: "+ quelist + CF.RESET);
		
		// 응답항목 가져오기 
		List<Integer> checklist = (List<Integer>)(map.get("checklistmap"));
		log.debug( CF.KYJ +"[QuestionnaireService selectQuestionnairepoint check]: "+ checklist + CF.RESET);
		
		// 학생아이디 가져오기
		String studentId = (String)(map.get("studentId"));
		
		// 문제 갯수만큼 반복
		for(int i = 0; i<quelist.size(); i++) {
		    int que = quelist.get(i);
			int check = checklist.get(i);
			Map<String ,Object> pushmap = new HashMap<>();
			pushmap.put("que",que);
			pushmap.put("studentId", studentId);
			pushmap.put("check", check);
			questionnaireMapper.selectQuestionnairecheck(pushmap);
		}
		
		log.debug( CF.KYJ +"[QuestionnaireService selectQuestionnairepoint quelist]: "+ "설문완료!!" + CF.RESET);
	}
	
	// 강좌 평점을 보여줌
	public Map<String, Object> selectScorelectureName() {
		return questionnaireMapper.selectLecScore();
	}
	
	// 강좌의 평가 항목에 대해 보여줌
	public List<Map<String,Object>> selectQueScore(Map<String,Object> map){
		List<Map<String, Object>> returnMap = questionnaireMapper.selectLecCategoryQueScore(map);
		return returnMap;
	}
	
	// 카테고리별 평점을 보여줌
	public List<Map<String,Object>> CategoryScore(int registrationNo){
		List<Map<String, Object>> map =  questionnaireMapper.selectCategoryScore(registrationNo);
		return map;
	}
}
