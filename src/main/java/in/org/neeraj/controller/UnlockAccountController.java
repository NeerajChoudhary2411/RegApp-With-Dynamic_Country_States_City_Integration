package in.org.neeraj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.org.neeraj.model.UnlockAccount;
import in.org.neeraj.service.IUserRegService;

@Controller
@RequestMapping("/unlockaccount")
public class UnlockAccountController {

	@Autowired
	private IUserRegService iUserRegService;

	@GetMapping("/show")
	public String showUnlockForm(Model model) {
		return "UnlockAccPage";
	}

	@PostMapping("/unlockacc")
	public String unlockUserAccount(Model model, @ModelAttribute UnlockAccount unlockAccount) {
		String message = "";
		if (unlockAccount.getNewPwd().equals(unlockAccount.getConfirmPwd())) {
			if (iUserRegService.isTempPasswordValid(unlockAccount.getEmailId(), unlockAccount.getTempPwd())) {
				if (iUserRegService.unlockAccount(unlockAccount)) {
					if (iUserRegService.unlockAccStatusByEmail("Unlocked", unlockAccount.getEmailId()) > 0) {
						message = "Account Unlocked, Please proceed with login";
					} else {
						message = "Not Unlocked , Internal Problem";
					}
				} else {
					message = "Invalid Credintial";
				}
			} else {
				message = "Invalid Credintial";
			}
		} else {
			message = "Invalid Credintial";
		}
		model.addAttribute("message", message);
		return "UnlockAccPage";
	}
}
