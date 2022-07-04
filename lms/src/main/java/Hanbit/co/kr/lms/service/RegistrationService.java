package Hanbit.co.kr.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
