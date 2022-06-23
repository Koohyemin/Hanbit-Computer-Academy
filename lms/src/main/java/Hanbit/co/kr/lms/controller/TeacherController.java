package Hanbit.co.kr.lms.controller;

import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.TeacherService;
import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TeacherController {
	@Autowired TeacherService teacherService;
	// 강사 목록 및 검색(학생, 강사, 운영자 모두 조회 가능)
	@GetMapping("/teacher/teacherList")
	public String getTeacherList(Model model, @RequestParam(name="searchValue", defaultValue = "") String searchValue) {
		List<Map<String,Object>> list = teacherService.getTeacherList(searchValue);
		int listSize =list.size(); // list가 1개이상일 시 목록 출력을 위해 개수 변수 선언
		
		log.debug(CF.KHM + "[TeacherController getMapping list.size] : " + listSize + CF.RESET);
		
		model.addAttribute("searchValue", searchValue); // 검색어
		model.addAttribute("teacherList",list); // 강사 정보를 담은 목록
		model.addAttribute("listSize",listSize); // 목록이 0이상일때만 실행시키기 위한 size
		return "teacher/teacherList";
	}
}
