package Hanbit.co.kr.lms.RESTController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Hanbit.co.kr.lms.service.ScoreService;
import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ScoreRESTController {
	@Autowired ScoreService scoreService;
	
	@GetMapping("/getScoreRank")
	public List<Map<String,Object>> getScoreRank(@RequestParam(name="lectureName") String lectureName) {
				
		log.debug(CF.LKL+"ScoreRESTController.getScoreRank.lectureName"+CF.RESET+lectureName);
		
		List<Map<String,Object>> list = scoreService.getAVGRank(lectureName);

		log.debug(CF.LKL+"ScoreRESTController.getScoreRank.list"+CF.RESET+list);			
		
		
		return list;
	}
}
