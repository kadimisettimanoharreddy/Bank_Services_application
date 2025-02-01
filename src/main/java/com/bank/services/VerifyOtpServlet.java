package com.bank.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.connection.factory.ConnectionFactory;

@WebServlet("/validateOtp")
public class VerifyOtpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enteredOtp = request.getParameter("otp");
        HttpSession session = request.getSession();

        // Retrieve the generated OTP and account number from the session
        int generatedOtp = (int) session.getAttribute("otp");
        String accNo = (String) session.getAttribute("accNo");

        if (String.valueOf(generatedOtp).equals(enteredOtp)) {
            // OTP is valid, fetch account details
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Bankaccount WHERE acc_no = ?");
                stmt.setString(1, accNo);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // Build HTML response with account details in a table format
                    response.setContentType("text/html");
                    response.getWriter().println("<html>");
                    response.getWriter().println("<head><title>Account Details</title>");
                    response.getWriter().println("<style>");
                    response.getWriter().println("table { width: 100%; border-collapse: collapse; }");
                    response.getWriter().println("th, td { padding: 12px; text-align: left; border: 1px solid #ddd; }");
                    response.getWriter().println("th { background-color: #f2f2f2; }");
                    response.getWriter().println("h1 { text-align: center; }");
                    response.getWriter().println(".container { max-width: 600px; margin: auto; }");
                    response.getWriter().println("</style>");
                    response.getWriter().println("</head>");
                    response.getWriter().println("<body>");
                    response.getWriter().println("<div class='container'>");
                    response.getWriter().println("<h1>Account Details</h1>");
                    response.getWriter().println("<table>");
                    response.getWriter().println("<tr><th>Account Number</th><td>" + rs.getString("acc_no") + "</td></tr>");
                    response.getWriter().println("<tr><th>Account Holder Name</th><td>" + rs.getString("acc_holder_name") + "</td></tr>");
                    response.getWriter().println("<tr><th>Account Type</th><td>" + rs.getString("acc_type") + "</td></tr>");
                    response.getWriter().println("<tr><th>Balance</th><td>$" + rs.getFloat("balance") + "</td></tr>");
                    response.getWriter().println("<tr><th>Branch</th><td>" + rs.getString("acc_branch") + "</td></tr>");
                    response.getWriter().println("<tr><th>Bank</th><td>" + rs.getString("acc_bank") + "</td></tr>");
                    response.getWriter().println("<tr><th>Email</th><td>" + rs.getString("Email") + "</td></tr>");
                    response.getWriter().println("</table>");
                    response.getWriter().println("</div>");
                    response.getWriter().println("</body></html>");
                } else {
                    response.getWriter().println("Account not found.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error: " + e.getMessage());
            }
        } else {
            // OTP is invalid
            response.getWriter().println("Invalid OTP. Please try again.");
        }
    }
}
