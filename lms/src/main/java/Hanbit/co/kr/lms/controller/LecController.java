package Hanbit.co.kr.lms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.LecService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Lec;
import Hanbit.co.kr.lms.vo.LecPlan;
import Hanbit.co.kr.lms.vo.TimeTable;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@Controller
public class LecController {
	
	@Autowired LecService lecService;
	@Autowired HttpSession session;
	
	// 운영자 강의 승인
	@PostMapping("lec/updateLecState")
	public String getUpdateLecState(@RequestParam(name="lectureName") String lectureName, @RequestParam(name="lecState") String lecState) {
		
		// 세션을 이용한 권한 처리
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if (memberLv != 3) { // 운영자가 아니라면 공지목록으로 돌아가기
			return "redirect:/lec/lecList";
		}
		
		// 수정 성공 행 반환(1 성공, 0 실패, 그 외 DB이상)
		int row = lecService.getUpdateLecState(lectureName, lecState);
		
		if(row == 1) {
			log.debug(CF.KHM + "[LecController postMapping updateLecState] :" + CF.RESET + "강의 승인/비승인 수정 성공"); // 성공
		} else {
			log.debug(CF.KHM + "[LecController postMapping updateLecState] :" + CF.RESET + "강의 승인/비승인 수정 실패"); // 실패
		}
		
		return "redirect:/people/peopleList?level=3";
	}
	
	// 강의 삭제 POST
	@PostMapping("lec/deleteLec")
	public String deleteLec(@RequestParam(name="lectureName") String lectureName) {
		
		// 세션을 이용한 권한 처리
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if (memberLv != 3) { // 운영자가 아니라면 공지목록으로 돌아가기
			return "redirect:/lec/lecList";
		}
		
		// 삭제 성공 행 반환(1 성공, 0 실패, 그 외 DB이상)
		int timeRow = lecService.getDeleteTime(lectureName);
		int lecPlanRow = lecService.getDeleteLecPlan(lectureName);
		int lecRow = lecService.getDeleteLec(lectureName);
		
		if(lecRow == 1 && timeRow == 1 && lecPlanRow == 1) {
			log.debug(CF.KHM + "[LecController postMapping deleteLec] :" + CF.RESET + "강의 삭제 성공"); // 성공
			return "redirect:/people/peopleList?level=3";
		} else {
			log.debug(CF.KHM + "[LecController postMapping deleteLec] :" + CF.RESET + "강의 삭제 실패"); // 실패
			return "redirect:/people/peopleList?level=3";
		}
	}
	
	// 강의 수정 POST
	@PostMapping("lec/updateLec")
	public String geUpdateLec(Lec lec, TimeTable timeTable, LecPlan lecPlan) {
		
		// 세션을 이용한 권한 처리
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if (memberLv != 3) { // 운영자가 아니라면 공지목록으로 돌아가기
			return "redirect:/lec/lecList";
		}
		
		// 수정 성공 행 반환(1 성공, 0 실패, 그 외 DB이상)
		int lecRow = lecService.getUpdateLec(lec);
		int lecPlanRow = lecService.getUpdateLecPlan(lecPlan);
		int timeRow = lecService.getUpdateTime(timeTable);
		
		if(lecRow == 1 && timeRow == 1 && lecPlanRow == 1) {
			log.debug(CF.KHM + "[LecController postMapping updateLec] :" + CF.RESET + "강의 수정 성공"); // 성공
		} else {
			log.debug(CF.KHM + "[LecController postMapping updateLec] :" + CF.RESET + "강의 수정 실패"); // 실패
		}
		
		return "redirect:/people/peopleList?level=3";
	}
	
	// 강의 수정 GET
	@GetMapping("lec/updateLec")
	public String getUpdateLec(Model model,
							@RequestParam(name="lectureName") String lectureName) {
		
		// 세션을 이용한 권한 처리
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if (memberLv != 3) { // 운영자가 아니라면 공지목록으로 돌아가기
			return "redirect:/lec/lecList";
		}
		
		Lec lectureInfo = lecService.getLecOne(lectureName);
		Map<String,Object> map = lecService.getUpdateLec(lectureName);
		
		// model에 카테고리 + 수정을 위해 필요한 정보 add
		model.addAttribute("lectureInfo", lectureInfo);
		model.addAttribute("subjectList", map.get("subjectList"));
		model.addAttribute("lecPlanList", map.get("lecPlanList"));
		model.addAttribute("lectureRoomList", map.get("lectureRoomList"));
		model.addAttribute("teacherList", map.get("teacherList"));
		
		return "lec/updateLec";
	}
	
	// 강의 상세보기 열린강좌에 대해서 전체 열람 가능
	@GetMapping("lec/lecOne")
	public String getLecOne(Model model, @RequestParam(name="lectureName") String lectureName) {
		
		Lec getLecOne = lecService.getLecOne(lectureName); // 강의 상세보기 정보
		
		// model에 상세보기 값 add
		model.addAttribute("lec", getLecOne);
		
		return "lec/getLecOne";
	}
	
	// 강의 등록 POST
	@PostMapping("lec/addLec")
	public String addLec(Lec lec, TimeTable timeTable, LecPlan lecPlan) {
		
		// 세션을 이용한 권한 처리
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if (memberLv != 3) { // 운영자가 아니라면 공지목록으로 돌아가기
			return "redirect:/lec/lecList";
		}
		
		// 등록 성공 행 반환(1 성공, 0 실패, 그 외 DB이상)
		int lecRow = lecService.insertLec(lec);
		int lecPlanRow = lecService.insertLecPlan(lecPlan);
		int timeRow = lecService.insertTime(timeTable);
		
		if(lecRow == 1 && timeRow == 1 && lecPlanRow == 1) { // 일정표, 강의 등록 성공 시 성공
			log.debug(CF.KHM + "[LecController postMapping addLec] :" + CF.RESET + "강의/일정표 등록 성공"); // 성공
		} else {
			log.debug(CF.KHM + "[LecController postMapping addLec] :" + CF.RESET + "강의/일정표 등록 실패"); // 실패
		}
		
		return "redirect:/people/peopleList?level=3";
	}
	
	// 강의 등록 GET
	@GetMapping("lec/addLec")
	public String addLec(Model model) {
		// 세션을 이용한 권한 처리
		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
		if (memberLv != 3) { // 운영자가 아니라면 공지목록으로 돌아가기
			return "redirect:/notice/getNoticeListByPage";
		}
		
		// returnMap 불러오기
		Map<String, Object> map = lecService.insertLec();
		
		// model에 값 add
		model.addAttribute("subjectList", map.get("subjectList"));
		model.addAttribute("lecPlanList", map.get("lecPlanList"));
		model.addAttribute("lectureRoomList", map.get("lectureRoomList"));
		model.addAttribute("teacherList", map.get("teacherList"));
		
		return "lec/addLec"; // lec/addLec.jsp로 이동
		
	}
	
	@GetMapping("lec/lecList")
	public String getLecListByPage(Model model,
								@RequestParam(name = "currentPage", defaultValue = "1") int currentPage, // 현재페이지, 1페이지부터 시작
								@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) { // 한페이지당 10개의 목록 출력
		
		// Service에 처리한 코드를 이용하여 매개값 대입
		Map<String, Object> map = lecService.getLecListByPage(currentPage, rowPerPage);
		
		log.debug(CF.KHM +"[LecController GetMapping currentPage]: " + CF.RESET + currentPage); // 현재페이지 디버깅
		//	log.debug(CF.KHM +"[LecController GetMapping totalCount]: " + CF.RESET + map.get("totalCount")); // 현재페이지 디버깅
		
		

		
		
		// model에 값 add
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", map.get("currnetPage"));
		model.addAttribute("totalCount", map.size());

			
		return "lec/lecList"; // lec/lecList.jsp로 이동
	}
}
