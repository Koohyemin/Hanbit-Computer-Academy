package Hanbit.co.kr.lms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.ImformationService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Manager;
import Hanbit.co.kr.lms.vo.PhotoFile;
import Hanbit.co.kr.lms.vo.Student;
import Hanbit.co.kr.lms.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ImformationMemberController {
	@Autowired ImformationService imformation;
	
	@GetMapping("/member/updatePw")
	public String updatePw() {
		return"/member/modifyPassword";
	}
	
	// 90일 연장하기
	@GetMapping("/member/prolongPw")
	public String prolongPw(HttpSession session) {
		
		// 세션에 있는 값 아이디값과 레벨값 담기
		String	memberId = (String)session.getAttribute("sessionMemberId");
		int	memberLv = (int)session.getAttribute("sessionMemberLv");
		log.debug(CF.SWB+"[ImformationMemberController PostMapping prolongPw memberId]"+CF.RESET+ memberId); // memberId 디버깅
		log.debug(CF.SWB+"[ImformationMemberController PostMapping prolongPw memberLv]"+CF.RESET+ memberLv); // memberLv 디버깅
		
		imformation.updatePw90(memberId, memberLv);
		
		return "redirect:/home/index";
	}
	// 비밀번호 변경
	@PostMapping("/member/updatePw")
	public String updatePw(HttpSession session
						,Model model
						,@RequestParam(name="memberPw")String memberPw
						,@RequestParam(name="updatePw")String updatePw
						,@RequestParam(name="checkPw")String checkPw) {
		
		// 세션에 있는 값 아이디값과 레벨값 담기
		String	memberId = (String)session.getAttribute("sessionMemberId");
		int	memberLv = (int)session.getAttribute("sessionMemberLv");
		log.debug(CF.SWB+"[ImformationMemberController PostMapping updatePhoto memberId]"+CF.RESET+ memberId); // memberId 디버깅
		log.debug(CF.SWB+"[ImformationMemberController PostMapping updatePhoto memberLv]"+CF.RESET+ memberLv); // memberLv 디버깅
		log.debug(CF.SWB+"[ImformationMemberController PostMapping updatePw memberPw]"+CF.RESET+ memberPw); // memberPw 디버깅
		log.debug(CF.SWB+"[ImformationMemberController PostMapping updatePw updatePw]"+CF.RESET+ updatePw); // updatePw 디버깅
		log.debug(CF.SWB+"[ImformationMemberController PostMapping updatePw ckeckPw]"+CF.RESET+ checkPw); // checkPw 디버깅
		
		
		/// 에러문구 받아오기
		String error = imformation.modifyPw(memberId, memberPw, updatePw,checkPw,memberLv);
		log.debug(CF.SWB+"[ImformationMemberController PostMapping updatePw error]"+CF.RESET+ error); // error 디버깅
		model.addAttribute("error",error);
		if(error == null) {
			return "redirect:/login";
		}
		
		return "redirect:/member/updatePw";
	}
	
	// 사진 업데이트
	@PostMapping("/updatePhoto")
	public String updatePhoto(HttpSession session
								, HttpServletRequest request
								,PhotoFile photoFile) {
		
		// 세션에 있는 값 아이디값과 레벨값 담기
		String	memberId = (String)session.getAttribute("sessionMemberId");
		log.debug(CF.SWB+"[ImformationMemberController PostMapping updatePhoto memberId]"+CF.RESET+ memberId); // memberId 디버깅
		
		// 사진이 들어가 경로 설정 
		String path = request.getServletContext().getRealPath("/upload/");
		log.debug(CF.SWB+"[ImformationMemberController PostMapping updatePhoto path]"+CF.RESET+ path); // path디버깅
		log.debug(CF.SWB+"[ImformationMemberController PostMapping updatePhoto photoFile]"+CF.RESET+ photoFile.getPhotoFile()); // photoFile디버깅
		
		// 컨트롤러에서 서비스로 파일에 필요한 값 보내주기
		imformation.updatePhoto(path, memberId,photoFile);
		return "redirect:/member/getMemberOne";
	}
	
	// 개인정보 업데이트에 새창주소
	@GetMapping("/member/addr")
	public String addr() {
		return "member/addr";
	}
	
	// 멤버 업데이트 폼
	@GetMapping("/member/modifyMember")
	public String modifyMember(Model model
								,HttpServletRequest request) {
		
		// 세션에 있는 값 아이디값과 레벨값 담기
		HttpSession session = request.getSession();
		String	memberId = (String)session.getAttribute("sessionMemberId");
		int memberLv = (int)session.getAttribute("sessionMemberLv");
		log.debug(CF.SWB+"[ImformationMemberController modifyStudent studentId]"+CF.RESET+ memberId); // studentId 디버깅
		log.debug(CF.SWB+"[ImformationMemberController modifyStudent memberLv]"+CF.RESET+ memberLv); // memberLv 디버깅

		
		Map<String, Object> returnMap = new HashMap<>();
		if(memberLv == 1) { // 학생이라면
			String studentId = memberId;
			returnMap = imformation.studentOne(studentId);
			model.addAttribute("student",returnMap.get("student"));
			
			log.debug(CF.SWB+"[ImformationMemberController modifyStudent student]"+CF.RESET+ returnMap.get("student").toString()); // student 디버깅
		} else if(memberLv == 2) {
			String teacherId = memberId;
			returnMap = imformation.teacherOne(teacherId);
			model.addAttribute("teacher",returnMap.get("teacher"));
			
			log.debug(CF.SWB+"[ImformationMemberController modifyStudent teacher]"+CF.RESET+ returnMap.get("teacher").toString()); // teacher 디버깅
		} else if(memberLv == 3) {
			String managerId = memberId;
			returnMap = imformation.managerOne(managerId);	
			model.addAttribute("manager",returnMap.get("manager"));
			
			log.debug(CF.SWB+"[ImformationMemberController modifyStudent manager]"+CF.RESET+ returnMap.get("manager").toString()); // manager 디버깅
		}
		// service에서 controller로 값 가져오기
		return "member/modifyMember";
	}
	
	// 개인정보 업데이트 액션
	@PostMapping("/member/modifyMember")
	public String modifyStudent(@RequestParam(name="memberName") String memberName
								,@RequestParam(name="addr") String addr
								,@RequestParam(name="addr2") String addr2
								,@RequestParam(name="phone") String phone
								,@RequestParam(name="finalEdu", defaultValue = "") String finalEdu
								,@RequestParam(name="email") String email
								,HttpServletRequest request
								) {
		
		// 세션에 있는 멤버레벨값 받아오기
		HttpSession session = request.getSession();
		int	memberLv = (int)session.getAttribute("sessionMemberLv");
		String	memberId = (String)session.getAttribute("sessionMemberId");
		log.debug(CF.SWB+"[ImformationMemberController modifyStudent MemberLv]"+CF.RESET + memberLv); // MemberLv 디버깅
		
		// 학생이라면
		if(memberLv == 1) {
			String studentId = memberId; // 변환
			Student student = new Student();
			student.setStudentId(studentId);
			student.setStudentName(memberName);
			student.setStudentAddr1(addr);
			student.setStudentAddr2(addr2);
			student.setStudentPhone(phone);
			student.setFinalEducation(finalEdu);
			student.setStudentEmail(email);
			imformation.updateStudent(student);
			log.debug(CF.SWB+"[ImformationMemberController modifyStudent students]"+CF.RESET + student.toString()); // student 디버깅
			
		// 강사라면
		} else if(memberLv == 2) {
			String teacherId = memberId;
			Teacher teacher = new Teacher();
			teacher.setTeacherId(teacherId);
			teacher.setTeacherName(memberName);
			teacher.setTeacherAddr1(addr);
			teacher.setTeacherAddr2(addr2);
			teacher.setTeacherPhone(phone);
			teacher.setFinalEducation(finalEdu);
			teacher.setTeacherEmail(email);
			imformation.updateTeacher(teacher);
			log.debug(CF.SWB+"[ImformationMemberController modifyStudent students]"+CF.RESET + teacher.toString()); // teacher 디버깅
			
		// 운영진이라면
		} else if(memberLv == 3) {
			String managerId = memberId;
			Manager manager = new Manager();
			manager.setManagerId(managerId);
			manager.setManagerName(memberName);
			manager.setManagerAddr1(addr);
			manager.setManagerAddr2(addr2);
			manager.setManagerPhone(phone);
			manager.setManagerEmail(email);
			imformation.updateManager(manager);
		}
		return "redirect:/member/getMemberOne";
	}
	
	// 멤버 상세보기
	@GetMapping("/member/getMemberOne")
	public String memberOne(Model model
							,HttpServletRequest request) {
		
		// 세션에 있는 멤버레벨값 받아오기
		HttpSession session = request.getSession();
		int memberLv = (int)session.getAttribute("sessionMemberLv");
		log.debug(CF.SWB+"[ImformationMemberController memberOne memberLv]"+ memberLv+CF.RESET); // memberLv
		
		// 변수 선언
		String studentId = null;
		String teacherId = null;
		String managerId = null;
		
		// service에서 controller로 값 가져오기
		Map<String, Object> returnMap = new HashMap<>();
		if(memberLv == 1) { // 학생일때
			studentId = (String)session.getAttribute("sessionMemberId"); // 변수등록 및 세션아이디 값 넣기
			returnMap = imformation.studentOne(studentId);
			log.debug(CF.SWB+"[ImformationMemberController memberOne student]"+CF.RESET+ returnMap.get("student").toString()); // student 디버깅
			model.addAttribute("student",returnMap.get("student"));
			
		} else if(memberLv == 2) { // 강사일때
			teacherId = (String)session.getAttribute("sessionMemberId");  // 변수등록 및 세션아이디 값 넣기
			returnMap = imformation.teacherOne(teacherId);
			model.addAttribute("teacher",returnMap.get("teacher"));
			log.debug(CF.SWB+"[ImformationMemberController memberOne teacher]"+CF.RESET+ returnMap.get("teacher").toString()); // teacher 디버깅
			model.addAttribute("registrationList",returnMap.get("registrationList"));
			
		} else if(memberLv == 3) { // 운영자일때
			managerId = (String)session.getAttribute("sessionMemberId");  // 변수등록 및 세션아이디 값 넣기
			returnMap = imformation.managerOne(managerId);
			log.debug(CF.SWB+"[ImformationMemberController memberOne manager]"+CF.RESET+ returnMap.get("manager").toString()); // manager 디버깅
			model.addAttribute("manager",returnMap.get("manager"));
		}
		
		// returnMap.size 디버깅
		log.debug(CF.SWB+"[ImformationMemberController memberOne returnMap.size()]" +CF.RESET+ returnMap.size());
		
		// jsp로 값보내기
		model.addAttribute("certificationList",returnMap.get("certificationList"));
		model.addAttribute("lecList",returnMap.get("lecList"));
		model.addAttribute("photoFile",returnMap.get("photoFile"));
		return "member/imformationMember";
	}
}
