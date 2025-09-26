package org.ftn.PSW2024_backend.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.ftn.PSW2024_backend.model.Tourist;
import org.ftn.PSW2024_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MessagingService {
	
	@Autowired
	private JavaMailSender sender;
	
	String senderMail;

	public void purchaseConfirmationMail(Tourist recipient, String[] tours) throws MessagingException, UnsupportedEncodingException 
	{
	    String toAddress = recipient.getEmail();
	    String fromAddress = this.senderMail; 
	    String senderName = "PSW2024";
	    String subject = "Purchase confirmation";
	    
	    String tourList = "";
	    for(String s : tours)
	    {
	    	tourList = tourList + s + "<br>";
	    }
	    
	    
	    String content = recipient.getUsername() + ",<br>"
	            + "You've succesfully reserved your spot in the following tours: <br><br>"
	            + tourList + "<br>"
	            + "Thank you for using out platform!";
	     
	    MimeMessage message = sender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	     
	    helper.setText(content, true);
	     
	    sender.send(message);
	    
	    System.out.println("mail sent!");
	}
	
	public void bannedMail(User recipient) throws MessagingException, UnsupportedEncodingException 
	{
	    String toAddress = recipient.getEmail();
	    String fromAddress = this.senderMail; 
	    String senderName = "PSW2024";
	    String subject = "Banned";
	    String content = recipient.getUsername() + ",<br>"
	            + "Your acount has been restricted from accessing this platform due to an excess of rule violations.";
	     
	    MimeMessage message = sender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);	     
	    helper.setText(content, true);
	     
	    sender.send(message);
	    
	    System.out.println("mail sent!");
	}
	
	
}
