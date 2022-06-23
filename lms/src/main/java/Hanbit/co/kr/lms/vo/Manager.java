package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class Manager {
	private String managerId; // 운영자아이디
	private String managerPw; // 패스워드
	private String managerName; // 이름
	private String managerAddr1; // 주소
	private String managerAddr2; // 상세주소
	private String managerPhone; // 연락처
	private String managerEmail; // 이메일
	private String managerBirth; // 생년월일
	private String managerGender; // 성별
	private String updateDate; // 업데이트날짜
}
