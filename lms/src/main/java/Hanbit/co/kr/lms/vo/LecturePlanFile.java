package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class LecturePlanFile {				//강의계획서파일
	private int lecPlanNo;					//강의계획서번호(FK)
	private String lecPlanFileName;			//강의계획서파일이름
	private String lecPlanFileOriginalName;	//강의계획서파일원본이름
	private String lecPlanFileType;			//강의계획서파일타입
	private String lecPlanFileSize;			//강의계획서파일크기
	private String createDate;
	private String updateDate;
}
