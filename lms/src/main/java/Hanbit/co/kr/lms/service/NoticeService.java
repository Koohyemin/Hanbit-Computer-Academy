package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.NoticeMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.ManagerNotice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class NoticeService {	
	@Autowired NoticeMapper noticeMapper; // NoticeMapper 객체 주입
	public Map<String, Object> getNoticeListByPage(int currentPage, int rowPerPage, String category) {
		int beginRow = (currentPage-1) * rowPerPage; // 현재페이지를 이용하여 시작페이지 계산
		log.debug( CF.KHM +"[NoticeService beginRow]: "+ beginRow + CF.RESET);
		
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow); // 시작 페이지
		map.put("rowPerPage", rowPerPage); // 한 페이지당 표시할 공지 개수	
		List<ManagerNotice> list = noticeMapper.getNoticeListByPage(map);
		
		int totalCount = noticeMapper.totalCount(category); // 카테고리별(전체/학생/강사) 전체 공지 개수
		log.debug( CF.KHM +"[NoticeService totalCount]: "+ totalCount + CF.RESET);
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage)); // 마지막 페이지
		log.debug( CF.KHM +"[NoticeService lastPage]: "+ lastPage + CF.RESET);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		return returnMap;
	}
}
