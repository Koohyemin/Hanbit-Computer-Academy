package Hanbit.co.kr.lms.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Registration;

@Mapper
public interface RegistrationMapper {
	
	//학생 수강 리스트
	List<Registration> selectRegistration(String studentId);
	
	//학생 수강 등록
	int insertRegistration(Registration registration);
	
	//학생 - 수강 상세보기 
	HashMap<String,Object> selectRegistrationByStudent(Registration registration);
	
	//학생 -수강 지불
	int updatePayment(Registration registration);
	
	//운영자 납부 리스트 조회
	List<Map<String,Object>> selectRegistrationByLec(String lectureName);
	
	//개강전 강좌
	List<String> beforeLecture();
	
	//삭제된 강좌
	int deleteRegistration(Registration registration);
}
