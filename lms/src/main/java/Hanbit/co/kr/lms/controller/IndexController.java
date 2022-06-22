package Hanbit.co.kr.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {
	@GetMapping("/indexStudent")
	public String test() {

		return "indexStudent";
	}
}
