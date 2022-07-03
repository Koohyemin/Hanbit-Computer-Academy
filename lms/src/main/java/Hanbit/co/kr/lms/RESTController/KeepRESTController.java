package Hanbit.co.kr.lms.RESTController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Hanbit.co.kr.lms.service.KeepingService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Keeping;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class KeepRESTController {
	
	@Autowired
	KeepingService keepingService;
	
	@GetMapping("/checkKeeping")
	public boolean checkKeeping(Keeping keeping){
		System.out.println("sadsasdasdadsasdadsadsasdasdasdadsads");
		
		
		//log.debug(CF.LKL+"KeepRESTController.checkKeeping.keeping" +CF.RESET +keeping);
		System.out.println(keeping);
		boolean check = keepingService.checkKeeping(keeping);
		
		log.debug(CF.LKL+"KeepRESTController.checkKeeping.check" + CF.RESET + check);
		
		return check;
			
	}
}
