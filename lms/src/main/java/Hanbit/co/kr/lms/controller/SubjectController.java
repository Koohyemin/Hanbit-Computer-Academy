package Hanbit.co.kr.lms.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.SubjectService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Subject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SubjectController {
	@Autowired SubjectService subjectService;
	
	@GetMapping("/subject/getSubjectList")
	public String getSubjectList(Model model
			, @RequestParam (name="searchValue",defaultValue = "") String searchValue) {
		
		// 검색어 출력
		log.debug( CF.KYJ +"[SubjectController GetMapping getSubjectList searchValue]: "+ searchValue + CF.RESET);
		
		// 과목의 리스트를 불러옴
		List<Subject> subjectList = subjectService.selectsubjectList(searchValue);
		log.debug( CF.KYJ +"[SubjectController GetMapping getSubjectList subjectList]: "+ subjectList + CF.RESET);
		
		model.addAttribute("subjectList",subjectList);
		
		return "subject/getSubjectList";
	}
	
	// 과목 입력 Get
	@GetMapping("/subject/getAddSubject")
	public String getAddSubject() {
		
		return "subject/getAddSubject";
	}
	
	// 과목 입력 Post
	@PostMapping("/subject/getAddSubject")
	public String getAddSubject(
			@RequestParam (name="subjectName")String subjectName
			,@RequestParam(name="subjectSubscription")String subjectSubscription) {
		
		// getAddSubject에서 받아온 값을 디버깅
		log.debug( CF.KYJ +"[SubjectController PostMapping getAddSubject subjectName]: "+ subjectName + CF.RESET);
		log.debug( CF.KYJ +"[SubjectController PostMapping getAddSubject subjectSubscription]: "+ subjectSubscription + CF.RESET);
		
		int row = subjectService.addSubject(subjectName, subjectSubscription);
		if(row == 1) {
			log.debug( CF.KYJ +"[SubjectController PostMapping row ]: "+ row+" SubjectController 입력완료" + CF.RESET);
		}
		return "redirect:/subject/getSubjectList";
	}
}
