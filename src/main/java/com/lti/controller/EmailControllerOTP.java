package com.lti.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.service.EmailService;

@RestController
@CrossOrigin
public class EmailControllerOTP {
	Random random=new Random(10000);
	
	@Autowired
	EmailService service;
	
	@PostMapping("/verifyemail")
	public boolean sendOTP(@RequestParam("email")String email) {
		System.out.println("EMAIL"+ email);
		int otp=random.nextInt(999999);
		System.out.println("OTP "+otp);
		
		String subject="OTP from Scheme For Farmers Smart Contact Team";
		String message="<h1> OTP = "+otp+"</h1>";
		String to=email;
		this.service.sendEmailForVerification(to, message, subject);
		
		//write code for send OTP\
		return true;
	}
}
