package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class Questionnaire {
	private int questionnaireNo; // 질문지 번호
	private int evaluationNo; // 항목 번호
	private String lectureName; // 강좌 이름
	private String category; // 카테고리
	private String questionnaireName; // 내용
}
