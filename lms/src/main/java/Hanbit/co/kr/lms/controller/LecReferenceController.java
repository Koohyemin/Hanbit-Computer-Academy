package Hanbit.co.kr.lms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.LecReferenceService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.LecPlan;
import Hanbit.co.kr.lms.vo.Registration;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class LecReferenceController {
	@Autowired LecReferenceService lecReferenceService;
	@Autowired HttpSession session;
		
			
	
		// 강좌에 따른 공지사항 페이지 접속
			@GetMapping("/lecReference/getLecReferenceListByPage")
			public String getLecReferenceListByPage(Model model) {
				
				int memberLv = (Integer)session.getAttribute("sessionMemberLv");
				if (memberLv == 2) { 
				// 강사 아이디 값 가져오기 
				String teacherId = (String) session.getAttribute("sessionMemberId");
				// 뷰를 호출시 모델레이어로 부터 반환된 값(모델)을 뷰로 전송				
				List<LecPlan> lectureNameList = lecReferenceService.lectureNameList(teacherId);		
				// 강좌 선택을 위해 가르치는 강좌 리스트를 보내줌
				model.addAttribute("lectureNameList", lectureNameList);
				} else if(memberLv == 1) { // 학생이면 학생이 수강중인 수업 공지만 표시
				String StuduntId = (String) session.getAttribute("sessionMemberId");
				List<Registration> studentLectureNameList = lecReferenceService.studentLectureNameList(StuduntId);
				log.debug( CF.KHV +"[lecReferenceService studentLectureNameList]: "+ CF.RESET + studentLectureNameList);
				
				model.addAttribute("studentLectureNameList", studentLectureNameList);	
				
				} else if(memberLv == 3) { 
				}return "lecReference/getLecReferenceListByPage";
			
			}
				
				
			// 강좌공지사항 리스트 및 페이징 
			@PostMapping("/lecReference/getLecReferenceListByPage")
			public String getLecNoticeListByPage(Model model,
											@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
											@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage, 
											@RequestParam(name = "lectureName",defaultValue = "") String lectureName) {
			
				String teacherId = (String) session.getAttribute("sessionMemberId");	
				// 뷰를 호출시 모델레이어로 부터 반환된 값(모델)을 뷰로 전송		
				List<LecPlan> lectureNameList = lecReferenceService.lectureNameList(teacherId);	
							
				Map<String, Object> map = lecReferenceService.getLecReferenceListByPage(currentPage, rowPerPage, lectureName);
				
				
				
				log.debug(CF.KHV +"[LecReferenceController.getLecReferenceListByPage.map ]: " + CF.RESET + map.get("list"));
				log.debug(CF.KHV +"[LecReferenceController.getLecReferenceListByPage.lectureNameList ]: " + CF.RESET + lectureNameList);

				if(lectureName.equals("")) {
					log.debug( CF.KHV +"[asdasdasdas lectureName]: "+ CF.RESET + lectureName);
					return "lecReference/getLecReferenceListByPage";
					
				}
				
				// 디버깅
				log.debug( CF.KHV +"[lectureNoticeService lectureNameList]: "+ CF.RESET + lectureNameList.size());
				
				
				model.addAttribute("list", map.get("list")); // request.setAttribute()기능
				model.addAttribute("lastPage", map.get("lastPage"));
				model.addAttribute("currentPage", currentPage);
				log.debug( CF.KHV +"[lecReferenceService lectureNameList]: "+ CF.RESET +lectureNameList);
				model.addAttribute("lectureNameList", lectureNameList);
				
				return "lecReference/getLecReferenceListByPage"; // getLecNoticeListByPage 보냄
			}
			

			
}
