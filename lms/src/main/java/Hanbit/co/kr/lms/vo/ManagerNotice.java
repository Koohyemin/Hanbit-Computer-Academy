package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class ManagerNotice {
	private int managerNoticeNo; // 공지사항번호
	private String managerId; // 운영자 아이디
	private String managerNoticeTitle; // 제목
	private String managerNoticeContent; // 내용
	private String category; // 카테고리
	private String createDate; // 생성날짜
	private String updateDate; // 업데이트날짜
}
