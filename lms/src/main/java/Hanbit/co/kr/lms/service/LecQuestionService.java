package Hanbit.co.kr.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.LecQuestionMapper;
import Hanbit.co.kr.lms.vo.LecQuestion;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LecQuestionService {
	@Autowired LecQuestionMapper lecQuestionMapper;
	// 강좌질문 리스트
	public List<LecQuestion> lecQuestionList() {
		return lecQuestionMapper.getLecQuestionListByPage(null);
	}
}
