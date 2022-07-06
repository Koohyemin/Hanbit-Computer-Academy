package Hanbit.co.kr.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import Hanbit.co.kr.lms.vo.LecReference;


@Mapper
public interface LecReferenceFile {
List<LecReference> selectLecReferenceList(int LecReferenceId);
	
	int insertLecReferenceFile(LecReferenceFile LecReferenceFile);
	
	int deleteLecReferenceFileList(int LecReferenceNo);
	
	int updateNoticefileone(int LecReferenceNo);
	
	List<String> selectLecReferenceFileNameList(int LecReferenceNo);
	
	String selectLecReferenceFileName(int LecReferenceNo);
}
