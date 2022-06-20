package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class Keeping {		//찜
	private String lectureName;		//강좌이름(FK)
	private String studentNo;		//학생번호(FK)
	private String createDate;		//생성일자
	private String updateDate;		//수정일자
}
