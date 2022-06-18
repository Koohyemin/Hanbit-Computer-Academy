package Hanbit.co.kr.lms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter("/*")
public class EncodingFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.debug("\u001B[31m"+"EncodingFilter.doFilter : 선 실행"+"\u001B[0m");
		request.setCharacterEncoding("utf-8");
		log.debug("\u001B[31m"+"EncodingFilter.doFilter : utf-8 인코딩 설정"+"\u001B[0m");
		// 요청보다 먼저 실행
		chain.doFilter(request, response);
		// 요청보다 후 실행
		log.debug("\u001B[31m"+"EncodingFilter.doFilter : 후 실행"+"\u001B[0m");
	}

}