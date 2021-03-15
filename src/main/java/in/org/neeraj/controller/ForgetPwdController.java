package in.org.neeraj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.org.neeraj.model.ForgetPwd;
import in.org.neeraj.service.IUserRegService;
import in.org.neeraj.utility.EmailUtil;

@Controller
@RequestMapping("/forget")
public class ForgetPwdController {
	@Autowired
	private IUserRegService iUserRegService;
	@Autowired
	private EmailUtil emailUtil;
	
	@GetMapping("/show")
	public String showForgetForm() {
		return "ForgetPwdPage";
	}
	
	@PostMapping("/forgetcheck")
	public String getForgetPwdByEmail(Model model,@ModelAttribute ForgetPwd forgetPwd) {
		String message="";
		String forgetData = iUserRegService.findUserByEmail(forgetPwd.getEmail());
		if(forgetData.equals("Invalid Email")) {
			message=forgetData;
		}
		else {
			String subject = "Unlock Account";
			String text = "Hi, " + forgetPwd.getEmail() + ": "
					+ "<br><b>Welcome to KirArun Infotech Ltd.</b>"+ "<br>Your Password is "+forgetData;
			emailUtil.send(forgetPwd.getEmail(), text, subject);
			message="Password sent to your mail.";
		}
		model.addAttribute("message", message);
		return "ForgetPwdPage";
	}

}
