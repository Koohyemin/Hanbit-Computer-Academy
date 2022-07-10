package Hanbit.co.kr.lms.mapper;

import java.util.HashMap;
import java.util.List;

import javax.servlet.Registration;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.HomeworkFile;
import Hanbit.co.kr.lms.vo.HomeworkMake;
import Hanbit.co.kr.lms.vo.HomeworkSubmission;
import Hanbit.co.kr.lms.vo.LecPlan;

@Mapper
public interface LecHomeworkMapper {
	
	// 강사과제
	List<HashMap<String , Object>> homeworkList(LecPlan lecPlan); // 강사 과제리스트
	int insertHomework(HomeworkMake homeworkMake); // 강사 과제등록
	HomeworkMake selectHomeworkOne(int homeworkMakeNo); // 과제 하나 상세보기
	List<HashMap<String, Object>> homeworkSubmitList(int homeworkMakeNo); // 한 과제의 학생들 과제제출 리스트
	HomeworkSubmission selectSubmitOne(int homeworkSubmissionNo); // 한개의 과제제출
	List<HomeworkFile> selectSubmitFileList(int homeworkSubmissionNo); // 한개의 과제제출에 여러개의 파일리스트
	int updateScore(HomeworkSubmission hoemworkSubmission);
	int updateHomework(HomeworkMake homeworkMake);
	int deleteHomework(int homeworkMakeNo);
	List<HomeworkSubmission> selectSubmitNumber(int homeworkMakeNo);

	
	// 학생의 수강리스트
	List<Registration> lectureNameList(String studentId);
	
	// 학생과제
	List<HomeworkMake> studentHomeworkList(String studentId, String lectureName);
	int insertSubmitStudent(HomeworkSubmission homeworkSubmission); // 과제제출
	int insertHomeworkFile(HomeworkFile homeworkFile);
	// HashMap<String, Object> selectStudentSubmit(int homeworkSubmissionNo); // 학생이 낸 과제 제목,내용,파일이름 
	int deleteSubmitFile(int homeworkSubmissionNo); // 학생이 낸 과제파일 전체삭제
	int updateSubmit(HomeworkSubmission homeworkSubmission); // 학생 과제제출 업데이트
	List<HomeworkFile> selectFileNameList(int homeworkSubmissionNo); // 파일 이름들 출력(삭제시 필요)
	int deleteFileOne(int HomeworkFileNo); // 학생이 낸 과제파일 하나만 삭제
	int deleteSubmit(int homeworkSubmissionNo); // 학생이 낸 과제를 삭제
	HomeworkFile fileNameOne(int homeworkFilmNo);
}
