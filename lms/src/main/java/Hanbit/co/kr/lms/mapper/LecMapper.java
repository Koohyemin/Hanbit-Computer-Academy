package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Lec;
import Hanbit.co.kr.lms.vo.LecPlan;
import Hanbit.co.kr.lms.vo.LectureRoom;
import Hanbit.co.kr.lms.vo.Subject;

@Mapper
public interface LecMapper {
	
	// 개강일 전 강의 목록
	List<Lec> getLecListByPage(Map<String,Object> map); // 개강일 이전 강좌 목록(운영자, 강사 - 전체 / 학생 - 상태가 T인것만 조회)
	int totalCount(); // 강좌 상태별 상태, 총 개수
	
	// 강의 등록
	int insertLec(Lec lec);
	
	// 강의 상세보기, 수정
	Lec getLecOne(String searchValue);
	
	// 강의 수정
	int getUpdateLec(Lec lec);
	
	// 강의 삭제
	int getDeleteLec(String lectureName);
	
	// 카테고리
	List<LectureRoom> lectureRoomList(); // 강의실 정보
	List<LecPlan> lecPlanList(); // 강의계획서 정보
	List<Subject> subjectList(); // 과목 정보
}
