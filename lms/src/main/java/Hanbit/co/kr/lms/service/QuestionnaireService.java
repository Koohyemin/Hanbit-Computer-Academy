package Hanbit.co.kr.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Hanbit.co.kr.lms.mapper.QuestionnaireMapper;
import Hanbit.co.kr.lms.vo.Questionnaire;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionnaireService {
	@Autowired QuestionnaireMapper questionnaireMapper;
	
	//설문지 목록 불러오기
	public List<Questionnaire> selectQuestionnaireList(String lectureName) {
		return questionnaireMapper.selectQuestionnaireList(lectureName);
	}

}
