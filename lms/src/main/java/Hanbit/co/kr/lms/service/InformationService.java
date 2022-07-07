package Hanbit.co.kr.lms.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import Hanbit.co.kr.lms.mapper.InformationMapper;
import Hanbit.co.kr.lms.mapper.MemberMapper;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Certification;
import Hanbit.co.kr.lms.vo.Lec;
import Hanbit.co.kr.lms.vo.Manager;
import Hanbit.co.kr.lms.vo.PasswordUpdateDate;
import Hanbit.co.kr.lms.vo.PhotoFile;
import Hanbit.co.kr.lms.vo.Student;
import Hanbit.co.kr.lms.vo.Teacher;
import Hanbit.co.kr.lms.vo.TimeTable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class InformationService {
   @Autowired InformationMapper informationMapper;
   @Autowired MemberMapper memberMapper;
   
   // 비밀번호 연장하기
   public void updatePw90(String memberId, int memberLv) {
      
      // 레벨에 따른 분기
      if(memberLv == 1) { // 학생이라면
         
         // 현재 사용중인 비밀번호로 현재날짜로 insert 해주기
         int row = informationMapper.studentLogInsertPw(memberId);
         log.debug(CF.SWB+"[InformationService updatePw90 row]"+CF.RESET+ row); // row 디버깅
         
      } else if(memberLv == 2) { // 강사라면
         
         // 현재 사용중인 비밀번호로 현재날짜로 insert 해주기
         int row2 = informationMapper.teacherLogInsertPw(memberId);
         log.debug(CF.SWB+"[InformationService updatePw90 row2]"+CF.RESET+ row2); // row2 디버깅
   
      } else if(memberLv == 3) { // 운영진이라면
         
         // 현재 사용중인 비밀번호로 현재날짜로 insert 해주기
         int row3 = informationMapper.managerLogInsertPw(memberId);
         
         log.debug(CF.SWB+"[InformationService updatePw90 row3]"+CF.RESET+ row3); // row3 디버깅
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
      int row1 = informationMapper.selectCurrentPw(map);
      log.debug(CF.SWB+"[InformationService modifyPw row1]"+CF.RESET+ row1); // row1 디버깅
      
      // vo.PasswordUpdateDate 등록
      PasswordUpdateDate passwordUpdateDate = new PasswordUpdateDate();
      passwordUpdateDate.setMemberId(memberId);
      passwordUpdateDate.setMemberPw(updatePw);
      
      String error = null; // error 변수 등록
      
      // 만약 row가 한개이상이라면 현재 비밀번호가 맞다
      if(row1 > 0) {
         int row2 = informationMapper.selectPwList(passwordUpdateDate);
         log.debug(CF.SWB+"[InformationService modifyPw row2]"+CF.RESET+ row2); // row2 디버깅
         
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
      log.debug(CF.SWB+"[InformationService modifyPw error]"+CF.RESET+ error); // error 디버깅
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
         log.debug(CF.SWB+"[InformationService updatePhoto photoFile.photoFile]"+CF.RESET+ photoFile.toString()); // photoFile 디버깅
         
         // 원래 가지고 있는 photoName -> upload에 있는 사진 삭제할려고 찾음
         String prePhotoName = informationMapper.selectPhotoName(memberId);
         log.debug(CF.SWB+"[InformationService updatePhoto prePhotoName]"+CF.RESET+prePhotoName); // prePhotoName 디버깅
         log.debug(CF.SWB+"[InformationService updatePhoto path]"+CF.RESET+path);
         // 파일이 존재한다면 삭제
         File f = new File(path+prePhotoName);
         log.debug(CF.SWB+"[InformationService updatePhoto f]"+CF.RESET+f); // f 디버깅
         if(f.exists() && !"defaultProfile.jpg".equals(prePhotoName)) {
            f.delete();
         }
         
         // 파일 업로드
         informationMapper.updatePhoto(photoFile);
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
	   informationMapper.updateManager(manager);
      return;
   }
   // 강사 업데이트
   public void updateTeacher(Teacher teacher) {
      
      // service에서 mapper로 값 넘겨주기
	   informationMapper.updateTeacher(teacher);
      return;
   }
   // 학생 업데이트
   public void updateStudent(Student student) {
      
      // service에서 mapper로 값 넘겨주기
	   informationMapper.updateStudent(student);
      return;
   }
   
   // 나의 정보(학생개인정+자격증리스트+수강내용+사진)
   public Map<String, Object> studentOne(String studentId) {
      
      // 한 학생의 상세보기
      Student student = new Student();
      student = informationMapper.selectStudentOne(studentId);
      log.debug(CF.SWB+"[InformationService studentOne student]"+ student+CF.RESET); // student 디버깅
      
      // 한 학생의 자격증 리스트
      List<Certification> certificationList = informationMapper.selectStudentCertification(studentId);
      log.debug(CF.SWB+"[InformationService studentOne certificationList.size]"+ certificationList.size()+CF.RESET); // certificationList.size 디버깅
      
      // 한 학생의 수강리스트
      List<Lec> lecList = informationMapper.selectLecList(studentId);
      log.debug(CF.SWB+"[InformationService studentOne lecList]"+ lecList+CF.RESET); // lecList 디버깅
      log.debug(CF.SWB+"[InformationService studentOne lecList.size]"+ lecList.size()+CF.RESET);
      
      // 한 학생의 사진
      PhotoFile photoFile= informationMapper.selectStudentPhoto(studentId);
      log.debug(CF.SWB+"[InformationService studentOne photoFile]"+ photoFile+CF.RESET); // photo 디버깅
      
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
      Teacher teacher = informationMapper.selectTeacherOne(teacherId);
      log.debug(CF.SWB+"[InformationService teacherOne teacher]"+ teacher.toString()+CF.RESET); // teacher 디버깅
      
      // 한 강사의 자격증
      List<Certification> certificationList = informationMapper.selectTeacherCertification(teacherId);
      log.debug(CF.SWB+"[InformationService teacherOne certificationList]"+ certificationList+CF.RESET); // certificationList 디버깅
      
      // 한 강사의 수강시간
      List<TimeTable> lecTimeList = informationMapper.selectLecTime(teacherId);
      log.debug(CF.SWB+"[InformationService teacherOne lecTime]"+CF.RESET+ lecTimeList.toString()); // lecTimeList 디버깅
      
      // 날짜 비교하기
      if(lecTimeList.size() > 0) {
         for(int i= 0; i<lecTimeList.size();i++) {
            // 변수등록
            String startDate = lecTimeList.get(i).getBeginClass();
            String endDate = lecTimeList.get(i).getEndClass();
            
            // 오늘날짜 yyyy-MM-dd로 생성
            String todayfm = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
            log.debug(CF.SWB+"[InformationService teacherOne todayfm]"+CF.RESET+ todayfm);
            
            // yyyy-MM-dd 포맷 설정
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            log.debug(CF.SWB+"[InformationService teacherOne dateFormat]"+CF.RESET+ dateFormat);
            
            // date 변수 선언
            Date date1 = null;
            Date date2 = null;
            Date today = null;
            
            // 예외처리
            try {
               date1 = dateFormat.parse(startDate);
               date2 = dateFormat.parse(endDate);
               today = dateFormat.parse(todayfm);
            } catch (ParseException e) {
               e.printStackTrace();
            }
            
            // 오늘날짜에 따른 분기
            if(date1.getTime() <= today.getTime() && today.getTime() <= date2.getTime()) { // 오늘날짜가 개강시작일과 종강날짜에 있다면 수업진행중
               lecTimeList.get(i).setCheckLec(1);
               log.debug(CF.SWB+"[InformationService teacherOne i checkLec]"+CF.RESET +i+ lecTimeList.get(i).getCheckLec());
            } else if(date1.getTime() > today.getTime()){ // 오늘날짜가 개강일보다 전이라면
               lecTimeList.get(i).setCheckLec(0);
               log.debug(CF.SWB+"[InformationService teacherOne i checkLec]"+CF.RESET+ i+lecTimeList.get(i).getCheckLec());
            } else if(today.getTime() > date2.getTime()){ // 오늘날짜가 종강날짜 이후라면
               lecTimeList.get(i).setCheckLec(2);
            }
         }
      }
      
      // 한 강사의 사진
      PhotoFile photoFile = informationMapper.selectTeacherPhoto(teacherId);
      log.debug(CF.SWB+"[InformationService teacherOne photoFile]"+ photoFile+CF.RESET); // photoFile디버깅
      
      // 한 강사의 강의-수강
      List<Lec> lecList = informationMapper.selectTeacherLecList(teacherId);
      log.debug(CF.SWB+"[informationMapper teacherOne lecList]"+ lecList+CF.RESET); // 강의 디버깅
      
      
      // 5개를 묶어서 contoller에 보내주기
      Map<String,Object> returnMap = new HashMap<>();
      returnMap.put("lecTimeList", lecTimeList);
      returnMap.put("teacher", teacher);
      returnMap.put("certificationList", certificationList);
      returnMap.put("lecList", lecList);
      returnMap.put("photoFile", photoFile);
      return returnMap;
   }
   
   // 나의 정보(운영진개인정보+사진)
   public Map<String, Object> managerOne(String managerId) {
      
      // 운영진 개인정보
      Manager manager = informationMapper.selectManagerOne(managerId);
      log.debug(CF.SWB+"[InformationService managerOne manager]"+ manager.toString()+CF.RESET); // manager 디버깅
      
      // 운영진 사진
      PhotoFile photoFile = informationMapper.selectManagerPhoto(managerId);
      log.debug(CF.SWB+"[InformationService managerOne photoFile]"+ photoFile.toString()+CF.RESET); // photo 디버깅
      
      
      // 2개를 묶어서 contoller에 보내주기
      Map<String,Object> returnMap = new HashMap<>();
      returnMap.put("manager", manager);
      returnMap.put("photoFile", photoFile);
      return returnMap;
   }
   
   //멤버 삭제
   public int removeMember(String memberId) {
	   
	   int row = informationMapper.deleteMember(memberId);
	   
	   return row;
   }
}