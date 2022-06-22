package Hanbit.co.kr.lms.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

import Hanbit.co.kr.lms.vo.Certification;
import Hanbit.co.kr.lms.vo.Student;

@Mapper
public interface StudentMapper {
	// 학생 상세보기
	Student selectStudentOne(String studentId);
	
	// select studentCerticationList
	List<Certification> selectStudentCertification(String studentId);
}
