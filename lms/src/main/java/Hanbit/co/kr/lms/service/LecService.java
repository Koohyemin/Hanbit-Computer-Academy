package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.LecMapper;
import Hanbit.co.kr.lms.vo.Lec;

@Service
@Transactional
public class LecService {
	
	@Autowired LecMapper lecMapper;
	
	// 강의 리스트
	public Map<String, Object> getLecListByPage(int currentPage, int rowPerPage) {
		
		// 현재페이지를 이욯하여 시작페이지 계산
		int beginRow = (currentPage-1) * rowPerPage;
		
		// map으로 하나의 값으로 묶어주기
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow); // 시작 페이지
		map.put("rowPerPage", rowPerPage); // 한 페이지당 표시할 강의 개수	
		
		// SQL 매개값 대입
		List<Lec> list = lecMapper.getLecListByPage(map);
		int totalCount = lecMapper.totalCount();
		
		// 마지막 페이지
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		
		// return값 하나의 값으로 묶어주기
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		
		// returnMap 반환
		return returnMap;
	}
}
