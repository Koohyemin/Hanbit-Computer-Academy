package Hanbit.co.kr.lms.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.NoticeService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.ManagerNotice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NoticeController {
	@Autowired NoticeService noticeService;
	@Autowired HttpSession session;
	
	/**
	 * @param managerNoticeNo
	 * @param model
	 * @return
	 */
	// 공지사항 삭제 - 운영자
	@PostMapping("/notice/deleteNotice")
	public String getDeleteNotice(int managerNoticeNo, Model model) {
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if (memberLv != 3) { // 운영자가 아니라면 공지목록으로 돌아가기
			return "redirect:/notice/getNoticeListByPage";
		}
		
		//
		int row = noticeService.getDeleteNotice(managerNoticeNo);
		
		if(row == 1) {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] : 공지 삭제 성공" + CF.RESET); // 성공 디버깅
		} else {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] : 공지 삭제 실패" + CF.RESET); // 실패 디버깅
		}
	
		return "redirect:/notice/noticeList"; // 공지 수정 후, 리스트로 돌아가기
	}
	/**
	 * @param managerNotice
	 * @param model
	 * @return
	 */
	// 공지사항 수정 - 운영자
	@PostMapping("/notice/updateNotice")
	public String getUpdateNotice(ManagerNotice managerNotice, Model model) {
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if (memberLv != 3) { // 운영자가 아니라면 공지목록으로 돌아가기
			return "redirect:/notice/getNoticeListByPage";
		}
		int row = noticeService.getUpdateNotic(managerNotice);
		if(row == 1) {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] :"+managerNotice.getManagerNoticeNo()+"번 공지 수정 성공" + CF.RESET); // 성공 디버깅
		} else {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] :"+managerNotice.getManagerNoticeNo()+"번 공지 수정 실패" + CF.RESET); // 실패 디버깅
		}
		return "redirect:/notice/noticeOne?managerNoticeNo="+managerNotice.getManagerNoticeNo(); // 공지 수정 후, 리스트로 돌아가기
	}
	/**
	 * 
	 * @param model
	 * @param managerNoticeNo
	 * @return
	 */
	@GetMapping("/notice/updateNotice")
	public String getUpdateNotice(Model model, @RequestParam(name="managerNoticeNo") int managerNoticeNo) {
		
		// 수정시, 기존 입력 값 화면에 보여주기 위해 상세보기 값 불러오기 - 운영자
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if (memberLv != 3) { // 운영자가 아니라면 공지목록으로 돌아가기
			return "redirect:/notice/getNoticeListByPage";
		}
		
		ManagerNotice managerNotice = noticeService.getNoticeOne(managerNoticeNo);
		model.addAttribute("managerNotice", managerNotice);
		return "notice/updateNotice";
	}
	/**
	 * 
	 * @param managerNotice
	 * @return
	 */
	// 공지사항 등록 - 운영자만 이용 가능
	@PostMapping("/notice/addNotice")
	public String getInsertNotice(ManagerNotice managerNotice) {
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if (memberLv != 3) { // 운영자가 아니라면 공지목록으로 돌아가기
			return "redirect:/notice/getNoticeListByPage";
		}
		int row = noticeService.getInsertNotice(managerNotice);
		if(row == 1) {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] : 공지 입력 성공" + CF.RESET); // 성공 디버깅
		} else {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] : 공지 입력 실패" + CF.RESET); // 실패 디버깅
		}
	
		return "redirect:/notice/noticeList"; // 공지 입력 후, 리스트로 돌아가기
	}
	
	@GetMapping("/notice/addNotice")
	public String getInsertNotice() {
		return "notice/addNotice";
	}
	/**
	 * 
	 * @param model
	 * @param managerNoticeNo
	 * @return
	 */
	// 공지사항 상세보기, 모두 이용 가능
	@GetMapping("/notice/noticeOne")
	public String getNoticeOne(Model model, @RequestParam(name="managerNoticeNo") int managerNoticeNo) {
		// 공지사항 번호를 통해 상세보기
		log.debug( CF.KHM +"[NoticeController GetMapping managerNoticeNo]: "+ managerNoticeNo + CF.RESET);
		ManagerNotice managerNotice = noticeService.getNoticeOne(managerNoticeNo);
		model.addAttribute("managerNotice", managerNotice);
		return "notice/getNoticeOne";
	}
	/**
	 * 
	 * @param model
	 * @param currentPage
	 * @param rowPerPage
	 * @param category
	 * @return
	 */
	// 공지사항 리스트 및 페이징
	@GetMapping("/notice/noticeList")
	public String getNoticeByPage(Model model,
								@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
								@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage,
								@RequestParam(name = "category", defaultValue = "전체") String category) { // 페이지 카테고리 기본값을 "전체"로 둠.
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if(category.equals("학생")) { // 비로그인 상태이면 로그인 페이지로 이동, 강사가 접근한다면 인덱스로 보내기
			if (memberLv == 2) {
				return "notice/getNoticeListByPage";
			}
		} else if(category.equals("강사")) { // 비로그인 상태이면 로그인 페이지로 이동, 학생이 접근한다면 공지 목록으로 보내기
			if (memberLv == 1) {
				return "notice/getNoticeListByPage";
			}
		}
		Map<String, Object> map = noticeService.getNoticeListByPage(currentPage, rowPerPage, category);
		
		log.debug( CF.KHM +"[NoticeService GetMapping category]: "+ category + CF.RESET);
		
		// getNoticeByPage에 필요한 값 보내주기
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("category", category); // 카테고리별 페이징을 위해 카테고리도 함께 값 전송
		
		return "notice/getNoticeListByPage";
	}
}
