package Hanbit.co.kr.lms.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.LecHomeworkService;
import Hanbit.co.kr.lms.service.LectureNoticeService;
import Hanbit.co.kr.lms.util.CF;
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
}
