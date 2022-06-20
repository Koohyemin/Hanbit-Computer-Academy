package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class Qna {
	private int qnaNoticeNo; // qna 게시물 번호
	private String lectureName; // 강좌 번호
	private String memberId; // 아이디
	private String qnaNoticeTitle; // 제목
	private String qnaNoticeContent; // 콘텐츠
	private String revelation; // 공개여부
	private String createDate;
	private String updateDate;
}
