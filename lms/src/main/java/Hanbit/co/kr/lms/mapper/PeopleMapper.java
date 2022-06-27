package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PeopleMapper {
	List<Map<String, Object>> getTeacherList(String searchValue); // 강사 리스트
	List<Map<String,Object>> getLecList(String searchValue); // 강의 리스트
}
