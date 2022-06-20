package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class Teacher { //강사
	private String teacherId; //강사아이디
	private String teacherPw; //패스워드
	private String teacherName; //이름
	private String teacherAddr1; //주소
	private String teacherAddr2; //상세주소
	private String teacherPhone; //연락처
	private String teacherEmail; //이메일
	private String teacherBirth; //생년월일
	private String teacherGender; //성별
	private String finalEducation; //최종학력
	private String updateDate;
}
