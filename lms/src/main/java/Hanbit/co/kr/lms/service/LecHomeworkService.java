package Hanbit.co.kr.lms.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	// 학생이 과제에대한 삭제
	public void deleteSubmit(int homeworkSubmissionNo, String path) {
		
		// upload폴더에 있는 과제파일 삭제
		List<HomeworkFile> homeworkList = lecHomeworkMapper.selectFileNameList(homeworkSubmissionNo);
		log.debug(CF.SWB+"[insertSubmitStudent  updateSubmit homeworkList]"+CF.RESET+ homeworkList); // homeworkFile 디버깅
		for(int i=0; i<homeworkList.size(); i++) {
			File f = new File(path+homeworkList.get(i).getHomeworkFileName());
			if(f.exists()) {
					f.delete();
				}
		}
		
		// DB에서 과제파일에 관한 데이터 삭제
		lecHomeworkMapper.deleteSubmitFile(homeworkSubmissionNo);
		
		// DB에서 과제 삭제
		lecHomeworkMapper.deleteSubmit(homeworkSubmissionNo);
		
		return;
	}
	
	// 학생이 파일하나만 삭제
	public String deleteFileOne(int homeworkFileNo) {
		
		int row	= lecHomeworkMapper.deleteFileOne(homeworkFileNo);
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
	// 학생과제제출에 대한 점수 업데이트
	public void updateScore(HomeworkSubmission homeworkSubmission) {
		
		lecHomeworkMapper.updateScore(homeworkSubmission);
		return;
	}
	// 강사가 보는 학생 과제리스트
	public HashMap<String, Object> studnetSubmitOne(int homeworkSubmissionNo){
		HashMap<String, Object> returnMap = new HashMap<>();
		
		// 파일과 과제상세보기
		HomeworkSubmission homeworkSubmission = lecHomeworkMapper.selectSubmitOne(homeworkSubmissionNo);
		List<HomeworkFile> homeworkFileNameList = lecHomeworkMapper.selectFileNameList(homeworkSubmissionNo);
		List<HomeworkFile> homeworkFileList = lecHomeworkMapper.selectSubmitFileList(homeworkSubmissionNo);
		
		log.debug(CF.SWB+"[LecHomeworkService  studnetSubmitOne homeworkSubmission]"+CF.RESET+ homeworkSubmission); // homeworkSubmission 디버깅
		log.debug(CF.SWB+"[LecHomeworkService  studnetSubmitOne homeworkFileNameList]"+CF.RESET+ homeworkFileNameList); // homeworkFileNameList 디버깅
		log.debug(CF.SWB+"[LecHomeworkService  studnetSubmitOne homeworkFileList]"+CF.RESET+ homeworkFileList); // homeworkFileList 디버깅
		
		returnMap.put("homeworkSubmission", homeworkSubmission);
		returnMap.put("homeworkFileNameList", homeworkFileNameList);
		returnMap.put("homeworkFileList", homeworkFileList);
		return returnMap;
	}
	
	// 학생 과제업데이트
	public void updateSubmit(HomeworkForm homeworkForm,String path,int homeworkSubmissionNo) {
		
		// 변경된 데이터를 가지고 옴
		HomeworkSubmission homeworkSubmission = new HomeworkSubmission();
		homeworkSubmission.setHomeworkSubmissionTitle(homeworkForm.getHomeworkSubmissionTitle());
		homeworkSubmission.setHomeworkSubmissionContent(homeworkForm.getHomeworkSubmissionContent());
		homeworkSubmission.setHomeworkSubmissionNo(homeworkSubmissionNo);
		
		// 과제 게시글 업데이트
		int row = lecHomeworkMapper.updateSubmit(homeworkSubmission);
		log.debug(CF.SWB+"[LecHomeworkService  insertSubmitStudent row]"+CF.RESET+ row); // map 디버깅
		log.debug(CF.SWB+"[LecHomeworkService  insertSubmitStudent homeworkForm]"+CF.RESET+ homeworkForm);
		
		// 과제 파일 업로드 부분 
		if(homeworkForm.getHomeworkFileList() != null  && row ==1)  {
			for(MultipartFile mf : homeworkForm.getHomeworkFileList()) {
				HomeworkFile homeworkFile = new HomeworkFile();
				String originName = mf.getOriginalFilename();
				
				// originName에서 마지막 .문자열 위치
				String ext = originName.substring(originName.lastIndexOf("."));
				
				// 파일을 저장할대 사용할 중복되지않는 새로운 이름 필요(UUID API사용)
				// filename = filename.replace("-","");
				String filename = UUID.randomUUID().toString();
				
				filename = filename + ext;
				
				homeworkFile.setHomeworkSubmissionNo(homeworkSubmissionNo);  
				homeworkFile.setHomeworkFileName(filename);
				homeworkFile.setHomerworkFileOriginalName(originName);
				homeworkFile.setHomeworkFileOriginalType(mf.getContentType());
				homeworkFile.setHomeworkFileSize(mf.getSize());
				log.debug(CF.SWB+"[insertSubmitStudent  updateSubmit homeworkFile]"+CF.RESET+ homeworkFile); // homeworkFile 디버깅
				
				/*
				// 과제이름 출력후 upload에 등록된 같은 이름데이터를 삭제
				List<HomeworkFile> homeworkList = lecHomeworkMapper.selectFileNameList(homeworkSubmissionNo);
				log.debug(CF.SWB+"[insertSubmitStudent  updateSubmit homeworkList]"+CF.RESET+ homeworkList); // homeworkFile 디버깅
				for(int i=0; i<homeworkList.size(); i++) {
					File f = new File(path+homeworkList.get(i).getHomeworkFileName());
					if(f.exists()) {
							f.delete();
						}
				}
				
				lecHomeworkMapper.deleteSubmitFile(homeworkSubmissionNo);
				*/
				
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
	
	// 학생 제출한 과제리스트
	//public HashMap<String, Object> selectSubmit(int homeworksubmissionNo){
	//	return lecHomeworkMapper.selectStudentSubmit(homeworksubmissionNo);
	//}
	
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
		if(homeworkForm.getHomeworkFileList() != null && row ==1)  {
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
		if(homeworkMake.size() >0 ) {
			for(int i=0;i<homeworkMake.size();i++) {
				
				String deadLine = homeworkMake.get(i).getHomeworkDeadline();
				
				// 오늘날짜 yyyy-MM-dd로 생성
	            String todayfm = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
	            log.debug(CF.SWB+"[InformationService teacherOne todayfm]"+CF.RESET+ todayfm);
	            
	            // yyyy-MM-dd 포맷 설정
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            log.debug(CF.SWB+"[InformationService teacherOne dateFormat]"+CF.RESET+ dateFormat);
	            
	            // date 변수 선언
	            Date date1 = null;
	            Date today = null;
	            
	            // 예외처리
	            try {
	               date1 = dateFormat.parse(deadLine);
	               today = dateFormat.parse(todayfm);
	            } catch (ParseException e) {
	               e.printStackTrace();
	            }
				
	            // 오늘날짜에 따른 분기
	            if(date1.getTime() < today.getTime()) { // 오늘날짜가 개강시작일과 종강날짜에 있다면 수업진행중
	            	homeworkMake.get(i).setCheckDeadLine(0);
	             } else if(date1.getTime() >= today.getTime()){ // 오늘날짜가 개강일보다 전이라면
	            	 homeworkMake.get(i).setCheckDeadLine(1);
	             }
			}
		}
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
