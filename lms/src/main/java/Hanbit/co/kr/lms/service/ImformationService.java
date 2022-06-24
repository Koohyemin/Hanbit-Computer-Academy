package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.ImformationMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Certification;
import Hanbit.co.kr.lms.vo.Lec;
import Hanbit.co.kr.lms.vo.Manager;
import Hanbit.co.kr.lms.vo.PhotoFile;
import Hanbit.co.kr.lms.vo.Student;
import Hanbit.co.kr.lms.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ImformationService {
	@Autowired ImformationMapper imformationMapper;
	
	// 학생 업데이트
	public String modifyStudent(Student student) {
		return imformationMapper.updateStudent(student);
	}
	
	// 나의 정보(학생개인정+자격증리스트+수강내용+사진)
	public Map<String, Object> studentOne(String studentId) {
		
		// 한 학생의 상세보기
		Student student = new Student();
		student = imformationMapper.selectStudentOne(studentId);
		log.debug(CF.SWB+"[ImformationService studentOne student]"+ student+CF.RESET); // student 디버깅
		
		// 한 학생의 자격증 리스트
		List<Certification> certificationList = imformationMapper.selectStudentCertification(studentId);
		log.debug(CF.SWB+"[ImformationService studentOne certificationList.size]"+ certificationList.size()+CF.RESET); // certificationList.size 디버깅
		
		// 한 학생의 수강리스트
		List<Lec> lecList = imformationMapper.selectLecList(studentId);
		log.debug(CF.SWB+"[ImformationService studentOne lecList]"+ lecList+CF.RESET); // lecList 디버깅
		log.debug(CF.SWB+"[ImformationService studentOne lecList.size]"+ lecList.size()+CF.RESET);
		
		// 한 학생의 사진
		PhotoFile photoFile= imformationMapper.selectStudentPhoto(studentId);
		log.debug(CF.SWB+"[ImformationService studentOne photoFile]"+ photoFile+CF.RESET); // photo 디버깅
		
		// 4개를 묶어서 contoller에 보내주기
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("student", student);
		returnMap.put("certificationList", certificationList);
		returnMap.put("lecList", lecList);
		returnMap.put("photoFile", photoFile);
		return returnMap;
	}
	// 나의 정보(선생개인정보+자격증리스트+수강내용+사진+(강좌-수강))
	public Map<String, Object> teacherOne(String teacherId) {

		// 한 강사의 자격증
		Teacher teacher = imformationMapper.selectTeacherOne(teacherId);
		log.debug(CF.SWB+"[ImformationService teacherOne teacher]"+ teacher.toString()+CF.RESET); // teacher 디버깅
		
		// 한 강사의 자격증
		List<Certification> certificationList = imformationMapper.selectTeacherCertification(teacherId);
		log.debug(CF.SWB+"[ImformationService teacherOne certificationList]"+ certificationList+CF.RESET); // certificationList 디버깅
		
		// 한 강사의 수강목록
		List<Registration> registrationList = imformationMapper.selectRegistrationList(teacherId);
		log.debug(CF.SWB+"[ImformationService teacherOne registrationList]"+ registrationList.toString()+CF.RESET); // registrationList 디버깅
		
		// 한 강사의 사진
		PhotoFile photoFile = imformationMapper.selectTeacherPhoto(teacherId);
		log.debug(CF.SWB+"[ImformationService teacherOne photoFile]"+ photoFile+CF.RESET); // photoFile디버깅
		
		// 한 강가의 강의-수강
		List<Lec> lecList = imformationMapper.selectTeacherLecList(teacherId);
		log.debug(CF.SWB+"[ImformationService teacherOne lecList]"+ lecList+CF.RESET); // 강의 디버깅
		
		
		// 5개를 묶어서 contoller에 보내주기
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("teacher", teacher);
		returnMap.put("certificationList", certificationList);
		returnMap.put("registrationList", registrationList);
		returnMap.put("lecList", lecList);
		returnMap.put("photoFile", photoFile);
		return returnMap;
	}
	
	// 나의 정보(운영진개인정보+사진)
	public Map<String, Object> managerOne(String managerId) {
		
		// 운영진 개인정보
		Manager manager = imformationMapper.selectManagerOne(managerId);
		log.debug(CF.SWB+"[ImformationService managerOne manager]"+ manager.toString()+CF.RESET); // manager 디버깅
		
		// 운영진 사진
		PhotoFile photoFile = imformationMapper.selectManagerPhoto(managerId);
		log.debug(CF.SWB+"[ImformationService managerOne photoFile]"+ photoFile.toString()+CF.RESET); // photo 디버깅
		
		
		// 2개를 묶어서 contoller에 보내주기
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("manager", manager);
		returnMap.put("photoFile", photoFile);
		return returnMap;
	}
}
