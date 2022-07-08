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

import Hanbit.co.kr.lms.service.LecQnaService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.LecAnswer;
import Hanbit.co.kr.lms.vo.LecQuestion;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Controller
@Slf4j
public class LecQnaController {
	
	// 세션권한 걸어야함
	
	@Autowired LecQnaService lecQnaService;
	@Autowired HttpSession session;
	
	// 강의 답변 입력
	@PostMapping("/lecQna/addAnswer")
	public String insertAnswer(LecAnswer lecAnswer) {
		
		int row = lecQnaService.insertAnswer(lecAnswer);
		if(row == 1) {
			log.debug(CF.KHM + "[LecQnaController PostMapping answer.insert] :" + CF.RESET + "강의실 답변 등록 성공"); // 성공
		} else {
			log.debug(CF.KHM + "[LecQnaController PostMapping answer.insert] :" + CF.RESET + "강의실 답변 등록 실패"); // 실패
		}
		
		return "redirect:/lecQna/lecQnaOne?lecQuestionNo="+lecAnswer.getLecQuestionNo();
	}
	
	// 강의 질문 삭제
	@PostMapping("/lecQna/deleteLecQna")
	public String deleteQuestion(int lecQuestionNo) {
		
		// 해당 질문의 답변 개수 확인
		int answerCount = lecQnaService.answerCount(lecQuestionNo);
		
		log.debug(CF.KHM + "[LecQnaController PostMapping answerCount] :" + CF.RESET + answerCount);
		
		// 강의 질문관련 답변 삭제 : 여러개 일 수 있음(1보다 큼), 없을 수 있음(0과 같음)
		if(answerCount > 0) { // 답변 개수가 0개 이상일 경우 삭제 진행
			int answerRow = lecQnaService.deleteAnswer(lecQuestionNo);
			
			// 강의 답변 삭제 디버깅
			if(answerRow > 0) {
				log.debug(CF.KHM + "[LecQnaController PostMapping delete] :" + CF.RESET + "질문관련 답변 삭제 성공"); // 성공
			} else {
				log.debug(CF.KHM + "[LecQnaController PostMapping delete] :" + CF.RESET + "질문관련 답변 삭제 실패"); // 실패
			}
		}

		// 강의 질문 삭제
		int questionRow = lecQnaService.deleteQuestion(lecQuestionNo);
		
		// 질문 삭제 여부 디버깅
		if(questionRow == 1) {
			log.debug(CF.KHM + "[LecQnaController PostMapping delete] :" + CF.RESET + "강의실 질문 삭제 성공"); // 성공
		} else {
			log.debug(CF.KHM + "[LecQnaController PostMapping delete] :" + CF.RESET + "강의실 질문 삭제 실패"); // 실패
		}
		
		
		return "redirect:/lecQna/lecQnaList"; // 삭제 후 강의 목록으로 돌아가기
	}
	
	
	// 강의 질문 수정
	@PostMapping("/lecQna/updateLecQna")
	public String updateQuestion(LecQuestion lecQuestion) {
		
		int row = lecQnaService.updateQuestion(lecQuestion);
		
		if(row == 1) {
			log.debug(CF.KHM + "[LecQnaController PostMapping update] :" + CF.RESET + "강의실 질문 수정 성공"); // 성공
		} else {
			log.debug(CF.KHM + "[LecQnaController PostMapping update] :" + CF.RESET + "강의실 질문 수정 실패"); // 실패
		}
		
		return "redirect:/lecQna/lecQnaList";
	}
	
	// 강의 질문 수정
	@GetMapping("/lecQna/updateLecQna")
	public String updateQuestion(Model model
								,@RequestParam(name="questionNo") int questionNo) {
		
		// 수정 글 기존 내용 불러오기
		LecQuestion lecQuestion = lecQnaService.lecQuestionOne(questionNo);
		
		String studentId = (String)session.getAttribute("sessionMemberId");
		// 해당 학생의 수강 중인 강의 목록
		List<String> lectureList = lecQnaService.lectureList(studentId);
		
		// model 값 전송
		model.addAttribute("lecQuestion", lecQuestion);
		model.addAttribute("lectureList", lectureList);
		
		return "lecQna/updateLecQna";
	}
	
	// 강의 질문 상세보기
	@GetMapping("lecQna/lecQnaOne")
	public String lecQuestionOne(Model model,
								@RequestParam(name="lecQuestionNo") int lecQuestionNo,
								@RequestParam(name = "currentPage", defaultValue = "1") int currentPage, // 현재페이지, 1페이지부터 시작
								@RequestParam(name = "rowPerPage", defaultValue = "5") int rowPerPage) { // 한페이지당, 5개 답변 출력
		
		// 상세보기 대상 강의 조회
		LecQuestion lecQuestion = lecQnaService.lecQuestionOne(lecQuestionNo);
		
		// 답변 목록 조회
		Map<String,Object> map = lecQnaService.lecAnswerList(lecQuestionNo, currentPage, rowPerPage);
		
		model.addAttribute("lecQuestion",lecQuestion);
		model.addAttribute("list", map.get("list")); // 강의별 질문 목록
		model.addAttribute("lastPage", map.get("lastPage")); // 마지막 페이지
		model.addAttribute("currentPage", currentPage); // 현재 페이지
		return "lecQna/lecQnaOne";
	}
	
	// 강의별 질문 목록
	@GetMapping("lecQna/lecQnaList")
	public String lecQuestionListByPage(Model model,
			@RequestParam(name = "lectureName", defaultValue = "") String lectureName, 
			@RequestParam(name = "currentPage", defaultValue = "1") int currentPage, // 현재페이지, 1페이지부터 시작
			@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) { // 한페이지당, 10개 게시글 출력
			
			// Service에 처리한 코드를 이용하여 매개값 대입
			Map<String, Object> map = lecQnaService.lecQuestionListByPage(lectureName, currentPage, rowPerPage);
			List<String> lectureList = lecQnaService.lectureList((String)session.getAttribute("sessionMemberId"));
			List<String> teacherLectureList = lecQnaService.teacherLectureList((String)session.getAttribute("sessionMemberId"));
			
			log.debug(CF.KHM +"[LecQnaController GetMapping currentPage]: " + CF.RESET + currentPage); // 현재페이지 디버깅
			log.debug(CF.KHM +"[LecQnaController GetMapping lastPage]: " + CF.RESET + map.get("lastPage")); // 마지막페이지 디버깅
			log.debug(CF.KHM +"[LecQnaController GetMapping lectureName]: " + CF.RESET + lectureName); // 선택 강의 디버깅
			log.debug(CF.KHM +"[LecQnaController GetMapping list.size]: " + CF.RESET + map.size()); // 글 개수 디버깅
			
			log.debug(CF.KHM +"[LecQnaController GetMapping list.toString]: " + CF.RESET + map.get("list").toString()); // 받아오는 목록 디버깅
			
			// model에 값 add
			model.addAttribute("lectureName", lectureName); // 강의이름
			model.addAttribute("list", map.get("list")); // 강의별 질문 목록
			model.addAttribute("lastPage", map.get("lastPage")); // 마지막 페이지
			model.addAttribute("currentPage", currentPage); // 현재 페이지
			model.addAttribute("totalCount", map.size()); // 게시글 개수
			model.addAttribute("lectureList", lectureList); // 학생별 수강 중인 강의 목록
			model.addAttribute("teacherLectureList", teacherLectureList); // 강사가 강의 중인 강의 목록
			
			return "lecQna/lecQnaList";
	}
	
	// 강의 질문 등록
	@PostMapping("lecQna/addLecQna")
	public String insertQustion(LecQuestion lecQuestion) {
		// 등록 성공 행 반환(1 성공, 0 실패, 그 외 DB이상)
		int row = lecQnaService.insertQuestion(lecQuestion);
		
		if(row == 1) {
			log.debug(CF.KHM + "[LecQnaController PostMapping insert] :" + CF.RESET + "강의실 질문 등록 성공"); // 성공
		} else {
			log.debug(CF.KHM + "[LecQnaController PostMapping insert] :" + CF.RESET + "강의실 질문 등록 실패"); // 실패
		}
		
		return "redirect:/lecQna/lecQnaList";
	}
	
	// 강의 질문 등록
	@GetMapping("lecQna/addLecQna")
	public String insertQuestion(Model model) {
		String studentId = (String)session.getAttribute("sessionMemberId");
		// 해당 학생의 수강 중인 강의 목록
		List<String> lectureList = lecQnaService.lectureList(studentId);
		
		log.debug(CF.KHM + "[LecQnaController GetMapping lectureList.size] :" + CF.RESET + lectureList.size());
		
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("listSize", lectureList.size());
		return "lecQna/addLecQuestion";
	}
}
