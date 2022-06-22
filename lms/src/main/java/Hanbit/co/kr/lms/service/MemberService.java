package Hanbit.co.kr.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import Hanbit.co.kr.lms.mapper.MemberMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.AddMember;
import Hanbit.co.kr.lms.vo.Manager;
import Hanbit.co.kr.lms.vo.Member;
import Hanbit.co.kr.lms.vo.PasswordUpdateDate;
import Hanbit.co.kr.lms.vo.Student;
import Hanbit.co.kr.lms.vo.Teacher;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Transactional
@Service
public class MemberService {
	@Autowired
	MemberMapper memberMapper;
	
	private Member m;
	private Manager mng;
	private PasswordUpdateDate PUD;
	public List<Member> getMember() {
		List<Member> memberList = memberMapper.selectMember();
		log.debug(CF.LKL+"MemberService.getMember"+memberList+CF.RESET);
		return memberList;
	}
//	public int addStudent(Member member,PasswordUpdateDate pud,Student student) {
//		memberMapper.insertMember(member);
//		memberMapper.insertPUD(pud);
//		int row=memberMapper.insertStudent(student);
//		return row;
//	}
//	public int addTeacher(Member member,PasswordUpdateDate pud, Teacher teacher) {
//		memberMapper.insertMember(member);
//		memberMapper.insertPUD(pud);
//		int row=memberMapper.insertTeacher(teacher);
//		return row;
//	}
	public int addManager(AddMember aM) {

		//member vo로 데이터 가공
		m= new Member();
		m.setMemberId(aM.getId());
		m.setMemberLevel(aM.getLevel());
//		m.setMemberState(0);
		//manager vo로 데이터 가공
		mng= new Manager();
		mng.setManagerId(aM.getId());
		mng.setManagerName(aM.getName());
		mng.setManagerPw(aM.getPw());
		mng.setPhone(aM.getPhone());
		mng.setManagerAddr1(aM.getAddr1());
		mng.setManagerAddr2(aM.getAddr2());
		mng.setManagerBirth(aM.getPid1());		// 나이 인가? 주번인가?
		mng.setManagerEmail(aM.getEmail());
		if(aM.getPid2()==1 ||aM.getPid2()==3) {
			mng.setManagerGender("남");
		}else {
			mng.setManagerGender("여");
		}
		//PUD vo로 데이터 가공
		PUD = new PasswordUpdateDate();
		PUD.setMemberId(aM.getId());
		PUD.setMemberPw(aM.getPw());

		
		memberMapper.insertMember(m);
		memberMapper.insertPUD(PUD);
		int row=memberMapper.insertManager(mng);
		
		return row;
	}
}
