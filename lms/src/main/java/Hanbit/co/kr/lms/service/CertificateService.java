package Hanbit.co.kr.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Hanbit.co.kr.lms.mapper.CertificateMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Certification;
import Hanbit.co.kr.lms.vo.Registration;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CertificateService {
	@Autowired private CertificateMapper certificateMapper;
	
	// 자격증 삭제
	public void deleteCertificate(int certificationNo) {
		
		// 서비스에서 맵퍼로 값 보내기
		certificateMapper.deleteCertification(certificationNo);
		return;
	}
	
	
	// 자격증 업데이트
	public void updateCertification(Certification certification) {
		
		// 서비스에서 맵퍼로 값 보내기
		certificateMapper.updateCertification(certification);
		return;
	}
	
	// 자격증 상세보기
	public Certification selectCertification(int certificationNo) {
		
		// 서비스에서 맴퍼로 값을 보내고 처리한 값을 vo.certification에 값 넣기
		Certification certification = certificateMapper.selectCertification(certificationNo);
		log.debug(CF.SWB+"[CertificateController CertificateMapper certification]"+CF.RESET+certification.toString()); // certification 디버깅
		return certification;
	}
	// 자격증 등록
	public void addCertification(Certification certification) {
		
		// 서비스에서 맴퍼로 값 넘겨주기
		certificateMapper.insertCertification(certification);
		return;
	}
	// 학생의 아이디를 통해 고지서를 보여준다
	public List<Registration> studentPaymentList(String studentId){
		return certificateMapper.paymentStudentList(studentId);
	}
}
