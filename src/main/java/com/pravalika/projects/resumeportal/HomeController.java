package com.pravalika.projects.resumeportal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@GetMapping("/home")
	public String test() {
		return "Hello User";
	}

}
