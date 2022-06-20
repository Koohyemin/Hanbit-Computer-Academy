package Hanbit.co.kr.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.UserFindMapper;
import Hanbit.co.kr.lms.vo.Student;
import lombok.extern.slf4j.Slf4j;
@Service
@Transactional
@Slf4j
public class UserFindService {
	@Autowired UserFindMapper userFindMapper;
	//id찾기
	public String studentFindId(String studentName, String studentPhone) {
		
		return userFindMapper.studentFindId(studentName, studentPhone);
	}
	
}
