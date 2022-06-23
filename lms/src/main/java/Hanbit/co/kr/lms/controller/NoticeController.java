package Hanbit.co.kr.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.mapper.NoticeMapper;
import Hanbit.co.kr.lms.service.NoticeService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.ManagerNotice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NoticeController {
	@Autowired NoticeService noticeService;
	
	/**
	 * 공지사항 삭제
	 * @param managerNoticeNo
	 * @param model
	 * @return
	 */
	@PostMapping("/Notice/deleteNotice")
	public String getDeleteNotice(int managerNoticeNo, Model model) {
		int row = noticeService.getDeleteNotice(managerNoticeNo);
		if(row == 1) {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] : 공지 삭제 성공" + CF.RESET); // 성공 디버깅
		} else {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] : 공지 삭제 실패" + CF.RESET); // 실패 디버깅
		}
	
		return "redirect:/Notice/noticeList"; // 공지 수정 후, 리스트로 돌아가기
	}
	/**
	 * 공지사항 수정
	 * @param managerNotice
	 * @param model
	 * @return
	 */
	@PostMapping("/Notice/updateNotice")
	public String getUpdateNotice(ManagerNotice managerNotice, Model model) {
		int row = noticeService.getUpdateNotic(managerNotice);
		if(row == 1) {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] :"+managerNotice.getManagerNoticeNo()+"번 공지 수정 성공" + CF.RESET); // 성공 디버깅
		} else {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] :"+managerNotice.getManagerNoticeNo()+"번 공지 수정 실패" + CF.RESET); // 실패 디버깅
		}
		return "redirect:/Notice/noticeOne?managerNoticeNo="+managerNotice.getManagerNoticeNo(); // 공지 수정 후, 리스트로 돌아가기
	}
	/**
	 * 
	 * @param model
	 * @param managerNoticeNo
	 * @return
	 */
	@GetMapping("/Notice/updateNotice")
	public String getUpdateNotice(Model model, @RequestParam(name="managerNoticeNo") int managerNoticeNo) {
		// 수정시, 기존 입력 값 화면에 보여주기 위해 상세보기 값 불러오기
		ManagerNotice managerNotice = noticeService.getNoticeOne(managerNoticeNo);
		model.addAttribute("managerNotice", managerNotice);
		return "notice/updateNotice";
	}
	/**
	 * 
	 * @param managerNotice
	 * @return
	 */
	// 공지사항 등록
	@PostMapping("/Notice/addNotice")
	public String getInsertNotice(ManagerNotice managerNotice) {
		int row = noticeService.getInsertNotice(managerNotice);
		if(row == 1) {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] : 공지 입력 성공" + CF.RESET); // 성공 디버깅
		} else {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] : 공지 입력 실패" + CF.RESET); // 실패 디버깅
		}
	
		return "redirect:/Notice/noticeList"; // 공지 입력 후, 리스트로 돌아가기
	}
	
	@GetMapping("/Notice/addNotice")
	public String getInsertNotice() {
		return "notice/addNotice";
	}
	/**
	 * 
	 * @param model
	 * @param managerNoticeNo
	 * @return
	 */
	// 공지사항 상세보기
	@GetMapping("/Notice/noticeOne")
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
	@GetMapping("/Notice/noticeList")
	public String getNoticeByPage(Model model,
								@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
								@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage,
								@RequestParam(name = "category", defaultValue = "전체") String category) { // 페이지 카테고리 기본값을 "전체"로 둠.
		
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
