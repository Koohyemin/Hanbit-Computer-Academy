package Hanbit.co.kr.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Hanbit.co.kr.lms.mapper.KeepingMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Keeping;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@Service
public class KeepingService {
	@Autowired
	KeepingMapper keepingMapper;
	
	public int addKeeping(Keeping keeping) {
		
		log.debug(CF.LKL+"KeepingService.addKeeping.keeping"+CF.RESET +keeping);
		int row=keepingMapper.insertKeeping(keeping);
		 
		return row;
	}
	
	public List<Keeping> getKeeping(String studentId){
		
		log.debug(CF.LKL+"KeepingService.getKeeping.studentId"+CF.RESET +studentId);
		
		List<Keeping> keepingList = keepingMapper.getKeepingByStudentId(studentId);
		
		log.debug(CF.LKL+"KeepingService.getKeeping.keepingList"+CF.RESET +keepingList);
		
		return keepingList;
	}
	
	public int remvoeKeeping(Keeping keeping){
		
		log.debug(CF.LKL+"KeepingService.remvoeKeeping.keeping"+CF.RESET +keeping);
		
		int row = keepingMapper.deleteKeeping(keeping);
		
		log.debug(CF.LKL+"KeepingService.remvoeKeeping.row"+CF.RESET +row);
		
		return row;
	}
}
