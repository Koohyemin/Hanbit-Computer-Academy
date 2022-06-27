package Hanbit.co.kr.lms.mapper;

import java.util.List;

import javax.servlet.Registration;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Certification;
import Hanbit.co.kr.lms.vo.Lec;
import Hanbit.co.kr.lms.vo.Manager;
import Hanbit.co.kr.lms.vo.PhotoFile;
import Hanbit.co.kr.lms.vo.Student;
import Hanbit.co.kr.lms.vo.Teacher;

@Mapper
public interface ImformationMapper {
	
	// 운영진
	void updateManager(Manager manager);
	
	// 강사 정보 업데이트
	void updateTeacher(Teacher teacher);
	
	// 학생 정보업데이트
	void updateStudent(Student student);
	
	// 학생 상세보기
	Student selectStudentOne(String studentId);
	
	// 학새 자격증리스트
	List<Certification> selectStudentCertification(String studentId);
	
	// 학생 강의수강
	List<Lec> selectLecList(String studentId);
	
	// 학생 사진
	PhotoFile selectStudentPhoto(String studentId);
	
	// 강사 상세보기
	Teacher selectTeacherOne(String teacherId);
	
	// 강사 자격증리스트
	List<Certification> selectTeacherCertification(String teacherId);
	
	// 강사 수강
	List<Registration> selectRegistrationList(String teacherId);
	
	// 강사 사진
	PhotoFile selectTeacherPhoto(String teacherId);
	
	// 강사 강좌-수강리스트
	List<Lec> selectTeacherLecList(String teacherId);
	
	// 운영진 상세보기
	Manager selectManagerOne(String managerId);
	
	// 운영진 사잔
	PhotoFile selectManagerPhoto(String mangaerId);
}
