package Hanbit.co.kr.lms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 선
		if(request instanceof HttpServletRequest) {
			log.debug("브라우저를 통한 요청");
			HttpSession session  = ((HttpServletRequest)request).getSession();
			if(session.getAttribute("loginUser") == null) {
				((HttpServletResponse)response).sendRedirect(
						((HttpServletRequest)request).getContextPath()+"/login");
			}
		} else {
			log.debug("브라우저가 아닌 통한 요청");
		}
		chain.doFilter(request, response); // 원 요청 처리
		
		// 후
	}

}