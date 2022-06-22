package Hanbit.co.kr.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.UserFindMapper;
import lombok.extern.slf4j.Slf4j;
@Service
@Transactional
@Slf4j
public class UserFindService {
	@Autowired UserFindMapper userFindMapper;
	//학생 id찾기
	public String studentFindId(String studentName, String studentPhone) {
		return userFindMapper.studentFindId(studentName, studentPhone);
	}
	//학생 pw찾기
	public String studentFindPw(String studentId, String studentName, String studentPhone) {
		return userFindMapper.studentFindPw(studentId, studentName, studentPhone);
	}
	
	//강사id찾기
	public String teacherFindId(String teacherName, String teacherPhone) {
		return userFindMapper.teacherFindId(teacherName, teacherPhone);
	}
	//운영자id찾기
	public String managerFindId(String managerName, String managerPhone) {
		return userFindMapper.managerFindId(managerName, managerPhone);
	}
	
}
