package com.pravalika.projects.resumeportal;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pravalika.projects.resumeportal.models.Education;
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
		//job1.setEndDate(LocalDate.of(2020, 3, 1));
		job1.getResponsibilities().add("Come up with the theory of relativity");
		job1.getResponsibilities().add("Advance quantum mechanics");
		job1.getResponsibilities().add("Blow people's minds");

		job1.setCurrentJob(true);

		Job job2 = new Job();
		job2.setCompany("Company 2");
		job2.setDesignation("Designation");
		job2.setId(2);
		job2.setStartDate(LocalDate.of(2019, 5, 1));
		job2.setEndDate(LocalDate.of(2020, 1, 1));
		job2.getResponsibilities().add("Come up with the theory of relativity");
		job2.getResponsibilities().add("Advance quantum mechanics");
		job2.getResponsibilities().add("Blow people's minds");

		profile1.getJobs().clear();
		profile1.getJobs().add(job1);
		profile1.getJobs().add(job2);

		Education e1 = new Education();
		e1.setCollege("Awesome College");
		e1.setQualification("Useless Degree");
		e1.setSummary("Studied a lot");
		e1.setStartDate(LocalDate.of(2019, 5, 1));
		e1.setEndDate(LocalDate.of(2020, 1, 1));


		Education e2 = new Education();
		e2.setCollege("Awesome College");
		e2.setQualification("Useless Degree");
		e2.setSummary("Studied a lot");
		e2.setStartDate(LocalDate.of(2019, 5, 1));
		e2.setEndDate(LocalDate.of(2020, 1, 1));

		profile1.getEducations().clear();
		profile1.getEducations().add(e1);
		profile1.getEducations().add(e2);
		profile1.getSkills().clear();

		profile1.getSkills().add("Quantum physics");
		profile1.getSkills().add("Modern Physics");
		profile1.getSkills().add("Violin");
		profile1.getSkills().add("Philosophy");

		userProfileRepository.save(profile1);

		return "profile";
	}

	@GetMapping("/view/{userName}")
	public String view(@PathVariable("userName") String userName,Model model) {
		Optional<UserProfile> userProfile = userProfileRepository.findByUserName(userName);
		model.addAttribute("profile",userProfile.get());
		return "resume-templates/"+userProfile.get().getTheme()+"/index";
	}
	@GetMapping("/edit")
	public String edit(Model model,Principal principal,@RequestParam(required = false) String add) {
		model.addAttribute("userId",principal.getName());
		Optional<UserProfile> userProfile = userProfileRepository.findByUserName(principal.getName());
		userProfile.orElseThrow(() -> new RuntimeException("Not found "));
		if ("job".equals(add)) {
            userProfile.get().getJobs().add(new Job());
        } else if ("education".equals(add)) {
            userProfile.get().getEducations().add(new Education());
        } else if ("skill".equals(add)) {
            userProfile.get().getSkills().add("");
        }
        model.addAttribute("profile", userProfile.get());

		return "edit";
	}

	@PostMapping("/edit")
	public String postEdit(Model model,Principal principal,@ModelAttribute UserProfile profile) {

		model.addAttribute("userId",principal.getName());
		Optional<UserProfile> userProfile = userProfileRepository.findByUserName(principal.getName());
		userProfile.orElseThrow(() -> new RuntimeException("Not found "));
		System.out.println(profile.toString());

		System.out.println(userProfile.toString());
		


	profile.setId(userProfile.get().getId());

	profile.setUserName(principal.getName());
	userProfileRepository.save(profile);
	return "redirect:/view/"+principal.getName();
}

}
