package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class Skill {
	public int skillNo; // 기술 번호 
	public String managerId; //  운영자 아이디
	public String skillName;  // 기술 이름
	public String createDate;
	public String updateDate;
}
