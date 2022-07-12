package Hanbit.co.kr.lms.RESTController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Hanbit.co.kr.lms.service.RegistrationService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Registration;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class PaymentRESTController {
	@Autowired RegistrationService registrationService;
	
	@GetMapping("/RegipaymentList")
	public List<Map<String,Object>> RegipaymentList(@RequestParam(name="lectureName", defaultValue="") String lectureName) {
		
		log.debug(CF.LKL+"PaymentRESTController.RegipaymentList.lectureName "+CF.RESET+lectureName);
		
		Map<String,Object> map=registrationService.getRegistrationByLec(lectureName);
		
		log.debug(CF.LKL+"PaymentRESTController.RegipaymentList.map "+CF.RESET+map);
		
		List<Map<String,Object>> list =  (List<Map<String, Object>>) map.get("paymentlist");
		
		log.debug(CF.LKL+"PaymentRESTController.RegipaymentList.list "+CF.RESET+list);
		
		return list;
		
	}
	
}
