package Hanbit.co.kr.lms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.TeacherMapper;

@Service
@Transactional
public class TeacherService {
	
	@Autowired TeacherMapper teacherMapper; // teacherMapper 객체 주입
		
	// 수강에 필요한 강사 정보 리스트
	public List<Map<String, Object>> getTeacherList(String searchValue) {
		return teacherMapper.getTeacherList(searchValue);
	}
}
