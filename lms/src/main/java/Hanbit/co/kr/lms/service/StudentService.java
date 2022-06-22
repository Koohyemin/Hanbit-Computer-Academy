package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.StudentMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Certification;
import Hanbit.co.kr.lms.vo.Lec;
import Hanbit.co.kr.lms.vo.PhotoFile;
import Hanbit.co.kr.lms.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class StudentService {
	@Autowired StudentMapper studentMapper;
	
	// 나의 정보(학생개인정+자격증리스트+수강내용+사진)
	public Map<String, Object> studentOne(String studentId) {

		// 한 학생의 상세보기
		Student student = new Student();
		student = studentMapper.selectStudentOne(studentId);
		// student 디버깅
		log.debug(CF.SWB+"[StudentService studentOne student]"+ student+CF.RESET);
		
		// 한 학생의 자격증 리스트
		List<Certification> certificationList = studentMapper.selectStudentCertification(studentId);
		// certificationList.size 디버깅
		log.debug(CF.SWB+"[StudentService studentOne certificationList.size]"+ certificationList.size()+CF.RESET);
		
		// 한 학생의 수강리스트
		List<Lec> lecList = studentMapper.selectLecList(studentId);
		// lecList 디버깅
		log.debug(CF.SWB+"[StudentService studentOne lecList]"+ lecList+CF.RESET);
		log.debug(CF.SWB+"[StudentService studentOne lecList.size]"+ lecList.size()+CF.RESET);
		
		// 한 학생의 사진
		PhotoFile photoFile= studentMapper.selectStudentPhoto(studentId);
		// photo 디버깅
		log.debug(CF.SWB+"[StudentService studentOne photoFile]"+ photoFile+CF.RESET);
		
		// 4개를 묶어서 contoller에 보내주기
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("student", student);
		returnMap.put("certificationList", certificationList);
		returnMap.put("lecList", lecList);
		returnMap.put("photoFile", photoFile);
		return returnMap;
	}
}
