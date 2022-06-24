package Hanbit.co.kr.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Hanbit.co.kr.lms.mapper.CertificateMapper;
import Hanbit.co.kr.lms.vo.Registration;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CertificateService {
	@Autowired private CertificateMapper certificateMapper;
	
	// 학생의 아이디를 통해 고지서를 보여준다
	public List<Registration> studentPaymentList(String studentId){
		return certificateMapper.paymentStudentList(studentId);
	}
}
