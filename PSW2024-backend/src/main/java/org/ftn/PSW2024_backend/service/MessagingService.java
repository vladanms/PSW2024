package org.ftn.PSW2024_backend.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.ftn.PSW2024_backend.model.Tour;
import org.ftn.PSW2024_backend.model.Tourist;
import org.ftn.PSW2024_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
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
	
	public void notifyTouristMail(User recipient, Tour tour) throws MessagingException, UnsupportedEncodingException 
	{
	    String toAddress = recipient.getEmail();
	    String fromAddress = this.senderMail; 
	    String senderName = "PSW2024";
	    String subject = "You might be interested in this";
	    String content = recipient.getUsername() + ",<br>"
	            + "A new tour, " + tour.getName() + " that fits your interest of " + tour.getCategory() + " has been published.<br>";
	     
	    MimeMessage message = sender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);	     
	    helper.setText(content, true);
	     
	    sender.send(message);
	    
	    System.out.println("mail sent!");
	}
	
	public void purchaseConfirmationMail(User recipient, List<Tour> tours) throws MessagingException, UnsupportedEncodingException 
	{
	    String toAddress = recipient.getEmail();
	    String fromAddress = this.senderMail; 
	    String senderName = "PSW2024";
	    String subject = "You might be interested in this";
	    
	    String tourList = "";
	    for(Tour tour : tours)
	    {
	    	tourList = tourList + tour.getName() + "<br>";
	    }
	    
	    String content = recipient.getUsername() + ",<br>"
	            + "You've succesfully purchased following tours:<br> " + tourList;
	     
	    MimeMessage message = sender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);	     
	    helper.setText(content, true);
	     
	    sender.send(message);
	    
	    System.out.println("mail sent!");
	}
	
	public void MonthlyReportMail(User recipient, int soldTours, List<Tour> bestTours, List<Tour> worstTours) throws MessagingException, UnsupportedEncodingException 
	{
	    String toAddress = recipient.getEmail();
	    String fromAddress = this.senderMail; 
	    String senderName = "PSW2024";
	    String subject = "Banned";
	    
	    String best = "";
	    if(bestTours.size() != 0)
	    {
	    	best = best 
	    		+ "Your best graded tours are: ";
	    		for(Tour tour : bestTours)
	    		{
	    			best = best + 
	    				tour.getName() + ", with total of " + tour.getGrades().size() + " grades.<br>";
	    		}
	    			
	    }
	    
	    String worst = "";
	    if(worstTours.size() != 0)
	    {
	    	best = best 
	    		+ "Your worst graded tours are: ";
	    		for(Tour tour : worstTours)
	    		{
	    			worst = worst + 
	    				tour.getName() + ", with total of " + tour.getGrades().size() + " grades.<br><br>";
	    		}
	    			
	    }
	    
	    String content = recipient.getUsername() + ",<br>"
	            + "Your monthly report is ready! <br>"
	    		+ "You've sold a total of " + soldTours + " tours. <br><br>"
	    		+ best;
	     
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
