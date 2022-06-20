package Hanbit.co.kr.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.NoticeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NoticeController {
	@Autowired NoticeService noticeService;
	
	@GetMapping("/getNoticeListByPage")
	public String getNoticeByPage(Model model,
								@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
								@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage,
								@RequestParam(name = "category", defaultValue = "전체") String category) { // 페이지 카테고리 기본값을 "전체"로 둠.
		
		Map<String, Object> map = noticeService.getNoticeListByPage(currentPage, rowPerPage, category);
		
		// getNoticeByPage에 필요한 값 보내주기
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		
		return "getNoticeListByPage";
	}
}
