package Hanbit.co.kr.lms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import Hanbit.co.kr.lms.service.ScoreService;
import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ScoreController {
	@Autowired HttpSession session;
	@Autowired ScoreService scoreService;
	
	//강사 수강반 성적 순위리스트
	@GetMapping("/lec/scoreRankigByLec")
	public String scoreRankigByLec(Model model	
								,@RequestParam(name="lectureName", required=false ) String lectureName) {						
		
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");		
		if (memberLv < 2) {								//멤버레벨이 2보다 낮으면 로그아웃
			return "redirect:/home/index";

		}
			
		String teacherId = (String) session.getAttribute("sessionMemberId");
		
		log.debug(CF.LKL+"ScoreController.getRegistration.lectureName"+CF.RESET+lectureName);
		
		Map<String,Object> map = scoreService.getScoreRank(lectureName,teacherId);
		

		log.debug(CF.LKL+"RegistrationController.getRegistration.map"+CF.RESET+map);

		log.debug(CF.LKL+"RegistrationController.getRegistration.lectureName"+CF.RESET+lectureName);			
		
		model.addAttribute("defaultValue", map.get("defaultValue"));
		model.addAttribute("scorelist",map.get("scorelist"));
		model.addAttribute("lectureList", map.get("lectureList"));
		
		return "/lec/scoreRankigByLec";
	}
	
	@GetMapping("/lec/scoreRankigByStudent")
	public String scoreRankigByStudent(Model model	
								,@RequestParam(name="lectureName", required=false ) String lectureName) {						
		
		int memberLv = (Integer) session.getAttribute("sessionMemberLv");
		
		if (memberLv != 1) {								//멤버레벨이 1이 아닐 시 로그아웃   
			return "redirect:/home/index";

		}
			
		String StudentId = (String) session.getAttribute("sessionMemberId");
		
		log.debug(CF.LKL+"ScoreController.scoreRankigByStudent.lectureName"+CF.RESET+StudentId);
		
		List<Map<String,Object>> list = scoreService.getScoreByStudent(StudentId);
		
		log.debug(CF.LKL+"ScoreController.scoreRankigByStudent.list"+CF.RESET+list);
		
		model.addAttribute("list",list);
		
		return "/lec/scoreRankingListByStudent";
	}
}
