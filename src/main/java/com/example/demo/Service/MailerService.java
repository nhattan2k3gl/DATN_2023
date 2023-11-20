package com.example.demo.Service;

import java.io.UnsupportedEncodingException;

import com.example.demo.Dto.MailerDTO;

import jakarta.mail.MessagingException;

public interface MailerService {

	void send(MailerDTO mail) throws MessagingException;

	void send(String to, String subject, String body) throws MessagingException;

	void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException;

}
