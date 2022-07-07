package Hanbit.co.kr.lms.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Certification;
import Hanbit.co.kr.lms.vo.Lec;
import Hanbit.co.kr.lms.vo.Manager;
import Hanbit.co.kr.lms.vo.PasswordUpdateDate;
import Hanbit.co.kr.lms.vo.PhotoFile;
import Hanbit.co.kr.lms.vo.Student;
import Hanbit.co.kr.lms.vo.Teacher;
import Hanbit.co.kr.lms.vo.TimeTable;

@Mapper
public interface InformationMapper {
	
	// 비밀번호 90일 연장하기
	int studentLogInsertPw(String memberId); // 학생
	int teacherLogInsertPw(String memberId); // 강사
	int managerLogInsertPw(String memberId); // 운영진
	
	// 현재 비밀번호 확인용
	int selectCurrentPw(HashMap<String , Object> map);
	
	// 최근 비밀번호리스트 비교 (개수조정 가능)
	int selectPwList(PasswordUpdateDate passwordUpdateDate);
	
	// 사진이름 찾기
	String selectPhotoName(String memberId);
	
	// 사진 등록
	void updatePhoto(PhotoFile photoFile);
	
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
	
	// 강사 수강 시간
	List<TimeTable> selectLecTime(String teacherId);
	
	// 강사 사진
	PhotoFile selectTeacherPhoto(String teacherId);
	
	// 강사 강좌-수강리스트
	List<Lec> selectTeacherLecList(String teacherId);
	
	// 운영진 상세보기
	Manager selectManagerOne(String managerId);
	
	// 운영진 사잔
	PhotoFile selectManagerPhoto(String mangaerId);
}
