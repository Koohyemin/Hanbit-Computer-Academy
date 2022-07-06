package Hanbit.co.kr.lms.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.IndexService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.EnquiryBoard;
import Hanbit.co.kr.lms.vo.LecPlan;
import Hanbit.co.kr.lms.vo.ManagerNotice;
import Hanbit.co.kr.lms.vo.Registration;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexAdminController {
	
	@Autowired IndexService indexService;
	@Autowired HttpSession session;

	
	@GetMapping("/home/index")
	public String indexAdminBoardList(ManagerNotice managerNotice, Registration registration, Model model) {
		String studentId = (String) session.getAttribute("sessionMemberId");
		//운영자
		List<Map<String, Object>> waitingList = indexService.selectIndexNonApprovalList();
		log.debug( CF.KHN +"[IndexAdminController selectIndexNonApprovalList waitingList]: "+ CF.RESET + waitingList);
		
		List<LecPlan> lecPlanList = indexService.selectIndexLecPlanList();
		log.debug( CF.KHN +"[IndexAdminController selectIndexLecPlanList lecPlanList]: "+ CF.RESET + lecPlanList);
		
		List<EnquiryBoard> enquiryBoardList = indexService.selectIndexEnquiryBoardList();
		log.debug( CF.KHN +"[IndexAdminController selectIndexEnquiryBoardList enquiryBoardList]: "+ CF.RESET + enquiryBoardList);
		
		List<ManagerNotice> noticeList = indexService.selectIndexNoticeList();
		log.debug( CF.KHN +"[IndexAdminController selectIndexNoticeList noticeList]: "+ CF.RESET + noticeList);
		
		//강사
		List<ManagerNotice> teacherList = indexService.selectIndexTeacherNoticeList(managerNotice);
		log.debug( CF.KHN +"[IndexAdminController selectIndexTeacherNoticeList teacherList]: "+ CF.RESET + teacherList);
		
		//학생
		List<Registration> studentLecList = indexService.selectIndexStudentLectureNameList(registration);
		log.debug( CF.KHN +"[IndexAdminController selectIndexStudentLectureNameList studentLecList]: "+ CF.RESET + studentLecList);	
		
		List<ManagerNotice> studentList = indexService.selectIndexStudentNoticeList(managerNotice);
		log.debug( CF.KHN +"[IndexAdminController selectIndexStudentNoticeList studentList]: "+ CF.RESET + studentList);
		
		//운영자
		model.addAttribute("waitingList", waitingList);
		model.addAttribute("lecPlanList", lecPlanList);
		model.addAttribute("enquiryBoardList", enquiryBoardList);
		model.addAttribute("noticeList", noticeList);
		
		//강사
		model.addAttribute("teacherList", teacherList);
		
		//학생
		model.addAttribute("studentLecList", studentLecList);
		model.addAttribute("studentList", studentList);
		
		
		//강사
		
		//학생
		
		//뷰 포워딩
		return "home/index";
	}
	
}
