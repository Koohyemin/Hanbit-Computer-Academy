package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class LectureNotice {		//강의 공지
	private int lecNoticeNo;		//강의공지번호
	private String memberId;		//계정 모음 아이디(FK)
	private String lectureName;		//강좌이름(FK)
	private String title;			//강좌공지제목
	private String content;			//강좌공지내용
	private String createDate;
	private String updateDate;
}
