package Hanbit.co.kr.lms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.LectureRoomService;
import Hanbit.co.kr.lms.vo.HomeworkMake;
import Hanbit.co.kr.lms.vo.LecQuestion;
import Hanbit.co.kr.lms.vo.LecReference;
import Hanbit.co.kr.lms.vo.LectureNotice;

@Controller
public class LectureRoomController {
	
	@Autowired LectureRoomService lectureRoomService;
	@Autowired HttpSession session;
	
	@GetMapping("/lectureRoom/index")
	public String lectureRoomIndex(Model model
									,@RequestParam(name="lectureName", defaultValue = "") String lectureName) {
		List<String> lectureList = lectureRoomService.lectureList((String)session.getAttribute("sessionMemberId")); // 학생 수강강의 목록
		List<String> teacherLectureList = lectureRoomService.teacherLectureList((String)session.getAttribute("sessionMemberId")); // 강사 수강강의 목록
		
		List<HomeworkMake> homeworkList = lectureRoomService.homeworkList(lectureName); // 과제 최근 5개 게시글
		List<LecQuestion> lecQuestionList = lectureRoomService.lecQuestionList(lectureName); // Q&A 최근 5개 게시글
		List<LectureNotice> lectureNoticeList = lectureRoomService.lectureNoticeList(lectureName); // 공지사항 최근 5개 게시글
		List<LecReference> lecReferenceList = lectureRoomService.lecReferenceList(lectureName); // 자료실 최근 5개 게시글
		
		// 최근 5개의 게시글 목록
		model.addAttribute("homeworkList", homeworkList);
		model.addAttribute("lecQuestionList", lecQuestionList);
		model.addAttribute("lectureNoticeList", lectureNoticeList);
		model.addAttribute("lecReferenceList", lecReferenceList);
		
		// 개수 전송
		model.addAttribute("lecReferenceSize",lecReferenceList.size());
		model.addAttribute("lectureNoticeSize",lectureNoticeList.size());
		model.addAttribute("lecQuestionSize",lecQuestionList.size());
		model.addAttribute("homeworkSize",homeworkList.size());
		
		model.addAttribute("lectureName", lectureName);
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("teacherLectureList", teacherLectureList);
		return "lectureRoom/lectureRoomIndex";
	}
}
