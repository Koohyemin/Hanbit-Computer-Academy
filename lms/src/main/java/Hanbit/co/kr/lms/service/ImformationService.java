package Hanbit.co.kr.lms.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.Registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import Hanbit.co.kr.lms.mapper.ImformationMapper;
import Hanbit.co.kr.lms.mapper.MemberMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Certification;
import Hanbit.co.kr.lms.vo.Lec;
import Hanbit.co.kr.lms.vo.Manager;
import Hanbit.co.kr.lms.vo.PasswordUpdateDate;
import Hanbit.co.kr.lms.vo.PhotoFile;
import Hanbit.co.kr.lms.vo.Student;
import Hanbit.co.kr.lms.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ImformationService {
	@Autowired ImformationMapper imformationMapper;
	@Autowired MemberMapper memberMapper;
	
	// 비밀번호 연장하기
	public void updatePw90(String memberId, int memberLv) {
		
		// 레벨에 따른 분기
		if(memberLv == 1) { // 학생이라면
			
			// 현재 사용중인 비밀번호로 현재날짜로 insert 해주기
			int row = imformationMapper.studentLogInsertPw(memberId);
			log.debug(CF.SWB+"[ImformationService updatePw90 row]"+CF.RESET+ row); // row 디버깅
			
		} else if(memberLv == 2) { // 강사라면
			
			// 현재 사용중인 비밀번호로 현재날짜로 insert 해주기
			int row2 = imformationMapper.teacherLogInsertPw(memberId);
			log.debug(CF.SWB+"[ImformationService updatePw90 row2]"+CF.RESET+ row2); // row2 디버깅
	
		} else if(memberLv == 3) { // 운영진이라면
			
			// 현재 사용중인 비밀번호로 현재날짜로 insert 해주기
			int row3 = imformationMapper.managerLogInsertPw(memberId);
			
			log.debug(CF.SWB+"[ImformationService updatePw90 row3]"+CF.RESET+ row3); // row3 디버깅
		}
		
		return;
	}
	
	// 비밀번호 비교후 변경
	public String modifyPw(String memberId, String memberPw, String updatePw, String checkPw,int memberLv) {
		
		// 맴퍼에 두개의 값을 쓰기위해 map으로 묶음
		HashMap<String , Object> map = new HashMap<>();
		map.put("memberId",memberId);
		map.put("memberPw",memberPw);
		
		// 디버깅
		int row1 = imformationMapper.selectCurrentPw(map);
		log.debug(CF.SWB+"[ImformationService modifyPw row1]"+CF.RESET+ row1); // row1 디버깅
		
		// vo.PasswordUpdateDate 등록
		PasswordUpdateDate passwordUpdateDate = new PasswordUpdateDate();
		passwordUpdateDate.setMemberId(memberId);
		passwordUpdateDate.setMemberPw(updatePw);
		
		String error = null; // error 변수 등록
		
		// 만약 row가 한개이상이라면 현재 비밀번호가 맞다
		if(row1 > 0) {
			int row2 = imformationMapper.selectPwList(passwordUpdateDate);
			log.debug(CF.SWB+"[ImformationService modifyPw row2]"+CF.RESET+ row2); // row2 디버깅
			
			// 설정한 개수만큼 이전 개수까지 비밀번호가 없으면 비밀번호 변경해주기
			if(row2 == 0) {
				if(memberLv ==  1) { // 학생이라면
					memberMapper.updateActiveStudent(passwordUpdateDate);
					memberMapper.updateActivePasswordUpdateDate(passwordUpdateDate);
				} else if(memberLv == 2) { // 강사라면
					memberMapper.updateActiveManager(passwordUpdateDate);
					memberMapper.updateActivePasswordUpdateDate(passwordUpdateDate);
				} else if(memberLv == 3) {
					memberMapper.updateActiveManager(passwordUpdateDate);
					memberMapper.updateActivePasswordUpdateDate(passwordUpdateDate);
				}
			} else {
				error = "이전에 사용했던 비밀번호입니다";
			}
		} else {
			error = "The current password is different";
		}
		log.debug(CF.SWB+"[ImformationService modifyPw error]"+CF.RESET+ error); // error 디버깅
		return error;
	}
	// 사진 업로드
	public void updatePhoto(String path,String memberId,PhotoFile photoFile) {
		if(photoFile.getPhotoFile() != null) {
			MultipartFile mf = photoFile.getPhotoFile();
			String originName = mf.getOriginalFilename();
			
			// originName에서 마지막 .문자열 위치
			String ext = originName.substring(originName.lastIndexOf("."));
			
			// 파일을 저장할대 사용할 중복되지않는 새로운 이름 필요(UUID API사용)
			// filename = filename.replace("-","");
			String filename = UUID.randomUUID().toString();

			filename = filename + ext;
			
			photoFile.setMemberId(memberId);
			photoFile.setPhotoName(filename);
			photoFile.setPhotoOriginalName(originName);
			photoFile.setPhotoType(mf.getContentType());
			photoFile.setPhotoSize(mf.getSize());
			log.debug(CF.SWB+"[ImformationService updatePhoto photoFile.photoFile]"+CF.RESET+ photoFile.toString()); // photoFile 디버깅
			
			// 원래 가지고 있는 photoName -> upload에 있는 사진 삭제할려고 찾음
			String prePhotoName = imformationMapper.selectPhotoName(memberId);
			log.debug(CF.SWB+"[ImformationService updatePhoto prePhotoName]"+CF.RESET+prePhotoName); // prePhotoName 디버깅
			
			// 파일이 존재한다면 삭제
			File f = new File(path+prePhotoName);
			log.debug(CF.SWB+"[ImformationService updatePhoto f]"+CF.RESET+f); // f 디버깅
			if(f.exists()) {
				f.delete();
			}
			
			// 파일 업로드
			imformationMapper.updatePhoto(photoFile);
			try {
				mf.transferTo(new File(path+filename));
			} catch (Exception e) {
				e.printStackTrace();
				// 새로운 예외 발생시켜야지만 @Transactional 작동을 위해
				throw new RuntimeException(); // RuntimeException은 예외처리를 하지 않아도 컴파일된다
			}		
		}
	}
	
	// 운영진 업데이트
	public void updateManager(Manager manager) {
		
		// service에서 mapper로 값 넘겨주기
		imformationMapper.updateManager(manager);
		return;
	}
	// 강사 업데이트
	public void updateTeacher(Teacher teacher) {
		
		// service에서 mapper로 값 넘겨주기
		imformationMapper.updateTeacher(teacher);
		return;
	}
	// 학생 업데이트
	public void updateStudent(Student student) {
		
		// service에서 mapper로 값 넘겨주기
		imformationMapper.updateStudent(student);
		return;
	}
	
	// 나의 정보(학생개인정+자격증리스트+수강내용+사진)
	public Map<String, Object> studentOne(String studentId) {
		
		// 한 학생의 상세보기
		Student student = new Student();
		student = imformationMapper.selectStudentOne(studentId);
		log.debug(CF.SWB+"[ImformationService studentOne student]"+ student+CF.RESET); // student 디버깅
		
		// 한 학생의 자격증 리스트
		List<Certification> certificationList = imformationMapper.selectStudentCertification(studentId);
		log.debug(CF.SWB+"[ImformationService studentOne certificationList.size]"+ certificationList.size()+CF.RESET); // certificationList.size 디버깅
		
		// 한 학생의 수강리스트
		List<Lec> lecList = imformationMapper.selectLecList(studentId);
		log.debug(CF.SWB+"[ImformationService studentOne lecList]"+ lecList+CF.RESET); // lecList 디버깅
		log.debug(CF.SWB+"[ImformationService studentOne lecList.size]"+ lecList.size()+CF.RESET);
		
		// 한 학생의 사진
		PhotoFile photoFile= imformationMapper.selectStudentPhoto(studentId);
		log.debug(CF.SWB+"[ImformationService studentOne photoFile]"+ photoFile+CF.RESET); // photo 디버깅
		
		// 4개를 묶어서 contoller에 보내주기
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("student", student);
		returnMap.put("certificationList", certificationList);
		returnMap.put("lecList", lecList);
		returnMap.put("photoFile", photoFile);
		return returnMap;
	}
	// 나의 정보(선생개인정보+자격증리스트+수강내용+사진+(강좌-수강))
	public Map<String, Object> teacherOne(String teacherId) {

		// 한 강사의 자격증
		Teacher teacher = imformationMapper.selectTeacherOne(teacherId);
		log.debug(CF.SWB+"[ImformationService teacherOne teacher]"+ teacher.toString()+CF.RESET); // teacher 디버깅
		
		// 한 강사의 자격증
		List<Certification> certificationList = imformationMapper.selectTeacherCertification(teacherId);
		log.debug(CF.SWB+"[ImformationService teacherOne certificationList]"+ certificationList+CF.RESET); // certificationList 디버깅
		
		// 한 강사의 수강목록
		List<Registration> registrationList = imformationMapper.selectRegistrationList(teacherId);
		log.debug(CF.SWB+"[ImformationService teacherOne registrationList]"+ registrationList.toString()+CF.RESET); // registrationList 디버깅
		
		// 한 강사의 사진
		PhotoFile photoFile = imformationMapper.selectTeacherPhoto(teacherId);
		log.debug(CF.SWB+"[ImformationService teacherOne photoFile]"+ photoFile+CF.RESET); // photoFile디버깅
		
		// 한 강가의 강의-수강
		List<Lec> lecList = imformationMapper.selectTeacherLecList(teacherId);
		log.debug(CF.SWB+"[ImformationService teacherOne lecList]"+ lecList+CF.RESET); // 강의 디버깅
		
		
		// 5개를 묶어서 contoller에 보내주기
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("teacher", teacher);
		returnMap.put("certificationList", certificationList);
		returnMap.put("registrationList", registrationList);
		returnMap.put("lecList", lecList);
		returnMap.put("photoFile", photoFile);
		return returnMap;
	}
	
	// 나의 정보(운영진개인정보+사진)
	public Map<String, Object> managerOne(String managerId) {
		
		// 운영진 개인정보
		Manager manager = imformationMapper.selectManagerOne(managerId);
		log.debug(CF.SWB+"[ImformationService managerOne manager]"+ manager.toString()+CF.RESET); // manager 디버깅
		
		// 운영진 사진
		PhotoFile photoFile = imformationMapper.selectManagerPhoto(managerId);
		log.debug(CF.SWB+"[ImformationService managerOne photoFile]"+ photoFile.toString()+CF.RESET); // photo 디버깅
		
		
		// 2개를 묶어서 contoller에 보내주기
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("manager", manager);
		returnMap.put("photoFile", photoFile);
		return returnMap;
	}
}
