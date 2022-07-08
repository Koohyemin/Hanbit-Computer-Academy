package Hanbit.co.kr.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Hanbit.co.kr.lms.mapper.LectureRoomMapper;

@Service
public class LectureRoomService {
	
	@Autowired LectureRoomMapper lectureRoomMapper;
	
	// 강사 수강 목록
	public List<String> teacherLectureList(String teacherId) {
		return lectureRoomMapper.teacherLectureList(teacherId);
	}
	
	// 학생 수강 목록
	public List<String> lectureList(String studentId) {
		return lectureRoomMapper.lectureList(studentId); // 수강 중인 강의 목록
	}
}
