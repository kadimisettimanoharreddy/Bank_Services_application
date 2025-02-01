package com.bank.services;
import java.io.IOException;
import java.io.PrintWriter;

import com.codegnan.beans.Account;
import com.connection.factory.AccountAction;
import com.connection.factory.AccountActionFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/addAndEdit")
public class addAndEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 PrintWriter out=response.getWriter();
		 HttpSession hs=request.getSession(false);
		 AccountAction accountAction=AccountActionFactory.getAccountAction();
		 Account account=new Account();
		 account.setAccNo((String)hs.getAttribute("accNo"));
		 account.setAccHolderName((String)hs.getAttribute("accHolderName"));
		 account.setAccType((String)hs.getAttribute("accType"));
		 account.setBalance((Float)hs.getAttribute("balance"));
		 account.setAccBranch((String)hs.getAttribute("accBranch"));
		 account.setAccBank((String)hs.getAttribute("accBank"));
		 account.setEmail((String)hs.getAttribute("email"));
		 String status=accountAction.addAccount(account);
		 RequestDispatcher rd=null;
		 if(status.equalsIgnoreCase("success")) {
			 rd=request.getRequestDispatcher("success.html");
			 rd.forward(request, response);
		 }else {
			 rd=request.getRequestDispatcher("faliure.html");
			 rd.forward(request, response);
		 }
		 
	}

}
