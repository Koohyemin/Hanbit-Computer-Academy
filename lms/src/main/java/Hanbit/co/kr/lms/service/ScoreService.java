package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.AVGMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Lec;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ScoreService {
	@Autowired AVGMapper avgmapper;
	
	public Map<String,Object> getScoreRank(String lectureName, String teacherId){
		
		
		//컨트롤러에서 받은 매개값 확인
		log.debug(CF.LKL+"ScoreService.getScoreRank.LectureName"+CF.RESET + lectureName);
		log.debug(CF.LKL+"ScoreService.getScoreRank.teacherId"+CF.RESET + teacherId);
		
		//강사 담당 조회
		List<Lec> lectureList = avgmapper.selectLectureListByTeacher(teacherId);
		
		log.debug(CF.LKL+"ScoreService.getScoreRank.lectureList"+CF.RESET + lectureList);
		log.debug(CF.LKL+"ScoreService.getScoreRank.lectureList"+CF.RESET + lectureList.size());
		
		String defaultValue = lectureList.get(0).getLectureName();
		
		log.debug(CF.LKL+"ScoreService.getScoreRank.defaultValue"+CF.RESET + defaultValue);
		List<Map<String,Object>> scorelist;
		if(lectureName == null || lectureList.size() != 0) {				
			
			lectureName=  defaultValue;
			
			lectureList.remove(0);
			
			 scorelist = avgmapper.selectAVGRank(defaultValue);
		}else {
		
		//다시 확인
		
		log.debug(CF.LKL+"ScoreService.getScoreRank.lectureName"+CF.RESET + lectureName);
		log.debug(CF.LKL+"ScoreService.getScoreRank.lectureList"+CF.RESET + lectureList);
		
		
		//학생 점수 리스트 
		 scorelist = avgmapper.selectAVGRank(lectureName);
		}
		Map<String,Object> map = new HashMap<>();
		log.debug(CF.LKL+"ScoreService.getScoreRank.scorelist"+CF.RESET + scorelist);
		
		map.put("scorelist", scorelist);
		map.put("lectureList", lectureList);
		map.put("defaultValue", defaultValue);
		return map;
		
	}
	
	public List<Map<String,Object>> getScoreByStudent(String StudentId){
		
		List<Map<String,Object>> list = avgmapper.selectScoreByStudent(StudentId);
		
		log.debug(CF.LKL+"ScoreService.getScoreByStudent.list"+CF.RESET+list);
		
		return list;
	}
	
	public List<Map<String,Object>> getAVGRank(String LectureName){
	
		List<Map<String,Object>> scorelist = avgmapper.selectAVGRank(LectureName);
		
		return scorelist;
	};
}
