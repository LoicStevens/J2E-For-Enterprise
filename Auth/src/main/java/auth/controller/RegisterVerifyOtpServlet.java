package auth.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.dao.UserDAO;
import auth.model.User;

@WebServlet("/RegisterVerifyOtpServlet")
public class RegisterVerifyOtpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String sentOtp = (String) session.getAttribute("otp");
        String enteredOtp = request.getParameter("otp");

        if (user == null || sentOtp == null || !sentOtp.equals(enteredOtp)) {
            response.sendRedirect("register.jsp"); // OTP invalide, redirection vers l'enregistrement
            return;
        }

        UserDAO userDAO = new UserDAO();
        try {
            userDAO.insertUser(user);
            session.removeAttribute("otp");
            response.sendRedirect("registration-success-temp.jsp"); // Enregistrement réussi, redirection vers la page d'accueil
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp"); // En cas d'erreur, redirection vers l'enregistrement
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not supported.");
    }
}
