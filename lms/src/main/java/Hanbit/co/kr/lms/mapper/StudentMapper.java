package Hanbit.co.kr.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Certification;
import Hanbit.co.kr.lms.vo.Lec;
import Hanbit.co.kr.lms.vo.PhotoFile;
import Hanbit.co.kr.lms.vo.Student;

@Mapper
public interface StudentMapper {
	// 학생 상세보기
	Student selectStudentOne(String studentId);
	
	// 자격증리스트
	List<Certification> selectStudentCertification(String studentId);
	
	// 강의수강
	List<Lec> selectLecList(String studentId);
	
	// 학생사진
	PhotoFile selectStudentPhoto(String studentId);
}
