package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.NoticeMapper;
import Hanbit.co.kr.lms.vo.ManagerNotice;

@Service
@Transactional
public class NoticeService {	
	
	@Autowired NoticeMapper noticeMapper; // NoticeMapper 객체 주입
	
	// 공지사항 삭제
	public int getDeleteNotice(int managerNoticeNo) {
		return noticeMapper.getDeleteNotice(managerNoticeNo);
	}
	
	// 공지사항 수정
	public int getUpdateNotic(ManagerNotice managerNotice) {
		return noticeMapper.getUpdateNotice(managerNotice);
	}
	
	// 공지사항 작성
	public int getInsertNotice(ManagerNotice managerNotice) {
		return noticeMapper.getInsertNotice(managerNotice); // 등록 성공 여부
	}
	
	// 공지사항 상세보기
	public ManagerNotice getNoticeOne(int managerNoticeNo) {
		return noticeMapper.getNoticeOne(managerNoticeNo);
	}
	
	// 공지사항 리스트
	public Map<String, Object> getNoticeListByPage(int currentPage, int rowPerPage, String category) {
		
		// 현재페이지를 이욯하여 시작페이지 계산
		int beginRow = (currentPage-1) * rowPerPage;
		
		// map으로 하나의 값으로 묶어주기
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow); // 시작 페이지
		map.put("rowPerPage", rowPerPage); // 한 페이지당 표시할 공지 개수	
		map.put("category", category); // 전체, 강사, 학생(카테고리)
		
		// SQL 매개값 대입
		List<ManagerNotice> list = noticeMapper.getNoticeListByPage(map);
		int totalCount = noticeMapper.totalCount(category); // 카테고리별(전체/학생/강사) 전체 공지 개수
		
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
