package Hanbit.co.kr.lms.controller;

import java.util.List;
import java.util.Map;

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
	
	@GetMapping("/teacher/teacherList")
	public String getTeacherList(Model model, @RequestParam(name="searchValue", defaultValue="") String searchValue) {
		List<Map<String,Object>> list = (List<Map<String,Object>>)teacherService.getTeacherList(searchValue).get("teacherList"); // 강사 리스트
	
		log.debug( CF.KHM +"[TeacherService GetMapping searchValue]: "+ searchValue + CF.RESET);
		model.addAttribute("teacherList", list);
		model.addAttribute("searchValue", searchValue);
		return "teacher/teacherList";
	}
}
