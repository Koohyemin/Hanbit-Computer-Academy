package Hanbit.co.kr.lms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.PeopleService;
import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PeopleController {
	@Autowired PeopleService peopleService;
	
	// 강사 목록 및 검색(학생, 강사, 운영자 모두 조회 가능)
	@GetMapping("/people/peopleList")
	public String getTeacherList(Model model,
								 @RequestParam(name="searchValue", defaultValue = "")String searchValue
								 ,@RequestParam(name="level", defaultValue = "2") int level) {
		
		// 받아온 값 출력 
		log.debug(CF.KHM + "[PeopleController getMapping searchValue] : " + CF.RESET + searchValue);
		log.debug(CF.KHM + "[PeopleController getMapping level] : " + CF.RESET + level); 
		
		
		if(level == 2) {
		// SQL 쿼리에 검색어 매개값 적용
		List<Map<String,Object>> list = peopleService.getTeacherList(searchValue);
		
		// 리스트가 1개이상일 시 목록 출력을 위해 리스트 개수 변수 선언
		int listSize = list.size();
		
		log.debug(CF.KHM + "[PeopleController getMapping list.size] : " + CF.RESET + listSize); // listSize 디버깅
		
		/*
			// ","를 기준으로 강의 이름을 분리
			for(int i=0; i<list.size(); i++) {
				if(list.get(i).get("lecPlanName")!=null && !"".equals(list.get(i).get("lecPlanName"))) {
					String[] lecture = ((String)list.get(i).get("lecPlanName").toString()).split("\\,");
					String[] lectureNo = ((String)list.get(i).get("lecPlanNo").toString()).split("\\,");
					log.debug(CF.KHM + "[TeacherController getMapping lecture] : " + CF.RESET + lecture.length); // lecture 분리된 목록 디버깅
					list.get(i).put("lecPlanName", lecture);
					list.get(i).put("lecPlanNo", lectureNo);
				}
			}
		*/
		// model에 값 add
		model.addAttribute("level", level); // 검색어
		model.addAttribute("searchValue", searchValue); // 검색어
		model.addAttribute("teacherList",list); // 강사 정보를 담은 목록
		model.addAttribute("listSize",listSize); // 목록이 1이상일때만 실행시키기 위한 개수
		
		}else if(level == 3) {
			// SQL 쿼리에 검색어 매개값 적용
			List<Map<String,Object>> list = peopleService.getLecList(searchValue);
			
			// 리스트가 1개이상일 시 목록 출력을 위해 리스트 개수 변수 선언
			int listSize = list.size();
			log.debug(CF.KHM + "[PeopleController getMapping list.size] : " + CF.RESET + listSize); // listSize 디버깅
			// model에 값 add
			model.addAttribute("level", level); // 검색어
			model.addAttribute("searchValue", searchValue); // 검색어
			model.addAttribute("LecList",list); // 강의 정보를 담은 목록
			model.addAttribute("listSize",listSize); // 목록이 1이상일때만 실행시키기 위한 개수
		}
		// people/teacherList.jsp로 이동
		return "people/peopleList";
	}
}