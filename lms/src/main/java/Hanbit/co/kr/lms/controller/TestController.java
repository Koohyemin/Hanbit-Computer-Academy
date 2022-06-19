package Hanbit.co.kr.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {

	@GetMapping("/test")
	public String test() {
		
		log.debug( CF.KYJ +"[TestController GetMapping test]: "+"김유진 디버깅 테스트"+ CF.RESET);
		log.debug( CF.KHM +"[TestController GetMapping test]: "+"구혜민 디버깅 테스트"+ CF.RESET);
		log.debug( CF.SWB +"[TestController GetMapping test]: "+"송원범 디버깅 테스트"+ CF.RESET);
		return "test";
	}
}
