package Hanbit.co.kr.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.EnquiryBoardService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.EnquiryAnswer;
import Hanbit.co.kr.lms.vo.EnquiryBoard;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EnquiryBoardController {
	
	@Autowired EnquiryBoardService enquiryBoardService;
	@Autowired HttpSession session;
	
	//문의사항 목록 리스트 페이징포함 get
	@GetMapping("/enquiryBoard/getEnquiryBoardListByPage")
	public String getEnquiryBoardListByPage(Model model
			,@RequestParam(name="currentPage", defaultValue="1") int currentPage
			,@RequestParam(name="rowPerPage", defaultValue="10")int rowPerPage
			,@RequestParam(name="category", defaultValue="전체") String category) {
		
		//값 디버깅
		log.debug( CF.KHN +"[EnquiryBoardController GetMapping currentPage]: "+ CF.RESET + currentPage);
		log.debug( CF.KHN +"[EnquiryBoardController GetMapping rowPerPage]: "+ CF.RESET + rowPerPage);
		log.debug( CF.KHN +"[EnquiryBoardController GetMapping category]: "+ CF.RESET + category);
		
			
		//서비스 호출 > 맵으로 묶어줌
		Map<String, Object> map = 	enquiryBoardService.selectEnquiryBoardListByPage(currentPage, rowPerPage, category);
		int lastPage = (int)(map.get("lastPage"));
		log.debug( CF.KHN +"[EnquiryBoardController GetMapping lastPage]: "+ CF.RESET + lastPage);

		
		//값 디버깅
		log.debug( CF.KHN +"[EnquiryBoardController GetMapping map]: "+ CF.RESET + map);
	
		//모델값 넘겨줌
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("category", category);
		
		//뷰 포워딩
		return "enquiryBoard/getEnquiryBoardListByPage";
	}
	
	//문의사항 상세보기 get
	@GetMapping("/enquiryBoard/getEnquiryBoardOne")
	public String getEnquiryBoardOne(Model model
			,@RequestParam(name="enquiryBoardNo") int enquiryBoardNo) {
		
		// 문의사항 상세보기 서비스 호출
		EnquiryBoard eb = enquiryBoardService.selectEnquiryBoardOne(enquiryBoardNo);
		log.debug(CF.KHN+ "EnquiryBoardController GetMappting getEnquiryBoardOne eb : "+ CF.RESET + eb);
		
		// 문의사항 댓글 리스트 호출
		List<EnquiryAnswer> answerList = enquiryBoardService.selectAnswerList(enquiryBoardNo);
		log.debug(CF.KHN+ "EnquiryBoardController GetMappting answerList : "+ CF.RESET + answerList);
		
		//모델값 넘겨줌
		model.addAttribute("eb", eb);
		model.addAttribute("enquiryBoardNo", enquiryBoardNo);
		model.addAttribute("answerList", answerList);
		
		//뷰 포워딩
		return "enquiryBoard/getEnquiryBoardOne";
	}
	
	//문의사항 입력 get
	@GetMapping("/enquiryBoard/addEnquiryBoard")
	public String addEnquiryBoard() {
		
		//뷰 포워딩
		return "enquiryBoard/addEnquiryBoard";
	}
	
	//문의사항 입력 post
	@PostMapping("/enquiryBoard/addEnquiryBoard")
	public String addEnquiryBoard(EnquiryBoard enquiryBoard) {
		
		//서비스호출
		int row = enquiryBoardService.insertEnquiryBoard(enquiryBoard);
		
		//입력성공 디버깅
		if(row == 1) {
			log.debug(CF.KHN+ "EnquiryBoardController PostMappting addEnquiryBoard 입력성공 : "+ CF.RESET);			
		} else {
			log.debug(CF.KHN+ "EnquiryBoardController PostMappting addEnquiryBoard 입력실패 : "+ CF.RESET);			
		}
		
		//입력 완료시 문의사항 목록으로 이동
		return "redirect:/enquiryBoard/getEnquiryBoardListByPage";
	}
	
	//문의사항 수정 get
	@GetMapping("/enquiryBoard/updateEnquiryBoard")
	public String modifyEnquiryBoard(Model model
				,@RequestParam(name="enquiryBoardNo") int enquiryBoardNo) {
		
		//서비스 호출
		EnquiryBoard eb = enquiryBoardService.selectEnquiryBoardOne(enquiryBoardNo);
		
		//모델값 넘겨줌
		model.addAttribute("eb",eb);
		model.addAttribute("enquiryBoardNo",enquiryBoardNo);
		
		//값 디버깅	
		log.debug( CF.KHN +"[EnquiryBoardController GetMapping modifyEnquiryBoard eb]: "+ CF.RESET + eb);
		log.debug( CF.KHN +"[EnquiryBoardController GetMapping modifyEnquiryBoard enquiryBoardNo]: "+ CF.RESET + enquiryBoardNo);

		//뷰 포워딩
		return "enquiryBoard/updateEnquiryBoard";
	}
	
	//문의사항 수정 post
	@PostMapping("/enquiryBoard/updateEnquiryBoard")
	public String modifyEnquiryBoard(Model model, EnquiryBoard enquiryBoard) {
		
		//서비스 호출
		int row = enquiryBoardService.updateEnquiryBoard(enquiryBoard);
	
		//디버깅
		log.debug(CF.KHN+ "EnquiryBoardController PostMappting updateEnquiryBoard enquiryBoardNo : "+ CF.RESET + enquiryBoard.getEnquiryBoardNo());			
		
		//모델값 넘겨줌
		model.addAttribute("enquiryBoard", enquiryBoard);
		
		//수정확인 디버깅
		if(row == 1) {
			log.debug(CF.KHN+ "EnquiryBoardController PostMappting updateEnquiryBoard 수정성공 : "+ CF.RESET);			

		} else {
			log.debug(CF.KHN+ "EnquiryBoardController PostMappting updateEnquiryBoard 수정실패 : "+ CF.RESET);			

		}
		
		//수정하고 상세보기로 이동
		return "redirect:/enquiryBoard/getEnquiryBoardOne?enquiryBoardNo="+enquiryBoard.getEnquiryBoardNo();
	}
	
	//문의사항 삭제 post
	@PostMapping("/enquiryBoard/deleteEnquiryBoard")
	public String removeEnquiryBoard(Model model
			,@RequestParam(name="enquiryBoardNo") int enquiryBoardNo) {
		
		//서비스 호출
		int row = enquiryBoardService.deleteEnquiryBoard(enquiryBoardNo);
		
		//디버깅
		log.debug(CF.KHN+ "EnquiryBoardController PostMappting removeEnquiryBoard enquiryBoardNo : "+ CF.RESET + enquiryBoardNo);			
		
		//모델값 넘겨줌
		model.addAttribute("enquiryBoardNo", enquiryBoardNo);
		
		//삭제확인 디버깅
		if(row == 1) {
			log.debug(CF.KHN+ "EnquiryBoardController PostMappting removeEnquiryBoard 삭제성공 : "+ CF.RESET);			

		} else {
			log.debug(CF.KHN+ "EnquiryBoardController PostMappting removeEnquiryBoard 삭제실패 : "+ CF.RESET);			

		}
		
		//삭제하고 목록페이지로 이동
		return "redirect:/enquiryBoard/getEnquiryBoardListByPage";
	}
	
	
	// 댓글삭제하기
	@GetMapping("/enquiryBoard/enquityDeleteAnswer")
	public String enquityDeleteAnswer(
			@RequestParam(name="enquiryBoardNo") int enquiryBoardNo
			,@RequestParam(name="enquiryBoardAnswerNo") int enquiryBoardAnswerNo ) {
		
		// 넘어온 문의사항 상세보기 번호 확인
		log.debug(CF.KHN+ "EnquiryBoardController GetMapping enquityDeleteAnswer enquiryBoardNo : "+ CF.RESET + enquiryBoardNo);	
		log.debug(CF.KHN+ "EnquiryBoardController GetMapping enquityDeleteAnswer enquiryBoardAnswerNo : "+ CF.RESET + enquiryBoardAnswerNo);		
		
		int row = enquiryBoardService.deleteAnswer(enquiryBoardAnswerNo);
		log.debug(CF.KHN+ "EnquiryBoardController GetMapping enquityDeleteAnswer row : "+ CF.RESET + "삭제되었습니다.");		
		
		// 삭제하고 문의사항 상세보기 이동
		return "redirect:/enquiryBoard/getEnquiryBoardOne?enquiryBoardNo="+enquiryBoardNo;
	}
	
	
	// 댓글 입력하기
	@PostMapping("/enquiryBoard/addEnquityAnswer")
	public String addEnquityAnswer(Model model
			, @RequestParam(name="enquiryBoardNo") int enquiryBoardNo
			,  @RequestParam(name="enquiryAnswerContent") String enquiryAnswerContent) {
		
		// 세션에 있는 아이디 값 가져오기
		String memberId = (String) session.getAttribute("sessionMemberId");
		log.debug(CF.KYJ+ "EnquiryBoardController PostMappting addEnquityAnswer memberId : "+ CF.RESET + memberId);		
		
		// 문의사항 글번호 출력
		log.debug(CF.KYJ+ "EnquiryBoardController PostMappting addEnquityAnswer enquiryBoardNo : "+ CF.RESET + enquiryBoardNo);		
		
		// 문의사항 글번호 출력
		log.debug(CF.KYJ+ "EnquiryBoardController PostMappting addEnquityAnswer enquiryAnswerContent : "+ CF.RESET + enquiryAnswerContent);		
		
		Map<String,Object> map = new HashMap<>();
		map.put("enquiryBoardNo", enquiryBoardNo);
		map.put("memberId", memberId);
		map.put("enquiryAnswerContent", enquiryAnswerContent);
		
		int row = enquiryBoardService.insertAnswer(map);
		log.debug(CF.KHN+ "EnquiryBoardController PostMappting addEnquityAnswer row : "+ CF.RESET + "입력되었습니다.");	
		
		// 입력하고 문의사항 상세보기 이동
		return "redirect:/enquiryBoard/getEnquiryBoardOne?enquiryBoardNo="+enquiryBoardNo;
		
	}
	
}