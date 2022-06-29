package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class LecPlan {				//강의계획서
	private String lectureName;		//강의계획서이름
	private String teacherId;		//강사아이디(FK)
	private String lecState;			//상태
	private String createDate;
	private String updateDate;
}	
