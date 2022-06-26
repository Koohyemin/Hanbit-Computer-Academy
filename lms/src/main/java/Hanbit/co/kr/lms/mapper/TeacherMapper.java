package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {
	List<Map<String, Object>> getTeacherList(String searchValue); // 강사 리스트
}
