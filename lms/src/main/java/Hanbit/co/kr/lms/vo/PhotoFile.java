package Hanbit.co.kr.lms.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PhotoFile {
	private String memberId; // 멤버 아이디
	private String photoName; // 사진 이름
	private String photoOriginalName; // 사진 원본 이름
	private String photoType; // 사진 타입
	private long photoSize; // 사진 사이즈
	private String createDate; 
	private String updateDate;
	private MultipartFile photoFile;
}
