package Hanbit.co.kr.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Lec;

@Mapper
public interface LecMapper {
	List<Lec> getLecListByPage(Map<String,Object> map); // 개설강좌 목록(운영자, 강사 - 전체 / 학생 - 상태가 T인것만 조회)
	int totalCount(); // 강좌 상태별 상태, 총 개수
		
}
