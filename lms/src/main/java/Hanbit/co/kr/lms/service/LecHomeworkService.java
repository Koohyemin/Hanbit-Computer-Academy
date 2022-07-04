package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.LecHomeworkMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.LecPlan;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LecHomeworkService {
	@Autowired LecHomeworkMapper lecHomeworkMapper;
	
	// 과제리스트
	public List<HashMap<String,Object>> homeworkList(LecPlan lecPlan) {
		List<HashMap<String, Object>> list = lecHomeworkMapper.homeworkList(lecPlan);
		log.debug(CF.SWB+"[LecHomeworkService homeworkList list]"+CF.RESET+ list); // 과제리스트 디버깅
		return list;
	}
}
