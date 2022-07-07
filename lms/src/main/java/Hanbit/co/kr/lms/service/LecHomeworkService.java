package Hanbit.co.kr.lms.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.Registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import Hanbit.co.kr.lms.mapper.LecHomeworkMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.HomeworkFile;
import Hanbit.co.kr.lms.vo.HomeworkForm;
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
	public void insertSubmitStudent(HomeworkForm homeworkForm, String path,String studentId) {
		log.debug(CF.SWB+"[insertSubmitStudent  insertSubmitStudent homeworkSubmission]"+CF.RESET+ homeworkForm); // homeworkSubmission 디버깅
		HomeworkSubmission homeworkSubmission = new HomeworkSubmission();
		homeworkSubmission.setHomeworkMakeNo(homeworkForm.getHomeworkMakeNo());
		homeworkSubmission.setHomeworkSubmissionTitle(homeworkForm.getHomeworkSubmissionTitle());
		homeworkSubmission.setHomeworkSubmissionContent(homeworkForm.getHomeworkSubmissionContent());
		homeworkSubmission.setStudentId(studentId);
		
		int row = lecHomeworkMapper.insertSubmitStudent(homeworkSubmission);
		log.debug(CF.SWB+"[insertSubmitStudent  insertSubmitStudent lastSubmissionNo]"+CF.RESET+ row); // map 디버깅
		// 파일이 들어왔다면 실행 
		if(homeworkForm.getHomeworkFileList().get(0).getSize() > 0 && row ==1)  {
			for(MultipartFile mf : homeworkForm.getHomeworkFileList()) {
				HomeworkFile homeworkFile = new HomeworkFile();
				String originName = mf.getOriginalFilename();
				
				// originName에서 마지막 .문자열 위치
				String ext = originName.substring(originName.lastIndexOf("."));
				
				// 파일을 저장할대 사용할 중복되지않는 새로운 이름 필요(UUID API사용)
				// filename = filename.replace("-","");
				String filename = UUID.randomUUID().toString();
				
				filename = filename + ext;
				
				homeworkFile.setHomeworkSubmissionNo(homeworkSubmission.getHomeworkSubmissionNo());  
				homeworkFile.setHomeworkFileName(filename);
				homeworkFile.setHomerworkFileOriginalName(originName);
				homeworkFile.setHomeworkFileOriginalType(mf.getContentType());
				homeworkFile.setHomeworkFileSize(mf.getSize());
				log.debug(CF.SWB+"[insertSubmitStudent  insertSubmitStudent homeworkFile]"+CF.RESET+ homeworkFile); // homeworkFile 디버깅
				lecHomeworkMapper.insertHomeworkFile(homeworkFile);
				try {
					mf.transferTo(new File(path+filename));
				} catch (Exception e) {
					e.printStackTrace();
					// 새로운 예외 발생시켜야지만 @Transactional 작동을 위해
					throw new RuntimeException(); // RuntimeException은 예외처리를 하지 않아도 컴파일된다
				}	
			}	
		}
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
