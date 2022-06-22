package Hanbit.co.kr.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.StudentService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class StudentController {
	@Autowired StudentService studentService;
	
	// 학생상세보기
	@GetMapping("getStudentOne")
	public String studentOne(Model model
							,@RequestParam(name="studentId",defaultValue = "student1")String studentId) {
		// studentId 디버깅
		log.debug(CF.SWB+"[StudentController studentOne studentId]"+ studentId+CF.RESET);
		Student student = new Student();
		student = studentService.studentOne(studentId);
		model.addAttribute("student",student);
		return "/student/studentOne";
	}
}
