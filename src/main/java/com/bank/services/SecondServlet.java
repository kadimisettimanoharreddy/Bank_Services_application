package com.bank.services;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sec")
public class SecondServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String accType = request.getParameter("accType");
        float balance = Float.parseFloat(request.getParameter("balance"));

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("accType", accType);
        httpSession.setAttribute("balance", balance);

        out.println("<html><head><style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; }");
        out.println(".container { width: 400px; margin: 50px auto; background: white; padding: 20px; border-radius: 10px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); }");
        out.println("h2, h3 { text-align: center; color: #333; }");
        out.println("table { width: 100%; }");
        out.println("td { padding: 10px; }");
        out.println("input, select { width: 100%; padding: 8px; margin-top: 5px; border: 1px solid #ccc; border-radius: 5px; }");
        out.println("input[type='submit'] { background: #007bff; color: white; border: none; cursor: pointer; padding: 10px; }");
        out.println("input[type='submit']:hover { background: #0056b3; }");
        out.println("</style></head>");

        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h2>Codegnan Banking Services</h2>");
        out.println("<h3>Account Creation Form</h3>");
        out.println("<form method='post' action='" + response.encodeURL("./disp") + "'>");
        out.println("<table>");

       
        out.println("<tr><td>Account Branch:</td><td>");
        out.println("<select name='accBranch'>");
        out.println("<option value='Hyderabad'>Hyderabad</option>");
        out.println("<option value='Bangalore'>Bangalore</option>");
        out.println("<option value='Chennai'>Chennai</option>");
        out.println("<option value='Mumbai'>Mumbai</option>");
        out.println("<option value='Delhi'>Delhi</option>");
        out.println("</select>");
        out.println("</td></tr>");

        
        out.println("<tr><td>Account Bank:</td><td>");
        out.println("<select name='accBank'>");
        out.println("<option value='SBI'>SBI</option>");
        out.println("<option value='HDFC'>HDFC</option>");
        out.println("<option value='ICICI'>ICICI</option>");
        out.println("<option value='Axis Bank'>Axis Bank</option>");
        out.println("<option value='Punjab National Bank'>Punjab National Bank</option>");
        out.println("</select>");
        out.println("</td></tr>");

        out.println("<tr><td colspan='2' align='center'><input type='submit' value='Next'></td></tr>");
        out.println("</table>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body></html>");
    }
}
