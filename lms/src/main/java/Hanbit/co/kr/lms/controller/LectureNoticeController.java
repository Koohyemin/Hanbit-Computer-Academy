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
import Hanbit.co.kr.lms.vo.Registration;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class LectureNoticeController {
	@Autowired LectureNoticeService lectureNoticeService;
	@Autowired HttpSession session;
	
	
		
	// 강좌 공지사항 상세보기
		@GetMapping("/lectureNotice/getLecNoticeOne")
		public String getLectureNoticeOne(Model model,
									@RequestParam(name="lecNoticeNo") int lecNoticeNo) {
						
			// 강좌공지 번호를 통해 상세보기
			log.debug( CF.KHV +"[LectureNoticeController GetMapping lecNoticeNo]: " + CF.RESET + lecNoticeNo); // 수정 번호 디버깅
			LectureNotice lectureNotice = lectureNoticeService.getLecNoticeOne(lecNoticeNo); // Service를 통해 검색어 매개값 적용
			
			log.debug( CF.KHV +"[LectureNoticeController GetMapping lectureNotice]: " + CF.RESET + lectureNotice); // 상세보기 디버깅
			// model에 값 add
			model.addAttribute("lectureNotice", lectureNotice);
			
			return "lectureNotice/getLecNoticeOne"; // 상세보기jsp로 이동
		}
		
		
		// 강좌에 따른 공지사항 페이지 접속
		@GetMapping("/lectureNotice/getLecNoticeListByPage")
		public String getLecNoticeListByPage(Model model) {
			
			int memberLv = (Integer)session.getAttribute("sessionMemberLv");
			if (memberLv == 2) { 
			// 강사 아이디 값 가져오기 
			String teacherId = (String) session.getAttribute("sessionMemberId");
			// 뷰를 호출시 모델레이어로 부터 반환된 값(모델)을 뷰로 전송				
			List<LecPlan> lectureNameList = lectureNoticeService.lectureNameList(teacherId);		
			// 강좌 선택을 위해 가르치는 강좌 리스트를 보내줌
			model.addAttribute("lectureNameList", lectureNameList);
			} else if(memberLv == 1) { // 학생이면 학생이 수강중인 수업 공지만 표시
			String StuduntId = (String) session.getAttribute("sessionMemberId");
			List<Registration> studentLectureNameList = lectureNoticeService.studentLectureNameList(StuduntId);
			log.debug( CF.KHV +"[lectureNoticeService studentLectureNameList]: "+ CF.RESET + studentLectureNameList);
			
			model.addAttribute("studentLectureNameList", studentLectureNameList);	
			} else if(memberLv == 3) {	
			
         	}return "lectureNotice/getLecNoticeListByPage";
		}
		
		
			
			
		// 강좌공지사항 리스트 및 페이징 
		@PostMapping("/lectureNotice/getLecNoticeListByPage")
		public String getLecNoticeListByPage(Model model,
										@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
										@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage, 
										@RequestParam(name = "lectureName",defaultValue = "") String lectureName) {
		
			String teacherId = (String) session.getAttribute("sessionMemberId");	
			// 뷰를 호출시 모델레이어로 부터 반환된 값(모델)을 뷰로 전송		
			List<LecPlan> lectureNameList = lectureNoticeService.lectureNameList(teacherId);	
						
			Map<String, Object> map = lectureNoticeService.getLecNoticeListByPage(currentPage, rowPerPage, lectureName);
			
			
			
			log.debug(CF.KHV +"[LectureNoticeController.getLecNoticeListByPage.map ]: " + CF.RESET + map.get("list"));
			log.debug(CF.KHV +"[LectureNoticeController.getLecNoticeListByPage.lectureNameList ]: " + CF.RESET + lectureNameList);

			if(lectureName.equals("")) {
				log.debug( CF.KHV +"[asdasdasdas lectureName]: "+ CF.RESET + lectureName);
				return "lectureNotice/getLecNoticeListByPage";
				
			}
			
			// 디버깅
			log.debug( CF.KHV +"[lectureNoticeService lectureNameList]: "+ CF.RESET + lectureNameList.size());
			
			
			model.addAttribute("list", map.get("list")); // request.setAttribute()기능
			model.addAttribute("lastPage", map.get("lastPage"));
			model.addAttribute("currentPage", currentPage);
			log.debug( CF.KHV +"[lectureNoticeService lectureNameList]: "+ CF.RESET +lectureNameList);
			model.addAttribute("lectureNameList", lectureNameList);
			
			return "lectureNotice/getLecNoticeListByPage"; // getLecNoticeListByPage 보냄
		}
	
		// 강좌 공지사항 등록 액션
		@PostMapping("/lectureNotice/getInsertLectureNotice")
		public String getInsertLectureNotice(LectureNotice lectureNotice) {
			log.debug( CF.KHV +"[getInsertLectureNotice]: +getInsertLectureNotice"+ CF.RESET + lectureNotice);
			int row = lectureNoticeService.addLectureNotice(lectureNotice);
			return "redirect:/lectureNotice/getLecNoticeListByPage";
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
		public String getUpdateLectureNotice(LectureNotice lectureNotice) {
			log.debug( CF.KHV +"[getUpdateLectureNotice]: +getUpdateLectureNotice"+ CF.RESET + lectureNotice);
			int row = lectureNoticeService.updateLecNotice(lectureNotice);
			return "redirect:/lectureNotice/getLecNoticeListByPage";
		}
		
		// 공지사항 수정 form 
		@GetMapping("/lectureNotice/getUpdateLectureNotice")
		public String getUpdateLectureNotice(Model model, 
				@RequestParam (name = "lecNoticeNo") int lecNoticeNo,
				Model model2,HttpSession session)  {
			//세션에 있는 아이디 값 가져옴
			String teacherId = (String) session.getAttribute("sessionMemberId");
			
			log.debug( CF.KHV +"[LectureNoticeController GetMapping getUpdateLectureNotice teacherId]: "+ teacherId + CF.RESET);
			
			// 학생 아이디 값에 따른 수강이름 목록 리스트 출력
			List<LecPlan> lectureNameList = lectureNoticeService.lectureNameList(teacherId);
			log.debug( CF.KHV +"[LectureNoticeController GetMapping getUpdateLectureNotice lectureNameList]: "  + CF.RESET + lectureNameList);
			log.debug( CF.KHV +"[LectureNoticeController GetMapping getUpdateLectureNotice lecNoticeNo]: "  + CF.RESET + lecNoticeNo);
			
			
			model.addAttribute("lectureNameList",lectureNameList);
			model.addAttribute("lecNoticeNo",lecNoticeNo);
			return "lectureNotice/getUpdateLectureNotice";
		}
		// 공지사항 등록(폼)
		@GetMapping("/lectureNotice/getInsertLectureNotice")
		public String getInsertLectureNotice(Model model
				,HttpSession session) {
			//세션에 있는 아이디 값 가져옴
			String teacherId = (String) session.getAttribute("sessionMemberId");
			
			log.debug( CF.KHV +"[LectureNoticeController GetMapping getInsertLectureNotice teacherId]: "+ teacherId + CF.RESET);
			
			// 강사 아이디 값에 따른 수강이름 목록 리스트 출력
			List<LecPlan> lectureNameList = lectureNoticeService.lectureNameList(teacherId);
			log.debug( CF.KHV +"[LectureNoticeController GetMapping getInsertLectureNotice lectureNameList]: "  + CF.RESET + lectureNameList);
			log.debug( CF.KHV +"[lectureNoticeService lectureNameList]: "+ CF.RESET + lectureNameList.size());
			
			
			model.addAttribute("lectureNameList",lectureNameList);
			return "lectureNotice/getInsertLectureNotice";	
		}
		
		
}