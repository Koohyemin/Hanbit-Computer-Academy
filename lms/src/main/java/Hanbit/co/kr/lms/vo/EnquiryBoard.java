package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class EnquiryBoard {
	private int enquiryBoardNo; // 문의사항 게시글 번호
	private String memberId; // 아이디
	private String content; // 내용
	private String category; // 카테고리
	private String createDate;
	private String updateDate;
}
