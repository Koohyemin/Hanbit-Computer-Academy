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
	
	// 공지사항 삭제 POST(운영자)
	@PostMapping("/notice/deleteNotice")
	public String getDeleteNotice(int managerNoticeNo) {
		
		// 세션을 이용한 권한 처리
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if (memberLv != 3) { // 운영자가 아니라면 공지목록으로 이동
			return "redirect:/notice/getNoticeListByPage";
		}
		
		// 삭제 성공 행 반환(1 성공, 0 실패, 그 외 DB이상)
		int row = noticeService.getDeleteNotice(managerNoticeNo);
		
		// 디버깅
		if(row == 1) {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] :" + CF.RESET + "공지 삭제 성공"); // 성공
		} else {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] :" + CF.RESET + "공지 삭제 실패"); // 실패
		}
	
		return "redirect:/notice/noticeList"; // 공지 수정 후, 리스트로 돌아가기
	}

	// 공지사항 수정 POST(운영자)
	@PostMapping("/notice/updateNotice")
	public String getUpdateNotice(ManagerNotice managerNotice, Model model) {
		
		// 세션을 이용한 권한 처리
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if (memberLv != 3) { // 운영자가 아니라면 공지목록으로 이동
			return "redirect:/notice/getNoticeListByPage";
		}
		
		// 수정 성공 행 반환(1 성공, 0 실패, 그 외 DB이상)
		int row = noticeService.getUpdateNotic(managerNotice);
		
		// 디버깅
		if(row == 1) {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] :" + CF.RESET +managerNotice.getManagerNoticeNo()+"번 공지 수정 성공"); // 성공
		} else {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] :" + CF.RESET + managerNotice.getManagerNoticeNo()+"번 공지 수정 실패"); // 실패
		}
		
		return "redirect:/notice/noticeOne?managerNoticeNo="+managerNotice.getManagerNoticeNo(); // 공지 수정 후, 리스트로 돌아가기
	}
	
	// 공지사항 수정 GET(운영자)
	@GetMapping("/notice/updateNotice")
	public String getUpdateNotice(Model model, @RequestParam(name="managerNoticeNo") int managerNoticeNo) {
		
		// 세션을 이용한 권한 처리
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if (memberLv != 3) { // 운영자가 아니라면 공지목록으로 돌아가기
			return "redirect:/notice/getNoticeListByPage";
		}
		
		// 수정시, 기존 입력 값 화면에 보여주기 위해 상세보기 값 불러오기
		ManagerNotice managerNotice = noticeService.getNoticeOne(managerNoticeNo);
		
		// model에 값 add
		model.addAttribute("managerNotice", managerNotice);
		
		// notice/updateNotice.jsp 이동
		return "notice/updateNotice";
	}

	// 공지사항 등록 POST(운영자)
	@PostMapping("/notice/addNotice")
	public String getInsertNotice(ManagerNotice managerNotice) {
		
		// 세션을 이용한 권한 처리
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if (memberLv != 3) { // 운영자가 아니라면 공지목록으로 돌아가기
			return "redirect:/notice/getNoticeListByPage";
		}
		
		// 등록 성공 행 반환(1 성공, 0 실패, 그 외 DB이상)
		int row = noticeService.getInsertNotice(managerNotice);
		if(row == 1) {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] :" + CF.RESET + "공지 입력 성공"); // 성공
		} else {
			log.debug(CF.KHM + "[NoticeController postMapping addNotice] :" + CF.RESET + "공지 입력 실패"); // 실패
		}
	
		return "redirect:/notice/noticeList"; // 공지 입력 후, 리스트로 돌아가기
	}
	
	// 공지사항 등록 GET(운영자)
	@GetMapping("/notice/addNotice")
	public String getInsertNotice() {
		
		// notice/addNotice.jsp로 이동
		return "notice/addNotice";
	}
	
	// 공지사항 상세보기 GET(학생, 강사, 운영자)
	@GetMapping("/notice/noticeOne")
	public String getNoticeOne(Model model,
							   @RequestParam(name="managerNoticeNo") int managerNoticeNo) {
		
		// 공지사항 번호를 통해 상세보기
		log.debug( CF.KHM +"[NoticeController GetMapping managerNoticeNo]: " + CF.RESET + managerNoticeNo); // 수정 번호 디버깅
		ManagerNotice managerNotice = noticeService.getNoticeOne(managerNoticeNo); // Service를 통해 검색어 매개값 적용
		
		// model에 값 add
		model.addAttribute("managerNotice", managerNotice);
		
		return "notice/getNoticeOne"; // notice/getNoticeOne.jsp로 이동
	}
	
	// 공지사항 리스트 및 페이징 GET 
	@GetMapping("/notice/noticeList")
	public String getNoticeByPage(Model model,
								@RequestParam(name = "currentPage", defaultValue = "1") int currentPage, // 현재페이지, 1페이지부터 시작
								@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage, // 한페이지당 10개의 게시글 목록 출력
								@RequestParam(name = "category", defaultValue = "전체") String category) { // 페이지 카테고리 기본값을 "전체"로 둠
		
		// 세션을 통한 권한별 처리
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if(category.equals("학생")) { // 비로그인 상태이면 로그인 페이지로 이동, 강사가 접근한다면 getNoticeByPage로 재이동
			if (memberLv == 2) {
				return "redirect:/notice/getNoticeListByPage";
			}
		} else if(category.equals("강사")) { // 비로그인 상태이면 로그인 페이지로 이동, 학생이 접근한다면 getNoticeByPage로 재이동
			if (memberLv == 1) {
				return "redirect:/notice/getNoticeListByPage";
			}
		}
		
		// Service에서 처리한 코드를 이용하여, 매개값 대입
		Map<String, Object> map = noticeService.getNoticeListByPage(currentPage, rowPerPage, category);
		
		log.debug( CF.KHM +"[NoticeController GetMapping category]: " + CF.RESET + category); // 카테고리 디버깅
		
		// model에 값 add
		model.addAttribute("list", map.get("list")); // 공지사항 리스트
		model.addAttribute("lastPage", map.get("lastPage")); // 마지막 페이지(다음버튼이 마지막 페이지에서 안보이게하기위해)
		model.addAttribute("currentPage", currentPage); // 현재 페이지
		model.addAttribute("category", category); // 카테고리별 페이징을 위해 카테고리도 함께 값 전송
		
		return "notice/getNoticeListByPage"; // notice/getNoticeListByPage.jsp로 이동
	}
}
