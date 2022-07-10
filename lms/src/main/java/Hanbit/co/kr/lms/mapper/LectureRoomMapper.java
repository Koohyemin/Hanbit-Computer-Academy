package Hanbit.co.kr.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.HomeworkMake;
import Hanbit.co.kr.lms.vo.LecQuestion;
import Hanbit.co.kr.lms.vo.LecReference;
import Hanbit.co.kr.lms.vo.LectureNotice;

@Mapper
public interface LectureRoomMapper {
	
	// 수강 강의 목록
	List<String> lectureList(String studentId); // 해당 학생 수강강의 불러오기(개강 한 강의)
	List<String> teacherLectureList(String teacherId); // 해당 강사 수강강의 불러오기(개강 한 강의)
	
	// 최근 5개 게시글 보여주기
	List<HomeworkMake> homeworkList(String lectureName); // 과제 최근 5개 게시글
	List<LecQuestion> lecQuestionList(String lectureName); // Q&A 최근 5개 게시글
	List<LectureNotice> lectureNoticeList(String lectureName); // 공지사항 최근 5개 게시글
	List<LecReference> lecReferenceList(String lectureName); // 자료실 최근 5개 게시글
}
