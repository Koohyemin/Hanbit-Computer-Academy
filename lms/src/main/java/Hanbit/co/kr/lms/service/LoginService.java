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
		System.out.println("111111111111111111");
		System.out.println(map);
		String memberId = (String)(map.get("memberId"));
		String memberPw = (String)(map.get("memberPw"));
		String role = (String)(map.get("role"));
		log.debug(CF.SWB+"[LoginService selectMemberId memberId]"+ memberId+CF.RESET);
		log.debug(CF.SWB+"[LoginService selectMemberId memberPw]"+ memberPw+CF.RESET);
		log.debug(CF.SWB+"[LoginService selectMemberId role]"+ role+CF.RESET);
		
		Member member = new Member();
		Map<String, Object> returnMap = new HashMap<>();
		if(role == "student") {
			String studentId = memberId;
			String studentPw = memberPw;
			member = loginMapper.selectStudent(studentId, studentPw);
		} else if(role == "teacher") {
			String teacherId = memberId;
			String teacherPw = memberPw;
			member = loginMapper.selectTeacher(teacherId, teacherPw);
		}else if(role == "manager") {
			String managerId = memberId;
			String managerPw = memberPw;
			member = loginMapper.selectManager(managerId, managerPw);
		}
		log.debug(CF.SWB+"[LoginService selectMemberId member]"+ member+CF.RESET);			
		returnMap.put("member",member);

		return returnMap;
	}
}
