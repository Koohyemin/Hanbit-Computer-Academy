package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.NoticeMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.ManagerNotice;
import lombok.extern.slf4j.Slf4j;

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
		int beginRow = (currentPage-1) * rowPerPage; // 현재페이지를 이용하여 시작페이지 계산
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow); // 시작 페이지
		map.put("rowPerPage", rowPerPage); // 한 페이지당 표시할 공지 개수	
		map.put("category", category); // 전체, 강사, 학생리스트인지 확인
		List<ManagerNotice> list = noticeMapper.getNoticeListByPage(map);
		
		int totalCount = noticeMapper.totalCount(category); // 카테고리별(전체/학생/강사) 전체 공지 개수
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage)); // 마지막 페이지
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		return returnMap;
	}
}
