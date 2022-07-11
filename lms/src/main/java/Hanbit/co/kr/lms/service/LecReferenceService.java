package Hanbit.co.kr.lms.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.LecReferenceMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.HomeworkFile;
import Hanbit.co.kr.lms.vo.LecPlan;
import Hanbit.co.kr.lms.vo.LecReference;
import Hanbit.co.kr.lms.vo.LecReferenceFile;
import Hanbit.co.kr.lms.vo.LectureNotice;
import Hanbit.co.kr.lms.vo.Registration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LecReferenceService {
@Autowired LecReferenceMapper lecReferenceMapper;
@Autowired HttpSession session;

//공지사항 리스트
		public Map<String, Object> getLecReferenceListByPage(int currentPage, int rowPerPage, String lectureName) {
			
			// 컨트롤러에서 가져온 값 
			log.debug(CF.KHV +"[LecReferenceService getLecReferenceListByPage currentPage ]: " + CF.RESET + currentPage);
			log.debug(CF.KHV +"[LecReferenceService getLecReferenceListByPage rowPerPage ]: " + CF.RESET + rowPerPage);
			log.debug(CF.KHV +"[LecReferenceService getLecReferenceListByPage lectureName ]: " + CF.RESET + lectureName);
			
			// 현재페이지를 이욯하여 시작페이지 계산
			int beginRow = (currentPage-1) * rowPerPage;
			
			// map으로 하나의 값으로 묶어주기
			Map<String, Object> map = new HashMap<>();
			map.put("beginRow", beginRow); // 시작 페이지
			map.put("rowPerPage", rowPerPage); // 한 페이지당 표시할 공지 개수	
			map.put("lectureName", lectureName);
			
			// SQL 매개값 대입
			List<LecReference> list = lecReferenceMapper.getLecReferenceListByPage(map);	
			int totalCount = lecReferenceMapper.totalCount(); // 전체 공지 개수
			
			// 마지막 페이지
			int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
			
			// return값 하나의 값으로 묶어주기
			Map<String, Object> returnMap = new HashMap<>();
			returnMap.put("list", list);
			returnMap.put("lastPage", lastPage);
			
			// returnMap 반환
			return returnMap;
		}
		

		// 공지사항 강사 수강별 강좌
		public List<LecPlan> lectureNameList(String teacherId) {
		List<LecPlan> lecPlan = lecReferenceMapper.lectureNameList(teacherId);
		log.debug( CF.KHV +"[LecReferenceService lectureNameList]: "+ CF.RESET + lecPlan.size());
		return lecPlan;
		}
		
		// 공지사항 학생 수강별 강좌
		public List<Registration> studentLectureNameList(String studentId) {
			List<Registration> registrations = lecReferenceMapper.studentLectureNameList(studentId);
			log.debug( CF.KHV +"[LecReferenceService studentLectureNameList studentId]: "+ CF.RESET + registrations);
			return registrations;
		}
		
		// 학생이 파일하나만 삭제
		public String deleteFileOne(int lecReferenceFileNo, String path) {
			
			// 파일 이름
			LecReferenceFile lecReferenceFile = lecReferenceMapper.fileNameOne(lecReferenceFileNo);
			String fileName = lecReferenceFile.getLecReferenceFileType();
			File f = new File(path+fileName);
			if(f.exists()) {
				f.delete();
			}
			
			int row	= lecReferenceMapper.deleteLecReferenceFileone(lecReferenceFileNo);
			log.debug(CF.SWB+"[LecHomeworkService  deleteFileOne row]"+CF.RESET+ row); // row 디버깅
			
			// 변수등록 -> consolㄷ.log 확인용
			String check = null;
			if(row == 1) {
				check = "삭제 성공";
			} else {
				check = "실패";
			}
			return check;
		}
		
}