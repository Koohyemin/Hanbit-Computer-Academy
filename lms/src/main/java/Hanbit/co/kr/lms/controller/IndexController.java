package Hanbit.co.kr.lms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Hanbit.co.kr.lms.service.IndexService;
import Hanbit.co.kr.lms.vo.EnquiryBoard;
import Hanbit.co.kr.lms.vo.LecPlan;
import Hanbit.co.kr.lms.vo.ManagerNotice;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {
	
	@Autowired IndexService indexService;
	
	@GetMapping("/home/index")
	public String indexBoardList(Model model, LecPlan lecPlan, EnquiryBoard enquiryBoard, ManagerNotice managerNotice) {
		//비승인 회원 목록_운영자
		List<Map<String, Object>> waitingList = indexService.selectIndexNonApprovalList();
		model.addAttribute("waitingList", waitingList);
		
		//강의계획서 목록_운영자
		List<LecPlan> lecPlanList = indexService.selectIndexLecList(lecPlan);
		model.addAttribute("lecPlanList", lecPlanList);
		
		//문의사항 목록_운영자
		List<EnquiryBoard> enquiryBoardList = indexService.selectIndexEnquiryBoardList(enquiryBoard);
		model.addAttribute("enquiryBoardList", enquiryBoardList);
		
		//공지사항 목록_운영자
		List<ManagerNotice> noticeList = indexService.selectIndexNoticeList(managerNotice);
		model.addAttribute("noticeList", noticeList);		
		
		//뷰 포워딩
		return "home/index";
	}
	
}
