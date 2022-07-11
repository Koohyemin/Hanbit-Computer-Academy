//package Hanbit.co.kr.lms.component;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import Hanbit.co.kr.lms.service.QuestionnaireService;
//import Hanbit.co.kr.lms.util.CF;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Component
//@Transactional
//public class QuestionnaireScheduler {
//	
//	@Autowired QuestionnaireService questionnaireService;
//	
//	@Scheduled(cron = "0 0 12 * * *") // 종강일에 실행
//	
//	public void insertQuestionnaire(String lectureName) {
//		
//		// service에서 설문 등록 메서드 호출
//		List<Integer> questionnaire = questionnaireService.insertQuestionnaire(lectureName);
//		
//		log.debug(CF.KHM + "[QuestionnaireScheduler lectureName] : " + CF.RESET + lectureName);
//		
//		for(int i=0; i<questionnaire.size(); i++) {
//			int row = questionnaire.get(i);
//			
//			if(row == 1) {
//				log.debug(CF.KHM + "[QuestionnaireScheduler insert] : " + CF.RESET + i+"번 설문 등록 성공");
//			}
//		}
//		
//	}
//}
