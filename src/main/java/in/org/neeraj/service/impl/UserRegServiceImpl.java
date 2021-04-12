package in.org.neeraj.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.org.neeraj.model.UnlockAccount;
import in.org.neeraj.model.UserAccount;
import in.org.neeraj.repository.CountryRepository;
import in.org.neeraj.repository.StateRepository;
import in.org.neeraj.repository.UserRegRepository;
import in.org.neeraj.service.IUserRegService;
import in.org.neeraj.utility.AES256;
import in.org.neeraj.utility.AppUtil;
import in.org.neeraj.utility.EmailUtil;

@Service
public class UserRegServiceImpl implements IUserRegService {
	@Autowired
	private UserRegRepository userRegRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private EmailUtil emailUtil;

	@Override
	public Integer registerUser(UserAccount userAccount) {
		String password = AppUtil.generateRandomString(7);
		String encryptedPwd = AES256.encrypt(password);
		userAccount.setPassword(encryptedPwd);
		userAccount.setAccStatus("Locked");
		userAccount.setRegDate(LocalDateTime.now());
		userAccount.setUpdatedDate(LocalDateTime.now());
		Integer id = userRegRepository.save(userAccount).getId();
		if (id != null) {
			String subject = "Unlock Account";
			String body = getUnlockAccEmailBody(userAccount,password);
			emailUtil.send(userAccount.getEmail(), body, subject);
		}
		return id;
	}

	public String getUnlockAccEmailBody(UserAccount userAccount,String password) {
		StringBuilder sb = new StringBuilder();
		String body = "";
		// load file
		File f = new File("UnlockAcc-Email-Body.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			//read line by line
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			br.close();
			body = sb.toString();
			body = body.replace("{FNAME}", userAccount.getFirstName());
			body = body.replace("{LNAME}", userAccount.getLastName());
			body = body.replace("{TEMP-PWD}", password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}

	@Override
	public Boolean isEmailExist(String email) {
		return userRegRepository.getEmailCountByEmail(email) > 0;
	}

	@Override
	public List<Object[]> getAllCountries() {
		return countryRepository.getAllCountriesIdAndName();
		// return AppUtil.convertToMap(list);
	}

	@Override
	public List<Object[]> getStateByCountryId(Integer countryId) {
		return countryRepository.getAllStatesIdAndNameByCountryId(countryId);
		// return AppUtil.convertToMap(list);
	}

	@Override
	public List<Object[]> getCityByStateId(Integer stateId) {
		return stateRepository.getAllCitiesIdAndNameByStateId(stateId);
		// return AppUtil.convertToMap(list);
	}

	@Override
	public String findUserByEmail(String email) {
		Optional<UserAccount> opt = userRegRepository.getUserByEmail(email);
		if (opt.isPresent()) {
			return AES256.decrypt(opt.get().getPassword());
		}

		return "Invalid Email";
	}

	@Override
	public Boolean isTempPasswordValid(String email, String tempPwd) {
		Optional<UserAccount> opt = userRegRepository.getUserByEmail(email);
		if (opt.isPresent()) {
			System.out.println("TEMP DECRI :" + AES256.decrypt(opt.get().getPassword()));
			System.out.println("TEMP : " + tempPwd);
			String decryptedPwd = AES256.decrypt(opt.get().getPassword());
			if (decryptedPwd.equals(tempPwd)) {
				return true;
			}
		}
		return false;
	}

	@Transactional
	public Boolean unlockAccount(UnlockAccount unlockAccount) {
		String confirmPwd = unlockAccount.getConfirmPwd();
		return userRegRepository.updateNewPassword(unlockAccount.getEmailId(), AES256.encrypt(confirmPwd)) > 0;
	}

	@Override
	public String userLoginCheck(String email, String pwd) {
		Optional<UserAccount> opt = userRegRepository.getUserByEmail(email);
		String verify = "";
		if (opt.isPresent()) {
			System.out.println("opt.isPresent() : " + opt.isPresent());
			if (pwd.equals(AES256.decrypt(opt.get().getPassword()))) {
				System.out.println("pwd.equals(AES256.decrypt(opt.get().getPassword()) : "
						+ pwd.equals(AES256.decrypt(opt.get().getPassword())));

				if (opt.get().getAccStatus().equals("Unlocked")) {
					System.out.println("VALID");
					verify = "Valid";
				} else {
					verify = "Account is locked";
				}
			} else {
				verify = "Invalid Credentials";
			}
		} else {
			verify = "Invalid Credentials";
		}
		return verify;
	}

	@Transactional
	public Integer unlockAccStatusByEmail(String status, String email) {
		return userRegRepository.updateAccStatusByEmail(status, email);
	}

}
