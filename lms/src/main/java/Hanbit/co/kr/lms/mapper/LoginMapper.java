package Hanbit.co.kr.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Member;

@Mapper
public interface LoginMapper {
	// 마지막 날짜 데이터
	String selectPwDate(String memberId);
	
	// select student
	Member selectStudent(String studentId, String studentPw);
	
	// select teacher
	Member selectTeacher(String teacherId, String teacherPw);
	
	// select manager
	Member selectManager(String managerId, String managerPw);
	
	// update logDate
	int updateLogDate(String memberId);
}
