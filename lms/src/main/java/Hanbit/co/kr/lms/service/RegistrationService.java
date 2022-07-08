package Hanbit.co.kr.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.mapper.RegistrationMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Registration;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class RegistrationService {
	@Autowired RegistrationMapper registrationMapper;
	
	public int addRegistration(Registration registration) { 
		
		log.debug(CF.LKL+"RegistrationService.addRegistration.registration"+CF.RESET+registration);
		
		return registrationMapper.insertRegistration(registration);
	}
	public List<Registration> getRegistration(String studentId) { 
		
		log.debug(CF.LKL+"RegistrationService.getRegistration.studentId"+CF.RESET+studentId);
		
		return registrationMapper.selectRegistration(studentId);
	}
	public HashMap<String,Object> getRegistrationByStudent(Registration registration){
		
		log.debug(CF.LKL+"RegistrationService.selectRegistrationByStudent.studentId"+CF.RESET+registration);
		
		return registrationMapper.selectRegistrationByStudent(registration);
		
	}
	
	public int modifyPayment(Registration registration, int payMoney) {
		
		registration.setPayment( registration.getPayment() + payMoney);			//페이지에서 받은 금액과 기존의 금액을 더한다.
		
		int row =  registrationMapper.updatePayment(registration);
		
		return row;
	}
	
	public Map<String,Object> getRegistrationByLec(String lectureName) {
		
		List<Map<String,Object>> paymentlist = registrationMapper.selectRegistrationByLec(lectureName);			//페이지에서 받은 금액과 기존의 금액을 더한다.
		List<String> beforeLectureList = registrationMapper.beforeLecture();
		Map<String,Object> map = new HashMap<>();
		map.put("paymentlist", paymentlist);
		map.put("beforeLectureList", beforeLectureList);
		
		log.debug(CF.LKL+"RegistrationService.getRegistrationByLec.map"+CF.RESET+map);
		
		return map;
	}
	public int removeRegistration(Registration registration) {
		
		int row = registrationMapper.deleteRegistration(registration);
		
		log.debug(CF.LKL+"RegistrationService.removeRegistration.row"+CF.RESET+row);
		
		return row;
	}
}
