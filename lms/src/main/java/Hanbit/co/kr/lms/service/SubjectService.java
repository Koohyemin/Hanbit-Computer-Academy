package Hanbit.co.kr.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.SubjectMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Subject;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class SubjectService {
	@Autowired SubjectMapper subjectMapper;
	
	public List<Subject> selectsubjectList(String searchValue){
		return subjectMapper.selectSubjectList(searchValue);
	}
	
	public int addSubject(String subjectName,String subjectSubscription) {
		int row = subjectMapper.addSubject(subjectName, subjectSubscription);
		if(row == 1) {
			log.debug( CF.KYJ +"[SubjectService addSubject row ]: "+ row+"입력완료" + CF.RESET);
		}
		return row;
	}
	
	public int deleteSubject(String subjectName) {
		return subjectMapper.deleteSubject(subjectName);
	}
}
