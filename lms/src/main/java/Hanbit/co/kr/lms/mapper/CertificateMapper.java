package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Certification;
import Hanbit.co.kr.lms.vo.Registration;

@Mapper
public interface CertificateMapper {
	
	// 수료 증명서 리스트
	List<Map <String,Object>> completionList(String studentId);
	
	// 자격증 상세보기
	Certification selectCertification(int certificationNo);
	
	// 자격증 등록
	void insertCertification(Certification certification);
	
	// 자격증 수정
	void updateCertification(Certification certification);
	
	// 자격증 삭제
	void deleteCertification(int certificationNo);
	
	// 납부 증명서 리스트 
	List <Map<String,Object>> paymentStudentList(String studentId);

}
