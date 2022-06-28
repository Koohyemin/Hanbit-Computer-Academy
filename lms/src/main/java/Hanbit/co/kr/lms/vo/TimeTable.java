package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class TimeTable { //시간표
	private String lectureName; //강좌이름
	private String startTime; //시작시간
	private String endTime; //종료시간
	private String lunchTime; //점심시간
	private String beginClass; //개강일
	private String endClass; //종강일
	private String createDate;
	private String updateDate;
}
