package in.org.neeraj.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_REG_TABLE")
public class UserAccount {

	@Id
	@GeneratedValue(generator = "user_seq")
	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq_gen", allocationSize = 1)
	@Column(name = "uid_col")
	private Integer id;
	
	@Column(name = "user_fname__col")
	private String firstName;
	
	@Column(name = "user_lname_col")
	private String lastName;
	
	@Column(name = "user_email_col")
	private String email;
	
	@Column(name="user_pwd_col",length = 35)
	private String password;
	
	@Column(name = "user_mob_col",length = 15)
	private String phNo;
	
	@Column(name = "user_dob_col")
	private String dob;
	
	@Column(name = "user_gen_col")
	private String gender;
	
	@Column(name = "user_country_col")
	private String country;
	
	@Column(name = "user_state_col")
	private String state;
	
	@Column(name = "user_city_col")
	private String city;
	
	@Column(name = "user_status_col",length = 15)
	private String accStatus;
	
	@Column(name = "user_reg_date_col")
	private LocalDateTime regDate;
	
	@Column(name = "user_updated_date_col")
	private LocalDateTime updatedDate;

}
