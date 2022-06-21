package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.ManagerNotice;

@Mapper
public interface NoticeMapper {
	// 운영자 공지사항(전체/학생/강사)
	List<ManagerNotice> getNoticeListByPage(Map<String, Object> map); // 운영자 공지사항 목록(페이징 포함)
	int totalCount(String category); // 카테고리별(전체/학생/강사) 공지 개수
	ManagerNotice getNoticeOne(int managerNoticeNo); // 공지사항 상세보기
}
