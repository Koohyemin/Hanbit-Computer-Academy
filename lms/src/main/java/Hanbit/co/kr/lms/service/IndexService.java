package Hanbit.co.kr.lms.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Hanbit.co.kr.lms.mapper.IndexMapper;
import Hanbit.co.kr.lms.vo.EnquiryBoard;
import Hanbit.co.kr.lms.vo.LecPlan;
import Hanbit.co.kr.lms.vo.ManagerNotice;
import lombok.extern.slf4j.Slf4j;

@Service
public class IndexService {
	
	@Autowired IndexMapper indexMapper;
	
	//가입요청 목록
	public List<Map<String,Object>> selectIndexNonApprovalList() {
		List<Map<String,Object>> waitingList = indexMapper.selectIndexNonApprovalList();
		return waitingList;
	}
	
	//강의계획서 요청목록
	public List<LecPlan> selectIndexLecList(LecPlan lecPlan){
		return indexMapper.selectIndexLecList(lecPlan);
	}
	
	//운영자에게 문의목록
	public List<EnquiryBoard> selectIndexEnquiryBoardList(EnquiryBoard enquiryBoard) {
		return indexMapper.selectIndexEnquiryBoardList(enquiryBoard);
	}
	
	//전체공지사항
	public List<ManagerNotice> selectIndexNoticeList(ManagerNotice managerNotice) {
		return indexMapper.selectIndexNoticeList(managerNotice);
	}
	
	
}
