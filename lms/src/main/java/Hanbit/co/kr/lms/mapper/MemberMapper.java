package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import Hanbit.co.kr.lms.vo.Manager;
import Hanbit.co.kr.lms.vo.Member;
import Hanbit.co.kr.lms.vo.PasswordUpdateDate;
import Hanbit.co.kr.lms.vo.Student;
import Hanbit.co.kr.lms.vo.Teacher;
@Mapper
public interface MemberMapper {
	//아이디 중복
	List<Member> selectMember();
	
	//회원가입
	int insertMember(Member member);
	int insertPUD(PasswordUpdateDate PUD);
	int insertStudent(Student student);
	int insertTeacher(Teacher teacher);
	int insertManager(Manager manager);
	int insertDefaultPhoto(Member member);
	
	//비승인 member들 리스트 출력
	List<Map<String,Object>> selectMemberListByState();
	
	//회원 승인
	int approveMember(String memberId);
	
	//회원스케줄러 ( 휴면계정)
	int inactivityMember();
	
	//회원휴면계정 활성화
	int updageActiveMember(PasswordUpdateDate passwordUpdateDate);
	int updateActivePasswordUpdateDate(PasswordUpdateDate passwordUpdateDate);
	int updateActiveStudent(PasswordUpdateDate passwordUpdateDate);
	int updateActiveTeacher(PasswordUpdateDate passwordUpdateDate);
	int updateActiveManager(PasswordUpdateDate passwordUpdateDate);
	
	
	//휴면계정 활성화 비밀번호(직전비밀번호 비교)
	int changePwbyactivity(PasswordUpdateDate passwordUpdateDate);
	
	int deleteMemberCk(String pw);
}
