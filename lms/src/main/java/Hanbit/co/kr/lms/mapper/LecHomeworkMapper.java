package Hanbit.co.kr.lms.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.HomeworkMake;
import Hanbit.co.kr.lms.vo.LecPlan;

@Mapper
public interface LecHomeworkMapper {
	
	// 강사 과제리스트
	List<HashMap<String , Object>> homeworkList(LecPlan lecPlan);
}
