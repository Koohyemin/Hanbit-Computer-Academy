package Hanbit.co.kr.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Registration;

@Mapper
public interface RegistrationMapper {
	List<Registration> selectRegistration(String studentId);
	int insertRegistration(Registration registration);
}
