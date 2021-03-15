package in.org.neeraj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.org.neeraj.model.UserAccount;
import in.org.neeraj.service.IUserRegService;

@Controller
@RequestMapping("/user")
public class UserRegController {
	@Autowired
	private IUserRegService iUserRegService;

	@GetMapping("/signup")
	public String registerUser(Model model) {

		model.addAttribute("countries", iUserRegService.getAllCountries());
		return "RegisterUser";
	}
	
	@GetMapping("/states")
	public @ResponseBody String getStatesByCountryId(@RequestParam Integer countryId) {
		String json=null;
		List<Object[]> list = iUserRegService.getStateByCountryId(countryId);
		try {
			json=new ObjectMapper().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@GetMapping("/cities")
	public @ResponseBody String getCitiesByStateId(@RequestParam Integer stateId) {
		String json=null;
		List<Object[]> list = iUserRegService.getCityByStateId(stateId);

		try {
			json=new ObjectMapper().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@PostMapping("/save")
	public String registerUser(Model model,@ModelAttribute UserAccount userAccount) {
			iUserRegService.registerUser(userAccount);
		
		return "LoginPage";
	}

	@GetMapping("/validateemail")
	@ResponseBody
	public String validateEmail(@RequestParam String email) {
		String message = "";
		if (iUserRegService.isEmailExist(email)) {
			System.out.println("EMAIL :"+iUserRegService.isEmailExist(email));
			message = "'" + email + "' already exist!";
		}
		return message;
	}

}
