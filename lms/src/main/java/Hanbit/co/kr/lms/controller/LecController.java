package Hanbit.co.kr.lms.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.LecService;
import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LecController {
	
	@Autowired LecService lecService;
	@Autowired HttpSession session;
	
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
