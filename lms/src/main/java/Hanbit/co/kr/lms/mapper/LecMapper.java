package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Lec;
import Hanbit.co.kr.lms.vo.LecPlan;
import Hanbit.co.kr.lms.vo.LectureRoom;
import Hanbit.co.kr.lms.vo.Subject;
import Hanbit.co.kr.lms.vo.Teacher;
import Hanbit.co.kr.lms.vo.TimeTable;

@Mapper
public interface LecMapper {
	
	// 삭제 기능 제거할 예정
	
	// 개강일 전 강의 목록
	List<Lec> openLecListByPage(Map<String,Object> map); // 개강 중인 강좌 목록(운영자)
	int totalOpenLecCount(); // 강좌 상태별 상태, 총 개수
	
	// 개강일 전 강의 목록
	List<Lec> getLecListByPage(Map<String,Object> map); // 개강일 이전 강좌 목록(운영자, 강사 - 전체 / 학생 - 상태가 T인것만 조회)
	int totalCount(); // 강좌 상태별 상태, 총 개수
	
	// 강의
	int insertLec(Lec lec); // 강의 등록
	Lec getLecOne(String searchValue); // 강의 상세보기, 수정
	int getUpdateLec(Lec lec); // 강의 수정
	int getDeleteLec(String lectureName); // 강의 삭제
	
	// 일정표
	int insertTimeTable(TimeTable timeTable); // 일정표 등록
	int getUpdateTime(TimeTable timeTable); // 일정표 수정
	int getDeleteTime(String lectureName); // 일정표 삭제
	
	// 강의계획서
	int insertLecPlan(LecPlan lecPlan); // 강의계획서 등록
	int getUpdateLecPlan(LecPlan lecPlan); // 강의계획서 수정
	int getDeleteLecPlan(String lectureName); // 강의계획서 삭제
	int getUpdateLecState(String lectureName, String lecState); // 강의 상태 수정
	
	// 카테고리
	List<LectureRoom> lectureRoomList(); // 강의실 정보
	List<LecPlan> lecPlanList(); // 강의계획서 정보
	List<Subject> subjectList(); // 과목 정보
	List<Teacher> teacherList(); // 재직중인 강사 목록

	// 강의 계획서 상세보기
	LecPlan lecPlan();
}
