package Hanbit.co.kr.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class HomeworkForm {
	private int homeworkMakeNo;
	private String studentId; // 학생 아이디
	private String homeworkSubmissionTitle; // 과제물 제출 제목
	private String homeworkSubmissionContent;
	private List<MultipartFile> homeworkFileList;
}
