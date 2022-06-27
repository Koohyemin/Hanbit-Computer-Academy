package Hanbit.co.kr.lms.controller;

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
		
		log.debug( CF.KHN +"[EnquiryBoardController GetMapping currentPage]: "+ CF.RESET + currentPage);
		log.debug( CF.KHN +"[EnquiryBoardController GetMapping rowPerPage]: "+ CF.RESET + rowPerPage);
		log.debug( CF.KHN +"[EnquiryBoardController GetMapping category]: "+ CF.RESET + category);
		
		
		//세션을 통한 권한에 따른 처리
//		int memberLv = (Integer)session.getAttribute("sessionMemberLv");
//		if(category.equals("학생")) {
//			if (memberLv == 2) {
//				return "redirect:/enquiryBoard/getEnquiryBoardListByPage";
//			}
//		} else if(category.equals("강사")) {
//			if(memberLv == 1) {
//				return "redirect:/enquiryBoard/getEnquiryBoardListByPage";
//			}
//		}
		//서비스 호출 > 맵으로 묶어줌
		Map<String, Object> map = 	enquiryBoardService.selectEnquiryBoardListByPage(currentPage, rowPerPage, category);
		
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
		
		//서비스 호출
		EnquiryBoard eb = enquiryBoardService.selectEnquiryBoardOne(enquiryBoardNo);
		
		//값 디버깅
		log.debug(CF.KHN+ "EnquiryBoardController GetMappting getEnquiryBoardOne eb : "+ CF.RESET + eb);
		
		//모델값 넘겨줌
		model.addAttribute("eb", eb);
		
		//뷰 포워딩
		return "enquiryBoard/getEnquiryBoardOne";
	}
	
	//문의사항 입력 get
	@GetMapping("/enquiryBoard/addEnquiryBoard")
	public String addEnquiryBoard() {
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
	public String updateEnquiryBoard(EnquiryBoard enquiryBoard, Model model
				,@RequestParam(name="enquiryBoardNo") int enquiryBoardNo) {
		EnquiryBoard eb = enquiryBoardService.selectEnquiryBoardOne(enquiryBoardNo);
		model.addAttribute("eb",eb);
	log.debug( CF.KHN +"[EnquiryBoardController GetMapping updateEnquiryBoard eb]: "+ CF.RESET + eb);

		return "enquiryBoard/updateEnquiryBoard";
	}
	
	//문의사항 수정 post
	@PostMapping("/enquiryBoard/updateEnquiryBoard")
	public String updateEnquiryBoard(EnquiryBoard enquiryBoard) {
		int row = enquiryBoardService.updateEnquiryBoard(enquiryBoard);
		
		//수정성공 디버깅
		if(row == 1) {
			log.debug(CF.KHN+ "EnquiryBoardController PostMappting updateEnquiryBoard 수정성공 : "+ CF.RESET);			

		} else {
			log.debug(CF.KHN+ "EnquiryBoardController PostMappting updateEnquiryBoard 수정성공 : "+ CF.RESET);			

		}
		return "redirect:/enquiryBoard/getEnquiryBoardOne="+enquiryBoard.getEnquiryBoardNo();
	}
}