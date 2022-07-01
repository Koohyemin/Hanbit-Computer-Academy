package Hanbit.co.kr.lms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.KeepingService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Keeping;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class KeepingController {

	@Autowired
	KeepingService keepingService;
	
	@GetMapping("keeping/addKeeping")
	public String addKeeping(HttpSession session,Keeping keeping) {
		
	    String memberId = (String) session.getAttribute("sessionMemberId");

		log.debug(CF.LKL+"KeepingController.addKeeping.lectureName :"+CF.RESET+keeping);
		log.debug(CF.LKL+"KeepingController.addKeeping.sessionMemberId :"+CF.RESET+memberId);
		
		keeping.setStudentId(memberId);
		
		keepingService.addKeeping(keeping);
		
		
		return "redirect:/lec/lecList";
		
	}
	
	@GetMapping("keeping/getKeeping")
	public String getKeeping(HttpSession session,Model model) {
		
	    String memberId = (String) session.getAttribute("sessionMemberId");

		log.debug(CF.LKL+"KeepingController.getKeeping.sessionMemberId :"+CF.RESET+memberId);
		
		List<Keeping> keepingList = keepingService.getKeeping(memberId);

		log.debug(CF.LKL+"KeepingController.getKeeping.keepingList :"+CF.RESET+keepingList);

		
		model.addAttribute("keepingList",keepingList);

		return "/keeping/keepingList";
		
	}
	
	@GetMapping("keeping/removeKeeping")
	public String removeKeeping(HttpSession session,Keeping keeping) {
		
	    String memberId = (String) session.getAttribute("sessionMemberId");

		log.debug(CF.LKL+"KeepingController.removeKeeping.keeping :"+CF.RESET+keeping);
		log.debug(CF.LKL+"KeepingController.removeKeeping.sessionMemberId :"+CF.RESET+memberId);
		
		keeping.setStudentId(memberId);
		
		int row = keepingService.remvoeKeeping(keeping);
		
		
		return "redirect:/keeping/getKeeping";
		
	}
	
}
