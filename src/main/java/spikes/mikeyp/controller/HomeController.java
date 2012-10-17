package spikes.mikeyp.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


	@RequestMapping({"/","home"})
	public String home(Principal currentUser, Model model) {
		
		return "home";
	}

	
}
