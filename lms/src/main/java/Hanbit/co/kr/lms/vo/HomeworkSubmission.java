package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class HomeworkSubmission {
	private int homeworkSubmissionNo; // 과제물 생성 번호
	private int homeworkMakeNo;
	private String studentId; // 학생 아이디
	private String homeworkSubmissionTitle; // 과제물 제출 제목
	private String homeworkSubmissionContent; // 과제물 제출 내용
	private int homeworkScore; // 과제물 점수
	private String createDate;
	private String updateDate;
}
