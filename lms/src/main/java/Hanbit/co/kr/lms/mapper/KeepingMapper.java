package Hanbit.co.kr.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.Keeping;

@Mapper
public interface KeepingMapper {
	//찜 추가
	int insertKeeping(Keeping keeping);
	
	//학생 찜 리스트 출력
	List<Keeping> getKeepingByStudentId(String studentId);
	
	//찜 삭제
	int deleteKeeping(Keeping keeping);
	
	//찜 유효성
	//List<Keeping> selectKeepingbyStudentId(Keeping keeping);
}
