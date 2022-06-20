package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class Student { //학생
	private String studentId; //학생아이디
	private String studentPw; //패스워드
	private String studentName; //이름
	private String studentAddr1; //주소
	private String studentAddr2; //상세주소
	private String studentPhone; //연락처
	private String studentBirth; //생년월일
	private String studentGender; //성별
	private String finalEducation; //최종학력
	private String studentEmail; //이메일
	private String updateDate;
}
