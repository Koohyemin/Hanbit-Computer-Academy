package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class Lec {						// 강좌 테이블
	private String lectureName;			// 강좌이름(
	private String lectureRoomName;		// 강좌 강의실(FK)
	private String managerId;			// 운영자 id(FK)
	private String subjectName;			// 과목이름(FK)
	private int registrationNumber;		// 수강인원
	private int registrationPassScore;	// 수료인정점수
	private String difficulty;			// 난이도
	private int lecCost;				// 강의 비용
	private String lecPhone;			// 문의연락처
	private String createDate;			// 생성일
	private String updateDate;			// 수정일
	private String beginClass;			// 강의시작일
	private String endClass;			// 강의종료일
	private String startTime;			// 강의 시작시간
	private String endTime;				// 강의 종료시간
	private String lunchTime;			// 점심시간
	private String teacherId;			// 강사 아이디
	private String teacherName;			// 강사 이름
	private String lecState;			// 강의상태
}
