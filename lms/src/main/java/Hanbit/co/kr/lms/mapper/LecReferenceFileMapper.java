package Hanbit.co.kr.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.LecReference;


@Mapper
public interface LecReferenceFileMapper {
List<LecReference> selectLecReferenceList(int LecReferenceId);
	
	int insertLecReferenceFile(LecReferenceFileMapper LecReferenceFile);
	
	int deleteLecReferenceFileList(int LecReferenceNo);
	
	int updateNoticefileone(int LecReferenceNo);
	
	List<String> selectLecReferenceFileNameList(int LecReferenceNo);
	
	String selectLecReferenceFileName(int LecReferenceNo);
}
