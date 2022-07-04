package Hanbit.co.kr.lms.mapper;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.EnquiryBoard;
import Hanbit.co.kr.lms.vo.LecPlan;
import Hanbit.co.kr.lms.vo.ManagerNotice;

@Mapper
public interface IndexMapper {
	//가입요청목록
	List<Map<String,Object>> selectIndexNonApprovalList();
	
	//강의계획서 승인요청목록
	List<LecPlan> selectIndexLecList(LecPlan lecplan);
	
	//to운영진 문의목록
	List<EnquiryBoard> selectIndexEnquiryBoardList(EnquiryBoard enquiryBoard);
	
	//전체 공지사항 목록
	List<ManagerNotice> selectIndexNoticeList(ManagerNotice managerNotice); 

}
