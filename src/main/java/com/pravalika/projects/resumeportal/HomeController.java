package com.pravalika.projects.resumeportal;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pravalika.projects.resumeportal.models.Job;
import com.pravalika.projects.resumeportal.models.User;
import com.pravalika.projects.resumeportal.models.UserProfile;
import com.pravalika.projects.resumeportal.repository.UserProfileRepository;

@Controller
public class HomeController {
	@Autowired
	private UserProfileRepository userProfileRepository;
	@GetMapping("/home")
	public String test() {
		   Optional<UserProfile> profileOptional = userProfileRepository.findByUserName("einstein");
	        profileOptional.orElseThrow(() -> new RuntimeException("Not found "));

	        UserProfile profile1 = profileOptional.get();
	        Job job1 = new Job();
	        job1.setCompany("Company 1");
	        job1.setDesignation("Designation");
	        job1.setId(1);
	        job1.setStartDate(LocalDate.of(2020, 1, 1));
	        job1.setEndDate(LocalDate.of(2020, 3, 1));
	        Job job2 = new Job();
	        job2.setCompany("Company 2");
	        job2.setDesignation("Designation");
	        job2.setId(2);
	        job2.setStartDate(LocalDate.of(2019, 5, 1));
	        job2.setEndDate(LocalDate.of(2020, 1, 1));
	        profile1.getJobs().clear();
	        profile1.getJobs().add(job1);
	        profile1.getJobs().add(job2);

	        userProfileRepository.save(profile1);

	        return "profile";
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
