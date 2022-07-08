package Hanbit.co.kr.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LectureRoomMapper {
	List<String> lectureList(String studentId); // 해당 학생 수강강의 불러오기(개강 한 강의)
	List<String> teacherLectureList(String teacherId); // 해당 강사 수강강의 부럴오기(개강 한 강의)
}
