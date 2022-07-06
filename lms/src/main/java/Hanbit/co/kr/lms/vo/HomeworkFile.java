package Hanbit.co.kr.lms.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class HomeworkFile {
	private int homeworkFileNo; // 과제물 파일 번호
	private int homeworkSubmissionNo; // 과제물 제출 번호
	private String homeworkFileName; // 과제 첨부 파일 이름
	private String homerworkFileOriginalName; // 과제 첨부 원본 파일 이름
	private String homeworkFileOriginalType; // 파일 타입
	private int homeworkFileSize; // 파일 크기
	private String createDate;
	private String updateDate;
	private MultipartFile homeworkFile;
}
