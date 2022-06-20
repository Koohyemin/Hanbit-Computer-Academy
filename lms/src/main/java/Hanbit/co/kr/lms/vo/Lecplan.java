package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class Lecplan {				//강의계획서
	private int lecPlanNo;			//강의계획서번호
	private String teacherId;		//강사아이디(FK)
	private String lecPlanName;		//강의계획서이름
	private String lecState;			//상태
	private String createDate;
	private String updateDate;
}	
