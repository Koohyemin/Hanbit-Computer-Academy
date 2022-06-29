package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class QuestionnaireAnswer {
	private int questionnaireNo; // 질문지 번호
	private String studentId; // 질문한 학생 아이디
	private int selectedEvaluationNo; //  선택한 평가 항목
	private String createDate;
	private String updateDate;
}
