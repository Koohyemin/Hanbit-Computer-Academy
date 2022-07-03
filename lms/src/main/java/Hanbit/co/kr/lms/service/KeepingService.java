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
	
	//찜 추가
	public int addKeeping(Keeping keeping) {									
		
		log.debug(CF.LKL+"KeepingService.addKeeping.keeping"+CF.RESET +keeping);
		int row=keepingMapper.insertKeeping(keeping);
		 
		return row;
	}
	
	//찜 리스트 출력
	public List<Keeping> getKeeping(String studentId){							
		
		log.debug(CF.LKL+"KeepingService.getKeeping.studentId"+CF.RESET +studentId);
		
		List<Keeping> keepingList = keepingMapper.getKeepingByStudentId(studentId);
		
		log.debug(CF.LKL+"KeepingService.getKeeping.keepingList"+CF.RESET +keepingList);
		
		return keepingList;
	}
	
	//찜 삭제
	public int remvoeKeeping(Keeping keeping){									
		
		log.debug(CF.LKL+"KeepingService.remvoeKeeping.keeping"+CF.RESET +keeping);
		
		int row = keepingMapper.deleteKeeping(keeping);
		
		log.debug(CF.LKL+"KeepingService.remvoeKeeping.row"+CF.RESET +row);
		
		return row;
	}
	
	
	//찜 유효성 검사
	public boolean checkKeeping(Keeping keeping) {
		
		List<Keeping> checkKeepingList = keepingMapper.getKeepingByStudentId(keeping.getStudentId());
		

		
		for(int i=0; i<checkKeepingList.size(); i++) {						
			
			
			if(checkKeepingList.get(i).getLectureName().equals(keeping.getLectureName())){			// 찜 테이블에 있는 강의이름과 추가할 강의이름이 있는지 중복 조회  
				
				return false;																		// 있으면 false 리턴
			} 
			
		}

		
		return true;  																				//없으면 true 리턴
	}
}
