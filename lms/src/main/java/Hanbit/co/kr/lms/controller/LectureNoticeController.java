package Hanbit.co.kr.lms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.LectureNoticeService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.LecPlan;
import Hanbit.co.kr.lms.vo.LectureNotice;
import Hanbit.co.kr.lms.vo.ManagerNotice;
import Hanbit.co.kr.lms.vo.Registration;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class LectureNoticeController {
	@Autowired LectureNoticeService lectureNoticeService;
	@Autowired HttpSession session;
	
	
		
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
				log.debug( CF.KHV +"[lectureNoticeService]: +lectureName"+ CF.RESET);
				model.addAttribute("list", map.get("list")); // request.setAttribute()기능
				model.addAttribute("lastPage", map.get("lastPage"));
				model.addAttribute("currentPage", currentPage);
				
				return "lectureNotice/getLecNoticeListByPage"; // forward기능
			}
		
		// 공지사항 등록 액션
		@PostMapping("/lectureNotice/getInsertLectureNotice")
		public String getInsertLectureNotice(LectureNotice lectureNotice) {
			log.debug( CF.KHV +"[getInsertLectureNotice]: +getInsertLectureNotice"+ CF.RESET + lectureNotice);
			int row = lectureNoticeService.addLectureNotice(lectureNotice);
			return "redirect:/lectureNotice/getLecNoticeListByPage";
		}
			
		
	// 공지사항 등록(폼)
		@GetMapping("/lectureNotice/getInsertLectureNotice")
		public String getInsertLectureNotice(Model model
				,HttpSession session) {
			//세션에 있는 아이디 값 가져옴
			//String teacherId = (String) session.getAttribute("sessionMemberId");
			
			String teacherId = "teacher1";
			log.debug( CF.KYJ +"[LectureNoticeController GetMapping getInsertLectureNotice teacherId]: "+ teacherId + CF.RESET);
			
			// 학생 아이디 값에 따른 수강이름 목록 리스트 출력
			List<LecPlan> lectureNameList = lectureNoticeService.lectureNameList(teacherId);
			log.debug( CF.KYJ +"[LectureNoticeController GetMapping getInsertLectureNotice lectureNameList]: "  + CF.RESET + lectureNameList);
			
			
			model.addAttribute("lectureNameList",lectureNameList);
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
		
			return "redirect:/lectureNotice/getLecNoticeListByPage"; // 공지 삭제 후, 리스트로 돌아가기
	}
		
		// 공지사항 수정 action
		@PostMapping("/lectureNotice/getUpdateLectureNotice")
		public String getUpdateLectureNotice(LectureNotice lectureNotice, Model model) {
			
			int row = lectureNoticeService.getUpdateLectureNotice(lectureNotice);
			
			// 디버깅
			if(row == 1) {
				log.debug(CF.KHV + "[LectureNoticeController postMapping getInsertLectureNotice] :" + CF.RESET +lectureNotice.getLecNoticeNo()+"번 공지 수정 성공"); // 성공
			} else {
				log.debug(CF.KHV + "[LectureNoticeController postMapping getInsertLectureNotice] :" + CF.RESET +lectureNotice.getLecNoticeNo()+"번 공지 수정 실패"); // 실패
			}
			
			return "redirect:/lectureNotice/getLecNoticeOne?lecNoticeNo="+lectureNotice.getLecNoticeNo(); // 공지 수정 후, 리스트로 돌아가기
		}
		
		// 공지사항 수정 form 
		@GetMapping("/lectureNotice/getUpdateLectureNotice")
		public String getUpdateLectureNotice(Model model
					,@RequestParam(name="lecNoticeNo") int lecNoticeNo) {
			// 수정시, 기존 입력 값 화면에 보여주기 위해 상세보기 값 불러오기
			LectureNotice lectureNotice = lectureNoticeService.getLecNoticeOne(lecNoticeNo);
			log.debug(CF.KHV + "[LectureNoticeController  lecNoticeNo] :" + CF.RESET +lectureNotice+"번 공지 수정 실패"); // 실패
			log.debug(CF.KHV + "[LectureNoticeController  lectureNotice] :" + CF.RESET +lectureNotice+"번 공지 수정 실패"); // 실패

			// model에 값 add
			model.addAttribute("lectureNotice", lectureNotice);
			model.addAttribute("lecNoticeNo", lecNoticeNo);
			// notice/updateNotice.jsp 이동
			return "lectureNotice/getUpdateLectureNotice";
		}

		
		
}

