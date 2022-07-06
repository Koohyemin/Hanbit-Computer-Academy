package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.Registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.LecHomeworkMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.HomeworkMake;
import Hanbit.co.kr.lms.vo.HomeworkSubmission;
import Hanbit.co.kr.lms.vo.LecPlan;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LecHomeworkService {
	@Autowired LecHomeworkMapper lecHomeworkMapper;
	
	// 학생 과제제출 
	public void insertSubmitStudent(HomeworkSubmission homeworkSubmission) {
		log.debug(CF.SWB+"[insertSubmitStudent  insertSubmitStudent homeworkSubmission]"+CF.RESET+ homeworkSubmission); // homeworkSubmission 디버깅
		lecHomeworkMapper.insertSubmitStudent(homeworkSubmission);
		return;
	}
	
	// 수강리스트
	public List<Registration> selectLectureList(String studentId){
		List<Registration> registration = lecHomeworkMapper.lectureNameList(studentId);
		return registration;
	}
	
	// 학생의 과제리스트
	public List<HomeworkMake> studentHomeworkList(String studentId, String lectureName) {
		List<HomeworkMake> homeworkMake = lecHomeworkMapper.studentHomeworkList(studentId, lectureName);
		return homeworkMake;
	}
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
