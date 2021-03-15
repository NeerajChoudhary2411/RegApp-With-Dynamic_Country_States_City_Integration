package in.org.neeraj.utility;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class EmailUtil {
	@Autowired
	private JavaMailSender sender;
	
	public boolean send(
			String to,
			String cc[],
			String bcc[],
			String text,
			String subject,
			MultipartFile file			
			) 
	{
		boolean isSent=false;
		try {
			//create new message
			MimeMessage message = sender.createMimeMessage();
			//fill data
			MimeMessageHelper helper = new MimeMessageHelper(message,file!=null?true:false);
			helper.setTo(to);
			helper.setText(text,true);
			helper.setSubject(subject);
			if(cc!=null)
				helper.setCc(cc);
			if(bcc!=null)
				helper.setBcc(bcc);
			if(file!=null){
				helper.addAttachment(file.getOriginalFilename(), file);
			}
			isSent=true;
			//send message
			sender.send(message);
		}catch(Exception e) {
			e.printStackTrace();
		}	
		return isSent;
	}
	
	public boolean send(
			String to,
			String text,
			String subject
			) 
	{
		return send(to,null,null,text,subject,null);
		
	}

}
