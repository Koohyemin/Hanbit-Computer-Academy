package Hanbit.co.kr.lms.RESTController;

import java.util.List;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class QuestionnaireRESRController {

	
	//설문지 목록 불러오기 get
	@PostMapping("/questionnaire/getQuestionnaireCheckList")
	public void getQuestionnairecheck(
			@RequestParam(value="quelist[]") List<String> quelist
			,@RequestParam(value="checklist[]") List<String> checklist) {
		
		log.debug(CF.KYJ+"[QuestionnaireController postMapping getQuestionnairecheck quelist]"+CF.RESET+quelist); 
		log.debug(CF.KYJ+"[QuestionnaireController postMapping getQuestionnairecheck checklist]"+CF.RESET+checklist); 
  
		
	}
}
