package in.org.neeraj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.org.neeraj.model.UserAccount;

public interface UserRegRepository extends JpaRepository<UserAccount, Integer> {
	
	@Query("SELECT COUNT(email) FROM UserAccount WHERE email=:email")
	Integer getEmailCountByEmail(String email);
	
	@Query("SELECT user FROM UserAccount user WHERE email=:email")
	Optional<UserAccount> getUserByEmail(String email);
	
	@Modifying
	@Query("UPDATE UserAccount SET password=:pwd WHERE email=:email")
	Integer updateNewPassword(String email, String pwd);
	
	@Modifying
	@Query("UPDATE UserAccount SET accStatus=:status WHERE email=:email")
	Integer updateAccStatusByEmail(String status,String email);
	

}
