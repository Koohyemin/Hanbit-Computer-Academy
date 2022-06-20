package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class Registration {
	private int registrationNo; // 수강 번호
	private int payment; // 납부 금액
	private String lectureName; // 강좌 이름
	private String studentId; // 학생 아이디
	private String createDate;
	private String updateDate;
}
