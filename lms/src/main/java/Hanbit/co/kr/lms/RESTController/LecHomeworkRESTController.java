package Hanbit.co.kr.lms.RESTController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Hanbit.co.kr.lms.service.LecHomeworkService;
import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LecHomeworkRESTController {
	@Autowired LecHomeworkService lecHomeworkSerivce;
	
	// 한개의 파일을 삭제
	@GetMapping("lecHomework/removeFile")
	public String removeFile(@RequestParam(name="homeworkFileNo")int homeworkFileNo) {
		
		log.debug(CF.SWB+"[LecHomeworkRESTController get removeFile homeworkFileNo]"+CF.RESET+ homeworkFileNo); // homeworkFileNo 디버깅
		String check = lecHomeworkSerivce.deleteFileOne(homeworkFileNo);
		
		return check;
	}
}
