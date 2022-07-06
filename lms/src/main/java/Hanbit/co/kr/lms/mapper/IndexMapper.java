package Hanbit.co.kr.lms.mapper;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.EnquiryBoard;
import Hanbit.co.kr.lms.vo.LecPlan;
import Hanbit.co.kr.lms.vo.ManagerNotice;
import Hanbit.co.kr.lms.vo.Registration;

@Mapper
public interface IndexMapper {
	//가입요청목록
	List<Map<String,Object>> selectIndexNonApprovalList();
	
	//강의계획서 비승인목록
	List <LecPlan> selectIndexLecPlanList();
	
	//문의사항 목록
	List<EnquiryBoard> selectIndexEnquiryBoardList();
	
	//전체 공지사항 목록
	List<ManagerNotice> selectIndexNoticeList();
	
	//강사에게 문의사항 목록
	List<EnquiryBoard> selectIndexStudentEnquiryBoardList(String category);
	
	//강사에 따른 담당강좌
	List<LecPlan> selectIndexTeacherLecList(String teacherId);
	//강사 공지사항 목록
	List<ManagerNotice> selectIndexTeacherNoticeList(ManagerNotice managerNotice);
	
	//학생이 수강중인 강좌
	List<Registration> selectIndexStudentLectureNameList(String studentId);
	
	//학생 공지사항 목록
	List<ManagerNotice> selectIndexStudentNoticeList(ManagerNotice managerNotice);
}
