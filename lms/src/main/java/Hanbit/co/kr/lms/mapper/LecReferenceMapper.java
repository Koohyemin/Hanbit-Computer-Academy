package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.LecReference;
import Hanbit.co.kr.lms.vo.Registration;

@Mapper
public interface LecReferenceMapper {
	
	// 강의자료실 리스트(학생/강사)
	List<LecReference> getLecReferenceListByPage(Map<String, Object> map); 

	// 공지 개수
	int totalCount();
	
	// 강사 강좌정보 불러오기
	String teacherList(String teacherId);
	
	// 학생 강좌정보 블러오기
	List<Registration> studentLectureNameList(String studuntId); 
	
	// 강의 자료 업로드
	int addLecReference(LecReference lecReference); 
	
	// 강의 자료 상세보기
	LecReference getLecReferenceOne(int lecReferecnceNo);
	
}
