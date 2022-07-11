package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.HomeworkFile;
import Hanbit.co.kr.lms.vo.LecPlan;
import Hanbit.co.kr.lms.vo.LecReference;
import Hanbit.co.kr.lms.vo.LecReferenceFile;
import Hanbit.co.kr.lms.vo.Registration;

@Mapper
public interface LecReferenceMapper {
	
	// 강의자료실 리스트(학생/강사)
	

	// 공지 개수
	int totalCount();
	
	// 학생 강좌정보 블러오기
	
	// 자료실 목록
	List<LecReference> getLecReferenceListByPage(Map<String, Object> map); 
	
	// 강의 자료 업로드
	
	// 강의 자료 상세보기
	
	// 강사 강좌정보 불러오기
	List<LecPlan> lectureNameList(String teacherId);
	
	// 학생 강좌장버 블러오기
	List<Registration> studentLectureNameList(String studuntId); 
	
	// 파일 업로드
	

	LecReferenceFile fileNameOne(int lecReferenceFileNo);

	int deleteLecReferenceFileone(int lecReferenceFileNo);
	
	// 파일 삭제 
	
	int deleteFileOne  (int lecReferenceFileNo);
	
}
