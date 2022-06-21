package Hanbit.co.kr.lms.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Hanbit.co.kr.lms.util.CF;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
		@GetMapping("getAddr")
		public String selectAddr() {
			
			return "/addMember/addr";
		}
		@PostMapping("addMember")
		public String addMember() {
			
			log.debug(CF.LKL+"addMember 호출");
			return "redirect:/login";
			
		}
	}

