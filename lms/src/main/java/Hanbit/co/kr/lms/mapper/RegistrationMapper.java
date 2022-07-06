package Hanbit.co.kr.lms.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Registration;

@Mapper
public interface RegistrationMapper {
	List<Registration> selectRegistration(String studentId);
	int insertRegistration(Registration registration);
	HashMap selectRegistrationByStudent(Registration registration);
}
