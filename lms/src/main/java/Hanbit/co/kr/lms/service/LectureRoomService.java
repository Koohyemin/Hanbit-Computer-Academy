package Hanbit.co.kr.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Hanbit.co.kr.lms.mapper.LectureRoomMapper;
import Hanbit.co.kr.lms.vo.HomeworkMake;
import Hanbit.co.kr.lms.vo.LecQuestion;
import Hanbit.co.kr.lms.vo.LecReference;
import Hanbit.co.kr.lms.vo.LectureNotice;

@Service
public class LectureRoomService {
	
	@Autowired LectureRoomMapper lectureRoomMapper;
	
	// 자료실 최근 5개 게시글
	public List<LecReference> lecReferenceList(String lectureName) {
		return lectureRoomMapper.lecReferenceList(lectureName);
	}
	
	// 공지사항 최근 5개
	public List<LectureNotice> lectureNoticeList(String lectureName) {
		return lectureRoomMapper.lectureNoticeList(lectureName);
	}
	
	// Q&A 최근 5개 게시글
	public List<LecQuestion> lecQuestionList(String lectureName) {
		return lectureRoomMapper.lecQuestionList(lectureName);
	}
	
	// 과제 최근 5개 게시글
	public List<HomeworkMake> homeworkList(String lectureName) {
		return lectureRoomMapper.homeworkList(lectureName);
	}
	
	// 강사 수강 목록
	public List<String> teacherLectureList(String teacherId) {
		return lectureRoomMapper.teacherLectureList(teacherId);
	}
	
	// 학생 수강 목록
	public List<String> lectureList(String studentId) {
		return lectureRoomMapper.lectureList(studentId); // 수강 중인 강의 목록
	}
}
