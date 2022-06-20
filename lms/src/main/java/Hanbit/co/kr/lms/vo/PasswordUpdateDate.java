package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class PasswordUpdateDate {
	private String memberId; // 아이디
	private String memberPw; // 비밀번호
	private String createDate; // 비밀번호 업데이트 날짜
}
