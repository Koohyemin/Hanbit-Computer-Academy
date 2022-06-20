package Hanbit.co.kr.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Student;
@Mapper
public interface UserFindMapper {
	//학생 id찾기
	String studentFindId(String studentName, String studentPhone);
}
