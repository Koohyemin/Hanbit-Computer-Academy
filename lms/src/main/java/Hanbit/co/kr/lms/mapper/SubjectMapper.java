package Hanbit.co.kr.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.vo.Subject;

@Mapper
public interface SubjectMapper {

	// 과목을 조회하는 리스트 
	List<Subject> selectSubjectList(String serachValue);
	
	// 과목을 입력
	int addSubject(String subjectName,String subjectSubscription);
	
	//과목삭제
	int deleteSubject(String subjectName);
}
