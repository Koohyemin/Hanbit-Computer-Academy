package Hanbit.co.kr.lms.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import Hanbit.co.kr.lms.service.MemberService;
import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MemberScheduler {
	@Autowired
	MemberService memberService;
	
	@Scheduled(cron = "10 01 23 * * *")		//정해진 시간대에 실행된다.

	public void modifyDormantMember() {
		//service에서 휴면계정 수정 메서드 호출
	
		 int row = memberService.sleepMember();
		 if( row > 1) {
		 log.debug(CF.LKL + "MemberScheduler" + CF.RESET + row); 
		 }
	}
	
}
