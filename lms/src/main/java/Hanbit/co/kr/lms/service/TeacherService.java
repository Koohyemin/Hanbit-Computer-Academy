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
	@Autowired TeacherMapper teacherMapper;
	// 강사 기본 정보 조회
	public List<Map<String, Object>> getTeacherList(String searchValue) {
		return teacherMapper.getTeacherList(searchValue);
	}
}
