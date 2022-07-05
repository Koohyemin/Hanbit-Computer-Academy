package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.LecHomeworkMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.HomeworkMake;
import Hanbit.co.kr.lms.vo.LecPlan;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LecHomeworkService {
	private static final int HashMap = 0;
	@Autowired LecHomeworkMapper lecHomeworkMapper;
	
	// 과제리스트
	public List<HashMap<String,Object>> homeworkList(LecPlan lecPlan) {
		List<HashMap<String, Object>> list = lecHomeworkMapper.homeworkList(lecPlan);
		log.debug(CF.SWB+"[LecHomeworkService homeworkList list]"+CF.RESET+ list); // 과제리스트 디버깅
		return list;
	}
	
	// 과제 등록
	public void insertHomework(HomeworkMake homeworkMake) {
		lecHomeworkMapper.insertHomework(homeworkMake);
		return;
	}
	
	// 한 과제의 상세보기 및 리스트
	public HashMap<String, Object> selectHomeworkOne(int homeworkMakeNo) {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		// 과제 상세보기
		HomeworkMake homeworkMake = lecHomeworkMapper.selectHomeworkOne(homeworkMakeNo);
		List<HashMap<String, Object>> submitList = lecHomeworkMapper.homeworkSubmitList(homeworkMakeNo);
		
		// 한과제의 학생들 과제 리스트
		returnMap.put("submitList", submitList);
		log.debug(CF.SWB+"[LecHomeworkService selectHomeworkOne submitList]"+CF.RESET+ submitList); // submitList 디버깅
		returnMap.put("homeworkMake", homeworkMake);
		log.debug(CF.SWB+"[LecHomeworkService selectHomeworkOne homeworkMake]"+CF.RESET+ homeworkMake); // homeworkMake 디버깅
		return  returnMap;
	}
}
