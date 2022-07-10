package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Lec;

@Mapper
public interface AVGMapper {
	List<Map<String, Object>> selectAVGRank(String lectureName);
	List<Lec> selectLectureListByTeacher(String teacherId);
}
