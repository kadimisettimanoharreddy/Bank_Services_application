package com.bank.services;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.*;
import com.connection.factory.ConnectionFactory;
import java.util.Properties;
import java.util.Random;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.sql.*;

@WebServlet("/verify")
public class ValidateUserServlet extends HttpServlet {
	
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String accNo = request.getParameter("accNo");
	        String email = request.getParameter("email");
	        HttpSession session = request.getSession();

	        try {
	            // Step 1: Database connection
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = ConnectionFactory.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("SELECT email FROM Bankaccount WHERE acc_no = ?");
	            stmt.setString(1, accNo);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                String registeredEmail = rs.getString("email");
	                if (registeredEmail.equals(email)) {
	                    // Step 2: Generate OTP
	                    int otp = new Random().nextInt(900000) + 100000; // Generate 6-digit OTP
	                    session.setAttribute("otp", otp);
	                    session.setAttribute("accNo", accNo);

	                    // Step 3: Send OTP via email
	                    sendOTP(email, otp);
	                    response.sendRedirect("enterOtp.html"); // Redirect to OTP input page
	                } else {
	                    response.getWriter().println("Email does not match the registered email.");
	                }
	            } else {
	                response.getWriter().println("Account number not found.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.getWriter().println("Error: " + e.getMessage());
	        }
	    }

	    // Method to send OTP email
	    private void sendOTP(String recipient, int otp) throws MessagingException {
	        String sender = "globalbanksender@gmail.com";
	        String password = "mrty rzzv dgks fikw"; // Use App Password here

	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");

	        Session session = Session.getInstance(props, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(sender,password );
	            }
	        });

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(sender));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
	        message.setSubject("OTP Verification");
	        message.setText("Your OTP is: " + otp);

	        Transport.send(message);
	        System.out.println("OTP sent successfully to " + recipient);
	    }
	}



