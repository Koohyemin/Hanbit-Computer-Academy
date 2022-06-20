package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class QnaAnswer {
	private int qnaNoticeAnswerNo; // qna 질문 답변
	private int qnaNoticeNo; // qna 질문 번호
	private String memberId; // 계정아디디 모음 아이이디
	private String qnaNoticeAnswerContent; // qna 질문내용
	private String revelation; // 공개 여부
	private String createDate;
	private String updateDate;
}
