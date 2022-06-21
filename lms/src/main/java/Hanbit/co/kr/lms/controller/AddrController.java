package Hanbit.co.kr.lms.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AddrController {
	
	@GetMapping("/searchAddr")
	public String getAddr(@RequestParam(value="currentPage",defaultValue="1") int currentPage,
						@RequestParam(value="keyword", defaultValue="봉소리") String keyword) {	
    // OPEN API 호출 URL 정보 설정
	log.debug("AddrController.getAddr"+keyword);
    final int countPerPage = 10;
    String confmKey = "U01TX0FVVEgyMDIyMDYxNjE2MzExNTExMjY5ODQ=";
    String resultType = "json";
    StringBuffer sb = null;
    System.out.println("여길 거칩니다.");
    try {
    String apiUrl = "https://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage="+currentPage+"&countPerPage="+countPerPage+"&keyword="+URLEncoder.encode(keyword,"UTF-8")+"&confmKey="+confmKey+"&resultType="+resultType;

       URL url = new URL(apiUrl);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
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
}