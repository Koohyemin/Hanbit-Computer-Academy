package Hanbit.co.kr.lms.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import Hanbit.co.kr.lms.service.QuestionnaireService;
import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class QuestionnaireScheduler {
	
	@Autowired QuestionnaireService questionnaireService;
	
	@Scheduled(cron = "00 00 12 * * *") // 종강일에 12시에 실행
	
	public void insertQuestionnaire() {
		
		// service에서 설문 등록 메서드 호출
		int row = questionnaireService.insertQuestionnaire();
		
		if(row == 20) { // 20개의 질문이 정상적으로 등록되었다면 등록 성공
			log.debug(CF.KHM + "[QuestionnaireScheduler insert] : " + CF.RESET + "설문 등록 성공");
		} else { // 아니라면 등록 실패
			log.debug(CF.KHM + "[QuestionnaireScheduler insert] : " + CF.RESET + "설문 등록 실패");
		}
	}
}
