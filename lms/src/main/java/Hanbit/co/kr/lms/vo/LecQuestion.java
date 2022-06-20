package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class LecQuestion {
	private int lecQuestionNo; // 강좌문의 번호
	private String memberId; // 아이디
	private String lectureName; // 강좌이름
	private String lecQuestionTitle; // 강좌문의 제목
	private String lecQuestionContent; // 강좌문의 내용
	private String revelation; // 공개여부
	private String createDate; // 생성날짜
	private String updateDate; // 업데이트날짜
}
