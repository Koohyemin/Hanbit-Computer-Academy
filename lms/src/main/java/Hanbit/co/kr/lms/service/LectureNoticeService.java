package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.LectureNoticeMapper;
import Hanbit.co.kr.lms.vo.LectureNotice;

@Service
@Transactional
public class LectureNoticeService {
	@Autowired LectureNoticeMapper lectureNoticeMapper;
	
	
	// 공지사항 리스트
		public Map<String, Object> getLecNoticeListByPage(int currentPage, int rowPerPage) {
			
			// 현재페이지를 이욯하여 시작페이지 계산
			int beginRow = (currentPage-1) * rowPerPage;
			
			// map으로 하나의 값으로 묶어주기
			Map<String, Object> map = new HashMap<>();
			map.put("beginRow", beginRow); // 시작 페이지
			map.put("rowPerPage", rowPerPage); // 한 페이지당 표시할 공지 개수	
			
			// SQL 매개값 대입
			List<LectureNotice> list = lectureNoticeMapper.getLecNoticeListByPage(map);
			int totalCount = lectureNoticeMapper.totalCount(null); // 전체 공지 개수
			
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
