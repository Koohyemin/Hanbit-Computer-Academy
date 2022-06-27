package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class Certification {
	private int certificationNo; 
	private String memberId; // 아이디
	private String certificationName; // 자격증 이름
	private String certificationIssued; // 주관처
	private String getDate; // 취득일
	private String createDate;
	private String updateDate;
}
