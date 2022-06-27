package Hanbit.co.kr.lms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

	@Slf4j
	
	// 세션 값이 없으면 로그인 페이지로 이동하는 필터 
	@WebFilter(value = { "/certificate/*", "/faq/*", "/notice/*", "/questionnaire/*", "/student/*", "/people/*", "/index", "/lec/*" ,"/member/*"})
	public class LoginFilter extends HttpFilter implements Filter {
	   @Override
	   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	      if(request instanceof HttpServletRequest) {
	         HttpSession session = ((HttpServletRequest)request).getSession();
	         // 로그인 정보가 없으면 login으로 보내주기
	         if(session.getAttribute("sessionMemberId") == null) {
	            log.debug(CF.SWB+"[StudentFiter doFilter 로그인x"+CF.RESET);
	            ((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/login");
	            return;
	         }
	      } else {
	         log.debug(CF.SWB+"[StudentFiter doFilter 브라우저가 아닌 경로를 통한 요청"+CF.RESET);
	      }      
	      chain.doFilter(request, response);
	      // 요청보다 후 실행
	      log.debug("\u001B[31m"+"LoginFilter.doFilter : 후 실행"+"\u001B[0m");
	   }

	}

