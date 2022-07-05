package Hanbit.co.kr.lms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.LecHomeworkService;
import Hanbit.co.kr.lms.service.LectureNoticeService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.HomeworkMake;
import Hanbit.co.kr.lms.vo.LecPlan;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LecHomeworkController {
	@Autowired LecHomeworkService lecHomeworkSerivce;
	@Autowired LectureNoticeService lectureNoticeService;
	@Autowired HttpSession session;
	
	// 과제 리스트
	@GetMapping("/lecHomework/getLecHomeworkList")
	public String getLecHomeworkList(Model model
									,@RequestParam(name="lectureName",required = false)String lectureName) {
		
		log.debug(CF.SWB+"[LecHomeworkController getLecHomeworkList lectureName]"+CF.RESET+ lectureName); // lectureName 디버깅
		// 강사 아이디 값 가져오기 
		String teacherId = (String) session.getAttribute("sessionMemberId");	
		
		// vo
		LecPlan lecPlan = new LecPlan();
		lecPlan.setLectureName(lectureName);
		lecPlan.setTeacherId(teacherId);
		
		// 뷰를 호출시 모델레이어로 부터 반환된 값(모델)을 뷰로 전송				
		List<LecPlan> lectureNameList = lectureNoticeService.lectureNameList(teacherId);		
		
		// 강좌 선택을 위해 가르치는 강좌 리스트를 보내줌
		model.addAttribute("lectureNameList", lectureNameList);
		// 뷰를 호출시 모델레이어로 부터 반환된 값(모델)을 뷰로 전송				
		List<HashMap<String,Object>> homeworkMake = lecHomeworkSerivce.homeworkList(lecPlan);
		log.debug(CF.SWB+"[LecHomeworkController getLecHomeworkList homeworkMake]"+CF.RESET+ homeworkMake); // 과제리스트 디버깅
		
		model.addAttribute("homeworkMake",homeworkMake);
		return "lecHomework/getLecHomeworkList";
	}
	
	// 과제등록 폼
	@GetMapping("/lecHomework/addHomework")
	public String addHomework(Model model) {
		
		// 강사 아이디 값 가져오기 
		String teacherId = (String) session.getAttribute("sessionMemberId");
		
		// 뷰를 호출시 모델레이어로 부터 반환된 값(모델)을 뷰로 전송				
		List<LecPlan> lectureNameList = lectureNoticeService.lectureNameList(teacherId);
		
		// 오늘날짜 yyyy-MM-dd로 생성
		String todayfm = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
		log.debug(CF.SWB+"[addHomework addHomework todayfm]"+CF.RESET+ todayfm); // todayfm 디버깅
		
		// 강좌 선택을 위해 가르치는 강좌 리스트를 보내줌
		model.addAttribute("lectureNameList", lectureNameList);
		model.addAttribute("deadline",todayfm);
		return "lecHomework/addHomework";
	}
	
	// 과제등록 액션
	@PostMapping("/lecHomework/addHomework")
	public String addHomework(@RequestParam(name="lectureName")String lectureName
							,@RequestParam(name="homeworkMakeTitle")String homeworkMakeTitle
							,@RequestParam(name="homeworkMakeContent")String homeworkMakeContent
							,@RequestParam(name="homeworkDeadline")String homeworkDeadline) {
		// vo 값 담기
		HomeworkMake h = new HomeworkMake();
		h.setLectureName(lectureName);
		h.setHomeworkMakeTitle(homeworkMakeTitle);
		h.setHomeworkMakeContent(homeworkMakeContent);
		h.setHomeworkDeadline(homeworkDeadline);
		
		log.debug(CF.SWB+"[addHomework post addHomework h]"+CF.RESET+ h); // HomeworkMake 디버깅
		
		lecHomeworkSerivce.insertHomework(h);
		
		return "redirect:/lecHomework/getLecHomeworkList";
	}
	
	// 과제 상세보기
	@GetMapping("lecHomework/lecHomeworkOne")
	public String lecHomeworkOne(Model model
			           							,@RequestParam(name="homeworkMakeNo")int homeworkMakeNo) {
		
		HashMap<String, Object> map = lecHomeworkSerivce.selectHomeworkOne(homeworkMakeNo);
		model.addAttribute("submitList", map.get("submitList")); 
		log.debug(CF.SWB+"[LecHomeworkController post addHomework submitList]"+CF.RESET+ map.get("submitList")); // submitList 디버깅
		model.addAttribute("homeworkMake", map.get("homeworkMake")); 
		
		return "/lecHomework/lecHomeworkOne";
	}
}
