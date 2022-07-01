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
	public String checkKeeping(@RequestParam(name="studentId") String studentId
								,@RequestParam(name="lectureName") String lectureName){

		Keeping keeping = null;
		keeping.setLectureName(lectureName);
		keeping.setStudentId(studentId);
		
		log.debug(CF.LKL+"KeepRESTController.checkKeeping.keeping" +CF.LKL +keeping);
		
		String check = keepingService.checkKeeping(keeping);
		
		log.debug(CF.LKL+"KeepRESTController.checkKeeping.check" + CF.LKL + check);
		
		return check;
			
	}
}
