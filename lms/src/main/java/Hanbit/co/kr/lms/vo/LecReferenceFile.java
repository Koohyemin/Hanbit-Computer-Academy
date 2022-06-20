package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class LecReferenceFile {
	private int lecReferemceFileNo; // 강의자료실파일번호
	private int lecReferenceNo; // 강의자료번호
	private String lecReferenceFileName; // 강의자료실파일 이름
	private String lecReferenceFileOriginalName; // 강의자료실 원본파일 이름
	private String lecReferenceFileType; // 강의자료실 파일 타입
	private int lecReferenceFileSize; // 강의자료실 파일 크기
	private String createDate; // 생성날짜
	private String updateDate; // 업데이트날짜
}
