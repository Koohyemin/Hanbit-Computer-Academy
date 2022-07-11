package Hanbit.co.kr.lms.RESTController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.LecHomeworkService;
import Hanbit.co.kr.lms.service.LecReferenceService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.LecReference;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LecReferenceRESTController {
	@Autowired  LecReferenceService lecReferenceService;
	
	// 한개의 파일을 삭제
	@GetMapping("lecReference/removeFile")
	public String removeFile(HttpServletRequest request,
							@RequestParam(name="lecReferenceFileNo")int lecReferenceFileNo) {
		// 파일이 들어가 경로 설정 
		String path = request.getServletContext().getRealPath("/upload/");
		log.debug(CF.SWB+"[LecReferenceRESTController get removeFile lecReferenceFileNo]"+CF.RESET+ lecReferenceFileNo); // homeworkFileNo 디버깅
		String check = lecReferenceService.deleteFileOne(lecReferenceFileNo, path);
		
		return check;
	}
}
