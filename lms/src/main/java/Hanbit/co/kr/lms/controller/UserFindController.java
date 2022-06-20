package Hanbit.co.kr.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.UserFindService;
import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserFindController {
	@Autowired private UserFindService userFindService;
	//id찾기
	@GetMapping("/findId")
	public String findId() {
		return "findId";
	}
	@PostMapping("/findId")
	public String findId(Model model,
			@RequestParam(name="studentName") String studentName
			,@RequestParam(name="studentPhone") String studentPhone) {
		log.debug( CF.KHN +"[UserController PostMapping findId studentName]:  "+studentName+ CF.RESET);
		log.debug( CF.KHN +"[UserController PostMapping findId studentPhone]:  "+studentPhone+ CF.RESET);
		String studendId = userFindService.studentFindId(studentName, studentPhone);
		log.debug( CF.KHN +"[UserController PostMapping findId studendId]:  "+studendId+ CF.RESET);
		return "findId";
	}

}
