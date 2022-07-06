package Hanbit.co.kr.lms.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.IndexMapper;
import Hanbit.co.kr.lms.vo.EnquiryBoard;
import Hanbit.co.kr.lms.vo.LecPlan;
import Hanbit.co.kr.lms.vo.ManagerNotice;
import Hanbit.co.kr.lms.vo.Registration;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j

public class IndexService {
	
	@Autowired IndexMapper indexMapper;
	
	//가입요청 목록
	public List<Map<String, Object>> selectIndexNonApprovalList() {
		List<Map<String, Object>> waitingList = indexMapper.selectIndexNonApprovalList();
		return waitingList;
	}
	
	//강의계획서 목록
	public List<LecPlan> selectIndexLecPlanList() {
		return indexMapper.selectIndexLecPlanList();
	}
	
	//문의사항 목록
	public List<EnquiryBoard> selectIndexEnquiryBoardList() {
		return indexMapper.selectIndexEnquiryBoardList();
	}
	
	//전체 공지사항 목록
	public List<ManagerNotice> selectIndexNoticeList() {
		return indexMapper.selectIndexNoticeList();
	}
	
	//강사에 따른 담당강좌
	
	//강사 공지사항 목록
	public List<ManagerNotice> selectIndexTeacherNoticeList(ManagerNotice managerNotice) {
		return indexMapper.selectIndexTeacherNoticeList(managerNotice);
	}
	
	
	//학생이 수강중인 강의
	public List<Registration> selectIndexStudentLectureNameList(Registration registration) {
		return indexMapper.selectIndexStudentLectureNameList(registration);
	}
	
	//학생 공지사항 목록
	public List<ManagerNotice> selectIndexStudentNoticeList(ManagerNotice managerNotice) {
		return indexMapper.selectIndexStudentNoticeList(managerNotice);
	}
}
