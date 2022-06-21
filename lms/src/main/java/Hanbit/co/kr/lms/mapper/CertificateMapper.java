package Hanbit.co.kr.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Registration;

@Mapper
public interface CertificateMapper {
	
	// 납부 증명서 리스트 
	List<Registration> paymentStudentList(String studentId);
	
}
