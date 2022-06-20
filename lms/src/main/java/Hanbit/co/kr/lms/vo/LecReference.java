package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class LecReference {
	private int lecReferenceNo; // 강의자료실번호
	private String lectureName; // 강좌이름
	private String lecReferenceTitle; // 강의자료실제목
	private String lecReferenceContent; // 강의자료실내용
	private String createDate; // 생성날짜
	private String updateDate; // 업데이트날짜
}
