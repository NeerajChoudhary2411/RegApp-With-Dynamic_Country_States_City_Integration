package in.org.neeraj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.org.neeraj.model.Login;
import in.org.neeraj.service.IUserRegService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private IUserRegService iUserRegService;
	
	@GetMapping("/show")
	public String showLoginForm() {
		return "LoginPage";
	}
	
	@PostMapping("/signincheck")
	public String signInToUserAccount(Model model,@ModelAttribute Login login) {
		System.out.println("Email"+login.getEmail());
		System.out.println("Password : "+login.getPassword());
		String lvnPage="";
		String verifiedLogin = iUserRegService.userLoginCheck(login.getEmail(), login.getPassword());
		System.out.println("Verified login :"+verifiedLogin);
		if(verifiedLogin.equals("Valid")) {
			model.addAttribute("message", "Welcome to KirArun Infotech Ltd.)");
			lvnPage="WelcomePage";
		}
		else {
			model.addAttribute("message", verifiedLogin);
			lvnPage="LoginPage";
		}
		return lvnPage;
	}

}
