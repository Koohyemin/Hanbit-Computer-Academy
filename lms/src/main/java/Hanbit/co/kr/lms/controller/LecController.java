package Hanbit.co.kr.lms.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.LecService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Lec;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LecController {
	
	@Autowired LecService lecService;
	@Autowired HttpSession session;
	
	// 강의 등록 POST
	@PostMapping("lec/addLec")
	public String addLec(Lec lec) {
		
		// 세션을 이용한 권한 처리
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if (memberLv != 3) { // 운영자가 아니라면 공지목록으로 돌아가기
			return "redirect:/lec/lecList";
		}
		
		// 등록 성공 행 반환(1 성공, 0 실패, 그 외 DB이상)
		int row = lecService.insertLec(lec);
		
		if(row == 1) {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] :" + CF.RESET + "강의 등록 성공"); // 성공
		} else {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] :" + CF.RESET + "강의 등록 실패"); // 실패
		}
		
		return "redirect:/people/peopleList?level=3";
	}
	
	// 강의 등록 GET
	@GetMapping("lec/addLec")
	public String addLec(Model model) {
		// 세션을 이용한 권한 처리
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if (memberLv != 3) { // 운영자가 아니라면 공지목록으로 돌아가기
			return "redirect:/notice/getNoticeListByPage";
		}
		
		// returnMap 불러오기
		Map<String, Object> map = lecService.insertLec();
		
		// model에 값 add
		model.addAttribute("subjectList", map.get("subjectList"));
		model.addAttribute("lecPlanList", map.get("lecPlanList"));
		model.addAttribute("lectureRoomList", map.get("lectureRoomList"));
		
		return "lec/addLec"; // lec/addLec.jsp로 이동
		
	}
	
	@GetMapping("lec/lecList")
	public String getLecListByPage(Model model,
								@RequestParam(name = "currentPage", defaultValue = "1") int currentPage, // 현재페이지, 1페이지부터 시작
								@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) { // 한페이지당 10개의 목록 출력
		
		// Service에 처리한 코드를 이용하여 매개값 대입
		Map<String, Object> map = lecService.getLecListByPage(currentPage, rowPerPage);
		
		log.debug(CF.KHM +"[LecController GetMapping currentPage]: " + CF.RESET + currentPage); // 현재페이지 디버깅
		log.debug(CF.KHM +"[LecController GetMapping totalCount]: " + CF.RESET + map.get("totalCount")); // 현재페이지 디버깅
		
		// model에 값 add
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", map.get("currnetPage"));
		model.addAttribute("totalCount", map.get("totalCount"));
		
		return "lec/lecList"; // lec/lecList.jsp로 이동
	}
}
