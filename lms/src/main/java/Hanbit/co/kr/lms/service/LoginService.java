package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.LoginMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LoginService {
	@Autowired LoginMapper loginMapper;
	
	public Map<String, Object> selectMemberId(Map<String, Object> map){
		String memberId = (String)(map.get("memberId"));
		String memberPw = (String)(map.get("memberPw"));
		String role = (String)(map.get("role"));
		
		// memberId + memberPw + role 디버깅
		log.debug(CF.SWB+"[LoginService selectMemberId memberId]"+ memberId+CF.RESET);
		log.debug(CF.SWB+"[LoginService selectMemberId memberPw]"+ memberPw+CF.RESET);
		log.debug(CF.SWB+"[LoginService selectMemberId role]"+ role+CF.RESET);
		
		// 변수 설정
		Member member = new Member();
		String error = null;
		
		Map<String, Object> returnMap = new HashMap<>();
		if("student".equals(role)) { // 사용자 선택이 학생이리면...
			String studentId = memberId;
			String studentPw = memberPw;
			member = loginMapper.selectStudent(studentId, studentPw);
			error = "학생 아이디나 비밀번호를 확인해주세요";
			log.debug(CF.SWB+"[LoginService selectMemberId member]"+ member+CF.RESET);
		} else if("teacher".equals(role)) { // 사용자 선택이 선생이라면...
			String teacherId = memberId;
			String teacherPw = memberPw;
			member = loginMapper.selectTeacher(teacherId, teacherPw);
			error = "선생님 아이디나 비밀번호를 확인해주세요";
		}else if("manager".equals(role)) { // 사용자 선택이 운영자라면...
			String managerId = memberId;
			String managerPw = memberPw;
			member = loginMapper.selectManager(managerId, managerPw);
			error = "운영자 아이디나 비밀번호를 확인해주세요";
		} else {
			error = "사용자를 선택해주세요";
		}
		
		// member 디버깅
		log.debug(CF.SWB+"[LoginService selectMemberId member]"+ member+CF.RESET);			
		
		// service에서 컨트롤러로 보낼 데이터들
		returnMap.put("member",member);
		returnMap.put("error", error);

		return returnMap;
	}
}
