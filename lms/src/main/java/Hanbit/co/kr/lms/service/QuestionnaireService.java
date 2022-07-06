package Hanbit.co.kr.lms.service;

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
	
	// 강좌 평점
	public Map<String, Object> selectScorelectureName() {
		return questionnaireMapper.selectLecScore();
	}
}
