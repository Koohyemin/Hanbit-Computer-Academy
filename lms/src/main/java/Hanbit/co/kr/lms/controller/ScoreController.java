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
	
	@GetMapping("/lec/scoreRankigByLec")
	public String getRegistration(Model model	
								,@RequestParam(name="lectureName", required=false ) String lectureName) {						//수강 등록 내역 리스트
		
/*		if (Integer.parseInt((String) session.getAttribute("sessionMemberLv")) < 2) { // 강사보다 권한이 낮으면 로그인페이지로 이동
			return "redirect:/logout";
		}*/
		
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
}
