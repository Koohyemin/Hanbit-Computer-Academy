package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class LectureRoom {				//강의실
	private String lectureRoomName;		//강의실이름
	private String lectureRoomAddr1;	//강의실 주소
	private String lectureRoomAddr2;	//강의실 상세주소
	private int lectureRoomNumber;		//강의실 수용인원
	private int lectureRoomSize;		//강의실 면적
	private String createDate;
	private String updateDate;
}
