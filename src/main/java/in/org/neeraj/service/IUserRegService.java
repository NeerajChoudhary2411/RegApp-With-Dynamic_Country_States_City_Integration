package in.org.neeraj.service;

import java.util.List;

import in.org.neeraj.model.UnlockAccount;
import in.org.neeraj.model.UserAccount;

public interface IUserRegService {
	
	Integer registerUser(UserAccount userAccount);
	
	Boolean isEmailExist(String email);
	
//	Map<Integer,String> getAllCountries();
	
	List<Object[]> getAllCountries();
	
//	Map<Integer,String> getStateByCountryId(Integer countryId);
	List<Object[]> getStateByCountryId(Integer countryId);
	
//	Map<Integer,String> getCityByStateId(Integer stateId);
	List<Object[]> getCityByStateId(Integer stateId);
	
	Boolean isTempPasswordValid(String email,String tempPwd);
	
	Boolean unlockAccount(UnlockAccount unlockAccount);
	
	String findUserByEmail(String email);
	
	String userLoginCheck(String email,String pwd);
	
	Integer unlockAccStatusByEmail(String status, String email);

}
