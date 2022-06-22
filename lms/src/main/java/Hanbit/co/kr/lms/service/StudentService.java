package Hanbit.co.kr.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.StudentMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Certification;
import Hanbit.co.kr.lms.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class StudentService {
	@Autowired StudentMapper studentMapper;
	
	// 나의 정보
	public Student studentOne(String studentId) {

		// 한 학생의 상세보기
		Student student = new Student();
		student = studentMapper.selectStudentOne(studentId);
		// student 디버깅
		log.debug(CF.SWB+"[StudentService studentOne student]"+ student+CF.RESET);
		
		// 한 학생의 자격증 리스트
		List<Certification> certificationList = studentMapper.selectStudentCertification(studentId);
		log.debug(CF.SWB+"[StudentService studentOne certificationList.size]"+ certificationList.size()+CF.RESET);
		
		return student;
	}
	
}
