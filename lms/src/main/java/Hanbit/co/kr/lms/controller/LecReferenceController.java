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
import Hanbit.co.kr.lms.vo.LectureNotice;
import Hanbit.co.kr.lms.vo.Registration;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class LecReferenceController {
	@Autowired LecReferenceService lecReferenceService;
	@Autowired HttpSession session;
	
	
	// 자료실 리스트
	// 강좌에 따른 자료실 페이지 접속
			@GetMapping("/lecReference/getLecReferenceListByPage")
			public String getLecReferenceListByPage(Model model) {
				
				int memberLv = (Integer)session.getAttribute("sessionMemberLv");
				if (memberLv == 2) { 
				// 강사 아이디 값 가져오기 
				String teacherId = (String) session.getAttribute("sessionMemberId");
				// 뷰를 호출시 모델레이어로 부터 반환된 값(모델)을 뷰로 전송				
				String teacherList = lecReferenceService.teacherList(teacherId);
				// 강좌 선택을 위해 가르치는 강좌 리스트를 보내줌
				model.addAttribute("teacherList", teacherList);
				} else if(memberLv == 1) { // 학생이면 학생이 수강중인  자료만 표시
				String StudentId = (String) session.getAttribute("sessionMemberId");
				List<Registration> studentLectureNameList = lecReferenceService.studentLectureNameList(StudentId);
				log.debug( CF.KHV +"[lecReferenceService studentLectureNameList]: "+ CF.RESET + studentLectureNameList);
				
				model.addAttribute("studentLectureNameList", studentLectureNameList);	
				} else if(memberLv == 3) {	
				
	         	}return "lecReference/getLecReferenceListByPage";
			}
			
				
			// 강좌공지사항 리스트 및 페이징 
			@PostMapping("/lecReference/getLecReferenceListByPage")
			public String getLecReferenceListByPage(Model model,
											@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
											@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage, 
											@RequestParam(name = "lectureName",defaultValue = "") String lectureName) {
			
				String teacherId = (String) session.getAttribute("sessionMemberId");	
				// 뷰를 호출시 모델레이어로 부터 반환된 값(모델)을 뷰로 전송		
				String teacherList = lecReferenceService.teacherList(teacherId);	
							
				Map<String, Object> map = lecReferenceService.getLecReferenceListByPage(currentPage, rowPerPage, lectureName);
				
				
				
				log.debug(CF.KHV +"[LecReferenceController.getLecReferenceListByPage.map ]: " + CF.RESET + map.get("list"));
				log.debug(CF.KHV +"[LecReferenceController.getLecReferenceListByPage.teacherList teacherId]: " + CF.RESET + teacherId);
				
				if(lectureName.equals("")) {
					return "lecReference/getLecReferenceListByPage";
				}
				
				
				
				model.addAttribute("list", map.get("list")); // request.setAttribute()기능
				model.addAttribute("lastPage", map.get("lastPage"));
				model.addAttribute("currentPage", currentPage);
				log.debug( CF.KHV +"[LecReferenceController teacherList teacherId]: "+ CF.RESET +teacherId);
				model.addAttribute("teacherList", teacherList);
				
				return "lecReference/getLecReferenceListByPage"; // getLecReferenceListByPage 보냄
			}
	
			// 자료 등록(폼)
			@GetMapping("/lecReference/addLecReference")
			public String addLecReference(Model model
					,HttpSession session) {
				//세션에 있는 아이디 값 가져옴
				String teacherId = (String) session.getAttribute("sessionMemberId");
				
				log.debug( CF.KHV +"[LecReferenceController GetMapping addLecReference teacherId]: "+ teacherId + CF.RESET);
				
				// 학생 아이디 값에 따른 수강이름 목록 리스트 출력
				List<Registration> studentLectureNameList = lecReferenceService.studentLectureNameList(teacherId);
				log.debug( CF.KHV +"[LecReferenceController GetMapping getInsertLectureNotice lectureNameList]: "  + CF.RESET + studentLectureNameList);
				log.debug( CF.KHV +"[lecReferenceService lectureNameList]: "+ CF.RESET + studentLectureNameList.size());
				
				model.addAttribute("studentLectureNameList",studentLectureNameList);
				return "lecReference/addLecReference";	
			}
			
			
}
