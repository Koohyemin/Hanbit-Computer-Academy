package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.EnquiryBoardMapper;
import Hanbit.co.kr.lms.util.CF;
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
		
		//맵으로 묶어줌
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		
		//반환
		return returnMap;
	}
		
	//상세보기
	public EnquiryBoard selectEnquiryBoardOne(int enquiryBoardNo) {
		return enquiryBoardMapper.selectEnquiryBoardOne(enquiryBoardNo);
		}
	
	//입력하기
	public int insertEnquiryBoard(EnquiryBoard enquiryBoard) {
		return enquiryBoardMapper.insertEnquiryBoard(enquiryBoard);
	}
	
	//수정하기
	public int updateEnquiryBoard(EnquiryBoard enquiryBoard) {
		return enquiryBoardMapper.updateEnquiryBoard(enquiryBoard);
	}
	}

