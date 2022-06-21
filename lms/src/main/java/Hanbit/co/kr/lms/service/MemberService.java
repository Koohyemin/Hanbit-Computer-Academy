package Hanbit.co.kr.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import Hanbit.co.kr.lms.mapper.MemberMapper;
import Hanbit.co.kr.lms.vo.Member;
@Transactional
@Service
public class MemberService {
	@Autowired
	MemberMapper memberMapper;
	public List<Member> getMember() {
		List<Member> memberList = memberMapper.selectMember();
		return memberList;
	}
}
