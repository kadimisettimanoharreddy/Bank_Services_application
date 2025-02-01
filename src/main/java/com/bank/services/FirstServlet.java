package com.bank.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String accNo = request.getParameter("accNo");
        String accHolderName = request.getParameter("accHolderName");
        String email = request.getParameter("email");
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("accNo", accNo);
        httpSession.setAttribute("accHolderName", accHolderName);
        httpSession.setAttribute("email",email);
        out.println("<html><head><title>Account Form</title></head><body style='font-family: Arial, sans-serif;'>");
        out.println("<h2 style='color: #e74c3c; text-align: center;'>Gobal Bank SServices</h2>");
        out.println("<h3 style='color: #3498db; text-align: center;'>Account Creation Form</h3>");
        out.println("<form method='post' action='" + response.encodeURL("./sec") + "' style='margin: auto; width: 50%;'>");
        out.println("<div style='margin-bottom: 15px;'>");
        out.println("<label>Account Type:</label><br>");
        out.println("<select name='accType' style='width: 100%; padding: 8px; margin-top: 5px;'>");
        out.println("<option value='Savings'>Savings</option>");
        out.println("<option value='Current'>Current</option>");
        out.println("<option value='Fixed Deposit'>Fixed Deposit</option>");
        out.println("<option value='Recurring Deposit'>Recurring Deposit</option>");
        out.println("</select>");
        out.println("</div>");
        out.println("<div style='margin-bottom: 15px;'>");
        out.println("<label>Account Balance:</label><br><input type='text' name='balance' style='width: 100%; padding: 8px; margin-top: 5px;'>");
        out.println("</div>");
        out.println("<button type='submit' style='background-color: #2ecc71; color: white; border: none; padding: 10px 20px; cursor: pointer;'>Next</button>");
        out.println("</form></body></html>");

    }
}

