package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class Faq {
	private int faqNo; // faq 번호
	private String managerId; // 운영자 아이디
	private String title; // 제목
	private String content; // 내용
	private String createDate;
	private String updateDate;
}
