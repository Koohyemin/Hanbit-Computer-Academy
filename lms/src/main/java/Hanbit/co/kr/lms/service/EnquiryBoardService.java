package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.EnquiryBoardMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.EnquiryAnswer;
import Hanbit.co.kr.lms.vo.EnquiryBoard;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class EnquiryBoardService {
	
	@Autowired EnquiryBoardMapper enquiryBoardMapper;
	
	//문의사항 리스트
	public Map<String, Object> selectEnquiryBoardListByPage(int currentPage, int rowPerPage, String category) {
		
		//컨트롤러에서 넘어온 값 가공 
		int startRow = (currentPage - 1)* rowPerPage;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowPerPage", rowPerPage);
		map.put("startRow", startRow);
		map.put("category", category);
		
		//map 디버깅
		log.debug( CF.KHN +"[EnquiryBoardService selectEnquiryBoardListByPage map]: "+ CF.RESET + map);
		
		//매퍼호출
		List<EnquiryBoard> list = enquiryBoardMapper.selectEnquiryBoardListByPage(map);
		
		//디버깅
		log.debug( CF.KHN +"[EnquiryBoardService selectEnquiryBoardListByPage list]: "+ CF.RESET+list);		
		
		//전체 행의 수
		int totalCount = enquiryBoardMapper.selectEnquiryBoardTotalCount(category);
		
		//마지막 페이지 계산
		int lastPage = (int)(Math.ceil((double)(totalCount) / (double)rowPerPage));
		
		//디버깅
		log.debug( CF.KHN +"[EnquiryBoardService selectEnquiryBoardListByPage lastPage]: "+ CF.RESET+lastPage);		
				
		
		//맵으로 묶어줌
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		
		//반환
		return returnMap;
	}
		
	// 문의사항 상세보기
	public EnquiryBoard selectEnquiryBoardOne(int enquiryBoardNo) {
		return enquiryBoardMapper.selectEnquiryBoardOne(enquiryBoardNo);
		}
	
	// 문의사항 입력하기
	public int insertEnquiryBoard(EnquiryBoard enquiryBoard) {
		return enquiryBoardMapper.insertEnquiryBoard(enquiryBoard);
	}
	
	// 문의사항 수정하기
	public int updateEnquiryBoard(EnquiryBoard enquiryBoard) {
		return enquiryBoardMapper.updateEnquiryBoard(enquiryBoard);
	}
	
	// 문의사항 삭제하기
	public int deleteEnquiryBoard(int enquiryBoardNo) {
		return enquiryBoardMapper.deleteEnquiryBoard(enquiryBoardNo);
	}
	
	// 문의사항 댓글 조회 하기
	public List<EnquiryAnswer> selectAnswerList(int enquiryBoardNo){
		List<EnquiryAnswer> list = enquiryBoardMapper.selectEnquiryAnswerList(enquiryBoardNo);
		log.debug(CF.KYJ+"[EnquiryBoardService selectAnswerList list]"+CF.RESET+ list);
		return list;
	}
	
	// 문의사항 댓글 하나 삭제하기
	public int deleteAnswer(int enquiryBoardAnswerNo) {
		int row = enquiryBoardMapper.deleteAnswer(enquiryBoardAnswerNo);
		
		if(row == 1) {
			log.debug(CF.KYJ+"[EnquiryBoardService deleteAnswer row]"+CF.RESET+ row+"삭제 되었습니다.");
		}
		return row;
	}
	
	// 문의사항 댓글 입력하기
	public int insertAnswer(Map<String,Object>map) {
		int row = enquiryBoardMapper.insertAnswer(map);
		if(row == 1) {
			log.debug(CF.KYJ+"[EnquiryBoardService insertAnswer row]"+CF.RESET+ row+"입력 되었습니다.");
		}
		return row;
	}
}



