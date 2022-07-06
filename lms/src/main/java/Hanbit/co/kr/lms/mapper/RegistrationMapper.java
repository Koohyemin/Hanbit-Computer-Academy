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
}
