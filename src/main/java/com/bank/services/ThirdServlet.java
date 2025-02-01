package com.bank.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/disp")
public class ThirdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String accBranch = request.getParameter("accBranch");
        String accBank = request.getParameter("accBank");
        
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("accBranch", accBranch);
        httpSession.setAttribute("accBank", accBank);

        String accNo = (String) httpSession.getAttribute("accNo");
        String accHolderName = (String) httpSession.getAttribute("accHolderName");
        String accType = (String) httpSession.getAttribute("accType");
        float balance = (float) httpSession.getAttribute("balance");
        String email = (String) httpSession.getAttribute("email");
      

        out.println("<html><head><title>Account Details</title></head><body style='font-family: Arial, sans-serif;'>");
        out.println("<h2 style='color: #e74c3c; text-align: center;'>Gobal Bank Services</h2>");
        out.println("<h3 style='color: #3498db; text-align: center;'>Account Details</h3>");
        out.println("<table border='1' align='center' style='border-collapse: collapse; width: 50%;'>");
        out.println("<tr><th>Field</th><th>Value</th></tr>");
        out.println("<tr><td>Account Number</td><td>" + accNo + "</td></tr>");
        out.println("<tr><td>Account Holder Name</td><td>" + accHolderName + "</td></tr>");
        out.println("<tr><td>Account Type</td><td>" + accType + "</td></tr>");
        out.println("<tr><td>Account Balance</td><td>" + balance + "</td></tr>");
        out.println("<tr><td>Account Branch</td><td>" + accBranch + "</td></tr>");
        out.println("<tr><td>User PhoneNumber</td><td>"+ email +"</td></tr>");
        out.println("<tr><td>Account Bank</td><td>" + accBank + "</td></tr>");
        out.println("</table>");
        out.println("<form method='post' action='" + response.encodeURL("./addAndEdit") + "' style='text-align: center; margin-top: 20px;'>");
        out.println("<button type='submit' style='background-color: #3498db; color: white; border: none; padding: 10px 20px; cursor: pointer;'>Confirm</button>");
        out.println("</form></body></html>");
    }
}

