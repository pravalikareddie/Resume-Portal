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
	@GetMapping("/")
	public String home() {
		
		return "index";
	}

	@GetMapping("/view/{userName}")
	public String view(Principal principal,@PathVariable("userName") String userName,Model model) {
		if (principal != null && principal.getName() != "") {
            boolean currentUsersProfile = principal.getName().equals(userName);
            model.addAttribute("currentUsersProfile", currentUsersProfile);
        }
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
	@GetMapping("/delete")
	public String delete(Model model, Principal principal, @RequestParam String type, @RequestParam int index) {
		String userId = principal.getName();
		Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
		userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));
		UserProfile userProfile = userProfileOptional.get();
		if ("job".equals(type)) {
			userProfile.getJobs().remove(index);
		} else if ("education".equals(type)) {
			userProfile.getEducations().remove(index);
		} else if ("skill".equals(type)) {
			userProfile.getSkills().remove(index);
		}
		userProfileRepository.save(userProfile);
		return "redirect:/edit";
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
