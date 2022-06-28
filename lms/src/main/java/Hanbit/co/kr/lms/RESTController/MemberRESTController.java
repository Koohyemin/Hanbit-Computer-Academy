package Hanbit.co.kr.lms.RESTController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Hanbit.co.kr.lms.service.MemberService;
import Hanbit.co.kr.lms.util.CF;
import Hanbit.co.kr.lms.vo.Member;
import Hanbit.co.kr.lms.vo.PasswordUpdateDate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberRESTController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/searchAddr")
	public String getAddr(@RequestParam(value="Keyword") String keyword) {
		
    // OPEN API 호출 URL 정보 설정
	//뷰에서 받은 keyword 디버깅
	log.debug(CF.LKL+"RController.getAddr : "+ CF.RESET +keyword);  
	//페이지당 개수 100으로 설정
    final int countPerPage = 100;
    //페이지 수 1로 설정
    int currentPage =1;
    //키 지정
    String confmKey = "U01TX0FVVEgyMDIyMDYxNjE2MzExNTExMjY5ODQ=";			
    //타입 지정
    String resultType = "json";												
    
    StringBuffer sb = null;
    
    try {
    	//api url 불러오기
    String apiUrl = "https://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage="+currentPage+"&countPerPage="+countPerPage+"&keyword="+URLEncoder.encode(keyword,"UTF-8")+"&confmKey="+confmKey+"&resultType="+resultType;

       URL url = new URL(apiUrl);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));		//api의 가공되지않은 결과 값
        //StringBuffer -> String을 
        sb = new StringBuffer();
        
        String tempStr = null;
        while((tempStr = br.readLine()) != null){
           sb.append(tempStr);                        // 응답결과 JSON 저장
        }
    } catch(Exception e) {
       e.printStackTrace();
    }
     return sb.toString();
//		    	response.setCharacterEncoding("UTF-8");
//				response.setContentType("text/xml");
//				response.getWriter().write(sb.toString());			// 응답결과 반환
		    	
		    	//rest -> json형식으로 전송
	}
	
	@GetMapping("/compMember")
	public String compMember(@RequestParam(value = "idCheck") String memberId){
		//뷰에서 memberId 가져오기 
		log.debug(CF.LKL+"MemberRESTController.compMember : "+ CF.RESET + memberId);
		//DB에서 member리스트를 가져온다
		List<Member> listMember= memberService.getMember();
		
		//받은 memberlist 수만큼 반복
		 for(int i=0; i<listMember.size(); i++) {
			 //뷰에서 가져온 memberId와 db의 memberId가 일치하면
			if(listMember.get(i).getMemberId().equals(memberId)) {
				//false값 반환
				return "false";
			}
		 }
		return memberId;
//세션 로그인 상태면 튕기게
//리스트 페이징?
//주민등록 유효성
//최종 제출 버튼 클릭 후 id 유효성 받은 걸 꼭 검사
	}
	@GetMapping("/prePw")
	public boolean prePw(PasswordUpdateDate passwordUpdateDate){
		
		log.debug(CF.LKL+"MemberRESTController.prePw.passwordUpdateDate : "+ CF.RESET + passwordUpdateDate);
		
		int row= memberService.changePwbyactivity(passwordUpdateDate);
		
		boolean pwCheck=false;
		if(row==0) {
			
			pwCheck= true;
			
		} else {
			
			pwCheck= false;
			
		}
		
		log.debug(CF.LKL+"MemberRESTController.prePw.pwCheck : "+ CF.RESET + pwCheck);
		
		return pwCheck;
		
	}

	
	
}