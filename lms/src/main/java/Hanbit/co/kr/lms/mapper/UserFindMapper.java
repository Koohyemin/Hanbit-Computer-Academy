package Hanbit.co.kr.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Student;
@Mapper
public interface UserFindMapper {
	//학생 id찾기
	String studentFindId(String studentName, String studentPhone);
	
	//학생 pw찾기
	String studentFindPw(String studentId, String studentName, String studentPhone);
	
	//강사 id찾기
	String teacherFindId(String teacherName, String teacherPhone);
	
	//운영자 id찾기
	String managerFindId(String managerName, String managerPhone);
}
