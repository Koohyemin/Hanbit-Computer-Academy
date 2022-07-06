package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class HomeworkMake {
	private int homeworkMakeNo; // 과제 생성 번호
	private String lectureName; // 강좌 이름
	private String homeworkMakeTitle; // 과제 제목
	private String homeworkMakeContent; // 과제 내용
	private String homeworkDeadline; // 과제 마감일
	private String createDate;
	private String updateDate;
	
	// DB에 없는 데이터
	private int homeworkScore;
	private int homeworkSubmissionNo;
}
