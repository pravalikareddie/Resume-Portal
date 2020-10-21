package com.pravalika.projects.resumeportal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pravalika.projects.resumeportal.models.User;
import com.pravalika.projects.resumeportal.models.UserProfile;
import com.pravalika.projects.resumeportal.repository.UserProfileRepository;

@Controller
public class HomeController {
	@Autowired
	private UserProfileRepository userProfileRepository;
	@GetMapping("/home")
	public String test() {
		return "Hello User";
	}
	@GetMapping("/edit")
	public String testAdmin() {
		return "Hello Authenticated User";
	}
	@GetMapping("/view/{userName}")
	public String view(@PathVariable("userName") String userName,Model model) {
		Optional<UserProfile> userProfile = userProfileRepository.findByUserName(userName);
		model.addAttribute("profile",userProfile.get());
		return "resume-templates/"+userProfile.get().getTheme()+"/index";
	}

}
