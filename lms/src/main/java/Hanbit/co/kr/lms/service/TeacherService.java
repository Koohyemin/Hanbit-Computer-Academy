package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.TeacherMapper;
import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class TeacherService {
	@Autowired TeacherMapper teacherMapper;
	// 강사 목록
	public Map<String,Object> getTeacherList(String searchValue) {
	Map<String,Object> returnMap = new HashMap<>();	
	
	log.debug(CF.KHM + "[TeacherController getMapping searchValue]" + searchValue + CF.RESET);
	
	List<Map<String,Object>> teacherList = teacherMapper.getTeacherList(searchValue);
	
	returnMap.put("searchValue", searchValue);
	returnMap.put("teacherList", teacherList);
	
	return returnMap;
	}
}
