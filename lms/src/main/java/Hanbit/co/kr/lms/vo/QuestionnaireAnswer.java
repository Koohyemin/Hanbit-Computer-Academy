package Hanbit.co.kr.lms.vo;

import lombok.Data;

@Data
public class QuestionnaireAnswer {
	private int questionnaireNo; // 질문지 번호
	private int registrationNo; // qna 수강번호
	private int selectedEvaluationNo; //  선택한 평가 항목
	private String createDate;
	private String updateDate;
}
