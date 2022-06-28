package Hanbit.co.kr.lms.controller;

import java.util.Map;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.LectureNoticeService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.LectureNotice;
import Hanbit.co.kr.lms.vo.ManagerNotice;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class LectureNoticeController {
	@Autowired LectureNoticeService lectureNoticeService;

	
	
		
	// 공지사항 상세보기
		@GetMapping("/lectureNotice/getLecNoticeOne")
		public String getLectureNoticeOne(Model model,
									@RequestParam(name="lecNoticeNo") int lecNoticeNo) {
									
			
			// 공지사항 번호를 통해 상세보기
			log.debug( CF.KHV +"[LectureNoticeController GetMapping lecNoticeNo]: " + CF.RESET + lecNoticeNo); // 수정 번호 디버깅
			LectureNotice lectureNotice = lectureNoticeService.getLecNoticeOne(lecNoticeNo); // Service를 통해 검색어 매개값 적용
			
			log.debug( CF.KHV +"[LectureNoticeController GetMapping lectureNotice]: " + CF.RESET + lectureNotice); // 상세보기 디버깅
			// model에 값 add
			model.addAttribute("lectureNotice", lectureNotice);
			
			return "lectureNotice/getLecNoticeOne"; // notice/getNoticeOne.jsp로 이동
		}
		
		// 공지사항 리스트 및 페이징 GET 
				@GetMapping("/lectureNotice/getLecNoticeListByPage")
				public String getLecNoticeListByPage(Model model,
											@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
											@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage, 
											@RequestParam(name = "lectureName", defaultValue = "전체") String lectureName) {
				
			// 뷰를 호출시 모델레이어로 부터 반환된 값(모델)을 뷰로 보낸다. 
				Map<String, Object> map = lectureNoticeService.getLecNoticeListByPage(currentPage, rowPerPage, lectureName);
				// 서비스에서 잘 받아 와지는지 확인
				log.debug( CF.KHV +"[lectureNoticeService]: +디버깅 확인"+ CF.RESET);
				model.addAttribute("list", map.get("list")); // request.setAttribute()기능
				model.addAttribute("lastPage", map.get("lastPage"));
				model.addAttribute("currentPage", currentPage);
				
				return "lectureNotice/getLecNoticeListByPage"; // forward기능
			}
		
		// 공지사항 액션
		@PostMapping("/lectureNotice/getInsertLectureNotice")
		public String getInsertLectureNotice(LectureNotice lectureNotice) {
			return "redirect:/lectureNotice/getLecNoticeListByPage";
		}
			
		
	// 공지사항 등록(폼)
		@GetMapping("/lectureNotice/getInsertLectureNotice")
		public String getInsertLectureNotice() {
			
			
			// 여기다가 해당 학생이 듣고 있는 수강을 보여주는 리스트 매퍼 작성후 모델 어쩌구 넘겨줘야죠~!
			
			// notice/addNotice.jsp로 이동
			return "lectureNotice/getInsertLectureNotice";
		}
		
	// 공지사항 삭제
		@PostMapping("/lectureNotice/getDeleteLectureNotice")
		public String getDeleteLectureNotice(int lecNoticeNo, Model model) {
			int row = lectureNoticeService.getDeleteLectureNotice(lecNoticeNo);
			if(row == 1) {
				log.debug(CF.KHV + "[LectureNoticeController postMapping getDeleteLectureNotice] : 공지 삭제 성공" + CF.RESET); // 성공 디버깅
			} else {
				log.debug(CF.KHV + "[LectureNoticeController postMapping getDeleteLectureNotice] : 공지 삭제 실패" + CF.RESET); // 실패 디버깅
			}
		
			return "redirect:/lectureNotice/getLecNoticeListByPage"; // 공지 수정 후, 리스트로 돌아가기
	}
}

