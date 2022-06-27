package Hanbit.co.kr.lms.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
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
import Hanbit.co.kr.lms.vo.Student;
import Hanbit.co.kr.lms.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ImformationMemberController {
	@Autowired ImformationService imformation;
	
	@GetMapping("/student/searchAddr")
	public String getAddr(@RequestParam(value="Keyword") String keyword) {
		
    // OPEN API 호출 URL 정보 설정
	//뷰에서 받은 keyword 디버깅
	log.debug(CF.SWB+"[LoginController GetMapping role]"+CF.RESET+keyword);
    final int countPerPage = 10; //페이지당 개수 10으로 설정
    int currentPage =1; //페이지 수 1로 설정
    
    //키 지정
    String confmKey = "U01TX0FVVEgyMDIyMDYxNjE2MzExNTExMjY5ODQ=";	
    
    //타입 지정
    String resultType = "json";												
    StringBuffer sb = null;
    
    try {
    	//api url 불러오기
    String apiUrl = "https://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage="+currentPage+"&countPerPage="+countPerPage+"&Keyword="+URLEncoder.encode(keyword,"UTF-8")+"&confmKey="+confmKey+"&resultType="+resultType;

		URL url = new URL(apiUrl);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
		//StringBuffer -> String을 
		sb = new StringBuffer();
		String tempStr = null;
		while((tempStr = br.readLine()) != null){
		   sb.append(tempStr);                        // 응답결과 JSON 저장
		    }
	    } catch(Exception e) {
	       e.printStackTrace();
	    }
	     return sb.toString();
		//		    	response.setCharacterEncoding("UTF-8");
		//				response.setContentType("text/xml");
		//				response.getWriter().write(sb.toString());			// 응답결과 반환
				    	
				    	//rest -> json형식으로 전송
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
