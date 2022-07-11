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
	public List<Integer> insertQuestionnaire(String lectureName) {
		
		List<Integer> list = new ArrayList<>();
		
		list.add(questionnaireMapper.insertQuestionnarie1(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie2(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie3(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie4(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie5(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie6(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie7(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie8(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie9(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie10(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie11(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie12(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie13(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie14(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie15(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie16(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie17(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie18(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie19(lectureName));
		list.add(questionnaireMapper.insertQuestionnarie20(lectureName));

		
		return list;
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
		
		// 학새아이디 가져오기
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
