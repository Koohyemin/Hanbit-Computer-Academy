package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class AddMember {
	private String id;
	private String pw;
	private String name;
	private String pid1;
	private int pid2;
	private String addr1;
	private String addr2;
	private String phone;
	private String email;
	private int level;
	private String finalEdu;
}
