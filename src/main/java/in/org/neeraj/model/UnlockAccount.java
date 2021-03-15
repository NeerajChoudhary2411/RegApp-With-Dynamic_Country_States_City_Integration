package in.org.neeraj.model;

import lombok.Data;

@Data
public class UnlockAccount {
	
	private String emailId;
	
	private String tempPwd;
	
	private String newPwd;
	
	private String confirmPwd;

}
