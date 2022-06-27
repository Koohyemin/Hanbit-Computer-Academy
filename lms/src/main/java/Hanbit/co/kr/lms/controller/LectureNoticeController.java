package Hanbit.co.kr.lms.controller;

import java.util.Map;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.LectureNoticeService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.LectureNotice;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class LectureNoticeController {
	@Autowired LectureNoticeService lectureNoticeService;

	
	// 공지사항 리스트 및 페이징 GET 
		@GetMapping("/lecNotice/getLecNoticeListByPage")
		public String getLecNoticeListByPage(Model model,
									@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
									@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		
		
	// 뷰를 호출시 모델레이어로 부터 반환된 값(모델)을 뷰로 보낸다. 
		Map<String, Object> map = lectureNoticeService.getLecNoticeListByPage(currentPage, currentPage);
		// 서비스에서 잘 받아 와지는지 확인
		log.debug( CF.KHV +"[lecNoticeService]: +디버깅 확인"+ CF.RESET);
		model.addAttribute("list", map.get("list")); // request.setAttribute()기능
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		// System.out.println("list.size():"+list.sizez());
		
		return "lectureNotice/getLecNoticeListByPage"; // forward기능
	}
}

