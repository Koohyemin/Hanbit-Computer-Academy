package Hanbit.co.kr.lms.mapper;

import java.util.List;

import Hanbit.co.kr.lms.vo.Manager;
import Hanbit.co.kr.lms.vo.Member;
import Hanbit.co.kr.lms.vo.Student;
import Hanbit.co.kr.lms.vo.Teacher;

public interface MemberMapper {
	List<Member> selectMember();
//	int insertMember(Member member);
//	int insertStudent(Student student);
//	int insertTeacher(Teacher teacher);
//	int insertManager(Manager manager);
}
