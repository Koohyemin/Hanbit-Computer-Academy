package Hanbit.co.kr.lms.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.HomeworkMake;
import Hanbit.co.kr.lms.vo.LecPlan;

@Mapper
public interface LecHomeworkMapper {
	
	// 강사과제
	List<HashMap<String , Object>> homeworkList(LecPlan lecPlan); // 강사 과제리스트
	int insertHomework(HomeworkMake homeworkMake); // 강사 과제등록
	HomeworkMake selectHomeworkOne(int homeworkMakeNo); // 과제 하나 상세보기
	List<HashMap<String, Object>> homeworkSubmitList(int homeworkMakeNo); // 한 과제의 학생들 과제제출 리스트
}
