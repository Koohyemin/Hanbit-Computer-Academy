package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class LecAnswer {				//강좌문의 답변
	private int lecAnswerNo;			//강좌답변번호
	private int lecQuestionNo;			//강좌문의번호(FK)
	private String memberId;			//계정모음아이디(FK)
	private String lecAnswerContent;	//강좌답변내용
	private String revelation;			//공개여부
	private String createDate;
	private String updateDate;
	
}
