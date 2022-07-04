package Hanbit.co.kr.lms.RESTController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import Hanbit.co.kr.lms.service.UserFindService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.PasswordUpdateDate;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserFindRESTController {
	
	@Autowired UserFindService userFindService;
	//직전 비밀번호 비교
		@GetMapping("/user/selectBeforePassword")
		   public boolean getBeforePassword(Model model, PasswordUpdateDate passwordUpdateDate) {
			
			  //서비스 호출
		      int pud = userFindService.selectBeforePassword(passwordUpdateDate);
		      log.debug( CF.KHN +"[----------------------------------------- ]:  "+ CF.RESET);

		      //값 디버깅
		      log.debug( CF.KHN +"[UserFindRESTController @GetMapping getBeforePassword pud ]:  "+ CF.RESET+ pud);
		      
		      //중복비교 성공 실패 디버깅
				boolean ck=false;

		      if(pud == 0) {
					log.debug( CF.KHN +"[UserFindRESTController @GetMapping getBeforePassword 중복아님 ]:  "+ CF.RESET);
					ck=true;
		      } else {
					log.debug( CF.KHN +"[UserFindRESTController @GetMapping getBeforePassword 중복 ]:  "+ CF.RESET);
					ck=false;
		      }
		      log.debug( CF.KHN +"[UserFindRESTController @GetMapping getBeforePassword ck ]:  "+ CF.RESET+ ck);

		      return ck;
		   }
}
