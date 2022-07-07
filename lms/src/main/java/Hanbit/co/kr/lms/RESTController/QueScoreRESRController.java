package Hanbit.co.kr.lms.RESTController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Hanbit.co.kr.lms.service.QuestionnaireService;
import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class QueScoreRESRController {
	@Autowired QuestionnaireService questionnaireService;
	
	// 항목에 따른 점수 분포를 보여줌
	@GetMapping("/questionnaire/checkQueScore")
	public List<Map<String, Object>> checkQueScore(
			@RequestParam (name="num",defaultValue = "1") int num
			,@RequestParam(name="checknum",defaultValue = "1")int checknum ) {
		
		log.debug( CF.KYJ +"[QueScoreRESRController checkQueScore]: "+ "QueScoreRESRController 실행" + CF.RESET);
		log.debug( CF.KYJ +"[QueScoreRESRController checkQueScore]: "+ "QueScoreRESRController 실행 checknum :" + checknum);
		// 맵으로 보내줄 값을 묶어줌
		Map<String,Object> pushmap = new HashMap<>();
		pushmap.put("num", num);
		pushmap.put("checknum", checknum);
		log.debug( CF.KYJ +"[QueScoreRESRController checkQueScore pushmap]: "+ pushmap + CF.RESET);
		
		List<Map<String, Object>> list = questionnaireService.selectQueScore(pushmap);
		log.debug( CF.KYJ +"[QueScoreRESRController checkQueScore list]: "+ list + CF.RESET);	
		
		return list;
	}
	
	
}
