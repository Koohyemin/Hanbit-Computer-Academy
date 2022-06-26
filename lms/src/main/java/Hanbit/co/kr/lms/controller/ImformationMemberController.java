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
import Hanbit.co.kr.lms.vo.Student;
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
	
	// 새창주소
	@GetMapping("/member/addr")
	public String addr() {
		return "member/addr";
	}
	// 학생정보 업데이트 폼
	@GetMapping("/member/modifyMember")
	public String modifyStudent(Model model
								,HttpServletRequest request) {
		
		// 세션에 있는 값 아이디값 담기
		HttpSession session = request.getSession();
		String	studentId = (String)session.getAttribute("sessionMemberId");
		log.debug(CF.SWB+"[ImformationMemberController modifyStudent studentId]"+CF.RESET+ studentId); // studentId 디버깅
	
		// service에서 controller로 값 가져오기
		Map<String, Object> returnMap = imformation.studentOne(studentId);
		model.addAttribute("student",returnMap.get("student"));
		return "member/modifyMember";
	}
	
	// 개인정보 업데이트 액션
	@PostMapping("/member/modifyMember")
	public String modifyStudent(@RequestParam(name="memberName") String memberName
								,@RequestParam(name="addr") String addr
								,@RequestParam(name="addr2") String addr2
								,@RequestParam(name="phone") String phone
								,@RequestParam(name="finalEdu") String finalEdu
								,@RequestParam(name="email") String email
								,HttpServletRequest request
								) {
		
		// 세션에 있는 멤버레벨값 받아오기
		HttpSession session = request.getSession();
		int	MemberLv = (int)session.getAttribute("sessionMemberLv");
		String	studentId = (String)session.getAttribute("sessionMemberId");
		log.debug(CF.SWB+"[ImformationMemberController modifyStudent MemberLv]"+CF.RESET + MemberLv); // MemberLv 디버깅
		
		// 학새이라면
		if(MemberLv == 1) {
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
			log.debug(CF.SWB+"[ImformationMemberController memberOne student]"+ returnMap.get("student").toString()+CF.RESET); // student 디버깅
			model.addAttribute("student",returnMap.get("student"));
		} else if(memberLv == 2) { // 강사일때
			teacherId = (String)session.getAttribute("sessionMemberId");  // 변수등록 및 세션아이디 값 넣기
			returnMap = imformation.teacherOne(teacherId);
			model.addAttribute("teacher",returnMap.get("teacher"));
			log.debug(CF.SWB+"[ImformationMemberController memberOne teacher]"+ returnMap.get("teacher").toString()+CF.RESET); // teacher 디버깅
			model.addAttribute("registrationList",returnMap.get("registrationList"));
		} else if(memberLv == 3) { // 운영자일때
			managerId = (String)session.getAttribute("sessionMemberId");  // 변수등록 및 세션아이디 값 넣기
			returnMap = imformation.managerOne(managerId);
			log.debug(CF.SWB+"[ImformationMemberController memberOne manager]"+ returnMap.get("manager").toString()+CF.RESET); // manager 디버깅
			model.addAttribute("manager",returnMap.get("manager"));
		}
		
		// returnMap.size 디버깅
		log.debug(CF.SWB+"[ImformationMemberController memberOne returnMap.size()]"+ returnMap.size()+CF.RESET);
		
		// jsp로 값보내기
		model.addAttribute("certificationList",returnMap.get("certificationList"));
		model.addAttribute("lecList",returnMap.get("lecList"));
		model.addAttribute("photoFile",returnMap.get("photoFile"));
		return "member/imformationMember";
	}
}
