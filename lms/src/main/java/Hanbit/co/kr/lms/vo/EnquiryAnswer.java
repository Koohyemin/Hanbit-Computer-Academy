package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class EnquiryAnswer {
	private int enquiryBoardAnswerNo; // 문의사항 답변 번호
	private int enquiryBoardNo; // 문의사항 게시물 번호
	private String memberId; // 아이디
	private String noticeContent; // 문의사항 답변 내용
	private String createDate;
	private String updateDate;
}
