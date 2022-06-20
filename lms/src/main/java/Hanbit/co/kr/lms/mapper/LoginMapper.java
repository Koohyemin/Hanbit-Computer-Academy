package Hanbit.co.kr.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Member;

@Mapper
public interface LoginMapper {
	// select student
	Member selectStudent(String studentId, String studentPw);
	
	// select teacher
	Member selectTeacher(String teacherId, String teacherPw);
	
	// select manager
	Member selectManager(String managerId, String managerPw);
}
